package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import db.DataBase;
import model.User;

public class HttpRequestUtils {
	public static String parseAllRequest(InputStream is) throws IOException {
		String result = "";

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		while ((line = br.readLine()) != null) {
			result += line;
			result += "\n";

			if (line.equals("")) {
				String queryString = IOUtils.readData(br, 57);
				break;
			}
		}

		return result;
	}

	// Content-Length
	public static String parseRequestUrl(InputStream is) throws IOException {
		String requestUrl = null;
		User user = null;
		String queryString = null;
		String requestStr = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String[] firstLineComponent = br.readLine().split(" ");

		requestStr = firstLineComponent[1];
		if (firstLineComponent[0].equals("GET")) {
			if (requestStr.contains("?")) {
				requestUrl = requestStr.substring(0, requestStr.indexOf("?"));
			} else {
				requestUrl = requestStr;
			}
			queryString = requestStr.substring(requestStr.indexOf("?") + 1);
		} else {
			requestUrl = requestStr;

			int contentLength = 0;
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals("")) {
					queryString = IOUtils.readData(br, contentLength);
					break;
				}

				if (line.substring(0, line.indexOf(":")).equals("Content-Length")) {
					contentLength = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
				}
			}
		}

		if (requestUrl.equals("/user/create")) {
			Map<String, String> params = parseQueryString(queryString);
			user = new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
		}

		if (user != null) {
			DataBase.addUser(user);
		}

		return requestUrl;
	}

	/**
	 * @param queryString은
	 *            URL에서 ? 이후에 전달되는 field1=value1&field2=value2 형식임
	 * @return
	 */
	public static Map<String, String> parseQueryString(String queryString) {
		return parseValues(queryString, "&");
	}

	/**
	 * @param 쿠키
	 *            값은 name1=value1; name2=value2 형식임
	 * @return
	 */
	public static Map<String, String> parseCookies(String cookies) {
		return parseValues(cookies, ";");
	}

	private static Map<String, String> parseValues(String values, String separator) {
		if (Strings.isNullOrEmpty(values)) {
			return Maps.newHashMap();
		}

		String[] tokens = values.split(separator);
		return Arrays.stream(tokens).map(t -> getKeyValue(t, "=")).filter(p -> p != null)
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
	}

	static Pair getKeyValue(String keyValue, String regex) {
		if (Strings.isNullOrEmpty(keyValue)) {
			return null;
		}

		String[] tokens = keyValue.split(regex);
		if (tokens.length != 2) {
			return null;
		}

		return new Pair(tokens[0], tokens[1]);
	}

	public static Pair parseHeader(String header) {
		return getKeyValue(header, ": ");
	}

	public static class Pair {
		String key;
		String value;

		Pair(String key, String value) {
			this.key = key.trim();
			this.value = value.trim();
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Pair [key=" + key + ", value=" + value + "]";
		}
	}
}

package model;

import java.util.HashMap;
import java.util.Map;

public class HttpSession {
	private Map<String, Object> values = new HashMap<>();
	
	private String id;
	
	public HttpSession(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setAttribute(String key, Object value) {
		values.put(key, value);
	}
	
	public Object getAttributes(String key) {
		return values.get(key);
	}
	
	public void removeAttributes(String key) {
		values.remove(key);
	}
	
	public void invalidate() {
		HttpSessions.remove(id);
	}
}

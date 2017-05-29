/**
 * Copyright 2017 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package util;

import java.io.OutputStream;

import model.HttpResponse;

/**
 * @author Eunji, Lim
 */
public class HttpResponseUtil {
	public static HttpResponse parseHttpResponse(OutputStream out) {
		return new HttpResponse(out);
	}
}

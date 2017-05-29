/**
 * Copyright 2017 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package model;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * @author Eunji, Lim
 */
public class HttpResponse {
	private DataOutputStream dos;

	public HttpResponse(OutputStream out) {
		this.dos = new DataOutputStream(out);
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
}

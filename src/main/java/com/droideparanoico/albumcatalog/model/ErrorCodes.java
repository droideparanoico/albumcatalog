package com.droideparanoico.albumcatalog.model;

public enum ErrorCodes {

	ARTIST_NOT_FOUND(1001),
	ALBUM_NOT_FOUND(1002);

	private int code;

	ErrorCodes(int code) {
		this.code = code;
	}

	public int code() {
		return this.code;
	}

}
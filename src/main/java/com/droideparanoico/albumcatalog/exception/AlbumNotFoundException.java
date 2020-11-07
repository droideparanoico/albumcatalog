package com.droideparanoico.albumcatalog.exception;

import java.math.BigInteger;

@SuppressWarnings("serial")
public class AlbumNotFoundException extends RuntimeException {

	public AlbumNotFoundException(final BigInteger id) {
		super(String.format("album with id '%s' not found", id));
	}
}
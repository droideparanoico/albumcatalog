package com.droideparanoico.albumcatalog.exception;

import java.math.BigInteger;

@SuppressWarnings("serial")
public class ArtistNotFoundException extends RuntimeException {

	public ArtistNotFoundException(final BigInteger id) {
		super(String.format("album with id '%s' not found", id));
	}
}
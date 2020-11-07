package com.droideparanoico.albumcatalog.exception;

import com.droideparanoico.albumcatalog.model.ErrorCodes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler
	public ResponseEntity<?> artistNotFound(ArtistNotFoundException ex) {
		return ResponseEntity.badRequest()
				.body(new ResponseStatusError(ErrorCodes.ARTIST_NOT_FOUND.code(), ex.getMessage()));
	}

	@ExceptionHandler
	public ResponseEntity<?> albumNotFound(AlbumNotFoundException ex) {
		return ResponseEntity.badRequest()
				.body(new ResponseStatusError(ErrorCodes.ALBUM_NOT_FOUND.code(), ex.getMessage()));
	}

}

@Data
@AllArgsConstructor
class ResponseStatusError {

	private int status;
	private String message;
}
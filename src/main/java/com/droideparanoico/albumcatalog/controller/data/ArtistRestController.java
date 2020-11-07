package com.droideparanoico.albumcatalog.controller.data;

import java.math.BigInteger;
import java.util.Optional;

import com.droideparanoico.albumcatalog.model.Artist;
import com.droideparanoico.albumcatalog.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.droideparanoico.albumcatalog.service.ArtistService;

@RestController
@RequestMapping("/artist")
public class ArtistRestController {

	public ArtistService service;

	@Qualifier("artistService")
	@Autowired
	public void setService(ArtistService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String root() {
		return "application is runnning!";
	}

	@GetMapping("/all")
	public Iterable<Artist> getAllArtists() {
		return service.getAllArtists();
	}

	@GetMapping("/{id}")
	public Artist getArtistById(final @PathVariable("id") BigInteger artistId) {
		return service.getAlbumsById(artistId);
	}

	@PostMapping("/{name}")
	public Optional<Artist> createArtist(final @PathVariable String name) {
		return service.createArtist(name);
	}

	@DeleteMapping("/{id}")
	public void deleteArtist(final @PathVariable("id") BigInteger artistId) {
		service.deleteArtist(artistId);
	}

	@GetMapping("/{id}/albums")
	public Iterable<Album> getAlbumsByArtist(@PathVariable("id") BigInteger artistId) {
		return service.getAlbums(artistId);
	}

	@DeleteMapping("/{id}/albums")
	public void deleteAllAlbumsFromArtist(final @PathVariable("id") BigInteger artistId) {
		service.deleteAlbums(artistId);
	}

	@PostMapping("/{id}/add")
	public Album addAlbumToArtist(final @PathVariable("id") BigInteger artistId,
								  final @RequestBody Album album) {
		return service.addAlbum(artistId, album);
	}

	@GetMapping("/albums")
	public Iterable<Album> getAllAlbums() {
		return service.getAlbums(null);
	}

	@PutMapping("/albums/{id}/move")
	public boolean moveAlbumToDifferentArtist(@PathVariable("id") BigInteger albumId,
											  @RequestParam("to_artist") BigInteger toArtistId) {
		return service.moveAlbum(albumId, toArtistId);
	}

	@DeleteMapping("/{id}/albums/{album_id}")
	public void deleteFromArtist(final @PathVariable("id") BigInteger artistId,
								 final @PathVariable("album_id") BigInteger albumId) {
		service.deleteAlbum(artistId, albumId);
	}

}

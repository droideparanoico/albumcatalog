package com.droideparanoico.albumcatalog.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

import com.droideparanoico.albumcatalog.database.AlbumsRepository;
import com.droideparanoico.albumcatalog.exception.ArtistNotFoundException;
import com.droideparanoico.albumcatalog.exception.AlbumNotFoundException;
import com.droideparanoico.albumcatalog.model.Album;
import com.droideparanoico.albumcatalog.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.droideparanoico.albumcatalog.database.ArtistRepository;

@Service("artistService")
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepo;

	@Autowired
	private AlbumsRepository albumsRepo;

	public Iterable<Artist> getAllArtists() {
		return artistRepo.findAll();
	}

	public Artist getAlbumsById(BigInteger artistId) {
		return getArtist(artistId);
	}

	public Optional<Artist> createArtist(String name) {
		Artist artist = new Artist();
		artist.setName(name);
		artist.setCreatedOn(new Date());
		return Optional.of(artistRepo.save(artist));
	}

	public void deleteArtist(BigInteger artistId) {
		Artist artist = getArtist(artistId);
		artist.setId(artistId);
		artistRepo.delete(artist);
	}

	public Iterable<Album> getAlbums(BigInteger artistId) {
		if (artistId == null) {
			return albumsRepo.findAll();
		}
		artistRepo.getAlbums(artistId);
		Artist artist = getArtist(artistId);
		return artistRepo.getAlbums(artist.getId());
	}

	public void deleteAlbums(BigInteger artistId) {
		Artist artist = getArtist(artistId);
		albumsRepo.deleteByArtistId(artist.getId());
	}

	public Album addAlbum(BigInteger artistId, Album album) {
		Artist artist = getArtist(artistId);
		album.setArtistId(artist.getId());
		album.setCreatedOn(new Date());
		return albumsRepo.save(album);
	}

	public boolean moveAlbum(BigInteger albumId, BigInteger toArtistId) {
		Album album = getAlbum(albumId);
		Artist artist = getArtist(toArtistId);
		return 1 == albumsRepo.updateArtist(album.getId(), artist.getId());
	}

	public void deleteAlbum(BigInteger artistId, BigInteger albumId) {
		Album album = getAlbum(albumId);
		albumsRepo.delete(artistId, album.getId());
	}

	private Artist getArtist(final BigInteger artistId) {
		return artistRepo.findById(artistId)
				.orElseThrow(() -> new ArtistNotFoundException(artistId));
	}

	private Album getAlbum(final BigInteger albumId) {
		return albumsRepo.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));
	}

}

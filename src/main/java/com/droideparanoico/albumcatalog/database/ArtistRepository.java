package com.droideparanoico.albumcatalog.database;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.droideparanoico.albumcatalog.model.Artist;
import com.droideparanoico.albumcatalog.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtistRepository extends JpaRepository<Artist, BigInteger> {

	public Optional<Artist> findByName(String name);

	@Query("select a from Album a where a.artistId = ?1")
	public Collection<Album> getAlbums(BigInteger artistId);

	@Query(value = "select name from Album where artist_id = ?", nativeQuery = true)
	public List<String> getAlbumsUsingNativeQuery(BigInteger albumId);

}
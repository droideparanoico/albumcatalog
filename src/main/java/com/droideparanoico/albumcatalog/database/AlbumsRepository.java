package com.droideparanoico.albumcatalog.database;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import com.droideparanoico.albumcatalog.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AlbumsRepository extends JpaRepository<Album, BigInteger> {

	public Optional<Album> findByName(String name);

	Collection<Album> findByArtistId(BigInteger artistId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("delete from Album s where s.artistId = ?1")
	void deleteByArtistId(BigInteger artistId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("delete from Album s where s.artistId = ?1 and s.id = ?2")
	public void delete(BigInteger artistId, BigInteger albumId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Album s set s.artistId = ?2 where s.id = ?1")
	public int updateArtist(BigInteger albumId, BigInteger artistId);

}
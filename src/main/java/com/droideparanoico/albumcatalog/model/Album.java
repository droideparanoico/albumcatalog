package com.droideparanoico.albumcatalog.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "album")
@NamedNativeQuery(name = "albumsByArtistId", query = "select id, name, artist_id, cover_url, created_on from album a where a.artist_id = ?", resultClass = Album.class)
public class Album {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private BigInteger id;

	@Column(name = "artist_id")
	@JsonProperty("artist_id")
	private BigInteger artistId;

	private String name;

	@Column(name = "cover_url")
	@JsonProperty("cover_url")
	private String coverUrl;

	@Column(name = "created_on")
	@JsonProperty("created_on")
	private Date createdOn;

}
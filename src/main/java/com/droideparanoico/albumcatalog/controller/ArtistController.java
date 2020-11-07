package com.droideparanoico.albumcatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.droideparanoico.albumcatalog.service.ArtistService;

@Controller
public class ArtistController {

	@Autowired
	private ArtistService artistService;

	@GetMapping("/")
	public String listArtistAndAlbums(Model model) {
		model.addAttribute("artists", artistService.getAllArtists());
		return "index";
	}

}

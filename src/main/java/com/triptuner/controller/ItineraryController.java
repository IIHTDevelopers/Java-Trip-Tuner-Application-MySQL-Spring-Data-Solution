package com.triptuner.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triptuner.dto.ItineraryDTO;
import com.triptuner.service.ItineraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

	private final ItineraryService itineraryService;

	@Autowired
	public ItineraryController(ItineraryService itineraryService) {
		this.itineraryService = itineraryService;
	}

	@PostMapping
	public ResponseEntity<ItineraryDTO> createItinerary(@Valid @RequestBody ItineraryDTO itineraryDTO) {
		ItineraryDTO createdItinerary = itineraryService.createItinerary(itineraryDTO);
		return new ResponseEntity<>(createdItinerary, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Page<ItineraryDTO>> getAllItineraries(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<ItineraryDTO> itineraries = itineraryService.getAllItineraries(pageable);
		return ResponseEntity.ok(itineraries);
	}

	@GetMapping("/{itineraryId}")
	public ResponseEntity<ItineraryDTO> getItineraryById(@PathVariable Long itineraryId) {
		ItineraryDTO itineraryDTO = itineraryService.getItineraryById(itineraryId);
		return ResponseEntity.ok(itineraryDTO);
	}

	@PutMapping("/{itineraryId}")
	public ResponseEntity<ItineraryDTO> updateItinerary(@PathVariable Long itineraryId,
			@Valid @RequestBody ItineraryDTO itineraryDTO) {
		ItineraryDTO updatedItinerary = itineraryService.updateItinerary(itineraryId, itineraryDTO);
		return ResponseEntity.ok(updatedItinerary);
	}

	@DeleteMapping("/{itineraryId}")
	public ResponseEntity<Void> deleteItinerary(@PathVariable Long itineraryId) {
		itineraryService.deleteItinerary(itineraryId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<ItineraryDTO>> searchItinerariesByName(@RequestParam String name) {
		List<ItineraryDTO> itineraries = itineraryService.searchItinerariesByName(name);
		return ResponseEntity.ok(itineraries);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<ItineraryDTO>> filterItinerariesByDateRange(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		List<ItineraryDTO> itineraries = itineraryService.filterItinerariesByDateRange(startDate, endDate);
		return ResponseEntity.ok(itineraries);
	}
}

package com.triptuner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.triptuner.dto.DestinationDTO;
import com.triptuner.service.DestinationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itineraries/{itineraryId}/destinations")
public class DestinationController {

	private final DestinationService destinationService;

	@Autowired
	public DestinationController(DestinationService destinationService) {
		this.destinationService = destinationService;
	}

	@PostMapping
	public ResponseEntity<DestinationDTO> addDestinationToItinerary(@PathVariable Long itineraryId,
			@Valid @RequestBody DestinationDTO destinationDTO) {
		DestinationDTO createdDestination = destinationService.addDestinationToItinerary(itineraryId, destinationDTO);
		return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<DestinationDTO>> getAllDestinationsForItinerary(@PathVariable Long itineraryId) {
		List<DestinationDTO> destinations = destinationService.getAllDestinationsForItinerary(itineraryId);
		return ResponseEntity.ok(destinations);
	}

	@GetMapping("/{destinationId}")
	public ResponseEntity<DestinationDTO> getDestinationByIdAndItineraryId(@PathVariable Long itineraryId,
			@PathVariable Long destinationId) {
		DestinationDTO destinationDTO = destinationService.getDestinationByIdAndItineraryId(itineraryId, destinationId);
		return ResponseEntity.ok(destinationDTO);
	}

	@PutMapping("/{destinationId}")
	public ResponseEntity<DestinationDTO> updateDestination(@PathVariable Long itineraryId,
			@PathVariable Long destinationId, @Valid @RequestBody DestinationDTO destinationDTO) {
		DestinationDTO updatedDestination = destinationService.updateDestination(itineraryId, destinationId,
				destinationDTO);
		return ResponseEntity.ok(updatedDestination);
	}

	@DeleteMapping("/{destinationId}")
	public ResponseEntity<Void> deleteDestinationFromItinerary(@PathVariable Long itineraryId,
			@PathVariable Long destinationId) {
		destinationService.deleteDestinationFromItinerary(itineraryId, destinationId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<DestinationDTO>> searchDestinationsWithinItineraryByName(@PathVariable Long itineraryId,
			@RequestParam String name) {
		List<DestinationDTO> destinations = destinationService.searchDestinationsWithinItineraryByName(itineraryId,
				name);
		return ResponseEntity.ok(destinations);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<DestinationDTO>> filterDestinationsWithinItineraryByType(@PathVariable Long itineraryId,
			@RequestParam String type) {
		List<DestinationDTO> destinations = destinationService.filterDestinationsWithinItineraryByType(itineraryId,
				type);
		return ResponseEntity.ok(destinations);
	}
}

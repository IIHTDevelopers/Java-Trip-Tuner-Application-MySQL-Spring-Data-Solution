package com.triptuner.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triptuner.dto.DestinationDTO;
import com.triptuner.entity.Destination;
import com.triptuner.entity.Itinerary;
import com.triptuner.exception.ResourceNotFoundException;
import com.triptuner.repo.DestinationRepository;
import com.triptuner.repo.ItineraryRepository;
import com.triptuner.service.DestinationService;

import jakarta.transaction.Transactional;

@Service
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	private DestinationRepository destinationRepository;

	@Autowired
	private ItineraryRepository itineraryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public DestinationDTO addDestinationToItinerary(Long itineraryId, DestinationDTO destinationDTO) {
		Itinerary itinerary = itineraryRepository.findById(itineraryId)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + itineraryId));
		Destination destination = modelMapper.map(destinationDTO, Destination.class);
		destination.setItinerary(itinerary); // Set the parent itinerary
		Destination savedDestination = destinationRepository.save(destination);
		return modelMapper.map(savedDestination, DestinationDTO.class);
	}

	@Override
	public List<DestinationDTO> getAllDestinationsForItinerary(Long itineraryId) {
		List<Destination> destinations = destinationRepository.findByItineraryId(itineraryId);
		return destinations.stream().map(destination -> modelMapper.map(destination, DestinationDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public DestinationDTO getDestinationByIdAndItineraryId(Long itineraryId, Long destinationId) {
		List<Destination> destinations = destinationRepository.findByIdAndItineraryId(destinationId, itineraryId);
		Destination destination = destinations.stream().filter(d -> d.getId().equals(destinationId)).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(
						"Destination not found with id: " + destinationId + " for itinerary id: " + itineraryId));
		return modelMapper.map(destination, DestinationDTO.class);
	}

	@Transactional
	@Override
	public DestinationDTO updateDestination(Long itineraryId, Long destinationId, DestinationDTO destinationDTO) {
		Destination destination = destinationRepository.findDestinationByIdAndItineraryId(destinationId, itineraryId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Destination not found with id: " + destinationId + " for itinerary id: " + itineraryId));

		modelMapper.map(destinationDTO, destination);
		Destination updatedDestination = destinationRepository.save(destination);
		return modelMapper.map(updatedDestination, DestinationDTO.class);
	}

	@Transactional
	@Override
	public boolean deleteDestinationFromItinerary(Long itineraryId, Long destinationId) {
		List<Destination> destinations = destinationRepository.findByIdAndItineraryId(destinationId, itineraryId);
		Destination destination = destinations.stream().filter(d -> d.getId().equals(destinationId)).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(
						"Destination not found with id: " + destinationId + " for itinerary id: " + itineraryId));
		destinationRepository.delete(destination);
		return true;
	}

	@Override
	public List<DestinationDTO> searchDestinationsWithinItineraryByName(Long itineraryId, String name) {
		List<Destination> destinations = destinationRepository.findByItineraryIdAndNameContainingIgnoreCase(itineraryId,
				name);
		return destinations.stream().map(destination -> modelMapper.map(destination, DestinationDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<DestinationDTO> filterDestinationsWithinItineraryByType(Long itineraryId, String type) {
		List<Destination> destinations = destinationRepository.findByItineraryIdAndType(itineraryId, type);
		return destinations.stream().map(destination -> modelMapper.map(destination, DestinationDTO.class))
				.collect(Collectors.toList());
	}
}

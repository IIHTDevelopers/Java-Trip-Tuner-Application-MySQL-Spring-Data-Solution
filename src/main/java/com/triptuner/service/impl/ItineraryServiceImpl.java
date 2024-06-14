package com.triptuner.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.triptuner.dto.ItineraryDTO;
import com.triptuner.entity.Itinerary;
import com.triptuner.exception.ResourceNotFoundException;
import com.triptuner.repo.ItineraryRepository;
import com.triptuner.service.ItineraryService;

@Service
public class ItineraryServiceImpl implements ItineraryService {

	@Autowired
	private ItineraryRepository itineraryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ItineraryDTO createItinerary(ItineraryDTO itineraryDTO) {
		Itinerary itinerary = modelMapper.map(itineraryDTO, Itinerary.class);
		Itinerary savedItinerary = itineraryRepository.save(itinerary);
		return modelMapper.map(savedItinerary, ItineraryDTO.class);
	}

	@Override
	public Page<ItineraryDTO> getAllItineraries(Pageable pageable) {
		Page<Itinerary> itineraries = itineraryRepository.findAllByOrderByNameAsc(pageable);
		return itineraries.map(itinerary -> modelMapper.map(itinerary, ItineraryDTO.class));
	}

	@Override
	public ItineraryDTO getItineraryById(Long id) {
		Itinerary itinerary = itineraryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + id));
		return modelMapper.map(itinerary, ItineraryDTO.class);
	}

	@Override
	public ItineraryDTO updateItinerary(Long id, ItineraryDTO itineraryDTO) {
		Itinerary existingItinerary = itineraryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + id));
		modelMapper.map(itineraryDTO, existingItinerary);
		Itinerary updatedItinerary = itineraryRepository.save(existingItinerary);
		return modelMapper.map(updatedItinerary, ItineraryDTO.class);
	}

	@Override
	public boolean deleteItinerary(Long id) {
		Itinerary itinerary = itineraryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + id));
		itineraryRepository.delete(itinerary);
		return true;
	}

	@Override
	public List<ItineraryDTO> searchItinerariesByName(String name) {
		List<Itinerary> itineraries = itineraryRepository.findByNameContainingIgnoreCase(name);
		return itineraries.stream().map(itinerary -> modelMapper.map(itinerary, ItineraryDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ItineraryDTO> filterItinerariesByDateRange(Date startDate, Date endDate) {
		List<Itinerary> itineraries = itineraryRepository.findItinerariesByDateRange(startDate, endDate);
		return itineraries.stream().map(itinerary -> modelMapper.map(itinerary, ItineraryDTO.class))
				.collect(Collectors.toList());
	}
}

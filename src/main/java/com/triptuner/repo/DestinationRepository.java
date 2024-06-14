package com.triptuner.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.triptuner.entity.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

	// Find destinations by the itinerary ID
	List<Destination> findByItineraryId(Long itineraryId);

	// Find a destination by its ID and itinerary ID
	List<Destination> findByIdAndItineraryId(Long id, Long itineraryId);

	// Search destinations within an itinerary by name
	List<Destination> findByItineraryIdAndNameContainingIgnoreCase(Long itineraryId, String name);

	// Filter destinations within an itinerary by type
	List<Destination> findByItineraryIdAndType(Long itineraryId, String type);

	/**
	 * Retrieves a destination by its ID and associated itinerary ID.
	 * 
	 * @param destinationId The ID of the destination.
	 * @param itineraryId   The ID of the itinerary.
	 * @return Optional containing the matched destination or empty if not found.
	 */
	@Query("SELECT d FROM Destination d WHERE d.id = :destinationId AND d.itinerary.id = :itineraryId")
	Optional<Destination> findDestinationByIdAndItineraryId(@Param("destinationId") Long destinationId,
			@Param("itineraryId") Long itineraryId);
}

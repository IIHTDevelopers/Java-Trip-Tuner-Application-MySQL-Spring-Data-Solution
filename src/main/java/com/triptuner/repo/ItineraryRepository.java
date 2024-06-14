package com.triptuner.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.triptuner.entity.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

	// Search for itineraries by name using a case-insensitive search
	List<Itinerary> findByNameContainingIgnoreCase(String name);

	// Filter itineraries within a given start and end date range
	List<Itinerary> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date startDate, Date endDate);

	// List all itineraries ordered by name in ascending in ascending in pages
	Page<Itinerary> findAllByOrderByNameAsc(Pageable pageable);

	/**
	 * Retrieves itineraries that start on or after the specified start date and end
	 * on or before the specified end date.
	 *
	 * @param startDate The start date of the date range.
	 * @param endDate   The end date of the date range.
	 * @return A list of itineraries within the specified date range.
	 */
	@Query("SELECT i FROM Itinerary i WHERE i.startDate >= :startDate AND i.endDate <= :endDate")
	List<Itinerary> findItinerariesByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}

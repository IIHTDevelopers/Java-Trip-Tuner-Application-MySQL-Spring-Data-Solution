package com.triptuner.dto;

import java.util.Date;
import java.util.Set;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItineraryDTO {
	private Long id;

	@NotBlank(message = "Name is required")
	@Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
	private String name;

	@NotNull(message = "Start date is required")
	@FutureOrPresent(message = "Start date must be in the future or present")
	private Date startDate;

	@NotNull(message = "End date is required")
	@FutureOrPresent(message = "End date must be in the future or present")
	private Date endDate;

	private Set<DestinationDTO> destinations;

	public ItineraryDTO() {
		super();
	}

	public ItineraryDTO(Long id,
			@NotBlank(message = "Name is required") @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters") String name,
			@NotNull(message = "Start date is required") @FutureOrPresent(message = "Start date must be in the future or present") Date startDate,
			@NotNull(message = "End date is required") @FutureOrPresent(message = "End date must be in the future or present") Date endDate,
			Set<DestinationDTO> destinations) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.destinations = destinations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<DestinationDTO> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<DestinationDTO> destinations) {
		this.destinations = destinations;
	}

	@Override
	public String toString() {
		return "ItineraryDTO [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", destinations=" + destinations + "]";
	}
}

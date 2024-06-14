package com.triptuner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DestinationDTO {

	private Long id;

	@NotBlank(message = "Name is required")
	@Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
	private String name;

	@NotBlank(message = "Type is required")
	@Size(min = 1, max = 255, message = "Type must be between 1 and 255 characters")
	private String type;

	@NotNull(message = "Itinerary ID is required")
	private Long itineraryId;

	public DestinationDTO() {
		super();
	}

	public DestinationDTO(Long id,
			@NotBlank(message = "Name is required") @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters") String name,
			@NotBlank(message = "Type is required") @Size(min = 1, max = 255, message = "Type must be between 1 and 255 characters") String type,
			@NotNull(message = "Itinerary ID is required") Long itineraryId) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.itineraryId = itineraryId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(Long itineraryId) {
		this.itineraryId = itineraryId;
	}

	@Override
	public String toString() {
		return "DestinationDTO [id=" + id + ", name=" + name + ", type=" + type + ", itineraryId=" + itineraryId + "]";
	}
}

package com.triptuner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "destinations")
public class Destination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column
	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itinerary_id")
	private Itinerary itinerary;

	public Destination() {
	}

	public Destination(Long id, String name, String type, Itinerary itinerary) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.itinerary = itinerary;
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

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	@Override
	public String toString() {
		return "Destination [id=" + id + ", name=" + name + ", type=" + type + ", itinerary=" + itinerary + "]";
	}
}

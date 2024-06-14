package com.triptuner.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "itineraries")
public class Itinerary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Destination> destinations = new HashSet<>();

	public Itinerary() {
	}

	public void addDestination(Destination destination) {
		destinations.add(destination);
		destination.setItinerary(this);
	}

	public void removeDestination(Destination destination) {
		destinations.remove(destination);
		destination.setItinerary(null);
	}

	public Itinerary(Long id, String name, Date startDate, Date endDate, Set<Destination> destinations) {
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

	public Set<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		this.destinations = destinations;
	}

	@Override
	public String toString() {
		return "Itinerary [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", destinations=" + destinations + "]";
	}
}

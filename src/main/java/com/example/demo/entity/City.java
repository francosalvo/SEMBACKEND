package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String parkingHours;

	public City() {
	}

	@Column
	private double valueHours;

	public City(String parkingHours, double valueHours) {
		this.parkingHours = parkingHours;
		this.valueHours = valueHours;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParkingHours() {
		return parkingHours;
	}

	public void setParkingHours(String parkingHours) {
		this.parkingHours = parkingHours;
	}

	public double getValueHours() {
		return valueHours;
	}

	public void setValueHours(double valueHours) {
		this.valueHours = valueHours;
	}

}

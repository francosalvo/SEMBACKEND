package com.example.demo.security.dto;

import java.io.Serializable;

public class TimePriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private double hours;
	private double minutes;
	private double price;

	public TimePriceDTO(double hours, double minutes, double price) {
		this.hours = hours;
		this.minutes = minutes;
		this.price = price;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

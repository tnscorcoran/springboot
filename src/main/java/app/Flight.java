package app;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Flight {
	private String id;
	private String from, number, to, departureDateAndTime, airline;

	public Flight() {
		super();
	}

	public Flight(String id, String number, String from, String to, String departureDateAndTime, String airline) {
		super();
		this.id = id;
		this.number = number;
		this.from = from;
		this.to = to;
		this.departureDateAndTime = departureDateAndTime;
		this.airline = airline;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDepartureDateAndTime() {
		return departureDateAndTime;
	}

	public void setDepartureDateAndTime(String departureDateAndTime) {
		this.departureDateAndTime = departureDateAndTime;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}







	
	
	
}
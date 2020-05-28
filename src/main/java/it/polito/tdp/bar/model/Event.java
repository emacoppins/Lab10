package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Event implements Comparable<Event> {

	public enum EventType{
		ARRIVO_CLIENTI, PARTENZA_CLIENTI;
	}
	
	private LocalTime time;
	private EventType type;
	/**
	 * @param time
	 * @param type
	 */
	public Event(LocalTime time, EventType type) {
		this.time = time;
		this.type = type;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.getTime().compareTo(o.getTime());
	}
	
	
	
}

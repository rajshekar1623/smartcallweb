package com.aakhya.smartcall.application.admin.service.utils;

import java.io.Serializable;

public class Element implements Serializable{
    private static final long serialVersionUID = -4928558730569671427L;
	public Distance distance;
    public Duration duration;
    public DurationInTraffic duration_in_traffic;
    public String status;
	public Distance getDistance() {
		return distance;
	}
	public void setDistance(Distance distance) {
		this.distance = distance;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public DurationInTraffic getDuration_in_traffic() {
		return duration_in_traffic;
	}
	public void setDuration_in_traffic(DurationInTraffic duration_in_traffic) {
		this.duration_in_traffic = duration_in_traffic;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

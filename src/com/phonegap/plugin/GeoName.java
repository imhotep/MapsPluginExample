package com.phonegap.plugin;

public class GeoName {
	
	public GeoName(String url, String title, String summary,
			double latitude, double longitude) {
		super();
		this.url = url;
		this.title = title;
		this.summary = summary;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	private String url;
	private String title;
	private String summary;
	private double latitude;
	private double longitude;
	
	public GeoName(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}

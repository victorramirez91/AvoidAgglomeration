package com.example.avoidagglomeration;

public class Sensor {
String deviceName;
String value;

public Sensor(String deviceName, String value) {
	super();
	this.deviceName = deviceName;
	this.value = value;
}
public String getDeviceName() {
	return deviceName;
}
public void setDeviceName(String deviceName) {
	this.deviceName = deviceName;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
}

package com.example.rayold.everydayneeds.activities;

public class Service {
    private String serviceName, hourlyRate;
    private Integer id;

    public Service() {
    }

    public Service(String s, String h, Integer id) {
        this.serviceName = s;
        this.hourlyRate = h;
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String service) {
        this.serviceName = service;
    }


    public String getHourlyRate() {
        return hourlyRate ;
    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
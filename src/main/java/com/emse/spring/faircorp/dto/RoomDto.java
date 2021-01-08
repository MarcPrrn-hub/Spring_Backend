package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Room;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


public class RoomDto {

    private Long id;
    private String name;
    private Integer floor;
    private Double currentTemperature;
    private Double targetTemperature;
    private Set<Long> heatersId;
    private Set<Long> rwindowsId;
    private Long buildingId;

    public RoomDto() {
    }
    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.currentTemperature = room.getCurrentTemperature();
        this.targetTemperature = room.getTargetTemperature();
        if (room.getHeaters() != null) {
            this.heatersId = room.getHeaters().stream()
                    .map(heater -> heater.getId()) // <<< this
                    .collect(Collectors.toSet());
        } else {
            this.heatersId = Collections.emptySet();
        }
        if (room.getRwindows() != null) {
            this.rwindowsId = room.getRwindows().stream()
                    .map(window -> window.getId()) // <<< this
                    .collect(Collectors.toSet());
        } else {
            this.rwindowsId = Collections.emptySet();
        }

        this.buildingId = room.getBuilding().getId();
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

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Set<Long> getHeatersId() {
        return heatersId;
    }

    public void setHeatersId(Set<Long> heatersId) {
        this.heatersId = heatersId;
    }

    public Set<Long> getRwindowsId() {
        return rwindowsId;
    }

    public void setRwindowsId(Set<Long> rwindowsId) {
        this.rwindowsId = rwindowsId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}

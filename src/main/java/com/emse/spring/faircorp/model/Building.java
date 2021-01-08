package com.emse.spring.faircorp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Building {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "building",cascade=CascadeType.REMOVE)
    @JsonManagedReference
    private Set<Room> rooms;

    public  Building(){
    }
    public Building(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}

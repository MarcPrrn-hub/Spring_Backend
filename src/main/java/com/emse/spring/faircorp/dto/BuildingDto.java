package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class BuildingDto {
    private Long id;
    private Set<Long> roomsid  ;
//= Collections.emptySet() to test
    public BuildingDto(){
    }
    public BuildingDto( Building building ) {
      this.id = building.getId();
      if ( building.getRooms() != null ) {
          this.roomsid = building.getRooms().stream()
                  .map(room -> room.getId()) // <<< this
                  .collect(Collectors.toSet());
        }
      else {
          this.roomsid = Collections.emptySet();
      }
      }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getRoomsid() {
        return roomsid;
    }

    public void setRoomsid(Set<Long> roomsid) {
        this.roomsid = roomsid;
    }
}

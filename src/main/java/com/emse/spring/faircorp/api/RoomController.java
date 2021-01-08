package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;

import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;

import com.emse.spring.faircorp.model.*;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@Transactional

public class RoomController {

    private final WindowDao windowDao;
    private final RoomDao roomDao;
    private final HeaterDao heaterDao;
    private final BuildingDao buildingDao;

    public RoomController( WindowDao windowDao, RoomDao roomDao, HeaterDao heaterDao, BuildingDao buildingDao) {
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
    }
    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null);
    }

    @PutMapping(path = "/{id}/switchWindow")
    public void switchWindowStatus(@PathVariable Long id) {
        List<RWindow> windows = windowDao.findByRoomId(id);
        for ( RWindow window : windows ) {
            window.setWindowStatus(window.getWindowStatus() == WindowStatus.CLOSED ? WindowStatus.OPEN: WindowStatus.CLOSED);
        }
    }
    @PutMapping(path = "/{id}/switchHeater")
    public void switchHeaterStatus(@PathVariable Long id) {
        List<Heater> heaters = heaterDao.findByRoomId(id);
        for ( Heater heater : heaters) {
            heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.OFF ? HeaterStatus.ON : HeaterStatus.OFF);
        }
    }
    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Building building = buildingDao.getOne(dto.getBuildingId());
        Room room = null;
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getFloor(),dto.getName()));
        }
        else {
            room = roomDao.getOne(dto.getId());
            room.setFloor(dto.getFloor());
        }
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteById(id);
        //we could have used deleteByRoom for heater and window but i used instead cascade properties to delete it
    }



}

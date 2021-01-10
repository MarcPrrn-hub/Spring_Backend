package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;



import com.emse.spring.faircorp.dto.BuildingDto;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;


import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
@Transactional

@CrossOrigin
public class BuildingController {

    private final BuildingDao buildingDao;
    private final RoomDao roomDao;
    private final WindowDao windowDao;
    private final HeaterDao heaterDao;

    public BuildingController(BuildingDao buildingDao, RoomDao roomDao, WindowDao windowDao, HeaterDao heaterDao) {
        this.buildingDao = buildingDao;
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public BuildingDto findById(@PathVariable Long id) {
        return buildingDao.findById(id).map(BuildingDto::new).orElse(null);
    }

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;
        if (dto.getId() == null) {
            building = buildingDao.save(new Building(dto.getId()));
        }
        else {
            building = buildingDao.getOne(dto.getId());
        }
        return new BuildingDto(building);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        buildingDao.deleteById(id);
    }
}

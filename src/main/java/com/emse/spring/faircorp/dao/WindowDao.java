package com.emse.spring.faircorp.dao;
import com.emse.spring.faircorp.model.RWindow;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface WindowDao extends JpaRepository<RWindow, Long> , WindowDaoCustom{
    @Modifying
    @Query("delete from RWindow c where c.room.id = ?1")
    void deleteByRoom(Long roomid);

    @Query("select c from RWindow c where c.room.id= ?1")
    List<RWindow> findByRoomId(Long id);
    // It is the way i understood that rule

    // Adds a new DAO BuildingDAO and add a new method to find all the building ligths.
    // You send a building ID and your method should return the list of the windows
    @Query("select c from RWindow c where c.room.building.id= ?1")
    List<RWindow> findByBuildingId(Long id);
}

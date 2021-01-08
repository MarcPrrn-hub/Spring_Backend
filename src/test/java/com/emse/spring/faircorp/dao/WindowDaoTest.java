package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.RWindow;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.WindowStatus;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class WindowDaoTest {
    @Autowired
    private WindowDao windowDao;


    @Test
    public void shouldFindRoomOpenWindows() {
        List<RWindow> result = windowDao.findRoomOpenWindows(-9L);
        Assertions.assertThat(result)
                .hasSize(1)
                .extracting("id", "windowStatus")
                .containsExactly(Tuple.tuple(-8L, WindowStatus.OPEN));
    }

    @Test
    public void shouldNotFindRoomOpenWindows() {
        List<RWindow> result = windowDao.findRoomOpenWindows(-10L);
        Assertions.assertThat(result).isEmpty();
    }
    @Test
    public void shouldFindAWindow() {
        RWindow window = windowDao.getOne(-10L);
        Assertions.assertThat(window.getName()).isEqualTo("Window 1");
        Assertions.assertThat(window.getWindowStatus()).isEqualTo(WindowStatus.CLOSED);
    }
    @Autowired
    private RoomDao roomDao;
    @Test
    public void shouldDeleteWindowsRoom() {
        Room room = roomDao.getOne(-10L);
        List<Long> roomIds = room.getRwindows().stream().map(RWindow::getId).collect(Collectors.toList());
        Assertions.assertThat(roomIds.size()).isEqualTo(2);

        windowDao.deleteByRoom(-10L);
        List<RWindow> result = windowDao.findAllById(roomIds);
        Assertions.assertThat(result).isEmpty();
    }
    @Autowired
    @Test
    public void shouldFindAllWindowInABuilding(){
        List<RWindow> result = windowDao.findByBuildingId(-1L);
        Assertions.assertThat(result.size()).isEqualTo(4);
    }
}
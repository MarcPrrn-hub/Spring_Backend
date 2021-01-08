package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.RWindow;
import java.util.List;

public interface WindowDaoCustom {
    List<RWindow> findRoomOpenWindows(Long id);
}

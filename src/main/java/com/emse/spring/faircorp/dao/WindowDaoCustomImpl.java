package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.RWindow;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WindowDaoCustomImpl implements WindowDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RWindow> findRoomOpenWindows(Long id) {
        String jpql = "select w from RWindow w where w.room.id = :id and w.windowStatus= :status";
        return em.createQuery(jpql, RWindow.class)
                .setParameter("id", id)
                .setParameter("status", WindowStatus.OPEN)
                .getResultList();
    }
}
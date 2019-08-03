package com.codegym.cms.service;

import com.codegym.cms.model.Area;

public interface AreaService {
    Iterable<Area> findAll();
    Area findById(Long id);
    void save(Area area);
    void remove(Long id);
}

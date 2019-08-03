package com.codegym.cms.service.Impl;

import com.codegym.cms.model.Area;
import com.codegym.cms.repository.AreaRepository;
import com.codegym.cms.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;

public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Iterable<Area> findAll() {
        return areaRepository.findAll();
    }

    @Override
    public Area findById(Long id) {
        return areaRepository.findOne(id);
    }

    @Override
    public void save(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void remove(Long id) {
        areaRepository.delete(id);
    }
}

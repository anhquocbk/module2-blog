package com.codegym.cms;

import com.codegym.cms.model.Area;
import com.codegym.cms.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class AreaFormatter implements Formatter<Area> {

    private AreaService areaService;

    @Autowired
    public AreaFormatter(AreaService areaService){
        this.areaService = areaService;
    }

    @Override
    public Area parse(String text, Locale locale) throws ParseException {
        return areaService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Area object, Locale locale) {
        return "[" + object.getId() + "," + object.getName() + "]";
    }
}

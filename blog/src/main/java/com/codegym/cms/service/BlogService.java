package com.codegym.cms.service;

import com.codegym.cms.model.Area;
import com.codegym.cms.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Blog findById(Long id);
    void save(Blog blog);
    void remove(Long id);

    Iterable<Blog> findAllByArea(Area area);

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
}

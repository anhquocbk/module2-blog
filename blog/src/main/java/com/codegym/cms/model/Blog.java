package com.codegym.cms.model;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String content;
    private String title;

    public Area getArea() {
        return area;
    }
    public void setArea(Area area) {
        this.area = area;
    }
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    public Blog(){}
    public Blog(String author, String content, String title){
        this.author = author;
        this.content = content;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Blog[id=%d,author='%s',title='%s',content='%s']",id, author, content, title);
    }
}

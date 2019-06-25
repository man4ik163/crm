package com.test.project.entity;

import java.util.Date;

interface Base {
    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    Date getCreatedAt();
    void setCreatedAt(Date createdAt);
}

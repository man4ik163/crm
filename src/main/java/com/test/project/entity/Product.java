package com.test.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Product implements Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date createdAt;
    private String article;
    private Long groupId;

    public Product() {
    }

    public Product(Long id, String name, Date createdAt, String article, Long groupId) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.article = article;
        this.groupId = groupId;
    }

    public Product(String name, Date createdAt, String article, Long groupId) {
        this.name = name;
        this.createdAt = createdAt;
        this.article = article;
        this.groupId = groupId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                article.equals(product.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", article='" + article + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}

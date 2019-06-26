package com.test.crm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "crm_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "article")
    private String article;
    @ManyToOne()
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group groupId;

    public Product() {
    }

    public Product(Long id, String name, Date createdAt, String article, Group groupId) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.article = article;
        this.groupId = groupId;
    }

    public Product(String name, Date createdAt, String article, Group groupId) {
        this.name = name;
        this.createdAt = createdAt;
        this.article = article;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Group getGroupId() {
        return groupId;
    }

    public void setGroupId(Group groupId) {
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

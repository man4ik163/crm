package com.test.crm.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@Entity
@Table(name = "crm_product")
public class Product implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @NotEmpty(message = "the field must not be empty")
    @NotNull(message = "the field must not be empty")
    @Column(name = "name")
    String name;

    @NotNull(message = "the field must not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    Date createdAt;

    @NotEmpty(message = "the field must not be empty")
    @NotNull(message = "the field must not be empty")
//    @UniqueElements
    @Column(name = "article")
    String article;

    @NotNull(message = "the field must not be empty")
    @ManyToOne()
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    Group groupId;

}

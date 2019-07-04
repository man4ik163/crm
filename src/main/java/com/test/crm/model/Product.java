package com.test.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
    @Column(name = "id", nullable = false)
    Long id;

    @NotEmpty(message = "not valid name")
    @NotNull(message = "not valid name")
    @Column(name = "name")
    String name;

    @NotNull(message = "not valid createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    Date createdAt;

    @NotEmpty(message = "not valid article")
    @NotNull(message = "not valid article")
    @Column(name = "article", unique = true)
    String article;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "not valid groupId")
    @ManyToOne()
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    Group groupId;

}

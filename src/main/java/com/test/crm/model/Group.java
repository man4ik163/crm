package com.test.crm.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "crm_group")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NonNull
    Long id;

    @NotBlank(message = "not valid name")
    @Column(name = "name")
    String name;

    @NotNull(message = "not valid createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    Date createdAt;


}

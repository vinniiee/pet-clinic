package com.springframework.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public boolean isNew() {
        return this.id == null;
    }

}

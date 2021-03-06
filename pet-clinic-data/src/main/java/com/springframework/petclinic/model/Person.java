package com.springframework.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
public class Person extends BaseEntity{

    private String firstName;
    private String lastName;


    public Person(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

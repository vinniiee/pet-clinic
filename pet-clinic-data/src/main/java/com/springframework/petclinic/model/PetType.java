package com.springframework.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


@Entity
public class PetType extends BaseEntity{

    private String name;

    @OneToMany(mappedBy = "petType")
    private Set<Pet> pets = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

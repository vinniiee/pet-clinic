package com.springframework.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Speciality extends BaseEntity{

    private String Description;

    @ManyToMany(mappedBy = "specialities")
    private Set<Vet> vets = new HashSet<>();

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

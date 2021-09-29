package com.springframework.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Vet extends Person{

    @ManyToMany
    private Set<Speciality> specialities = new HashSet<>();

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void addSpecialities(Speciality speciality) {
        this.specialities.add(speciality);
    }
}

package com.springframework.petclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Vet extends Person{

    @ManyToMany
    private Set<Speciality> specialities = new HashSet<>();

    public void addSpecialities(Speciality speciality) {
        this.specialities.add(speciality);
    }
}

package com.springframework.petclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pet extends BaseEntity{

    @ManyToOne
    private PetType petType;

    @ManyToOne
    private Owner owner;

    private LocalDate birthDate;

    private String name;

    @OneToMany(mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();


    public void setOwner(Owner owner) {
        this.owner = owner;
        owner.setPets(this);
    }

    public void setVisits(Visit visit) {
        this.visits.add(visit);
        visit.setPet(this);
    }
}

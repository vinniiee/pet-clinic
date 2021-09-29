package com.springframework.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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


    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        owner.setPets(this);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Visit visit) {
        this.visits.add(visit);
        visit.setPet(this);
    }
}

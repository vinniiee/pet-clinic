package com.springframework.petclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Owner extends Person{

    private String address;
    private String city;
    private String phone;

    @OneToMany(mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public void setPets(Pet pet) {
        this.pets.add(pet);
    }
    public Owner(Long id, String firstName, String lastName, String address, String city, String phone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.pets = pets;
    }
}

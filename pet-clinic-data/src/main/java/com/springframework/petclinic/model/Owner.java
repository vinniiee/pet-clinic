package com.springframework.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Owner extends Person{

    private String address;

    private String city;

    private String phone;

    @OneToMany(mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Pet pet) {
        this.pets.add(pet);
    }
}

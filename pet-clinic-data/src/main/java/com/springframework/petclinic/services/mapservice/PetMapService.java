package com.springframework.petclinic.services.mapservice;

import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.services.CrudService;

import java.util.Set;

public class PetMapService extends AbstractMapService<Pet,Long> implements CrudService<Pet,Long> {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Pet save(Long id, Pet object) {
        super.save(id,object);
        return object;
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}

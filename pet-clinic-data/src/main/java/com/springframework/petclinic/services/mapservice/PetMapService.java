package com.springframework.petclinic.services.mapservice;

import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractMapService<Pet,Long> implements PetService {

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
    public Pet save(Pet object) {
        super.save(object);
        return object;
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}

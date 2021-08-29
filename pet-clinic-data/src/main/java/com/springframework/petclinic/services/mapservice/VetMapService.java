package com.springframework.petclinic.services.mapservice;

import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.CrudService;

import java.util.Set;

public class VetMapService extends AbstractMapService<Vet,Long> implements CrudService<Vet,Long> {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Vet save(Long id, Vet object) {
        super.save(id,object);
        return object;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}

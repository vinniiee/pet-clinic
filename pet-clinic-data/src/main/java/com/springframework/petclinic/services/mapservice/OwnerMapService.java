package com.springframework.petclinic.services.mapservice;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.services.CrudService;

import java.util.Set;

public class OwnerMapService extends AbstractMapService<Owner,Long> implements CrudService<Owner,Long> {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner save(Long id, Owner object) {
        super.save(id,object);
        return object;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }
}

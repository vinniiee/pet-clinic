package com.springframework.petclinic.services.mapservice;

import com.springframework.petclinic.model.Speciality;
import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.SpecialityService;
import com.springframework.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class VetMapService extends AbstractMapService<Vet,Long> implements VetService {


    SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

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
    public Vet save(Vet object) {

        if(object!=null){

                if (object.getSpecialities().size() > 0) {
                    object.getSpecialities().forEach(speciality -> {
                        if (speciality.getId() == null) {
                            Speciality savedSpeciality = specialityService.save(speciality);
                            speciality.setId(savedSpeciality.getId());
                        }

                    });
                }



            super.save(object);
            return object;
        }
        else{
            return null;
        }

    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}

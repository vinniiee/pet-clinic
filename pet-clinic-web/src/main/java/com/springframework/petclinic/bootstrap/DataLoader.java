package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.*;
import com.springframework.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialityService specialityService,
                      VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
        this.petService = petService;
    }


    @Override
    public void run(String... args) throws Exception {
        if( petTypeService.findAll().size() == 0 ){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();

        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setPhone("1231231234");
        Owner savedOwner1 = ownerService.save(owner1);

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogType);
        mikesPet.setOwner(savedOwner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        petService.save(mikesPet);


        Owner owner2 = new Owner();

        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setPhone("1231231234");
        Owner savedOwnerService = ownerService.save(owner2);

        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(savedOwnerService);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatType);
        Pet savedFionasCat = petService.save(fionasCat);


        System.out.println("Loaded Owners....");

        Visit catVisit = new Visit();
        catVisit.setPet(savedFionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        Speciality speciality1 = new Speciality();
        speciality1.setDescription("radiology");
        Speciality radiology = specialityService.save(speciality1);

        Vet vet1 = new Vet();

        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(radiology);
        vetService.save(vet1);

        Speciality speciality2 = new Speciality();
        speciality2.setDescription("surgery");
        Speciality surgery = specialityService.save(speciality2);


        Vet vet2 = new Vet();

        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}

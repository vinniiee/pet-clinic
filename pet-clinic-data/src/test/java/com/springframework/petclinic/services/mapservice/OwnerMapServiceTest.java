package com.springframework.petclinic.services.mapservice;

import com.springframework.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String lastName = "smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(lastName);
        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1,owners.size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        Owner owner = ownerMapService.findById(ownerId);
        owner.setFirstName(lastName);
        ownerMapService.save(owner);
        assertEquals(1,ownerMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Owner owner = new Owner();
        ownerMapService.save(owner);
        assertEquals(2,ownerMapService.findAll().size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void findByLastName() {
        assertEquals(ownerId,ownerMapService.findByLastName(lastName).getId());
    }

    @Test
    void findByLastNameNotFound() {
        assertNull(ownerMapService.findByLastName("lastName"));
    }


}
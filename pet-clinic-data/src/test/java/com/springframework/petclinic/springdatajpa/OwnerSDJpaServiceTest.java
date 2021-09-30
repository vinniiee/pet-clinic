package com.springframework.petclinic.springdatajpa;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.repositories.OwnerRepository;
import com.springframework.petclinic.repositories.PetRepository;
import com.springframework.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Set<Owner> owners = new HashSet<>();
    Owner owner1 = new Owner();
    Owner owner2 = new Owner();
    String lastName;
    Long id;

    @BeforeEach
    void setUp() {
        owner1.setLastName("qwertyuiop");
        owner2.setLastName("asdfghjkl");
        lastName = "qwertyuiop";
        id = 1L;
        owner1.setId(id);
        owners.add(owner1);
        owners.add(owner2);
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(owners);
        assertEquals(2,ownerSDJpaService.findAll().size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(owner1));
        assertEquals(id,ownerSDJpaService.findById(id).getId());
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());
        assertNull(ownerSDJpaService.findById(id));
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner1);
        ownerSDJpaService.save(owner1);
        verify(ownerRepository).save(any());

    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(id);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner1);
        verify(ownerRepository).delete(any());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(java.util.Optional.ofNullable(owner1));
        Owner owner = ownerSDJpaService.findByLastName(lastName);
        assertEquals(lastName,owner.getLastName());
        verify(ownerRepository).findByLastName(anyString());
    }
}
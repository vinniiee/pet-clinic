package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetService;
import com.springframework.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;
    Owner owner = new Owner();
    Set<PetType> petTypes = new HashSet<>();
    Pet pet = new Pet();

    @BeforeEach
    void setUp() {

         mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
         owner.setId(1L);
         PetType dog = new PetType();
         PetType cat = new PetType();

         dog.setId(1L);
         dog.setName("Dog");

         cat.setId(2L);
         cat.setName("Cat");

        petTypes = new HashSet<PetType>(Arrays.asList(cat,dog));

        pet.setId(1L);
    }

    @Test
    void initCreationForm() throws Exception{
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(status().isOk());
    }

    @Test
    void processCreationForm() throws Exception{
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(status().is3xxRedirection());
        verify(petService).save(any());
//        verify(petTypeService).save(any());

    }

    @Test
    void initUpdateOwnerForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(pet);
        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attribute("pet",hasProperty("id")))
                .andExpect(status().isOk());
        verify(ownerService).findById(anyLong());
    }

    @Test
    void processUpdateOwnerForm() throws Exception{
//        when(petTypeService.findAll()).thenReturn(petTypes);
//        when(ownerService.save(any())).thenReturn(owner);
        mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(status().is3xxRedirection());
        verify(petService).save(any());
    }

}
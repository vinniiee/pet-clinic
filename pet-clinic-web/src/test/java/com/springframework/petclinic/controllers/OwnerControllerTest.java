package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController ownerController;
    Set<Owner> owners = new HashSet<>();
    Owner owner = new Owner();
    Owner owner1 = new Owner();
    Owner owner2 = new Owner();
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        owner.setId(1L);
        owners.add(owner);
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

//    @Test
//    void listOwner() throws Exception {
//
//        when(ownerService.findAll()).thenReturn(owners);
//        mockMvc.perform(get("/owners/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/owners/index"))
//                .andExpect(model().attribute("owners",hasSize(1)));
//
//    }

    @Test
    void ownersFind() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(view().name("owners/findOwners"))
                .andExpect(status().isOk());
    }

    @Test
    void findOwnersReturnOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(owner));
        mockMvc.perform(get("/owners"))
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void findOwnersReturnMany() throws Exception {

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(owner1,owner2));
        mockMvc.perform(get("/owners"))
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("listOwners",hasSize(2)))
                .andExpect(status().isOk());
    }

    @Test
    void ownerDisplay() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(get("/owners/1"))
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner",hasProperty("id",is(1L))))
                .andExpect(status().isOk());
    }

    @Test
    void initCreationForm() throws Exception{
        mockMvc.perform(get("/owners/new"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(status().isOk());
    }

    @Test
    void processCreationForm() throws Exception{
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner);
        mockMvc.perform(post("/owners/new"))
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(status().is3xxRedirection());
        verify(ownerService).save(any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attribute("owner",hasProperty("id",is(1L))))
                .andExpect(status().isOk());
        verify(ownerService).findById(anyLong());
    }

    @Test
    void processUpdateOwnerForm() throws Exception{
        when(ownerService.save(any())).thenReturn(owner);
        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(status().is3xxRedirection());
        verify(ownerService).save(any());
    }

}
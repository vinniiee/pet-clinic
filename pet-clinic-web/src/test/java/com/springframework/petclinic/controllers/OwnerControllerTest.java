package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController ownerController;
    Set<Owner> owners = new HashSet<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        owners.add(new Owner());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

    }

    @Test
    void listOwner() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/index"))
                .andExpect(model().attribute("owners",hasSize(1)));

    }

    @Test
    void ownersFind() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(view().name("notimplemented"))
                .andExpect(status().isOk());
    }
}
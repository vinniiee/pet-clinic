package com.springframework.petclinic.formatter;

import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        return petTypeService.findByName(s);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}

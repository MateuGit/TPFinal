package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ExceptionController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Services.interfaces.ICityService;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/phoneline/")
public class PhonelineController {

    private final IPhonelineService phonelineService;
    private final ICityService cityService;

    @Autowired
    public PhonelineController(IPhonelineService phonelineService, ICityService cityService) {
        this.phonelineService = phonelineService;
        this.cityService = cityService;
    }

    @PostMapping(value = "add/")
    public Phoneline add(@RequestBody @NotNull Phoneline phoneline) throws Exception {
        if (phoneline.hasNullAtribute()) throw new ParametersException("Parameters can´t contain null values");
        if (!phoneline.validNumberWithPrefix(cityService.getById(phoneline.getCity().getId()).getPrefix()))throw new PhonelineExceptions("The prefix plus the numbers, are more than 10 digits");
        try {
            return phonelineService.add(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineAddException(ex);
        }
        return phoneline;
    }



    @PostMapping("disable/")
    public Boolean disable(@RequestBody @NotNull Integer phoneNumber) throws ParametersException {
        if (phoneNumber == null) {
            throw new ParametersException("Parameters can´t contain null values");
        } else {
            return phonelineService.disable(phoneNumber);
        }
    }

    @PostMapping("enable/")
    public Boolean enable(@RequestBody @NotNull Integer phoneNumber) throws ParametersException {
        if (phoneNumber == null) {
            throw new ParametersException("Parameters can´t contain null values");
        } else {
            return phonelineService.enable(phoneNumber);
        }
    }

}

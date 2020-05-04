package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CityExceptions;
import com.utn.UTNphones.Exceptions.ExceptionController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UserExceptions;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.ICityService;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService, ICityService cityService) {
        this.userService = userService;
    }

    @PostMapping(value = "login/")
    public User login(@RequestBody @NotNull User user) throws ParametersException, UserExceptions {

        if (user.getIdentification() == null || user.getPassword() == null) {
            throw new ParametersException("Parameters can´t contain null values");
        } else {
            return userService.login(user);
        }
    }


    @PostMapping("register/")
    public User register(@RequestBody @NotNull User user) throws Exception {
        if (user.hasNullAtribute()) throw new ParametersException("Parameters can´t contain null values");
        try {
            return userService.register(user);
        } catch (DataAccessException ex) {
            if (ex.getRootCause().getMessage().contains("type_user"))
                throw new ParametersException("The user`s type doesn´t exist");
            else {
                ConstraintViolationException cve = (ConstraintViolationException) ex.getCause();
                ExceptionController.userRegisterException(cve);
            }
        }
        return user;
    }

    @DeleteMapping(value = "delete/")
    public void delete(@RequestBody @NotNull User user) throws ParametersException {
        if (user.getId() == null) throw new ParametersException("Parameter id can´t contain null value");
        this.userService.deleteById(user.getId());
    }

    @PostMapping(value = "update/")
    public void update(@RequestBody @NotNull User user) throws Exception {
        if (user.getId() == null) throw new ParametersException("Parameter id can´t contain null value");
       User inDataBaseUser = this.userService.findById(user.getId());
       user.setNonNullValues(inDataBaseUser);
        try {
            this.userService.update(user);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(ex);
        }

    }


}

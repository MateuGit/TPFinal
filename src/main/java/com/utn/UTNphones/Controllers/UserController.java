package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UserDoesntExistException;
import com.utn.UTNphones.Exceptions.UserExistsException;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login/")
    public User login(@RequestBody @NotNull User user) throws ParametersException, NumberFormatException, UserDoesntExistException {
        User u;
        if (user.getIdentification()==null || user.getPassword()==null){
            throw new ParametersException();
        }else{
            u = userService.login(user);
        }
         return u;
    }

    @PostMapping("/register/")
    public User register(@RequestBody @NotNull User user) throws ParametersException, UserExistsException {
       if (user.hasNullAtribute()){
          throw new ParametersException();
       }else{
           try {
               userService.register(user);
           } catch (DataAccessException th) {

               ConstraintViolationException cve= (ConstraintViolationException) th.getCause();

               throw new UserExistsException();
           }
       }
        return user;
    }




}

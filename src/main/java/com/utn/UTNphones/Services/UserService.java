package com.utn.UTNphones.Services;

import com.utn.UTNphones.Controllers.Web.AdviceController;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.Dto.ErrorResponseDto;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Repositories.IUserRepository;
import com.utn.UTNphones.Services.interfaces.IUserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(User user) throws UserExceptions {
        User u = userRepository.findByIdentificationAndPassword(user.getIdentification(), user.getPassword());
        return Optional.ofNullable(u).orElseThrow(() -> new LogException());
    }
    @Override
    public User register(User user) throws DataAccessException, UserDoesntExist {
        userRepository.save(user);
        return findById(user.getId());
    }



    @Override
    public void deleteByIdentification(String identification) {
        this.userRepository.deleteByIdentification(identification);
    }

    @Override
<<<<<<< HEAD
    public User update(User user) throws UserExceptions,DataAccessException {
      User userUpdated=this.userRepository.save(user);
       return Optional.ofNullable(userUpdated).orElseThrow(UserDoesntExist::new);
=======
    public User update(User user) throws UserExceptions, ErrorResponseDto {
      User userUpdated = null;


        try {
            userUpdated=this.userRepository.save(user);
      }catch (TransactionSystemException ex){
            String error = AdviceController.PatternsHandler((ConstraintViolationException) ex.getCause().getCause());
            throw new ErrorResponseDto(3, error);
      }

       return Optional.ofNullable(userUpdated).orElseThrow(() -> new UserDoesntExist());
>>>>>>> 34c32bbbb62eb82607102fc5f1917c7fbecb1f1c
    }



    @Override
    public User findById(Integer id) throws UserDoesntExist {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())throw new UserDoesntExist();
        else return user.get();
    }

    @Override
    public User findByIdentification(String identification) throws UserDoesntExist {
        User user = userRepository.findByIdentification(identification);
        if (user==null)throw new UserDoesntExist();
        else return user;
    }


}
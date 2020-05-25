package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domain.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Domain.Exceptions.PhonelineExceptions.PhonelineAlreadyExists;
import com.utn.UTNphones.Domain.Exceptions.PhonelineExceptions.PhonelineTypeError;
import com.utn.UTNphones.Domain.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Domain.Exceptions.UsersExceptions.UserIdentificationAlreadyExists;
import com.utn.UTNphones.Domain.Exceptions.UsersExceptions.UserTypeDoesntExist;
import org.hibernate.JDBCException;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class ExceptionController {
    public static void userRegisterException(SQLException ex) throws Exception {
        switch (ex.getErrorCode()) {
            case 1452:
                throw new CityDoesntExist();
            case 1062:
                throw new UserIdentificationAlreadyExists();
            case 1265:
                throw new UserTypeDoesntExist();
            default:
                throw new Exception("External error");
        }
    }


    public static void phonelineAddException(DataAccessException Error) throws Exception {
        JDBCException ex = (JDBCException) (Error).getCause();
        phonelineAddExceptionSQLCode(ex.getErrorCode());
    }

    private static void phonelineAddExceptionSQLCode(int errorNumber) throws Exception{
        switch (errorNumber) {
            case 1452:
                throw new UserDoesntExist();
            case 1265:
                throw new PhonelineTypeError();
            case 1062:
                throw new PhonelineAlreadyExists();
            default:
                throw new Exception("External error");
        }
    }

    public static void userUpdateException(DataAccessException Error) throws Exception {

        //City id
        if (Error.getRootCause().getMessage().contains("Models.City")) //110
            throw new CityDoesntExist();
            //Identification unique
        else if (Error.getRootCause().getMessage().contains("for key 'identification_card'")) //92
            throw new UserIdentificationAlreadyExists();
            //User type enum
        else if (Error.getRootCause().getMessage().contains("type_user"))//91  - 98 root
            throw new UserTypeDoesntExist();

        else throw new Exception("External error");
    }


}

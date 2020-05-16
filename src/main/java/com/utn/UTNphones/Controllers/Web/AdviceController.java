package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Exceptions.CallException;
import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineAlreadyExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserIdentificationAlreadyExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeDoesntExist;
import com.utn.UTNphones.Models.Dto.ErrorResponseDto;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDoesntExist.class)
    public ErrorResponseDto handleUserNotExists(UserDoesntExist ex) {
        return new ErrorResponseDto(1, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParametersException.class)
    public ErrorResponseDto handleParametersException(ParametersException ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LogException.class)
    public ErrorResponseDto handleParametersException(LogException ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CityDoesntExist.class)
    public ErrorResponseDto handleCityDoesntExist(CityDoesntExist ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineAlreadyExists.class)
    public ErrorResponseDto handlePhonelineAlreadyExists(PhonelineAlreadyExists ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineDigitsCountPlusPrefix.class)
    public ErrorResponseDto handlePhonelineDigitsCountPlusPrefix(PhonelineDigitsCountPlusPrefix ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineDoesntExist.class)
    public ErrorResponseDto handlePhonelineDoesntExist(PhonelineDoesntExist ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelinesNotRegisteredByUser.class)
    public ErrorResponseDto handlePhonelinesNotRegisteredByUser(PhonelinesNotRegisteredByUser ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserIdentificationAlreadyExists.class)
    public ErrorResponseDto handleUserIdentificationAlreadyExists(UserIdentificationAlreadyExists ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserTypeDoesntExist.class)
    public ErrorResponseDto handleUserTypeDoesntExist(UserTypeDoesntExist ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CallException.class)
    public ErrorResponseDto handleCallException(CallException ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseDto handleCallException(ConstraintViolationException ex) {
        return new ErrorResponseDto(2, PatternsHandler(ex));
    }


    public String PatternsHandler(ConstraintViolationException message) {
        Pattern pattern = Pattern.compile("'.*?'");
        Matcher matcher = pattern.matcher(message.getMessage());
        String PatternErrors = "Errors with the patterns: ";
        while (matcher.find()) {
            switch (matcher.group()) {
                case "'Invalid lastname!'":
                    if (PatternErrors.indexOf("'Invalid lastname!'") == -1) PatternErrors += matcher.group() + " - ";
                    break;
                case "'Invalid identification!'":
                    if (PatternErrors.indexOf("'Invalid identification!'") == -1) PatternErrors += matcher.group() + " - ";
                    break;
                case "'Invalid name!'":
                    if (PatternErrors.indexOf("'Invalid name!'") == -1) PatternErrors += matcher.group() + " - ";
                    break;
                case "'Invalid number!'":
                    if (PatternErrors.indexOf("'Invalid number!'") == -1) PatternErrors += matcher.group() + " - ";
                    break;
            }
        }
        return PatternErrors;
    }

}
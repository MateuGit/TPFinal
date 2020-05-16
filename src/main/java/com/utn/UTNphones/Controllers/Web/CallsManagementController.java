package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Exceptions.CallException;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/callsManagementController")
public class CallsManagementController {
    private final CallController callController;
    private final SessionManager sessionManager;

    @Autowired
    public CallsManagementController(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/searchByUser")
    public ResponseEntity<List<Call>> getByUserId(@RequestHeader("Authorization") String sessionToken, @RequestBody @NotNull Integer userId) throws CallException, PhonelineExceptions, UserExceptions, ParametersException {
        if (!hasEmployeePermissions(sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        List<Call> callsByAnUser = this.callController.getCallsByUserId(userId);
        return callsByAnUser.isEmpty()?ResponseEntity.status(201).build() :ResponseEntity.ok(callsByAnUser);
    }

    @GetMapping("/mostDestinationsCalled")
    public ResponseEntity<List<Object>> mostDestinationsCalled(@RequestHeader("Authorization") String sessionToken, @RequestBody @NotNull Integer userId) throws UserExceptions, CallException, ParametersException {
        if (!hasEmployeePermissions(sessionToken)) {
            return ResponseEntity.status(403).build();        }
        List<Object> citiesWithCounter=this.callController.getTopDestinationsCalled(userId);
        return citiesWithCounter.isEmpty()?ResponseEntity.status(201).build() : ResponseEntity.ok(citiesWithCounter);
    }

    @GetMapping("/getCallsBetweenDates")
    public ResponseEntity<List<Call>> getCallsBetweenDates(@RequestHeader("Authorization") String sessionToken, @RequestBody Date start, @RequestBody Date end) throws UserExceptions, ParametersException {
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        if(currentUser.isEmpty()){ return ResponseEntity.status(403).build();}
        List<Call> calls=this.callController.getByUserBetweenDates(currentUser.get().getId(),start,end);
        return calls.isEmpty()?ResponseEntity.status(201).build() : ResponseEntity.ok(calls);
    }

    public Boolean hasEmployeePermissions(String sessionToken) {
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        return (!currentUser.isEmpty() && !currentUser.get().getType().equals("client"));
    }
}

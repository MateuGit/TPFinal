package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Controllers.PhonelineController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/phonelineManagement")
public class PhonelineManagementController {
    private final SessionManager sessionManager;
    private final PhonelineController phonelineController;

    @Autowired
    public PhonelineManagementController(SessionManager sessionManager, PhonelineController phonelineController) {
        this.sessionManager = sessionManager;
        this.phonelineController = phonelineController;
    }

    @PostMapping
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody Phoneline newPhoneline) throws Exception {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        phonelineController.add(newPhoneline);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/Phoneline/{phoneNumber}")
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken, @PathVariable("phoneNumber") String phoneNumber) throws Exception {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        this.phonelineController.remove(phoneNumber);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/disable/{phoneNumber}")
    public ResponseEntity disable(@RequestHeader("Authorization") String sessionToken, @PathVariable("phoneNumber") @NotNull String phoneNumber) throws ParametersException, PhonelineExceptions {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        this.phonelineController.disable(phoneNumber);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/enable/{phoneNumber}")
    public ResponseEntity enable(@RequestHeader("Authorization") String sessionToken, @PathVariable("phoneNumber") @NotNull String phoneNumber) throws ParametersException, PhonelineExceptions {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        this.phonelineController.enable(phoneNumber);
        return ResponseEntity.ok().build();
    }


}

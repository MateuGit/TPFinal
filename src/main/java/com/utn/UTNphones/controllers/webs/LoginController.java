package com.utn.UTNphones.controllers.webs;

import com.utn.UTNphones.controllers.UserController;
import com.utn.UTNphones.domains.dto.requests.ClientLoginDTO;
import com.utn.UTNphones.domains.dto.requests.EmployeeLoginDTO;
import com.utn.UTNphones.sessions.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class LoginController {
    private final UserController userController;
    private final SessionManager sessionManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid ClientLoginDTO userLogging) {
        return ResponseEntity.ok(sessionManager.createSession(userController.login(userLogging)));
    }

    @PostMapping("/Employee")
    public ResponseEntity<String> employeeLogin(@RequestBody @Valid EmployeeLoginDTO userLogging) {
        return ResponseEntity.ok(sessionManager.createSession(userController.login(userLogging)));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        sessionManager.removeSession(token);
        return ResponseEntity.ok().build();
    }

    public HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return responseHeaders;
    }
}

package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.InvoiceController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Models.Invoice;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/InvoicesManagmentController")
public class InvoicesManagmentController {
    private final InvoiceController invoiceController;
    private final SessionManager sessionManager;

    public InvoicesManagmentController(InvoiceController invoiceController, SessionManager sessionManager) {
        this.invoiceController = invoiceController;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<List<Invoice>> getByUserId(@RequestHeader("Authorization") String sessionToken, @RequestBody Integer userId) throws ParametersException {
        if (!hasEmployeePermissions(sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        List<Invoice>invoices=this.invoiceController.getAllByUserId(userId);
        return invoices.isEmpty() ?  ResponseEntity.status(204).build(): ResponseEntity.ok(invoices);
    }

    public Boolean hasEmployeePermissions(String sessionToken) {
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        return (!currentUser.isEmpty() && !currentUser.get().getType().equals("client"));
    }

}
package com.utn.UTNphones.controllers.webs.Client;

import com.utn.UTNphones.controllers.InvoiceController;
import com.utn.UTNphones.domains.Invoice;
import com.utn.UTNphones.domains.dto.requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.sessions.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.utn.UTNphones.utils.UserRouter.CLIENT_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(CLIENT_MAPPING + "/invoices")
public class InvoicesClientController {
    private final InvoiceController invoiceController;
    private final SessionManager sessionManager;

    @GetMapping
    public ResponseEntity<List<Invoice>> getByUserIdBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                                 @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate,
                                                                 @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> endDate) {

        Integer userId = sessionManager.getCurrentUser(sessionToken).get().getId();
        List<Invoice> invoices = this.invoiceController.findByUserBetweenDates(userId,
                SearchBetweenDatesDTO.fromDates(startDate.orElse(LocalDate.of(2020, 1, 1)),
                        endDate.orElse(LocalDate.now())));

        return invoices.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(invoices);
    }
}
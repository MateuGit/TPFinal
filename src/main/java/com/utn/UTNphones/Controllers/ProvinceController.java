package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Services.interfaces.IProvineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    private final IProvineService invoiceService;

    @Autowired
    public ProvinceController(IProvineService invoiceService) {
        this.invoiceService = invoiceService;
    }
}
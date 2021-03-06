package com.utn.UTNphones.controllers;

import com.utn.UTNphones.domains.Rate;
import com.utn.UTNphones.services.CityService;
import com.utn.UTNphones.services.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    private final CityService cityService;

    public List<Rate> getAllRates() {
        return rateService.getAllRates();
    }

    public Rate findByOriginAndDestination(Integer originCityIdId, Integer destinationCityId) {
        cityService.getById(originCityIdId);
        cityService.getById(destinationCityId);
        return this.rateService.findByOriginAndDestination(originCityIdId, destinationCityId);
    }

    public List<Rate> findByOrigin(Integer originCityId) {
        rateService.findById(originCityId);
        return this.rateService.findByOrigin(originCityId);
    }

    public List<Rate> findByDestination(Integer destinationCityId) {
        rateService.findById(destinationCityId);
        return this.rateService.findByDestination(destinationCityId);
    }

    public Rate findById(Integer id) {
        return rateService.findById(id);
    }
}

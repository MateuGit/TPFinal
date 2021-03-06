package com.utn.UTNphones.controllers;

import com.utn.UTNphones.domains.Call;
import com.utn.UTNphones.domains.dto.requests.NewCallDTO;
import com.utn.UTNphones.domains.dto.requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.domains.dto.responses.CityTop;
import com.utn.UTNphones.services.CallService;
import com.utn.UTNphones.services.PhonelineService;
import com.utn.UTNphones.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CallController {

    private final CallService callService;
    private final UserService userService;
    private final PhonelineService phonelineService;

    public List<Call> getCallsByUserId(Integer userId) {
        userService.findById(userId);
        return callService.getCallsByPhoneNumbers(phonelineService.findByUserId(userId));
    }

    public List<CityTop> getTopDestinationsCalled(Integer userId) {
        return this.callService.getTopMostCalledCities(userId);
    }

    public List<Call> getByUserBetweenDates(Integer userId, SearchBetweenDatesDTO dates) {
        return this.callService.getByUserAndBetweenDates(userId, dates);
    }

    public void registerCall(NewCallDTO newCall) {
        this.callService.add(Call.fromDto(newCall));
    }

}

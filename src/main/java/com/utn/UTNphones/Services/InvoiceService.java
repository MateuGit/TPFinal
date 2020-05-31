package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Dto.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Repositories.IInvoiceRepository;
import com.utn.UTNphones.Services.interfaces.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    private final IInvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(IInvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> getAllByUserId(Integer userId) {
        return this.invoiceRepository.findByPhonelineUserId(userId);
    }

    @Override
    public List<Invoice> getByUserAndBetweenDates(Integer id, SearchBetweenDatesDTO datesDto) {
        return this.invoiceRepository.findAllByPhonelineUserIdAndDateBetween(id,datesDto.getStart(),datesDto.getEnd());
    }

    @Override
    public List<Invoice> getByUserStartDate(Integer id, Date startDate) {
        return this.invoiceRepository.findAllByPhonelineUserIdAndDateAfter(id,startDate);

    }

    @Override
    public List<Invoice> getByUserEndDate(Integer id, Date endDate) {
        return this.invoiceRepository.findAllByPhonelineUserIdAndDateBefore(id,endDate);
    }
}

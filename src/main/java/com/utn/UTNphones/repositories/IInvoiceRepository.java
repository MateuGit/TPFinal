package com.utn.UTNphones.repositories;

import com.utn.UTNphones.domains.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByPhonelineUserId(Integer userId, Pageable pageable);

    List<Invoice> findAllByPhonelineUserIdAndDateBetweenOrderByIdDesc(Integer userId, Date startDate, Date endDate, Pageable pageable);

}

package com.utn.UTNphones.Repositories;


import com.utn.UTNphones.Models.Phoneline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IPhonelineRepository extends JpaRepository<Phoneline,Integer> {
    @Transactional
    @Modifying
    @Query(value = "update phonelines ph set ph.status_phoneline = ?1 where ph.phone_number = ?2", nativeQuery = true)
    int disableOrEnable(Boolean newStatus,Integer phoneNumber);

}
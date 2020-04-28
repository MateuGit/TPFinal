package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="invoices")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phoneline")
    private Phoneline phoneline;

    @Column(name = "calls_quantity")
    private Integer callsQuantity;

    @Column(name = "cost_price")
    private Integer costPrice;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "invoice_date")
    private Date date;

    @Column(name = "invoice_expiration_date")
    private Date expirationDate;

}
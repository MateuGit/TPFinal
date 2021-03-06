package com.utn.UTNphones.domains;

import com.utn.UTNphones.domains.dto.requests.Login;
import com.utn.UTNphones.domains.dto.requests.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "user_status")
    private Boolean status;
    @Column(name = "user_type")
    private String type;
    @Column(name = "identification_card")
    private String identification;
    @Column(name = "user_password")
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    public static User fromLoginDto(Login loginDTO) {
        return User.builder()
                .identification(loginDTO.getIdentification())
                .password(loginDTO.getPassword())
                .type(loginDTO.getType())
                .build();
    }


    public static User fromDto(UserDTO u) {
        return User.builder().name(u.getName()).lastname(u.getLastname())
                .status(u.getStatus())
                .type(u.getType())
                .identification(u.getIdentification())
                .password(u.getPassword())
                .city(City.builder().id(u.getCityId()).build())
                .build();
    }
}


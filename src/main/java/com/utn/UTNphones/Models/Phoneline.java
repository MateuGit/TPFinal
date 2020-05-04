package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;
import java.util.stream.Stream;

@Entity
@Table(name = "phonelines")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Phoneline {
    @Id
    @Column(name = "phone_number")
    private Integer number;

    @Column(name = "type_user")
    private String type;

    @Column(name = "status_phoneline")
    private Boolean status;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    public boolean hasNullAtribute() {
        if (Stream.of(number, type, city, user,status).anyMatch(x -> x == null)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validNumberWithPrefix(){
        if ((String.valueOf(number).length() + String.valueOf(city.getPrefix()).length())!=10)return false;
        return true;
    }
    public boolean validNumberWithPrefix(Integer prefix){
        if ((String.valueOf(number).length() + String.valueOf(prefix).length())!=10)return false;
        return true;
    }
}

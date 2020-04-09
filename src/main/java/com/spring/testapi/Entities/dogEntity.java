package com.spring.testapi.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class dogEntity implements animal {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String name;
    Date birthDate;
    @ManyToOne(optional = false)
    personEntity owner;
    String photoName="";

    public dogEntity(String name, Date birthDate, personEntity owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
    }
}

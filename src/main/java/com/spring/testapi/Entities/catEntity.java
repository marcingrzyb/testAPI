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
public class catEntity implements animal {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id=null;
    String name;
    Date birthDate;
    @ManyToOne(optional = false)
    personEntity owner;
    String photoName="";

    public catEntity(String name, Date birthDate, personEntity owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
    }


}

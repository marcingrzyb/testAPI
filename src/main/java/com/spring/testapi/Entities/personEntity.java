package com.spring.testapi.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class personEntity {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long Id=null;
    String firstName;
    String lastName;
    String phoneNumber;
    @OneToMany(mappedBy = "owner")
    @JsonBackReference("dog")
    List<dogEntity> dogs;
    @OneToMany(mappedBy = "owner")
    @JsonBackReference("cat")
    List<catEntity> cats;

}

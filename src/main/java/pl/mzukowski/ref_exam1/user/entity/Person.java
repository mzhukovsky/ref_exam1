package pl.mzukowski.ref_exam1.user.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String lastName;

}

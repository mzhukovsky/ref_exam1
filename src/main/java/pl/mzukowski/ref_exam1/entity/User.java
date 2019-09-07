package pl.mzukowski.ref_exam1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue("USER")
public class User extends Person {

    private String username;

    private String password;
}

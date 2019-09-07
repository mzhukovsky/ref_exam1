package pl.mzukowski.ref_exam1.user.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue("USER")
public class User extends Person {
    private String login;
    private String password;
}

package pl.mzukowski.ref_exam1.user.register;

import lombok.Data;

@Data
public class AddUserDto {
    Long idd;
    String username;
    String password;
}

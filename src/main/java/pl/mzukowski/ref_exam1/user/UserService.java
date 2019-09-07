package pl.mzukowski.ref_exam1.user;

import pl.mzukowski.ref_exam1.entity.User;
import pl.mzukowski.ref_exam1.user.register.AddUserDto;

interface UserService {
    AddUserDto updateUser(AddUserDto addUserDto);
    User findUserById(Long id);
}

package pl.mzukowski.ref_exam1.service;

import pl.mzukowski.ref_exam1.dto.AddUserDto;
import pl.mzukowski.ref_exam1.entity.User;

interface UserService {
    AddUserDto saveUser(AddUserDto addUserDto);

    AddUserDto updateUser(AddUserDto addUserDto);

    User findUserById(Long id);
}

package pl.mzukowski.ref_exam1.user.register;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mzukowski.ref_exam1.user.entity.User;
import pl.mzukowski.ref_exam1.user.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
class UserCreator {

    private final UserRepository userRepository;
    private final UserRegisterValidation registerUserValidation;

    @Transactional
    UserCreateResponseDto saveUser(AddUserDto addUserDto) {
        registerUserValidation.validateUser(addUserDto);
        User user = prepareUser(addUserDto);
        User newUser = userRepository.save(user);

        return new UserCreateResponseDto(newUser.getId().toString());
    }

    private User prepareUser(AddUserDto addUserDto) {
        User user = new User();
        user.setLogin(addUserDto.getLogin());
        user.setPassword(addUserDto.getPassword());

        return user;
    }
}
package pl.mzukowski.ref_exam1.user.register;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mzukowski.ref_exam1.user.UserRepository;

@Component
@RequiredArgsConstructor
class UserRegisterValidation {

    private final UserRepository userRepository;

    void validateUser(AddUserDto addUserDto) {
    }
}

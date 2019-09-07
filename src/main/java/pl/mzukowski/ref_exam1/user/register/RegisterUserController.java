package pl.mzukowski.ref_exam1.user.register;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/register")
@RequiredArgsConstructor
class RegisterUserController {

    private final UserCreator userCreator;

    @PostMapping
    public UserCreateResponseDto addUser(@RequestBody AddUserDto addUserDto) {
        return userCreator.saveUser(addUserDto);
    }
}

package pl.mzukowski.ref_exam1.user;

import pl.mzukowski.ref_exam1.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mzukowski.ref_exam1.user.register.AddUserDto;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PutMapping
    @ResponseStatus(OK)
    public AddUserDto modifyUser(@RequestBody AddUserDto addUserDto) {
        return userService.updateUser(addUserDto);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }

}

package pl.mzukowski.ref_exam1.controller;

import pl.mzukowski.ref_exam1.dto.AddUserDto;
import pl.mzukowski.ref_exam1.entity.User;
import pl.mzukowski.ref_exam1.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public void addUser(@RequestBody AddUserDto addUserDto) {
        userService.saveUser(addUserDto);
    }

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

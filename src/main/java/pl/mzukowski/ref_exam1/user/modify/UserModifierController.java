package pl.mzukowski.ref_exam1.user.modify;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserModifierController {

    private final UserModifier userModifier;

    @PutMapping
    public ModifyUserResponseDto modifyUser(@RequestBody ModifyUserDto modifyUserDto) {
        return userModifier.updateUser(modifyUserDto);
    }
}

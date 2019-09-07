package pl.mzukowski.ref_exam1.user.finder;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserFinderController {

    private final UserFinder userFinder;

    @GetMapping("/{id}")
    public UserResponseDto findUser(@PathVariable("id") Long id){
        return userFinder.findUserById(id);
    }
}

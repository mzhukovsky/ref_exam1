package pl.mzukowski.ref_exam1.user.finder;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserFinderController {

    private final UserFinder userFinder;

    @GetMapping("/{id}")
    public UserResponseDto findUser(@PathVariable("id") Long id){
        try {
            return userFinder.findUserById(id);
        } catch(IllegalArgumentException exc) {
            throw new ResponseStatusException(NOT_FOUND, exc.getMessage(), exc);
        }
    }
}

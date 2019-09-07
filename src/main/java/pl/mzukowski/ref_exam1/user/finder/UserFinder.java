package pl.mzukowski.ref_exam1.user.finder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mzukowski.ref_exam1.user.UserRepository;
import pl.mzukowski.ref_exam1.user.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserFinder {

    private final UserRepository userRepository;

    UserResponseDto findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserResponseDto(user.getId().toString(), user.getLogin(), user.getName());
        } else {
            throw new IllegalArgumentException("Użytkownik o podanym id nie istnieje");
        }
    }
}

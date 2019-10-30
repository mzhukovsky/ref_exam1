package pl.mzukowski.ref_exam1.user.finder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mzukowski.ref_exam1.user.UserRepository;

@Service
@RequiredArgsConstructor
class UserFinder {

    private final UserRepository userRepository;

    UserResponseDto findUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserResponseDto(user.getId().toString(), user.getLogin(), user.getName()))
                .orElseThrow(() -> new IllegalArgumentException("Użytkownik o podanym id nie istnieje"));
    }
}

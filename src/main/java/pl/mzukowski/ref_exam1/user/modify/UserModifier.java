package pl.mzukowski.ref_exam1.user.modify;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mzukowski.ref_exam1.user.UserRepository;

@Service
@RequiredArgsConstructor
class UserModifier {

    private final UserRepository userRepository;

    @Transactional
    ModifyUserResponseDto updateUser(ModifyUserDto modifyUserDto) {
        return null;
    }
}

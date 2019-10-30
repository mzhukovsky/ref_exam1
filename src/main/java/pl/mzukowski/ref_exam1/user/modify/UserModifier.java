package pl.mzukowski.ref_exam1.user.modify;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mzukowski.ref_exam1.user.UserRepository;
import pl.mzukowski.ref_exam1.user.entity.User;

@Service
@RequiredArgsConstructor
class UserModifier {

    private final UserRepository userRepository;
    private final ModifyUserValidation modifyUserValidation;

    @Transactional
    ModifyUserResponseDto updateUser(ModifyUserDto modifyUserDto) {
        modifyUserValidation.validateModifyUser(modifyUserDto);
        User user = userRepository.getOne(Long.valueOf(modifyUserDto.getId()));
        user.setName(modifyUserDto.getName());
        user.setLastName(modifyUserDto.getLastName());
        User newUser = userRepository.save(user);

        return new ModifyUserResponseDto(newUser.getId().toString());
    }
}

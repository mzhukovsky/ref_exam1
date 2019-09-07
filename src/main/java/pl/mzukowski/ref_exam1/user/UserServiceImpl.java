package pl.mzukowski.ref_exam1.user;

import pl.mzukowski.ref_exam1.entity.Person;
import pl.mzukowski.ref_exam1.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mzukowski.ref_exam1.user.register.AddUserDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public AddUserDto updateUser(AddUserDto addUserDto) {
        final Person userByPassword = userRepository.findUserByPassword(addUserDto.getPassword()).get();
        userByPassword.setName(addUserDto.getUsername());
        return addUserDto;
    }

    private void validateUser(AddUserDto addUserDto) {

    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}

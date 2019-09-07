package pl.mzukowski.ref_exam1.service;

import pl.mzukowski.ref_exam1.dto.AddUserDto;
import pl.mzukowski.ref_exam1.entity.Person;
import pl.mzukowski.ref_exam1.entity.User;
import pl.mzukowski.ref_exam1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public AddUserDto saveUser(AddUserDto addUserDto) {
        validateUser(addUserDto);
        User user = new User();
        user.setPassword(addUserDto.getPassword());
        user.setUsername(addUserDto.getUsername());
        user.setName("test");
        userRepository.save(user);
        return addUserDto;
    }

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

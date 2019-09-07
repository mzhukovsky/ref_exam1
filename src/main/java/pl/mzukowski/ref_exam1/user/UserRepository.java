package pl.mzukowski.ref_exam1.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mzukowski.ref_exam1.user.entity.Person;
import pl.mzukowski.ref_exam1.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Person> findUserByLogin(String login);
}

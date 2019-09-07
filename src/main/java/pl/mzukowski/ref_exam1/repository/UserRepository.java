package pl.mzukowski.ref_exam1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mzukowski.ref_exam1.entity.Person;
import pl.mzukowski.ref_exam1.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Person> findUserByPassword(String password);
}

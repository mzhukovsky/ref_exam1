package pl.mzukowski.ref_exam1.controller;

import pl.mzukowski.ref_exam1.user.entity.Person;
import pl.mzukowski.ref_exam1.user.entity.User;
import pl.mzukowski.ref_exam1.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUserAndFind() throws Exception {
        // given
        final String login = "login_test";
        final String password = "password_test";

        //when
        mockMvc.perform(post("/user/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"login\":\"" + login + "\"," +
                        "\"password\":\"" + password + "\"" +
                        "}")
        ).andDo(print());

        //and
        final Long userId = userRepository.findAll().iterator().next().getId();

        //expect
        mockMvc.perform(get("/user/"+userId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("login", Is.is(login)))
                .andExpect(jsonPath("id", Is.is(userId.toString())));
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setName("test");
        user.setLastName("test");
        user.setPassword("test");

        user = userRepository.save(user);

        mockMvc.perform(put("/user").contentType(APPLICATION_JSON_UTF8).content(
                "{\"username\":\"userBla\",\"password\":\"test\"}"
        )).andExpect(jsonPath("username", Is.is("userBla")));

        Assertions.assertThat(userRepository.findUserByLogin("test")).isPresent()
                .map(Person::getName)
                .hasValue("userBla");
    }
}

package pl.mzukowski.ref_exam1.controller;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

import static org.assertj.core.api.Assertions.*;
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
        mockMvc.perform(post("/user/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"login\":\"" + login + "\"," +
                        "\"password\":\"" + password + "\"" +
                        "}"))
                .andDo(print());
        final Long userId = userRepository.findAll().iterator().next().getId();

        //when
        ResultActions result = mockMvc.perform(get("/user/" + userId));

        //then
        result.andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("login", Is.is(login)))
                .andExpect(jsonPath("id", Is.is(userId.toString())));
    }

    @Test
    public void tryFindUserAndGetException() throws Exception {
        // given
        final Integer userId = 1;

        //when
        final ResultActions result = mockMvc.perform(get("/user/" + userId));

        //then
        result.andExpect(content().contentType(APPLICATION_JSON_UTF8));
        result.andExpect(status().isNotFound());
    }

    @Test
    public void updateUser() throws Exception {
        // given
        User user = new User();
        user.setName("name_test");
        user.setLastName("lastname_test");
        user.setLogin("login_test");
        user.setPassword("password_test");
        User newUser = userRepository.save(user);
        String newName = "new_name";
        String newLastName =  "new_lastname";

        // when
        final ResultActions result = mockMvc.perform(put("/user").contentType(APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"id\":\""+ newUser.getId() +"\"," +
                        "\"name\":\""+ newName +"\"," +
                        "\"lastName\":\""+ newLastName +"\"" +
                        "}"))
                .andDo(print());

        //then
        result.andExpect(jsonPath("id", Is.is(newUser.getId().toString())));
        assertThat(userRepository.findUserByLogin("login_test"))
                .isPresent()
                .map(Person::getName)
                .hasValue(newName);
        assertThat(userRepository.findUserByLogin("login_test"))
                .isPresent()
                .map(Person::getLastName)
                .hasValue(newLastName);
    }
}

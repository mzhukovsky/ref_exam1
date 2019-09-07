package pl.mzukowski.ref_exam1.controller;

import pl.mzukowski.ref_exam1.entity.Person;
import pl.mzukowski.ref_exam1.entity.User;
import pl.mzukowski.ref_exam1.repository.UserRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUser() throws Exception {
        final String password = "passsword";
        final String username = "username";
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"username\":\"" + username + "\"," +
                        "\"password\":\"" + password + "\"" +
                        "}")
        ).andDo(print());

        userRepository.flush();

        final Long userId = userRepository.findAll().iterator().next().getId();

        mockMvc.perform(get("/user/"+userId))
                .andExpect(MockMvcResultMatchers.jsonPath("username", Is.is(username)));
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setName("test");
        user.setUsername("test");
        user.setPassword("test");

        user = userRepository.save(user);

        mockMvc.perform(put("/user").contentType(APPLICATION_JSON_UTF8).content(
                "{\"username\":\"userBla\",\"password\":\"test\"}"
        )).andExpect(MockMvcResultMatchers.jsonPath("username", Is.is("userBla")));

        Assertions.assertThat(userRepository.findUserByPassword("test")).isPresent()
                .map(Person::getName)
                .hasValue("userBla");
    }
}

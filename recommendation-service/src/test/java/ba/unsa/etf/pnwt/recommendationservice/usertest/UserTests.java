package ba.unsa.etf.pnwt.recommendationservice.usertest;
/*
import ba.unsa.etf.pnwt.recommendationservice.controller.UserController;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.repository.JobRepository;
import ba.unsa.etf.pnwt.recommendationservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserTests {
    private static final String API_USER = "/user/";
    private static UserEntity privateMockUser;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Autowired
    UserRepository  userRepository;

    @BeforeEach
    void setUpMockUsers() {
        privateMockUser = new UserEntity();
        privateMockUser.setEmail("test1@gmail.com");
        privateMockUser.setName("test");

        userRepository.save(privateMockUser);

    }
    @AfterEach
    void deleteMock(){
        Optional<UserEntity> u = userRepository.findUserEntityByEmail("test1@gmail.com");
        userRepository.delete(u.get());
    }

//    @Test
//    public void contextLoads() throws Exception {
//        assertThat(userController).isNotNull();
//    }

    @Test
    void testGetAll() throws Exception {
        this.mockMvc
                .perform(get(API_USER))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testUserWithInvalidEmail() throws Exception{
        privateMockUser.setEmail("test"); //nepravilan format email-a
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/addNewUser")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400));


    }




}


 */
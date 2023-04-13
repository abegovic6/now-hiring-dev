package ba.unsa.etf.pnwt.jobsservice.controller;

import ba.unsa.etf.pnwt.jobsservice.dto.UserDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import ba.unsa.etf.pnwt.jobsservice.mapper.UserMapper;
import ba.unsa.etf.pnwt.jobsservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    private static final String API_USER = "/api/user/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    private static UserDTO user;

    @BeforeEach
    void setUpMockUser(){
        user = new UserDTO();
        //user.setId(100000);
        user.setUserType("PRIVATE");
        user.setUid("SifraUserTEST");
        user.setEmail("test@gmail.com");

    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    void testGetAll() throws Exception {
        this.mockMvc
                .perform(get(API_USER + "all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testUserById() throws Exception {

        UserEntity userEntity = userRepository.findAll().get(0);
        String url = "/get?uid=" + userEntity.getUid().toString();

        this.mockMvc
                .perform(get(API_USER + url))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc
                .perform(get(API_USER + "/id?id=-6"))
                .andDo(print())
                .andExpect(status().is(404));
    }

    @Test
    void testAdd() throws Exception{
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201));

    }

    @Test
    void testAddNotValidUuid() throws Exception{

        user.setUid(null);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400));

    }

    @Test
    void testAddNotValidEmail() throws Exception{

        user.setEmail(null);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400));

    }

    @Test
    void testAddNotValidUserType() throws Exception{

        user.setEmail(null);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    void testUpdate() throws Exception{

        UserEntity userEntity = userRepository.findAll().get(0);
        userEntity.setCompanyName("TEST COMPANY NAME NEW");
        UserDTO userDTO = UserMapper.mapToProjection(userEntity);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/update")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().string(Matchers.containsString("TEST COMPANY NAME NEW")));

    }

    @Test
    void testDelete() throws Exception{
        UserEntity userEntity = userRepository.findAll().get(0);

        String url = "/deleteUser?id=" + userEntity.getUid();

        this.mockMvc
                .perform(delete(API_USER+ url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("User successfully deleted")));

        this.mockMvc
                .perform(delete(API_USER + "/deleteUser?id=-6"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString("User not found")));
    }

}
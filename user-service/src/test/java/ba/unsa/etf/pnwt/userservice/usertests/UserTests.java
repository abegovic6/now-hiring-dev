package ba.unsa.etf.pnwt.userservice.usertests;
/*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.controller.UserController;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserTests {
    private static final String API_USER = "/user/";
    @Autowired private MockMvc mockMvc;
    @Autowired private UserController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
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
        this.mockMvc
                .perform(get(API_USER + "/id/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("\"id\":1")));

        this.mockMvc
                .perform(get(API_USER + "/id/-1"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.USER_NOT_FOUND_WITH_ID)));
    }

    @Test
    void testUserByEmail() throws Exception {
        this.mockMvc
                .perform(get(API_USER + "/email/abegovic6@etf.unsa.ba"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("\"email\":\"abegovic6@etf.unsa.ba\"")));

        this.mockMvc
                .perform(get(API_USER + "/email/nekirandommail@test.com"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.USER_NOT_FOUND_WITH_EMAIL)));
    }

    @Test
    void testUserByUUID() throws Exception {
        this.mockMvc
                .perform(get(API_USER + "/uuid/1111"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("\"uuid\":\"1111\"")));

        this.mockMvc
                .perform(get(API_USER + "/uuid/1234"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.USER_NOT_FOUND_WITH_UUID)));
    }
}
*/
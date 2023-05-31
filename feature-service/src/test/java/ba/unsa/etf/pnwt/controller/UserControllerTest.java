package ba.unsa.etf.pnwt.controller;
/*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ba.unsa.etf.pnwt.constants.ApiResponseMessages;
import static org.assertj.core.api.Assertions.assertThat;

import ba.unsa.etf.pnwt.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private static final String API_USER = "/user/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController controller;

    private static UserDTO user;

    @BeforeEach
    void setUpMockUsers() {
        user = new UserDTO();
        user.setId(111);
        user.setUuid("useruuidtest1");
        user.setEmail("elmaleka@etf.unsa.ba");
    }

    @Test
    void getAllUsers() throws Exception{
        this.mockMvc
            .perform(get(API_USER + "all"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void emailMissing() throws Exception {
        user.setEmail(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_EMAIL)));
    }

    @Test
    void emailHasWrongFormat() throws Exception {
        user.setEmail("test");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.EMAIL_HAS_WRONG_FORMAT)));
    }

    @Test
    void uuidMissing() throws Exception {
        user.setUuid(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_UUID)));
    }

    @Test
    void getUserById() throws Exception{
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
    void getUserByUuid() throws Exception{
        this.mockMvc
                .perform(get(API_USER + "/uuid/uuid2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("\"uuid\":\"uuid2\"")));

        this.mockMvc
                .perform(get(API_USER + "/uuid/1234"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.USER_NOT_FOUND_WITH_UUID)));
    }

    @Test
    void getUserByEmail() throws Exception{
        this.mockMvc
                .perform(get(API_USER + "/email/eleka1@etf.unsa.ba"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("\"email\":\"eleka1@etf.unsa.ba\"")));

        this.mockMvc
                .perform(get(API_USER + "/email/nekirandommail@test.com"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.USER_NOT_FOUND_WITH_EMAIL)));
    }

}

 */
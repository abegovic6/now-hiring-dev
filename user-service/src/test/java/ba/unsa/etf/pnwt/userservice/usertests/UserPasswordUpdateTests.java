package ba.unsa.etf.pnwt.userservice.usertests;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.dto.PasswordDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserPasswordUpdateTests {

    private static final String API_USER = "/user/1111/passwordUpdate";
    private static PasswordDTO passwordDTO;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUpPassword() {
        passwordDTO =  new PasswordDTO("Sifra0001", "Sifra0002");
    }

    @Test
    void missingNewPassword() throws Exception {
        passwordDTO.setNewPassword(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.put(API_USER)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(passwordDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_PASSWORD)));
    }

    @Test
    void passwordLengthWrong() throws Exception {
        passwordDTO.setNewPassword(StringUtils.repeat("*", 26));
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.put(API_USER)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(passwordDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.PASSWORD_LENGTH)));

        passwordDTO.setNewPassword(StringUtils.repeat("*", 7));
        requestBuilder =
                MockMvcRequestBuilders.put(API_USER)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(passwordDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.PASSWORD_LENGTH)));
    }

    @Test
    void passwordHasWrongFormat() throws Exception {
        passwordDTO.setNewPassword("testpassword");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.put(API_USER)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(passwordDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.PASSWORD_TO_WEAK)));
    }

    @Test
    void oldPasswordIsWrong() throws Exception {
        passwordDTO.setOldPassword("testpassword");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.put(API_USER)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(passwordDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.PASSWORDS_DO_NOT_MATCH)));
    }

    @Test
    void successfulChanged() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.put(API_USER)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(passwordDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }
}

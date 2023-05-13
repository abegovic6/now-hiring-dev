package ba.unsa.etf.pnwt.userservice.usertests;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.constants.Role;
import ba.unsa.etf.pnwt.userservice.dto.LocationDTO;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
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
public class UserCreationTests {
    private static final String API_USER = "user/";
    private static UserDTO privateMockUser;
    private static UserDTO companyMockUser;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUpMockUsers() {
        privateMockUser = new UserDTO();
        privateMockUser.setEmail("test1@test.com");
        privateMockUser.setFirstName("Test");
        privateMockUser.setLastName("Test");
        privateMockUser.setDescription("Test");
        privateMockUser.setLocationDTO(new LocationDTO("city", "country"));
        privateMockUser.setPassword("Sifra0001");
        privateMockUser.setUserType(Role.PRIVATE);

        companyMockUser = new UserDTO();
        companyMockUser.setEmail("test2@test.com");
        companyMockUser.setCompanyName("Test");
        companyMockUser.setDescription("Test");
        companyMockUser.setLocationDTO(new LocationDTO("city", "country"));
        companyMockUser.setPassword("Sifra0001");
        companyMockUser.setUserType(Role.COMPANY);
    }

    @Test
    void userHasNoUserType() throws Exception {
        privateMockUser.setUserType(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_ACCOUNT_TYPE)));
    }

    @Test
    void emailMissing() throws Exception {
        privateMockUser.setEmail(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_EMAIL)));
    }

    @Test
    void emailTooLong() throws Exception {
        privateMockUser.setEmail(StringUtils.repeat("*", 51));
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.EMAIL_TOO_LONG)));
    }

    @Test
    void emailHasWrongFormat() throws Exception {
        privateMockUser.setEmail("test");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.EMAIL_HAS_WRONG_FORMAT)));
    }

    @Test
    void passwordMissing() throws Exception {
        privateMockUser.setPassword(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_PASSWORD)));
    }

    @Test
    void passwordLengthWrong() throws Exception {
        privateMockUser.setPassword(StringUtils.repeat("*", 26));
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.PASSWORD_LENGTH)));

        privateMockUser.setPassword(StringUtils.repeat("*", 7));
        requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.PASSWORD_LENGTH)));
    }

    @Test
    void passwordHasWrongFormat() throws Exception {
        privateMockUser.setPassword("testpassword");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.PASSWORD_TO_WEAK)));
    }

    @Test
    void descriptionTooLong() throws Exception {
        privateMockUser.setDescription(StringUtils.repeat("*", 260));
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.DESCRIPTION_TO_LONG)));
    }

    @Test
    void privateProfileAssertions() throws Exception {
        privateMockUser.setCompanyName("test");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.CAN_NOT_DECLARE_NAME_WITH_PRIVATE_ACCOUNT)));
    }

    @Test
    void companyProfileAssertions1() throws Exception {
        companyMockUser.setFirstName("test");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(companyMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.CAN_NOT_DECLARE_NAME_WITH_COMPANY_ACCOUNT)));
    }

    @Test
    void companyProfileAssertions2() throws Exception {
        companyMockUser.setLastName("test");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(companyMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.CAN_NOT_DECLARE_NAME_WITH_COMPANY_ACCOUNT)));
    }

    @Test
    void companyNameTooLong() throws Exception {
        companyMockUser.setCompanyName(StringUtils.repeat("*", 51));
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(companyMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.NAME_TOO_LONG)));
    }

    @Test
    void firstNameTooLong() throws Exception {
        privateMockUser.setFirstName(StringUtils.repeat("*", 51));
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.FIRST_NAME_TOO_LONG)));
    }

    @Test
    void lastNameTooLong() throws Exception {
        privateMockUser.setLastName(StringUtils.repeat("*", 51));
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.LAST_NAME_TOO_LONG)));
    }

    @Test
    void missingLocation() throws Exception {
        privateMockUser.setLocationDTO(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_CITY)));
    }

    @Test
    void missingCity() throws Exception {
        privateMockUser.setLocationDTO(new LocationDTO(null, "country"));
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_CITY)));
    }

    @Test
    void missingCountry() throws Exception {
        privateMockUser.setLocationDTO(new LocationDTO("city", null));
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_COUNTRY)));
    }

    @Test
    void createPrivateUserSuccessful() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(privateMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201));
    }

    @Test
    void createCompanyUserSuccessful() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/upload")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(companyMockUser));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201));
    }

}

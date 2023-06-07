package ba.unsa.etf.featureservice.controller;
/*
import ba.unsa.etf.pnwt.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.dto.ExperienceDTO;
import ba.unsa.etf.pnwt.dto.UserDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class EducationControllerTest {
    private static final String API_USER = "education/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EducationController educationController;

    private static EducationDTO educationDTO;

    @BeforeEach
    void setUpMockUsers() {
        UserDTO user = new UserDTO();
        user.setId(111);
        user.setUuid("useruuidtest1");
        user.setEmail("elmaleka@etf.unsa.ba");

        educationDTO = new EducationDTO();
        educationDTO.setUser(user);
        educationDTO.setTitle("title");
        educationDTO.setDescription("description");
        educationDTO.setStartingMonth(1);
        educationDTO.setEndYear(2022);
        educationDTO.setEndMonth(12);
        educationDTO.setEndYear(2022);
    }

    @Test
    void getAllExperience() throws Exception{
        this.mockMvc
                .perform(get(API_USER + "all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void titleShort() throws Exception {
        educationDTO.setTitle("t");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(educationDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.TITLE_SHORT)));
    }

    @Test
    void monthsSmallerMin() throws Exception {
        educationDTO.setEndMonth(0);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(educationDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MONTHS_SMALLER)));
    }

    @Test
    void monthsLargerMax() throws Exception {
        educationDTO.setEndMonth(13);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(educationDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MONTHS_LARGER)));
    }

    @Test
    void getExperienceForUser() throws Exception{
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(API_USER + "/user/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(educationDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getExperienceForUserNotFound() throws Exception{
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(API_USER + "/user/0")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(educationDTO));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_USER)));
    }

    @Test
    void addNewExperience() {
    }

    @Test
    void getExperienceForUser2() {
    }

    @Test
    void deleteExperience() {
    }
}

 */
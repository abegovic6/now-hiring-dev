package ba.unsa.etf.featureservice.controller;
/*
import ba.unsa.etf.pnwt.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.dto.SkillDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SkillControllerTest {
    private static final String API_USER = "/skill/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SkillController skillController;

    private static SkillDTO skill;

    @BeforeEach
    void setUpMockUsers() {
        UserDTO user = new UserDTO();
        user.setId(111);
        user.setUuid("useruuidtest1");
        user.setEmail("elmaleka@etf.unsa.ba");

        skill = new SkillDTO();
        skill.setUser(user);
        skill.setTitle("title");
        skill.setId(111);
    }

    @Test
    void getAllSkills() throws Exception{
        this.mockMvc
                .perform(get(API_USER + "all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addNewSkill() {
    }

    @Test
    void titleMissing() throws Exception {
        skill.setTitle(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(skill));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_TITLE)));
    }

    @Test
    void titleShort() throws Exception {
        skill.setTitle("t");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_USER + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(skill));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.TITLE_SHORT)));
    }

    @Test
    void getSkillForUser() throws Exception{
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(API_USER + "/user/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(skill));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getSkillForUserNotFound() throws Exception{
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(API_USER + "/user/0")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(skill));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.MISSING_USER)));
    }

    @Test
    void deleteSkill() {
    }
}
 */
package ba.unsa.etf.pnwt.jobsservice.controller;

import ba.unsa.etf.pnwt.jobsservice.dto.ApplicationDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.ApplicationEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import ba.unsa.etf.pnwt.jobsservice.repository.ApplicationRepository;
import ba.unsa.etf.pnwt.jobsservice.repository.JobRepository;
import ba.unsa.etf.pnwt.jobsservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ApplicationControllerTest {

    private static final String API_APPLICATION = "application/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationController applicationController;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public static ApplicationDTO application;



    @BeforeEach
    void SetUpMockApplication(){

        JobEntity job = jobRepository.findAll().get(0);
        List<UserEntity> users = userRepository.findAll();
        UserEntity user = users.stream().filter(userx -> Objects.equals(userx.getUserType(), "PRIVATE")).toList().get(0);

        application = new ApplicationDTO();
        application.setJobId(job.getId());
        application.setUserId(user.getUid());
        application.setCoverLetter("TestCover");
    }


    @Test
    public void contextLoads() throws Exception {
        assertThat(applicationController).isNotNull();
    }

    @Test
    void testGetAll() throws Exception {
        this.mockMvc
                .perform(get(API_APPLICATION + "all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testApplicationById() throws Exception {

        ApplicationEntity applicationEntity = applicationRepository.findAll().get(0);
        String url = "/get?id=" + applicationEntity.getId().toString();
        String expect = "\"id\":" + applicationEntity.getId().toString();

        this.mockMvc
                .perform(get(API_APPLICATION + url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(expect)));

        this.mockMvc
                .perform(get(API_APPLICATION + "/id?id=-6"))
                .andDo(print())
                .andExpect(status().is(404));
    }

    @Test
    void testAdd() throws Exception{
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_APPLICATION + "/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(application));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201));

    }

    @Test
    void testAddNotValidJob() throws Exception{

        application.setJobId(null);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_APPLICATION + "/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(application));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().string(Matchers.containsString("Job ID not provided")));

    }

    @Test
    void testAddNotValidUser() throws Exception{

        application.setUserId(null);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_APPLICATION + "/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(application));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().string(Matchers.containsString("User ID not provided")));

    }

    @Test
    void testGetAppsForUser() throws Exception{

        List<UserEntity> users = userRepository.findAll();
        UserEntity user = users.stream().filter(userx -> Objects.equals(userx.getUserType(), "PRIVATE")).toList().get(0);

        String url = "getAppsForUser?id=" + user.getUid();

        this.mockMvc
                .perform(get(API_APPLICATION + url))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void testGetAppsForJob() throws Exception{

        JobEntity job = jobRepository.findAll().get(0);

        String url = "getAppsForJob?id=" + job.getId();

        this.mockMvc
                .perform(get(API_APPLICATION + url))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void testDelete() throws Exception{
        ApplicationEntity applicationEntity = applicationRepository.findAll().get(0);

        String url = "/deleteApplication?id=" + applicationEntity.getId().toString();

        this.mockMvc
                .perform(delete(API_APPLICATION + url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Application successfully deleted")));

        this.mockMvc
                .perform(delete(API_APPLICATION + "/deleteApplication?id=-6"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString("Application with provided ID not found")));
    }
}
package ba.unsa.etf.pnwt.jobsservice.controller;

import ba.unsa.etf.pnwt.jobsservice.controller.JobController;
import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.UserDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import ba.unsa.etf.pnwt.jobsservice.mapper.JobMapper;
import ba.unsa.etf.pnwt.jobsservice.mapper.UserMapper;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class JobControllerTest {

    private static final String API_JOB = "/job/";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JobController jobController;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    private static JobDTO job;
    private static UserDTO company;

    @BeforeEach
    void setUpMocks(){

        company = new UserDTO();
        company.setUserType("COMPANY");
        company.setCompanyName("Test Company");
        company.setEmail("testcompany@gmail.com");
        company.setUid("SIFRATEST");

        userRepository.save(UserMapper.mapToEntity(company));

        job = new JobDTO();
        job.setCompanyId("SIFRATEST");
        job.setTitle("TESTTITLE");
        job.setLocation("TESTLOCATION");
        job.setValidTo(LocalDate.now());
    }

    @AfterEach
    void deleteMockCompany(){
        UserEntity user = userRepository.findUserEntityByUid("SIFRATEST");
        userRepository.deleteById((long) user.getId());
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(jobController).isNotNull();
    }

    @Test
    void testGetAll() throws Exception {
        this.mockMvc
                .perform(get(API_JOB + "all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testJobById() throws Exception {

        JobEntity jobEntity = jobRepository.findAll().get(0);
        String url = "/get?id=" + jobEntity.getId().toString();
        String expect = "\"id\":" + jobEntity.getId().toString();

        this.mockMvc
                .perform(get(API_JOB + url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(expect)));

        this.mockMvc
                .perform(get(API_JOB + "/id?id=-6"))
                .andDo(print())
                .andExpect(status().is(404));
    }

    @Test
    void testAdd() throws Exception{
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_JOB + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(job));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201));

    }

    @Test
    void testAddNotValidTitle() throws Exception{
        job.setTitle(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_JOB + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(job));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().string(Matchers.containsString("Job title must be specified")));

    }

    @Test
    void testAddNotValidLocation() throws Exception{
        job.setLocation(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_JOB + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(job));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().string(Matchers.containsString("Job location must be specified")));

    }

    @Test
    void testAddNotValidCompany() throws Exception{
        job.setCompanyId(null);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(API_JOB + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(job));

        this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().string(Matchers.containsString("Company ID must be specified")));

    }

    @Test
    void testUpdate() throws Exception{

        JobEntity jobEntity = jobRepository.findAll().get(0);
        JobDTO jobDTO = JobMapper.mapToProjection(jobEntity);
        jobDTO.setLocation("Test Location");

        RequestBuilder requestBuilder1 =
                MockMvcRequestBuilders.post(API_JOB + "/update")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(jobDTO));

        this.mockMvc
                .perform(requestBuilder1)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().string(Matchers.containsString("Test Location")));
    }

    @Test
    void testDelete() throws Exception{
        JobEntity jobEntity = jobRepository.findAll().get(0);
        String url = "/deleteJob?id=" + jobEntity.getId().toString();
        this.mockMvc
                .perform(delete(API_JOB + url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Job successfully deleted")));

        this.mockMvc
                .perform(delete(API_JOB + "/deleteJob?id=-6"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString("Job not found")));
    }

}
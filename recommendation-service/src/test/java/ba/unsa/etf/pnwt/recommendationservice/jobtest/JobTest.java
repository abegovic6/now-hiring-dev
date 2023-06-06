package ba.unsa.etf.pnwt.recommendationservice.jobtest;
/*
import ba.unsa.etf.pnwt.recommendationservice.entity.JobEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.repository.JobRepository;
import ba.unsa.etf.pnwt.recommendationservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class JobTest {
    private static final String API_JOB = "/job/";
    private static  JobEntity mockJob;
    private static UserEntity mockUser;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository  userRepository;
    @Autowired
    JobRepository jobRepository;
    @BeforeEach
    void setUpMockUsers() {
        mockUser = new UserEntity();
        mockUser.setEmail("test1@gmail.com");
        mockUser.setName("test");

        userRepository.save(mockUser);
        mockJob = new JobEntity();
        mockJob.setDescription("Opis");
        mockJob.setName("Profesor");
        mockJob.setUserEntity(mockUser);

    }
    @AfterEach
    void deleteMock(){
        jobRepository.delete(mockJob);
        userRepository.delete(mockUser);
    }
    @Test
    void testGetAll() throws Exception {
        this.mockMvc
                .perform(get(API_JOB))
                .andDo(print())
                .andExpect(status().isOk());
    }



}


 */
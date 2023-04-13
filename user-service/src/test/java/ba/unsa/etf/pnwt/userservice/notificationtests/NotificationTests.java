package ba.unsa.etf.pnwt.userservice.notificationtests;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.constants.NotificationType;
import ba.unsa.etf.pnwt.userservice.constants.ServerConfigValue;
import ba.unsa.etf.pnwt.userservice.service.NotificationService;
import ba.unsa.etf.pnwt.userservice.service.ServerConfigService;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class NotificationTests {
    private final String API_URL = "/api/notification/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServerConfigService serverConfigService;

    @BeforeEach
    void disableEmails() {
        when(serverConfigService.getBooleanValue(ServerConfigValue.EMAIL_SENDING_ACTIVE))
                .thenReturn(false);
    }

    @Test
    void companyCreatedAJobTest() throws Exception {
        this.mockMvc
                .perform(post(API_URL + "5555/created-job"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(NotificationType.JOB_CREATED.toString())));
    }

    @Test
    void userAppliedForJobTest() throws Exception {
        this.mockMvc
                .perform(post(API_URL + "1111/user-applied-for-job/5555"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(NotificationType.JOB_APPLICATION.toString())))
                .andExpect(content().string(Matchers.containsString("5555")));
    }

    @Test
    void userWroteReviewTest() throws Exception {
        this.mockMvc
                .perform(post(API_URL + "1111/review/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(NotificationType.REVIEW.toString())))
                .andExpect(content().string(Matchers.containsString("2222")));
    }
}

package ba.unsa.etf.pnwt.userservice.connectiontests;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.constants.NotificationType;
import ba.unsa.etf.pnwt.userservice.constants.ServerConfigValue;
import ba.unsa.etf.pnwt.userservice.service.ServerConfigService;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ConnectionTests {

    private final String API_URL = "/api/connection/";

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
    void getAllConnections() throws Exception {
        this.mockMvc
                .perform(get(API_URL + "5555/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("1111")));
    }

    @Test
    void startConnection() throws Exception {
        this.mockMvc
                .perform(post(API_URL + "5555/start/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("2222")))
                .andExpect(content().string(Matchers.containsString(NotificationType.CONNECTION.toString())));
    }

    @Test
    void approveConnection() throws Exception {
        this.mockMvc
                .perform(post(API_URL + "5555/start/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("2222")))
                .andExpect(content().string(Matchers.containsString(NotificationType.CONNECTION.toString())));

        this.mockMvc
                .perform(put(API_URL + "5555/accept/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.CONNECTION_ACCEPTED)));
    }

    @Test
    void approveConnectionWithoutRequest() throws Exception {
        this.mockMvc
                .perform(put(API_URL + "5555/accept/2222"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.CONNECTION_FOR_ACCEPTANCE_DOES_NOT_EXISTS)));
    }

    @Test
    void rejectConnection() throws Exception {
        this.mockMvc
                .perform(post(API_URL + "5555/start/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("2222")))
                .andExpect(content().string(Matchers.containsString(NotificationType.CONNECTION.toString())));

        this.mockMvc
                .perform(put(API_URL + "5555/reject/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.CONNECTION_REJECTED)));
    }

    @Test
    void rejectConnectionWithoutRequest() throws Exception {
        this.mockMvc
                .perform(put(API_URL + "5555/reject/2222"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.CONNECTION_FOR_REJECTING_DOES_NOT_EXISTS)));
    }

    @Test
    void startDoubleConnectionRequest() throws Exception {
        this.mockMvc
                .perform(post(API_URL + "5555/start/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("2222")))
                .andExpect(content().string(Matchers.containsString(NotificationType.CONNECTION.toString())));

        this.mockMvc
                .perform(post(API_URL + "5555/start/2222"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.CONNECTION_ALREADY_EXISTS)));
    }

    @Test
    void getAllConnectionsAfterAcceptance() throws Exception {
        this.mockMvc
                .perform(post(API_URL + "5555/start/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("2222")))
                .andExpect(content().string(Matchers.containsString(NotificationType.CONNECTION.toString())));

        this.mockMvc
                .perform(put(API_URL + "5555/accept/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(ApiResponseMessages.CONNECTION_ACCEPTED)));

        this.mockMvc
                .perform(get(API_URL + "5555/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("1111")))
                .andExpect(content().string(Matchers.containsString("2222")));
    }
}

package ba.unsa.etf.pnwt.userservice.dto;

import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String uuid;

    public AuthResponse(String accessToken, String refreshToken, String uuid) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.uuid = uuid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
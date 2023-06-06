package ba.unsa.etf.pnwt.userservice.constants;

public enum ServerConfigValue {
    EMAIL_SENDING_ACTIVE("false");

    private final String value;

    ServerConfigValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

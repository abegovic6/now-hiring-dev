package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.ServerConfigValue;

public interface ServerConfigService {

    boolean getBooleanValue(ServerConfigValue value);
}

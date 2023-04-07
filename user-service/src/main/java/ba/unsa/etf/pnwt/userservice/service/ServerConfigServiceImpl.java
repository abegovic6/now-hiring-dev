package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.ServerConfigValue;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

@Service
public class ServerConfigServiceImpl implements ServerConfigService{

    @Override
    public boolean getBooleanValue(ServerConfigValue value) {
        return BooleanUtils.toBoolean(value.getValue());
    }
}

package ba.unsa.etf.featureservice.service;

import ba.unsa.etf.featureservice.dto.ProfileDTO;

public interface ProfileService {
    ProfileDTO getUserProfile(String uuid);
}

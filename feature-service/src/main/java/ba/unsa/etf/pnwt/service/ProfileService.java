package ba.unsa.etf.pnwt.service;

import ba.unsa.etf.pnwt.dto.ProfileDTO;

public interface ProfileService {
    ProfileDTO getUserProfile(String uuid);
}

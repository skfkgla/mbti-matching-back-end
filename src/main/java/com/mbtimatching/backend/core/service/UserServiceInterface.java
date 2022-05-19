package com.mbtimatching.backend.core.service;

import com.mbtimatching.backend.web.dto.RequestUser;
import com.mbtimatching.backend.web.dto.ResponseUser;

import java.util.Optional;

public interface UserServiceInterface {
    void register(RequestUser.Register registerDto);
    Optional<ResponseUser.Login> login(RequestUser.Login loginDto);
    String createRefreshToken(String id);
    String createAccessToken(String id);
}

package com.mbtimatching.backend.core.service;

import com.mbtimatching.backend.web.dto.RequestUser;

public interface UserServiceInterface {
    void register(RequestUser.Register registerDto);
}

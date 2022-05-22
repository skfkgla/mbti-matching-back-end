package com.mbtimatching.backend.core.service;

import com.mbtimatching.backend.web.dto.ResponseMatching;

import java.util.Optional;

public interface MatchingServiceInterface {
    Optional<ResponseMatching.Random> randomMatching(String userId);
}

package com.mbtimatching.backend.core.service;

import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.web.dto.RequestMatching;
import com.mbtimatching.backend.web.dto.ResponseMatching;

import java.util.Optional;

public interface MatchingServiceInterface {
    Optional<ResponseMatching.Select> randomMatching(String userId);
    Optional<ResponseMatching.Select> selectMatching(String userId, MbtiType mbti);
}

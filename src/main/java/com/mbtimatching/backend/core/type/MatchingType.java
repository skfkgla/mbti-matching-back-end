package com.mbtimatching.backend.core.type;

import lombok.Getter;

import java.util.List;

@Getter
public enum MatchingType {
    //분석형
    INTJ(MbtiType.ENFP,MbtiType.ENTJ),
    INTP(MbtiType.ENTJ,MbtiType.ESTJ),
    ENTJ(MbtiType.INTP,MbtiType.ISTP),
    ENTP(MbtiType.INFJ,MbtiType.INTJ),
    //외교형
    INFJ(MbtiType.ENFP,MbtiType.ENTP),
    INFP(MbtiType.ENTJ,MbtiType.ENFJ),
    ENFJ(MbtiType.INFP,MbtiType.ISFP),
    ENFP(MbtiType.INFJ,MbtiType.INTJ),
    //관리자형
    ISTJ(MbtiType.ESFP,MbtiType.ESFJ),
    ISFJ(MbtiType.ESFP,MbtiType.ESTP),
    ESTJ(MbtiType.INTP,MbtiType.ISTP),
    ESFJ(MbtiType.ISFP,MbtiType.ISTP),
    //탐험가형
    ISTP(MbtiType.ESFJ,MbtiType.ESTJ),
    ISFP(MbtiType.ENFJ,MbtiType.ESTJ),
    ESTP(MbtiType.ISFJ,MbtiType.ENTP),
    ESFP(MbtiType.ISFJ,MbtiType.ISTJ);

    private MbtiType first;
    private MbtiType second;
    MatchingType(MbtiType first, MbtiType second){
        this.first = first;
        this.second = second;
    }
}

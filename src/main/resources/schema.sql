DROP TABLE IF EXISTS `member`;
DROP TABLE IF EXISTS `chat_room`;
DROP TABLE IF EXISTS `message`;

CREATE TABLE member(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    mbti enum('INTJ','INTP','ENTJ','ENTP','INFJ','INFP','ENFJ','ENFP','ISTJ','ISFJ','ESTJ','ESFJ','ISTP','ISFP','ESTP','ESFP') NOT NULL,
    image_url VARCHAR(500),
    nickname VARCHAR(100) NOT NULL,
    salt VARCHAR(255),
    refresh_token VARCHAR(255)
);
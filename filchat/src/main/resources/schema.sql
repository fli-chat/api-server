CREATE TABLE `users`
(
    id          bigint auto_increment primary key comment 'ID',
    user_token  varchar(50) not null comment 'user_token',
    nickname    varchar(30) not null comment '닉네임',
    profile_url varchar(255) comment '프로필 사진',
    identifier  varchar(32) not null comment 'oauth 사용자를 식별하는데 사용되는 ID',
    provider    varchar(16) not null comment '로그인 플랫폼',
    gender      varchar(5)  not null comment '성별',
    age_group   tinyint(1)  not null comment '연령대',
    created_at  timestamp   not null comment '가입일자',
    updated_at  timestamp   not null comment '수정일자',
    is_active   boolean     not null comment '탈퇴여부'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '사용자';

CREATE TABLE `videos`
(
    id           bigint auto_increment primary key comment 'ID',
    title        varchar(50)  not null comment '작품명',
    synopsis     text         not null comment '줄거리',
    release_year date         not null comment '출시년도',
    poster       varchar(255) not null comment '포스터',
    characters   varchar(300) not null comment '등장인물'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '작품';

CREATE TABLE `tokens`
(
    id            bigint auto_increment primary key comment 'ID',
    user_token    varchar(50)  not null comment 'user_token',
    refresh_token varchar(255) not null comment '리프레시 토큰'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '토큰';

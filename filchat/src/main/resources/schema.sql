CREATE TABLE `user`.`user`
(
    id          bigint auto_increment primary key comment 'ID',
    user_token  varchar(50) not null comment 'user_token',
    nickname    varchar(30) not null comment '닉네임',
    profile_url varchar(255) comment '프로필 사진',
    gender      char(1)     not null comment '성별',
    age_group   tinyint(1)  not null comment '연령대',
    created_at  timestamp   not null comment '가입일자',
    updated_at  timestamp   not null comment '수정일자',
    is_active   boolean     not null comment '탈퇴여부'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '사용자';

CREATE TABLE `video`.`videos`
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

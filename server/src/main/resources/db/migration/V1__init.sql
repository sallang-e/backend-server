create table cycle
(
    created_at datetime(6),
    id         bigint not null auto_increment,
    updated_at datetime(6),
    status     enum ('AVAILABLE','BROKEN','RENT'),
    primary key (id)
) engine=InnoDB;

create table rent_history
(
    created_at datetime(6),
    cycle_id   bigint,
    id         bigint not null auto_increment,
    updated_at datetime(6),
    user_id    bigint,
    type       enum ('RENT','RETURN'),
    primary key (id)
) engine=InnoDB;

create table users
(
    left_rent_count integer,
    created_at      datetime(6),
    id              bigint not null auto_increment,
    oauth_id        bigint,
    updated_at      datetime(6),
    name            varchar(255),
    oauth_provider  enum ('GOOGLE','KAKAO'),
    primary key (id)
) engine=InnoDB;

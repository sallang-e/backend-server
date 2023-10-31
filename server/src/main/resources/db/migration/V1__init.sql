create table cycle
(
    id         bigint not null auto_increment,
    status     enum ('AVAILABLE','BROKEN','RENT'),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id)
) engine=InnoDB;

create table rent_history
(
    id         bigint not null auto_increment,
    user_id    bigint,
    cycle_id   bigint,
    type       enum ('RENT','RETURN'),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id)
) engine=InnoDB;

create table users
(
    id              bigint not null auto_increment,
    name            varchar(255),
    oauth_provider  enum ('GOOGLE','KAKAO'),
    oauth_id        bigint,
    left_rent_count integer,
    created_at      datetime(6),
    updated_at      datetime(6),
    primary key (id)
) engine=InnoDB;

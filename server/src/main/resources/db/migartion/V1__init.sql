create table cycle
(
    status tinyint check (status between 0 and 2),
    id     bigint not null auto_increment,
    primary key (id)
) engine=InnoDB;

create table rent_history
(
    type     tinyint check (type between 0 and 1),
    cycle_id bigint,
    id       bigint not null auto_increment,
    user_id  bigint,
    primary key (id)
) engine=InnoDB;

create table user
(
    left_rent_count integer,
    id              bigint not null auto_increment,
    oauth_id        bigint,
    name            varchar(255),
    oauth_provider  enum ('GOOGLE','KAKAO'),
    primary key (id)
) engine=InnoDB;

create table if not exists note(
    id bigint PRIMARY KEY AUTO_INCREMENT not null,
    title varchar(200) not null,
    content varchar(200)
);
create table repositories
(
  id      varchar(36)  not null
    constraint repositories_pk
      primary key,
  name    varchar(100) not null,
  address varchar(512) not null
);

alter table repositories
  owner to postgres;

create table files
(
  id            varchar(36)  not null
    constraint files_pk
      primary key,
  name          varchar(512) not null,
  repository_id varchar(36)  not null
    constraint files_repositories_id_fk
      references repositories
);

alter table files
  owner to postgres;

create table files_words
(
  file_id varchar(36)  not null
    constraint files_words_files_id_fk
      references files,
  word    varchar(256) not null,
  constraint files_words_pk
    primary key (file_id, word)
);

alter table files_words
  owner to postgres;

create index files_words_word_index
  on files_words (word);

create table words
(
  sequence varchar(256) not null
    constraint words_pk
      primary key
);

alter table words
  owner to postgres;

create index words_sequence_index
  on words (sequence);
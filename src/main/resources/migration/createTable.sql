
create database `ticketLinus` CHARACTER SET utf8 COLLATE utf8_general_ci;
use ticketLinus;

create table Board (
boardSrl int(11) auto_increment,
title varchar(100) not null,
contents text,
writer varchar(100),
password varchar(250),
regDttm timestamp default CURRENT_TIMESTAMP,
updDttm timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY(boardSrl)
);


create table Comment(
  commentSrl int(11) auto_increment,
  boardSrl int(11) not null,
  contents varchar(1000) not null,
  writer varchar(25) not null,
  regDttm timestamp default CURRENT_TIMESTAMP,
  updDttm timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  primary key(commentSrl),
  FOREIGN KEY (boardSrl) REFERENCES Board(boardSrl)
);
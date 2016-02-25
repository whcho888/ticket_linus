
create database `ticketLinus` CHARACTER SET utf8 COLLATE utf8_general_ci;

create table `ticketLinus`.`board` (
boardSrl int(11) auto_increment,
title varchar(100) not null,
contents text,
writer varchar(100),
password varchar(250),
regDttm timestamp default CURRENT_TIMESTAMP,
updDttm timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY(boardSrl)
)


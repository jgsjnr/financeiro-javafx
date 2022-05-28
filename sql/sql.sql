create database if not exists fin;

use fin;

create table usuarios(
    id int not null primary key auto_increment,
    usuario varchar(25) not null,
    senha varchar(25) not null
);

create table depara_padrao(
    id int not null auto_increment,
    tipo varchar(25) not null,
    depara int not null primary key,
    key(id)
);

create table valores(
    id int not null primary key auto_increment,
    preco numeric(10, 2) not null,
    data_compra date not null,
    fk_id int not null,
    fk_depara int not null,
    foreign key(fk_id) references usuarios(id),
    foreign key(fk_depara) references depara_padrao(depara)
);
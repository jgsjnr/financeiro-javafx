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



insert into usuarios(usuario, senha) values('admin', 'admin');
insert into usuarios(usuario, senha) values('usuario', 'usuario');
insert into depara_padrao(tipo, depara) values('Compra', 201);
insert into depara_padrao(tipo, depara) values('Gasto', 301);
insert into depara_padrao(tipo, depara) values('Investimento', 401);
insert into depara_padrao(tipo, depara) values('Despeza', 501);
insert into depara_padrao(tipo, depara) values('Drogas', 601);
insert into valores(preco, data_compra, fk_id, fk_depara) values(2012.02, '2021-01-02', 1, 201);
insert into valores(preco, data_compra, fk_id, fk_depara) values(2012.02, '2022-01-02', 1, 301);
insert into valores(preco, data_compra, fk_id, fk_depara) values(6042.02, '2020-01-02', 1, 301);
insert into valores(preco, data_compra, fk_id, fk_depara) values(1000.02, '2019-01-02', 1, 201);
insert into valores(preco, data_compra, fk_id, fk_depara) values(500.02, '2018-01-02', 1, 401);
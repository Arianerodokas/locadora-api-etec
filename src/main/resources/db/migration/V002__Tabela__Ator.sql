Create Table Ator (
    id int not null primary key auto_increment,
    nomeator varchar (100)
);

insert into ator(nomeator) values ("Terry Crews");

Create Table Filmes(
    id bigint not null primary key auto_increment,
    nomefilme varchar (140),
    idgenero int not null,
    idator int not null
);

insert into Filmes(nomefilme, idgenero, idator) values ('Brooklyn Nine', 3, 1);
insert into Filmes(nomefilme, idgenero, idator) values ('White Chicks', 2, 1);
insert into Filmes(nomefilme, idgenero, idator) values ('Everybody hates Chris', 1, 1);

Alter table Filmes add constraint FK_genero_filme foreign key (idgenero) references genero(id);
Alter table Filmes add constraint FK_ator_filme foreign key (idator) references ator(id);
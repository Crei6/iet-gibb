create database mp3_verwaltung;

use mp3_verwaltung;

create table Benutzer(
	BenutzerID int identity(1, 1) NOT NULL,
	Benutzername varchar(20) NOT NULL,
	Passwort varchar(20),
	Vorname varchar(20),
	Nachname varchar(20),
	constraint PK_Benutzer primary key (BenutzerID)
);
create index idx_Benutzer on Benutzer (Benutzername);

create table Interpret(
	InterpretID int identity(1, 1) NOT NULL,
	Name varchar(20) NOT NULL,
	constraint PK_Interpret primary key (InterpretID)
);
create index idx_Interpret on Interpret (Name);

create table Album(
	AlbumID int identity(1, 1) NOT NULL,
	Titel varchar(20) NOT NULL,
	UNC_Verlinkung varchar(20),
	constraint PK_Album primary key (AlbumID)
);
create index idx_Album on Album (Titel);

create table Playlist(
	PlaylistID int identity(1, 1) NOT NULL,
	Titel varchar(20) NOT NULL,
	BenutzerID int NOT NULL,
	constraint PK_Playlist primary key (PlaylistID),
	constraint FK_Benutzer_Playlist foreign key (BenutzerID)
	references Benutzer(BenutzerID) on delete cascade on update no action
);
create index idx_Playlist on Playlist (Titel);

create table Genre(
	GenreID int identity(1, 1) NOT NULL,
	Genre varchar(20),
	constraint PK_Genre primary key (GenreID)
);
create index idx_Genre on Genre (Genre);

create table Herkunftsland(
	HerkunftslandID int identity(1, 1) NOT NULL,
	Land varchar(20),
	constraint PK_Herkunftsland primary key (HerkunftslandID)
);
create index idx_Herkunftsland on Herkunftsland (land);

create table Musiktitel(
	MusiktitelID int identity(1, 1) NOT NULL,
	Titel varchar(20),
	Ausgabejahr int,
	Kaufpreis int,
	Kaufdatum date,
	InterpretID int,
	Dauer_in_Sekunden int,
	GenreID int,
	HerkunftslandID int,
	Persoenliche_Bewertung varchar(100),
	BenutzerID int,
	constraint PK_MusiktitelID primary key (MusiktitelID),
	constraint FK_Interpret_Musiktitel foreign key (InterpretID)
	references Interpret(InterpretID) on delete cascade on update no action,
	constraint FK_Benutzer_Musiktitel foreign key (BenutzerID)
	references Benutzer(BenutzerID) on delete cascade on update no action,
	constraint FK_Genre_Musiktitel foreign key (GenreID),
	references Genre(GenreID) on delete cascade on update no action,
	constraint FK_Herkunftsland_Musiktitel foreign key (HerkunftslandID),
	references Herkunftsland(HerkunftslandID) on delete cascade on update no action
);
create index idx_Musiktitel on Musiktitel (Titel);

create table MusiktitelAlbum(
	MusiktitelAlbumID int identity(1, 1) NOT NULL,
	MusiktitelID int,
	AlbumID int,
	constraint PF_MusiktitelAlbum primary key (MusiktitelAlbumID),
	constraint FK_MusiktitelAlbum_Musiktitel foreign key (MusiktitelID)
	references Musiktitel(MusiktitelID) on delete cascade on update no action,
	constraint FK_MusiktitelAlbum_Album foreign key (AlbumID)
	references Album(AlbumID) on delete cascade on update no action
);

create table PlaylistMusiktitel(
	PlaylistMusiktitelID int identity(1, 1) NOT NULL,
	PlaylistID int,
	MusiktitelID int,
	constraint PF_PlaylistMusiktitel primary key (PlaylistMusiktitelID),
	constraint FK_PlaylistMusiktitel_Playlist foreign key (PlaylistID)
	references Playlist(PlaylistID) on delete cascade on update no action,
	constraint FK_PlaylistMusiktitel_Musiktitel foreign key (MusiktitelID)
	references Musiktitel(MusiktitelID) on delete no action on update no action
);


insert into Benutzer(Benutzername, Passwort, Vorname, Nachname)
values ('joel', 1234, 'Joel', 'Blaser'),
('jeffrey', 1234, 'Jeffrey', 'Blanco');

insert into Interpret(Name)
values ('Linus'),
('Tim');

insert into Album(Titel, UNC_Verlinkung)
values ('Album 1', 'Link'),
('Album 2', 'Link'),
('Album 3', 'Link'),
('Album 4', 'Link'),
('Album 5', 'Link');

insert into Playlist(Titel, BenutzerID)
values ('Playlist 1', (select BenutzerID from Benutzer where Benutzername = 'joel')),
('Playlist 2', (select BenutzerID from Benutzer where Benutzername = 'joel')),
('Playlist 3', (select BenutzerID from Benutzer where Benutzername = 'joel')),
('Playlist 4', (select BenutzerID from Benutzer where Benutzername = 'jeffrey')),
('Playlist 5', (select BenutzerID from Benutzer where Benutzername = 'jeffrey')),
('Playlist 6', (select BenutzerID from Benutzer where Benutzername = 'jeffrey'));

insert into Genre(Genre)
values ('Pop'),
('Hip Hop'),
('Rock'),
('Trap'),
('Metal');

insert into Herkunftsland(Herkunftsland)
values ('Schweiz'),
('Deutschland'),
('Osterreich');

insert into Musiktitel(Titel, Ausgabejahr, Kaufpreis, Kaufdatum, InterpretID, Dauer_in_Sekunden, Genre, Herkunftsland, Persoenliche_Bewertung, BenutzerID)
values ('Song 1', 2016, 45, '2017-11-23', (select InterpretID from Interpret where Name = 'Linus'), 200, (select GenreID from Genre where Genre = 'Pop'), (select HerkunftslandID from Herkunftsland where Land = 'Schweiz'), 'Gut', (select BenutzerID from Benutzer where Benutzername = 'joel')),
('Song 2', 2017, 42, '2018-11-23', (select InterpretID from Interpret where Name = 'Linus'), 180, (select GenreID from Genre where Genre = 'Hip Hop'), (select HerkunftslandID from Herkunftsland where Land = 'Deutschland'), 'Schlecht', (select BenutzerID from Benutzer where Benutzername = 'jeffrey')),
('Song 3', 2014, 39, '2016-11-23', (select InterpretID from Interpret where Name = 'Linus'), 250, (select GenreID from Genre where Genre = 'Rock'), (select HerkunftslandID from Herkunftsland where Land = 'Osterreich'), 'Gut', (select BenutzerID from Benutzer where Benutzername = 'joel')),
('Song 4', 2015, 10, '2016-11-23', (select InterpretID from Interpret where Name = 'Linus'), 300, (select GenreID from Genre where Genre = 'Trap'), (select HerkunftslandID from Herkunftsland where Land = 'Schweiz'), 'Schlecht', (select BenutzerID from Benutzer where Benutzername = 'jeffrey')),
('Song 5', 2012, 22, '2013-11-23', (select InterpretID from Interpret where Name = 'Linus'), 400, (select GenreID from Genre where Genre = 'Metal'), (select HerkunftslandID from Herkunftsland where Land = 'Deutschland'), 'Gut', (select BenutzerID from Benutzer where Benutzername = 'joel')),
('Song 6', 2013, 39, '2015-11-23', (select InterpretID from Interpret where Name = 'Tim'), 420, (select GenreID from Genre where Genre = 'Pop'), (select HerkunftslandID from Herkunftsland where Land = 'Osterreich'), 'Schlecht', (select BenutzerID from Benutzer where Benutzername = 'jeffrey')),
('Song 7', 2015, 26, '2017-11-23', (select InterpretID from Interpret where Name = 'Tim'), 130, (select GenreID from Genre where Genre = 'Hip Hop'), (select HerkunftslandID from Herkunftsland where Land = 'Schweiz'), 'Gut', (select BenutzerID from Benutzer where Benutzername = 'joel')),
('Song 8', 2011, 13, '2014-11-23', (select InterpretID from Interpret where Name = 'Tim'), 210, (select GenreID from Genre where Genre = 'Trap'), (select HerkunftslandID from Herkunftsland where Land = 'Deutschland'), 'Schlecht', (select BenutzerID from Benutzer where Benutzername = 'jeffrey')),
('Song 9', 2010, 17, '2013-11-23', (select InterpretID from Interpret where Name = 'Tim'), 370, (select GenreID from Genre where Genre = 'Rock'), (select HerkunftslandID from Herkunftsland where Land = 'Osterreich'), 'Gut', (select BenutzerID from Benutzer where Benutzername = 'joel')),
('Song 10', 2016, 48, '2017-11-23', (select InterpretID from Interpret where Name = 'Tim'), 290, (select GenreID from Genre where Genre = 'Metal'), (select HerkunftslandID from Herkunftsland where Land = 'Schweiz'), 'Schlecht', (select BenutzerID from Benutzer where Benutzername = 'jeffrey'));

insert into MusiktitelAlbum (MusiktitelID, AlbumID)
values ((select MusiktitelID from Musiktitel where Titel = 'Song 1'), (select AlbumID from Album where Titel = 'Album 1')),
((select MusiktitelID from Musiktitel where Titel = 'Song 2'), (select AlbumID from Album where Titel = 'Album 1')),
((select MusiktitelID from Musiktitel where Titel = 'Song 3'), (select AlbumID from Album where Titel = 'Album 2')),
((select MusiktitelID from Musiktitel where Titel = 'Song 4'), (select AlbumID from Album where Titel = 'Album 2')),
((select MusiktitelID from Musiktitel where Titel = 'Song 5'), (select AlbumID from Album where Titel = 'Album 3')),
((select MusiktitelID from Musiktitel where Titel = 'Song 6'), (select AlbumID from Album where Titel = 'Album 3')),
((select MusiktitelID from Musiktitel where Titel = 'Song 7'), (select AlbumID from Album where Titel = 'Album 4')),
((select MusiktitelID from Musiktitel where Titel = 'Song 8'), (select AlbumID from Album where Titel = 'Album 4')),
((select MusiktitelID from Musiktitel where Titel = 'Song 9'), (select AlbumID from Album where Titel = 'Album 5')),
((select MusiktitelID from Musiktitel where Titel = 'Song 10'), (select AlbumID from Album where Titel = 'Album 5'));

insert into PlaylistMusiktitel(PlaylistID, MusiktitelID)
values ((select PlaylistID from Playlist where Titel = 'Playlist 1'), (select MusiktitelID from Musiktitel where Titel = 'Song 1')),
((select PlaylistID from Playlist where Titel = 'Playlist 1'), (select MusiktitelID from Musiktitel where Titel = 'Song 2')),
((select PlaylistID from Playlist where Titel = 'Playlist 1'), (select MusiktitelID from Musiktitel where Titel = 'Song 3')),
((select PlaylistID from Playlist where Titel = 'Playlist 2'), (select MusiktitelID from Musiktitel where Titel = 'Song 4')),
((select PlaylistID from Playlist where Titel = 'Playlist 2'), (select MusiktitelID from Musiktitel where Titel = 'Song 5')),
((select PlaylistID from Playlist where Titel = 'Playlist 2'), (select MusiktitelID from Musiktitel where Titel = 'Song 6')),
((select PlaylistID from Playlist where Titel = 'Playlist 3'), (select MusiktitelID from Musiktitel where Titel = 'Song 7')),
((select PlaylistID from Playlist where Titel = 'Playlist 3'), (select MusiktitelID from Musiktitel where Titel = 'Song 8')),
((select PlaylistID from Playlist where Titel = 'Playlist 3'), (select MusiktitelID from Musiktitel where Titel = 'Song 9')),
((select PlaylistID from Playlist where Titel = 'Playlist 4'), (select MusiktitelID from Musiktitel where Titel = 'Song 10')),
((select PlaylistID from Playlist where Titel = 'Playlist 4'), (select MusiktitelID from Musiktitel where Titel = 'Song 1')),
((select PlaylistID from Playlist where Titel = 'Playlist 4'), (select MusiktitelID from Musiktitel where Titel = 'Song 2')),
((select PlaylistID from Playlist where Titel = 'Playlist 5'), (select MusiktitelID from Musiktitel where Titel = 'Song 3')),
((select PlaylistID from Playlist where Titel = 'Playlist 5'), (select MusiktitelID from Musiktitel where Titel = 'Song 4')),
((select PlaylistID from Playlist where Titel = 'Playlist 5'), (select MusiktitelID from Musiktitel where Titel = 'Song 5')),
((select PlaylistID from Playlist where Titel = 'Playlist 6'), (select MusiktitelID from Musiktitel where Titel = 'Song 6')),
((select PlaylistID from Playlist where Titel = 'Playlist 6'), (select MusiktitelID from Musiktitel where Titel = 'Song 7')),
((select PlaylistID from Playlist where Titel = 'Playlist 6'), (select MusiktitelID from Musiktitel where Titel = 'Song 8'));


select min(Kaufpreis) from Musiktitel;

select max(Kaufpreis) from Musiktitel;

select round(avg(Kaufpreis), 2) from Musiktitel;

select i.Name, sum(m.Kaufpreis) from Musiktitel as m
left join Interpret as i
on m.InterpretID = i.InterpretID
group by i.Name;

select g.Genre, sum(m.Kaufpreis) from Musiktitel as m
left join Genre as g
on m.GenreID = g.GenreID
group by Genre;

select h.Land, sum(m.Kaufpreis) from Musiktitel as m
left join Herkunftsland as h
on m.HerkunftslandID = h.HerkunftslandID
group by land;

select Persoenliche_Bewertung, sum(Kaufpreis) from Musiktitel
group by Persoenliche_Bewertung;
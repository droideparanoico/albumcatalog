create table if not exists artist(id bigint auto_increment primary key, name VARCHAR(250) NOT NULL, created_on DATE default CURRENT_TIMESTAMP );
create table if not exists album(id bigint auto_increment PRIMARY KEY, album_id bigint NOT NULL, name VARCHAR(250) NOT NULL, cover_url VARCHAR(250) NOT NULL, created_on DATE default CURRENT_TIMESTAMP, FOREIGN KEY(artist_id) REFERENCES artist(id) ON UPDATE CASCADE);

insert into artist(id, name, created_on) values(1, 'Radiohead', CURRENT_TIMESTAMP);
insert into album(id, name, artist_id, cover_url, created_on) values(2, 'OK Computer', 1, 'https://upload.wikimedia.org/wikipedia/en/b/ba/Radioheadokcomputer.png', CURRENT_TIMESTAMP);
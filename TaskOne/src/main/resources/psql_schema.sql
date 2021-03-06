
-- sudo su postgres
-- psql
-- \l
CREATE DATABASE Travel;
CREATE USER lexa WITH PASSWORD 'lexa';
GRANT ALL PRIVILEGES ON DATABASE "travel" to lexa;
GRANT ALL PRIVILEGES ON table "ordering" to lexa;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO lexa;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO lexa;

create table dummy (
id serial primary key,
time date
);

DROP TABLE "dummy";

create table travel (
id serial primary key,
name VARCHAR(64),
type VARCHAR(64),
transport VARCHAR(64),
cost int
);

ALTER TABLE "travel"
ADD "startDate" date NOT NULL DEFAULT 'now()';

create table Ordering (
id serial primary key,
username varchar(64) not null,
userphone varchar(64) not null,
travelId int references travel(id) not null,
days int not null default 14,
quantity int not null default 1
);

insert into travel (name, type,transport, cost) 
values ( 'Bulgaria', 'health', 'bus', 400),
( 'Egypt', 'shopping', 'plane', 650),
 ( 'Belarus', 'relax', 'train', 100);

insert into travel (name, type,transport, cost, "startDate") 
values ( 'Bulgaria', 'health', 'bus', 400, '21-04-2018');

UPDATE "travel" SET
"id" = '4',
"name" = 'Romania',
"type" = 'health',
"transport" = 'bus',
"cost" = '400',
"startDate" = '2018-04-21'
WHERE "id" = '4';

insert into travel (name, type,transport, cost, "startDate") 
values ( 'Taiti', 'health', 'bus', 4100, '21-04-2018'),
( 'Maldives', 'relax', 'bus', 4000, '23-04-2018'),
 ( 'Thailand', 'relax', 'plane', 3400, '21-04-2018'),
 ( 'China', 'excursion', 'plane', 1400, '22-04-2018'),
 ( 'Japan', 'shopping', 'plane', 2500, '1-05-2018'),
 ( 'France', 'shopping', 'bus', 4000, '11-4-2018'),
 ( 'Slovakia', 'excursion', 'bus', 500, '17-04-2018'),
 ( 'Turkey', 'relax', 'ship', 900, '20-04-2018'),
 ( 'Georgia', 'health', 'train', 700, '22-04-2018'),
 ( 'Greece', 'excursion', 'ship', 1200, '23-04-2018');

insert into ordering (username, userphone, travelId, days)
values ( 'Oleksii', '0931112233', 5, 90);
INSERT INTO ordering (username, userphone, travelId, days, quantity)
VALUES( 'vitaly', '0853322111', 5, 7, 2);
delete from ordering where id != 1;

select * from travel where (id between 3 and 9) and ( type in ('health', 'relax')) and (transport in ('bus','plane'));
select * from travel where "startDate"  between '2018-04-20' and '2018-04-24'

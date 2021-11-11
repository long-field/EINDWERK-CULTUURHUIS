insert into genres(naam)
values  ('testGenre1'),
        ('testGenre2');
insert into voorstellingen(titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen, versie)
values  ('testTitel1', 'testUitvoerder1', '2050-09-10 8:00:00', (select id from genres where naam = 'testGenre1'), 8.00, 150, 1),
        ('testTitel2', 'testUitvoerder2', '2050-09-11 8:00:00', (select id from genres where naam = 'testGenre2'), 10.00, 50, 1),
        ('testTitel3', 'testUitvoerder2', '2010-09-11 8:00:00', (select id from genres where naam = 'testGenre2'),15.00, 50, 1);

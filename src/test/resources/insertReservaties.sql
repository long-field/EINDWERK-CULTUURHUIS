insert into reservaties (klantid, voorstellingid, plaatsen)
values ((select id from klanten where familienaam = 'testFamilienaam'),
        (select id from voorstellingen where titel = 'testTitel1'
                                         and genreid = (select id from genres where naam = 'testGenre1')), 1);
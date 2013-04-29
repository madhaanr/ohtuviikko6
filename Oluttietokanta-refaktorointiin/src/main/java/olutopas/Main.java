package olutopas;

import database.Datamapper;
import database.EbeanSqliteDatamapper;
import domain.Beer;
import domain.Brewery;
import domain.Rating;
import domain.User;

public class Main {

    enum Database {

        H2, SQLite
    }

    public static void main(String[] args) {
         boolean dropAndCreateTables = false;
         Datamapper mapper = new EbeanSqliteDatamapper("jdbc:sqlite:beer.db", dropAndCreateTables, Beer.class, Brewery.class, Rating.class, User.class);
         new Application(mapper).run(dropAndCreateTables);
    }
}

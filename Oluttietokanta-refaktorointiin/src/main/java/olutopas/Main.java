package olutopas;

import database.Datamapper;
import database.EbeanSqliteDatamapper;
import model.Beer;
import model.Brewery;
import model.Rating;
import model.User;

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

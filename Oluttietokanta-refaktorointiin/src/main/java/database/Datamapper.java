/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;
import domain.Beer;
import domain.Brewery;
import domain.Rating;
import domain.User;

/**
 *
 * @author mhaanran
 */
public interface Datamapper {
    Brewery brewerywithName(String n);
    void addBeer(Brewery b);
    Beer findBeer(String n);
    List listBreweries();
    List listBeers();
    void addBrewery(Brewery b);
    void addRating(Rating r);
    List findUsers();
    User userExists(String n);
    User getCurrentUser();
    void setCurrentUser(User u);
    void createUser(String n);
}

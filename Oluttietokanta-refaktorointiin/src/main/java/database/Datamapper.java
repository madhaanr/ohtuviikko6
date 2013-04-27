/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author mhaanran
 */
public interface Datamapper {
    public Brewery brewerywithName(String n);
    User getCurrentUser();
    void setCurrentUser(User user);
}

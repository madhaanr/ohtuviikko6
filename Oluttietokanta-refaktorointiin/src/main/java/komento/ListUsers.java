package komento;

import database.Datamapper;
import java.util.List;
import olutopas.model.User;

/* @author mhaanran */
public class ListUsers implements Komento {

    private Datamapper datamapper;

    public ListUsers(Datamapper datamapper) {
        this.datamapper=datamapper;
    }
    
    @Override
    public void suorita() {
        List<User> users = datamapper.findUsers();
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getRatings().size() + " ratings");
        }
    }

}

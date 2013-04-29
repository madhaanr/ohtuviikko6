package komento;

import database.Datamapper;
import java.util.List;
import model.Rating;
import model.User;

/* @author mhaanran */
public class ListRatings implements Komento{

    private Datamapper datamapper;

    public ListRatings(Datamapper datamapper) {
        this.datamapper=datamapper;
    }
    
    @Override
    public void suorita() {
        User user = datamapper.getCurrentUser();
        System.out.println("Ratings by " + user.getName());
        for (Rating rating : user.getRatings()) {
            System.out.println(rating);             
        }
    }

}

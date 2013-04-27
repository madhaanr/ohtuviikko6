package komento;

import database.Datamapper;
import olutopas.model.Rating;
import olutopas.model.User;

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

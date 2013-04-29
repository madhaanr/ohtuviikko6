package komento;

import database.Datamapper;
import java.util.List;
import domain.Beer;

/* @author mhaanran */
public class ListBeers implements Komento {
    private Datamapper datamapper;

    public ListBeers(Datamapper datamapper) {
        this.datamapper=datamapper;
    }
    @Override
    public void suorita() {
        List<Beer> beers = datamapper.listBeers();
        for (Beer beer : beers) {
            System.out.println(beer);
            if (beer.getRatings() != null && beer.getRatings().size()!=0) {
                System.out.println("  ratings given "+beer.getRatings().size() + " average " + beer.averageRating());
            } else {
                System.out.println("  no ratings");
            }
        }
    }
}

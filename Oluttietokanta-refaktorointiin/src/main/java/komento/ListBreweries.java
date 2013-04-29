package komento;

import database.Datamapper;
import java.util.List;
import domain.Brewery;

/* @author mhaanran */
public class ListBreweries implements Komento{

    private Datamapper datamapper;

    public ListBreweries(Datamapper datamapper) {
        this.datamapper=datamapper;
    }
    @Override
    public void suorita() {
        List<Brewery> breweries = datamapper.listBreweries();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }
    
}

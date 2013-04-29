package komento;

import database.Datamapper;
import java.util.HashMap;
import java.util.Map;

/* @author mhaanran */
public class Komentotehdas {
    
    private Map<String,Komento> komennot = new HashMap<String,Komento>(); 
    
    public Komentotehdas(Datamapper mapper) {
        komennot.put("1",new FindBrewery(mapper));
        komennot.put("2",new FindBeer(mapper));
        komennot.put("3",new AddBeer(mapper));
        komennot.put("4",new ListBreweries(mapper));
        komennot.put("5",new ListBeers(mapper));
        komennot.put("6",new AddBrewery(mapper));
        komennot.put("7",new ListRatings(mapper));
        komennot.put("8",new ListUsers(mapper));
        komennot.put("9",new Logout());
    }

    public Komento hae(String command) {
        return komennot.get(command);     
    }
}

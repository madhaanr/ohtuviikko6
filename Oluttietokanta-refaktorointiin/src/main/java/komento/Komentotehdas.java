package komento;

import database.Datamapper;
import java.util.HashMap;
import java.util.Map;

/* @author mhaanran */
public class Komentotehdas {
    
    private Map<String,Komento> komennot = new HashMap<String,Komento>(); 
    
    public Komentotehdas(Datamapper mapper) {
        komennot.put("1",new FindBrewery(mapper));
        
    }

    public Komento hae(String command) {
        return komennot.get(command);     
    }
}

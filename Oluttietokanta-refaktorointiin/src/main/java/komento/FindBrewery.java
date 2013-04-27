package komento;

import database.Datamapper;
import java.util.Scanner;
import olutopas.model.Beer;
import olutopas.model.Brewery;

/* @author mhaanran */
public class FindBrewery implements Komento {
    
    private Scanner scanner = new Scanner(System.in);
    private Datamapper datamapper;
    
    public FindBrewery(Datamapper datamapper) {
        this.datamapper=datamapper;
    }
    
    @Override
    public void suorita() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = datamapper.brewerywithName(n);

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }   
    }
}

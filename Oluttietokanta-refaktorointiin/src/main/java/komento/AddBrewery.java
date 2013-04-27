package komento;

import database.Datamapper;
import java.util.Scanner;
import olutopas.model.Brewery;

/* @author mhaanran */
public class AddBrewery implements Komento {

    private Scanner scanner = new Scanner(System.in);
    private Datamapper datamapper;

    public AddBrewery(Datamapper datamapper) {
        this.datamapper=datamapper;
    }
    @Override
    public void suorita() {   
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery brewery = datamapper.brewerywithName(name);

        if (brewery != null) {
            System.out.println(name + " already exists!");
            return;
        }
        brewery=new Brewery(name);

        datamapper.addBrewery(brewery);
    }

}

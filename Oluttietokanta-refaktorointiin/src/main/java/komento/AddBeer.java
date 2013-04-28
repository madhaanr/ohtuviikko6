package komento;

import database.Datamapper;
import java.util.Scanner;
import model.Beer;
import model.Brewery;

/* @author mhaanran */
public class AddBeer implements Komento {

    private Scanner scanner = new Scanner(System.in);
    private Datamapper datamapper;

    public AddBeer(Datamapper datamapper) {
        this.datamapper=datamapper;
    }
    
    @Override
    public void suorita() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = datamapper.brewerywithName(name);

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = datamapper.findBeer(name);
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        datamapper.addBeer(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }
}

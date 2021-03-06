package komento;

import database.Datamapper;
import java.util.Scanner;
import domain.Beer;
import domain.Rating;
import domain.User;

/* @author mhaanran */
public class FindBeer implements Komento {

    private Scanner scanner = new Scanner(System.in);
    private Datamapper datamapper;

    public FindBeer(Datamapper datamapper) {
        this.datamapper=datamapper;
    }
    
    @Override
    public void suorita() {
        System.out.print("beer to find: ");
        String name = scanner.nextLine();
        Beer foundBeer = datamapper.findBeer(name);

        if (foundBeer == null) {
            System.out.println(name + " not found");
            return;
        }

        System.out.println(foundBeer);

        if (foundBeer.getRatings() != null && foundBeer.getRatings().size()!=0) {
            System.out.println("  number of ratings: " + foundBeer.getRatings().size() + " average " + foundBeer.averageRating());
        } else {
            System.out.println(" no ratings ");
        }

        System.out.print("give rating (leave emtpy if not): ");
        try {
            int rating = Integer.parseInt(scanner.nextLine());
            addRating(foundBeer, rating);
        } catch (Exception e) {
        }
    }
    private void addRating(Beer foundBeer, int value) {
        User user = datamapper.getCurrentUser();
        Rating rating = new Rating(foundBeer, user, value);
        datamapper.addRating(rating);
    }
}

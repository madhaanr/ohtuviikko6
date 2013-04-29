package olutopas;

import com.avaje.ebean.EbeanServer;
import database.Datamapper;
import database.EbeanSqliteDatamapper;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import komento.Komento;
import komento.Komentotehdas;
import komento.Login;
import model.Beer;
import model.Brewery;
import model.User;

public class Application {

    
    private Komentotehdas komennot;
    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);
    private User user;
    private Datamapper mapper;

//    public Application(EbeanServer server) {
//        this.server = server;
//    }
    public Application(Datamapper mapper) {
        this.mapper=mapper;
        this.server = ((EbeanSqliteDatamapper)mapper).getServer();
        this.komennot = new Komentotehdas(mapper);
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();                  
        }
        
        Login login = new Login(mapper);        
        user=login.suorita();
        
        System.out.println("\nWelcome to Ratebeer " + user.getName());

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();
                
            Komento komento = komennot.hae(command);
    
            if(komento!=null) {
                komento.suorita();
                continue;
            }  
            else {
                System.out.println("unknown command");
            }
        }
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find/rate beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   list beers");
        System.out.println("6   add brewery");
        System.out.println("7   show my ratings");
        System.out.println("8   list users");
        System.out.println("9   quit");
        System.out.println("");
    }

    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);

        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());
        brewery = server.find(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        server.save(brewery);

        server.save(new Brewery("Paulaner"));

        server.save(new User("mluukkai"));
    }

//    private void login() {
//        while (true) {
//            System.out.println("\nLogin (give ? to register a new user)\n");
//
//            System.out.print("username: ");
//            String name = scanner.nextLine();
//
//            if (name.equals("?")) {
//                registerUser();
//                continue;
//            }
//            
//            user = server.find(User.class).where().like("name", name).findUnique();
//
//            if (user != null) {
//                break;
//            }
//            System.out.println("unknown user");
//        }
//        
//    }

//    private void registerUser() {
//        System.out.println("Register a new user");
//        System.out.print("give username: ");
//        String name = scanner.nextLine();
//        User u = server.find(User.class).where().like("name", name).findUnique();
//        if (u != null) {
//            System.out.println("user already exists!");
//            return;
//        }
//        user = new User(name);
//        server.save(new User(name));
//        System.out.println("user created!\n");
//    }

//    private void addRating(Beer foundBeer, int value) {
//        Rating rating = new Rating(foundBeer, user, value);
//        server.save(rating);
//    }
//
//    private void myRatings() {
//        System.out.println("Ratings by " + user.getName());
//        for (Rating rating : user.getRatings()) {
//            System.out.println(rating);
//        }
//    }

//    private void listUsers() {
//        List<User> users = server.find(User.class).findList();
//        for (User user : users) {
//            System.out.println(user.getName() + " " + user.getRatings().size() + " ratings");
//        }
//    }
}

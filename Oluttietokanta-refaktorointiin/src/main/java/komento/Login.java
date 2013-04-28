package komento;

import database.Datamapper;
import java.util.Scanner;
import model.User;

/* @author mhaanran */
public class Login {

    private Scanner scanner = new Scanner(System.in);
    private Datamapper datamapper;
    private User user;


    public Login(Datamapper datamapper) {
        this.datamapper=datamapper;
    }
    
    public User suorita() {
        while (true) {
            
            System.out.println("\nLogin (give ? to register a new user)\n");

            System.out.print("username: ");
            String name = scanner.nextLine();
            
            if (name.equals("?")) {
                registerUser();
                continue;
            }
            user = new User(name);
           
            if (user != null) {
                datamapper.setCurrentUser(user);
                break;
            }
            System.out.println("unknown user");
        }
        return user;
    }
        
    private void registerUser() {
        System.out.println("Register a new user");
        System.out.print("give username: ");
        String name = scanner.nextLine();
        
        User exists = datamapper.userExists(name);
        
        if (exists != null) {   
            System.out.println(name + " exists already");
            return;            
        }
        datamapper.createUser(name);
        user = new User(name);
        datamapper.setCurrentUser(user);
        System.out.println("user created!\n");
    }

}

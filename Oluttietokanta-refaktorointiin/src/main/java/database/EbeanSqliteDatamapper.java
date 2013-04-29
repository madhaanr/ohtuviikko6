package database;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import java.util.List;
import domain.Beer;
import domain.Brewery;
import domain.Rating;
import domain.User;

/* @author mhaanran */
public class EbeanSqliteDatamapper implements Datamapper {
    private Class[] luokat;
    private EbeanServer server;
    private String tietokantaUrl;
    private boolean dropAndCreate;
    private User currentUser;
    
    public EbeanSqliteDatamapper(String tietokantaUrl ,boolean dropAndCreate, Class... luokat) {
        this.luokat = luokat;
        this.dropAndCreate = dropAndCreate;
        this.tietokantaUrl = tietokantaUrl;      
        init();
    }

    public void init() {
        ServerConfig config = new ServerConfig();
        config.setName("beerDb");
        DataSourceConfig sqLite = new DataSourceConfig();
        // loput konfiguraatiosta

        // konstruktorin parametrina annettavat hallinnoitavat luokat lisätään seuraavasti
        for (Class luokka : luokat) {
            config.addClass(luokka);
        }     
        sqLite.setUsername("marko");
        sqLite.setPassword("marko");
        sqLite.setUrl(tietokantaUrl);
        sqLite.setDriver("org.sqlite.JDBC");
        config.setDataSourceConfig(sqLite);
        config.setDatabasePlatform(new SQLitePlatform());
        config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);
          
        server = EbeanServerFactory.create(config);
    }

    @Override
    public Brewery brewerywithName(String n) {
        return server.find(Brewery.class).where().like("name", n).findUnique();
    }
    @Override
    public Beer findBeer(String n) {
        return server.find(Beer.class).where().like("name", n).findUnique();
    }
    @Override
    public void addBeer(Brewery b) {
        server.save(b);
    }
    @Override
    public List listBreweries() {
        return server.find(Brewery.class).findList();
    }
    @Override
    public List listBeers() {
        return server.find(Beer.class).findList();
    }
    @Override
    public void addBrewery(Brewery b) {
        server.save(b);
    }
    @Override
    public User getCurrentUser() {
        return currentUser;
    }
    @Override
    public void setCurrentUser(User user) {  
        currentUser=server.find(User.class).where().like("name", user.getName()).findUnique();
//        System.out.println(currentUser.getName()+"::"+currentUser.getId());
              
    }
    @Override
    public void createUser(String name) {
        server.save(new User(name));
    }
    @Override
    public User userExists(String n) {    
        return server.find(User.class).where().like("name", n).findUnique();
    }  
    // muut metodit
   
    // apumetodi, jonka avulla Application-olio pääsee aluksi käsiksi EbeanServer-olioon
    public EbeanServer getServer() {
        return server;
    }

    @Override
    public void addRating(Rating r) {
        server.save(r);
    }
    @Override
    public List findUsers() {
        return server.find(User.class).findList();
    }  
}

package database;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import olutopas.model.Brewery;
import olutopas.model.User;

/* @author mhaanran */
public class EbeanSqliteDatamapper implements Datamapper {
    private Class[] luokat;
    private EbeanServer server;
    private String tietokantaUrl;
    private boolean dropAndCreate;
    
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

    // muut metodit
   
    // apumetodi, jonka avulla Application-olio pääsee aluksi käsiksi EbeanServer-olioon
    public EbeanServer getServer() {
        return server;
    }

    @Override
    public User getCurrentUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCurrentUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

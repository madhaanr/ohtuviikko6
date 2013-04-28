package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
                             
        );
        Matcher p = new Or(new HasAtLeast(25, "goals"),
                           new HasAtLeast(30, "assists")
        );
        Matcher d = new Not(new PlaysIn("TBL"),
                            new PlaysIn("WSH"),
                            new PlaysIn("PIT"),
                            new PlaysIn("CHI")
                          
                      
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        System.out.println("\n\n\n");
        for (Player player : stats.matches(p)) {
            System.out.println( player );
        }
        System.out.println("\n\n\n");
        for (Player player : stats.matches(d)) {
            System.out.println( player );
        }
    }
}

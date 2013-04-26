package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukuJoukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        lukuJoukko = new int[KAPASITEETTI];
        for (int i = 0; i < lukuJoukko.length; i++) {
            lukuJoukko[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukuJoukko = new int[kapasiteetti];
        for (int i = 0; i < lukuJoukko.length; i++) {
            lukuJoukko[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if(kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }
        lukuJoukko = new int[kapasiteetti];
        for (int i = 0; i < lukuJoukko.length; i++) {
            lukuJoukko[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            lukuJoukko[0] = luku;
            alkioidenLkm++;
            return true;
        }
        if (kuuluukoLukuJoukkoon(luku)) {
            return true;
        }
        return false;
    }
    public boolean kuuluukoLukuJoukkoon(int luku) {
        if (!kuuluu(luku)) {
            lukuJoukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukuJoukko.length == 0) {
                int[] ljono_data = lukuJoukko;
                lukuJoukko = new int[alkioidenLkm + kasvatuskoko];
                System.arraycopy(ljono_data, 0, lukuJoukko, 0, ljono_data.length);
            }
            return true;
        }
        return false;
    }
    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                return true;
            }
        }
        return false;
    } 

    public boolean poista(int luku) {
        int poistoKohta = -1, apu;
        poistoKohta = poistoKohdanEtsinta(luku, poistoKohta);
        if (poistoKohta != -1) {
            for (int i = poistoKohta; i < alkioidenLkm - 1; i++) {
                apu = lukuJoukko[i];
                lukuJoukko[i] = lukuJoukko[i + 1];
                lukuJoukko[i + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public int poistoKohdanEtsinta(int luku, int poistoKohta) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                poistoKohta = i;
                lukuJoukko[poistoKohta] = 0;
                break;
            }
        }
        return poistoKohta;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(lukuJoukko, 0, taulu, 0, alkioidenLkm);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko yhdiste = new IntJoukko();
        int[] tauluA = joukkoA.toIntArray();
        int[] tauluB = joukkoB.toIntArray();
        for (int i = 0; i < tauluA.length; i++) {
            yhdiste.lisaa(tauluA[i]);
        }
        for (int i = 0; i < tauluB.length; i++) {
            yhdiste.lisaa(tauluB[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko leikkaus = new IntJoukko();
        int[] tauluA = joukkoA.toIntArray();
        int[] tauluB = joukkoB.toIntArray();
        for (int i = 0; i < tauluA.length; i++) {
            for (int j = 0; j < tauluB.length; j++) {
                if (tauluA[i] == tauluB[j]) {
                    leikkaus.lisaa(tauluB[j]);
                }
            }
        }
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko erotus = new IntJoukko();
        int[] tauluA = joukkoA.toIntArray();
        int[] tauluB = joukkoB.toIntArray();
        for (int i = 0; i < tauluA.length; i++) {
            erotus.lisaa(tauluA[i]);
        }
        for (int i = 0; i < tauluB.length; i++) {
            erotus.poista(i);
        }
        return erotus;
    }
    
    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += lukuJoukko[i] + ", ";
            }
            tuotos += lukuJoukko[alkioidenLkm - 1] + "}";
            return tuotos;
        }
    }
}
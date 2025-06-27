package mypackage;

public class TestConnessione {
    public static void main(String[] args) {
        // Forza il caricamento della classe Connessione
        try {
            Class.forName("mypackage.Connessione"); // Sostituisci "tuo.pacchetto" col tuo package
            if (Connessione.getCon() != null) {
                System.out.println("Connessione attiva.");
            } else {
                System.out.println("Connessione è null.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
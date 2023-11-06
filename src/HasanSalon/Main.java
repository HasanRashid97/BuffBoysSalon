package HasanSalon;

public class Main {
    private static Kalender kalender = new Kalender();
    private static Printer printer = new Printer(kalender);
    private Ejer ejer = new Ejer("Harry", 1, kalender, printer);
    private Personale personale = new Personale("Harriet", 2, kalender, printer);
    private MenuRunner menuRunner = new MenuRunner(ejer, personale);




    public static void main(String[] args) {
        new Main().run(); //
    }

    public void run() {
        kalender.opdaterArraylistFraFil();
        menuRunner.eksekverMenu();
        kalender.overskrivFilFraArraylist();
    }
}

package HasanSalon;

public class Person {
    private String navn;
    private int id;
    private Kalender kalender;
    private Printer printer;

    public Person(String navn, int id, Kalender kalender, Printer printer) {
        this.navn = navn;
        this.id = id;
        this.kalender = kalender;
        this.printer = printer;
    }

    public Kalender getKalender(){
        return kalender;
    }

    public Printer getPrinter() { return printer;}


}

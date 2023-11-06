package HasanSalon;


import java.time.LocalDate;

public class Ejer extends Personale {

    private Printer printer;

    public Ejer(String navn, int id, Kalender kalender, Printer printer) {
        super(navn, id, kalender, printer);
        this.printer = printer;
    }

    public void visHeleRegnskab() {
        System.out.println("Dette er regnskabet:\n");
        printer.printHeleRegnskab();
    }

    public void vaelgDatoRegnskab() {
        System.out.println("Indtast dato for regnskabet du vil se([DD-MM-ÅÅÅÅ]):");
        LocalDate valgtDato = indtastDato();
        printer.printRegnskabForDato(valgtDato);
    }
}

package HasanSalon;

import java.time.LocalDate;

public class Printer {

    private Kalender kalender;

    public Printer (Kalender kalender){
        this.kalender = kalender;
    }

    public void printConfirmation(Aftale a) {
        System.out.println("\nDin aftale ser sådan ud:\n");
        System.out.println("Dato: " + a.getDato() + " Tidspunkt: " + a.getTidspunkt()
                + " Navn: " + a.getKunde().getKundenavn() + " Tlf: " + a.getKunde().getKundeTlfNr());
    }


    public void printKalender() {
        for (int i = 0; i < kalender.getAftaler().size(); i++) {
            printKalenderInfo(i);
        }
    }

    public void printRegnskabForDato (LocalDate valgtDato) {
        for (int i = 0; i < kalender.getAftaler().size(); i++) {
            if (kalender.getAftaler().get(i).getDato().isEqual(valgtDato)) {
                printRegnskabInfo(i);
            }
        }
    }
    public void printUbetalteAftaler() {
        for (int i = 0; i < kalender.getAftaler().size(); i++) {
            if (kalender.getAftaler().get(i).getBetaling().equals("mangler")) {
                printRegnskabInfo(i);
            }
        }
    }

    public void printHeleRegnskab() {
        for (int i = 0; i < kalender.getAftaler().size(); i++) {
            printRegnskabInfo(i);
        }

    }

    public void printKalenderInfo(int i){
        System.out.printf("\n%s%2d%s" , "Aftalenr: ", (i+1), ".");
        System.out.printf("%10s%-10s", "Navn: ", kalender.getAftaler().get(i).getKunde().getKundenavn());
        System.out.printf("%5s%-10d", " Tlf: ", kalender.getAftaler().get(i).getKunde().getKundeTlfNr());
        System.out.printf("%5s%-10s", " Dato: ",kalender.getAftaler().get(i).getDato().toString());
        System.out.printf("%15s%-10s\n"," Tidspunkt: ", kalender.getAftaler().get(i).getTidspunkt().toString());
        System.out.println("---------------------------------------------------------------------------------------");
    }


    public void printRegnskabInfo(int i){
        System.out.printf("\n%s%-2d%s" , "Aftalenr: ", (i+1), ".");
        System.out.printf("%5s%-10s", " Dato: ",kalender.getAftaler().get(i).getDato().toString());
        System.out.printf("%15s%-10s"," Tidspunkt: ", kalender.getAftaler().get(i).getTidspunkt().toString());
        System.out.printf("%10s%-10s", "Navn: ", kalender.getAftaler().get(i).getKunde().getKundenavn());
        System.out.printf("%10s%-10d", "Pris: ", kalender.getAftaler().get(i).getPris());
        System.out.printf("%10s%-10s\n", "Betalingsstatus: ", kalender.getAftaler().get(i).getBetaling());
        System.out.println("---------------------------------------------------------------------------------------" +
                "--------------------------------");
    }


    public void printIntroOgKalender() {
        System.out.println("Dette er kalenderen:\n");
        printKalender();
    }

}



package HasanSalon;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Personale extends Person {
    private Scanner filescanner = null;
    private Scanner scanner = new Scanner(System.in);
    private Kalender kalender;
    private Printer printer;


    public Personale(String navn, int id, Kalender kalender, Printer printer) {
        super(navn, id, kalender, printer);
        this.kalender = kalender;
        this.printer = printer;

    }


    public void opretAftale() {
        try {
            Aftale aftale = new Aftale(vaelgDatoForAftale(), indtastTidspunkt(), opretKunde(), 0, "mangler");

            kalender.gemIDokument(aftale);
            kalender.opdaterArraylistFraFil();
            printer.printConfirmation(aftale);
        } catch (Exception e) {
            System.out.println("Noget gik galt. Prøv forfra, ellers får du den bagfra.");
        }
    }

    private LocalDate vaelgDatoForAftale() {
        System.out.println("Vælg Dato for aftale:");

        LocalDate valgtDato = indtastDato();
        ArrayList<Aftale> aftaler = kalender.getAftaler();
        Aftale[] tider = (new Aftale[7]);

        for (Aftale a : aftaler) {
            LocalTime tidspunkt = a.getTidspunkt();
            int time = tidspunkt.getHour();
            if (a.getDato().isEqual(valgtDato)) {
                if (time >= 10 && time <= 17) {
                    tider[time - 10] = a;
                }
            }
        }

        for (int i = 0; i < tider.length; i++) {
            if (tider[i] != null) {
                System.out.println(tider[i].getKunde().getKundenavn() + ": " +
                        tider[i].getTidspunkt() + "-" + tider[i].getTidspunkt().plusHours(1) + ".");
            } else { System.out.println("Frit tidsrum");
            }
        }

        return valgtDato;
    }

    private Kunde opretKunde() {
        String kundeNavn = indtastNavn();
        int kundeTlfNr = indtastTlfNr();
        Kunde kunde = new Kunde(kundeNavn, kundeTlfNr);
        return kunde;
    }


    private String indtastNavn() {
        String kundeNavn = "";
        while (kundeNavn.isEmpty()) {
            System.out.println("Skriv kundens navn:");
            kundeNavn = scanner.nextLine().trim();
            if (kundeNavn.isEmpty()) {
                System.out.println("Feltet må ikke være tomt. Prøv igen.");
            }
        }
        return kundeNavn;
    }

    private int indtastTlfNr() {
        int kundeTlfNr = -1;
        boolean validIndput = false;

        while (!validIndput) {
            System.out.println("Skriv kundens telefonnummer:");
            try {
                kundeTlfNr = Integer.parseInt(scanner.nextLine().trim());
                validIndput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt telefonnummer. Prøv igen.");
            }
        }
        return kundeTlfNr;
    }
    public LocalDate indtastDato() {
        String datoFormat = "^(\\d{4}-\\d{2}-\\d{2})$";
        DateTimeFormatter datoFormaterer = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("Skriv dato([ÅÅÅÅ-MM-DD]):");
            String dato = scanner.nextLine().trim();

            if (dato.matches(datoFormat)) {
                try {
                    return LocalDate.parse(dato, datoFormaterer);
                } catch (Exception e) {
                    System.out.println("Ugyldig dato. Prøv igen.");
                }
            } else {
                System.out.println("Ugyldigt datoformat. Prøv igen");
            }
        }
    }

    private LocalTime indtastTidspunkt() { //Skal rykkes til kalender
        String tidsformat = "^(\\d{2}:\\d{2})$";
        DateTimeFormatter tidsFormaterer = DateTimeFormatter.ofPattern("HH:mm");

// TODO MAN MÅ ALDRIG SKRIVE WHILE(TRUE)
        // RETURN SKAL OGSÅ KOMME UDENFRO TRY
        while (true) {
            System.out.println("Skriv tidspunkt( [TT:MM]):");
            String tidspunkt = scanner.nextLine().trim();

            if (tidspunkt.matches(tidsformat)) {
                try {
                    return LocalTime.parse(tidspunkt, tidsFormaterer).withNano(0);
                } catch (Exception e) {
                    System.out.println("Ugyldigt tidspunkt. Prøv igen.");
                }
            } else {
                System.out.println("Ugyldigt tidsindput. Prøv igen");
            }

        }
    }


    public void sletAftale() {
        System.out.println("Indtast det aftalenummer du vil slette.\n");
        printer.printKalender();
        int vaelgAftale = scanner.nextInt();
        int aftaleDerSlettes = vaelgAftale - 1;

        if (aftaleDerSlettes >= 0 && aftaleDerSlettes < kalender.getAftaler().size()) {
            kalender.getAftaler().remove(aftaleDerSlettes);
            System.out.println("Aftale " + vaelgAftale + " er nu slettet.");
            System.out.println("Dette er den nye kalender");
            printer.printKalender();
        } else {
            System.out.println("Der opstod en fejl. Prøv igen.");
        }
    }

    public void modtagBetaling() {
        String betaling = "betalt";
        int aftaleValg;
        System.out.println("Vælg aftalenummer:");
        printer.printUbetalteAftaler();
        aftaleValg = scanner.nextInt();

        kalender.getAftaler().get(aftaleValg - 1).setPris(indtastPris());
        kalender.getAftaler().get(aftaleValg - 1).setBetaling(betaling);
        System.out.println("Aftalenummer: " + aftaleValg + ". betalingsstatus: " +
                kalender.getAftaler().get(aftaleValg - 1).getBetaling());
    }

    private int indtastPris() { //Skal rykkes til kalender
        int pris = 0;

        boolean validIndput = false;
        while (!validIndput) {
            System.out.println("Skriv pris:");
            try {
                scanner.nextLine();//SCANNERBUG
                pris = Integer.parseInt(scanner.nextLine().trim());
                validIndput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig pris. Prøv igen.");
            }
        }
        return pris;
    }
}



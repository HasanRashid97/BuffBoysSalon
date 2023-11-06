package BuffBoys;

import java.util.Scanner;

public class MenuRunner {
    private Menu menuStart = new Menu("HOVEDMENU:", punkterStartMenu());
    private Menu menuEjer = new Menu("EJERMENU:", punkterEjerMenu());
    private Menu menuPersonale = new Menu("PERSONALEMENU:", punkterPersonaleMenu());
    private Menu menuRevisor = new Menu("REVISORMENU:", punkterRevisorMenu());

    private int personaleType = -1;
    private int valg = -1;
    Ejer ejer;
    Personale personale;
    Scanner scanner = new Scanner(System.in);

    public MenuRunner(Ejer ejer, Personale personale) {
        this.ejer = ejer;
        this.personale = personale;
    }

    public void eksekverMenu() {
        vaelgFraHovedmenu();
        do {
            if (personaleType == 1) {
                vaelgFraEjerMenu();
            } else if (personaleType == 2) {
                vaelgFraPersonaleMenu();
            } else if (personaleType == 3) {
                vaelgFraRevisorMenu();
            } else {
                System.out.println("Ugyldigt valg. Prøv igen");
                eksekverMenu();
            }
        } while (valg != 0);
    }
    public void vaelgFraHovedmenu() {
        menuStart.printMenu();
        personaleType = scanner.nextInt();
        if (personaleType == 0) {
            afslutProgram();
        }
    }

    public void vaelgFraEjerMenu() {
        menuEjer.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> ejer.opretAftale();
            case 2 -> ejer.sletAftale();
            case 3 -> ejer.getPrinter().printIntroOgKalender();
            case 4 -> ejer.modtagBetaling();
            case 5 -> ejer.visHeleRegnskab();
            case 9 -> visHovedmenu();
            default -> {
                System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    private void vaelgFraPersonaleMenu() {
        menuPersonale.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> personale.opretAftale();
            case 2 -> personale.sletAftale();
            case 3 -> personale.getPrinter().printIntroOgKalender();
            case 4 -> personale.modtagBetaling();
            case 9 -> visHovedmenu();
            default -> {
                System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    public void vaelgFraRevisorMenu() {
        menuRevisor.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> ejer.visHeleRegnskab();
            case 2 -> ejer.vaelgDatoRegnskab();
            case 9 -> visHovedmenu();
            default -> {
                System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }

    }

    public void afslutProgram() {
        valg = 0;
    }

    public void visHovedmenu() {
        vaelgFraHovedmenu();
    }

    public String[] punkterStartMenu() {
        return new String[]{"1. Ejer.", "2. Personale.", "3. Revisor.", "0. Afslut program"};
    }

    public String[] punkterEjerMenu() {
        return new String[]{"1. Opret aftale.", "2. Slet aftale.", "3. Vis kalender.", "4. Registrer betaling.",
                "5. Vis regnskab.", "9. Vis hovedmenu", "0. Afslut program"};
    }

    public String[] punkterPersonaleMenu() {
        return new String[]{"1. Opret aftale.", "2. Slet aftale.", "3. Vis kalender.", "4. Registrer betaling.",
                "9. Vis hovedmenu", "0. Afslut program"};
    }

    public String[] punkterRevisorMenu() {
        return new String[]{"1. Vis hele regnskabet.", "2. Se regnskab for bestemt dag", "9. Vis hovedmenu",
                "0. Afslut program"};
    }

}
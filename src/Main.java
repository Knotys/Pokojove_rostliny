import java.time.LocalDate;

public class Main {
    public static final String FILENAME = "kvetiny.txt";

    public static final String OUTPUT_FILENAME = "vystup.txt";

//    public static final String FILENAME = "kvetiny-spatne-datum.txt";

//    public static final String FILENAME = "kvetiny-spatne-frekvence.txt";

    public static void main(String[] args) {
        // načtení a přečtení souboru:
        RegisterOfPlants register = new RegisterOfPlants();
        try {
//            System.out.println("Seznam květin: ");
            register.readPlantsFromFile(FILENAME);
        } catch (PlantException e) {
            System.err.println("Chyba při načtení souboru: "+e.getLocalizedMessage());
        }
            System.out.println("Seznam květin: ");


        // výpis info o zálivce:
//        System.out.println("Informace o zálivce: ");
//        for (Plant tmp : register.getPlants()) {
//            System.out.println(tmp.getWateringInfo());
//        }

        // Zápis do souboru
        try {
            // Přidání dvou květin:
            register.addPlant(new Plant("Kaktus",
                    LocalDate.of(2022,10,15),10));
            register.addPlant(new Plant("Sedmikráska"));
        }
        catch (Exception e) {
            System.err.println("Nastala chyba při přidávání položky: "+e.getLocalizedMessage());
        }

        try {
            register.writePlantsToFile(OUTPUT_FILENAME);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
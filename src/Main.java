import java.time.LocalDate;
import java.util.List;

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
        List<Plant> plants = register.getPlants();
        System.out.println(plants);


        // Zápis do souboru
        try {
            // Získání květiny na zadaném indexu:
//            register.getPlantAtIndex(1);
            // Odstranění jedné z květin:
//            register.removePlantAtIndex(2);

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
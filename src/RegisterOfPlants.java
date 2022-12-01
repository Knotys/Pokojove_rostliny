import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterOfPlants {
    public static final String DELIMITER = "\t";
    private List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant newPlant) {plants.add(newPlant);}

    public Plant getPlantAtIndex(int index) {return plants.get(index);
    }

    public void removePlantAtIndex(int index) {
        plants.remove(index);
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }


    public void readPlantsFromFile(String filename) throws PlantException {
        String nextLine = null;
        String[] flowers = new String[0];
        Plant newPlant;
        String name = null;
        String notes = null;
        LocalDate planted = null;
        LocalDate watering = null;
        int frequencyOfWatering = 0;
        int lineNumber = 0;
        String separator = "\t";

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            // Zpracovávání souboru..
            while (scanner.hasNextLine()) {
                lineNumber++;
                nextLine = scanner.nextLine();
                flowers = nextLine.split(separator);
                name = flowers[0];
                notes = flowers[1];
                frequencyOfWatering = Integer.parseInt(flowers[2]);
                watering = LocalDate.parse(flowers[3]);
                planted = LocalDate.parse(flowers[4]);


                newPlant = new Plant(name, notes, planted, watering, frequencyOfWatering);
                addPlant(newPlant);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException(
                    "Nepodařilo se najít soubor "+filename+":"+e.getLocalizedMessage());
        } catch (DateTimeParseException e) {
            throw new PlantException(
                    "Datum poslední zálivky nesmí být starší než datum zasazení rostliny.");
        } catch (NumberFormatException e) {
            throw new PlantException(
                    "Frekvence zálivky nesmí nést hodnotu 0 nebo záporné číslo. ");
        }
    }

    public void writePlantsToFile(String filename) throws PlantException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Plant plant : plants) {
                String outputLine =
                        plant.getName()+DELIMITER
                        + plant.getNotes()+DELIMITER
                        + plant.getFrequencyOfWatering()+DELIMITER
                        + plant.getWatering()+DELIMITER
                        + plant.getPlanted();
                writer.println(outputLine);
            }
        } catch (IOException e) {
            throw new PlantException("Nastala chyba při zápisu do souboru: "
                    +e.getLocalizedMessage());
        }
    }
}

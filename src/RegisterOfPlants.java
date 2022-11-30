import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterOfPlants {
    public static final String DELIMITER = ";";
    private List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant newPlant) {
        plants.add(newPlant);}

    public Plant getPlantAtIndex(int index) {
        return plants.get(index);
    }

    public void removePlantAtIndex(int index) {
        plants.remove(index);
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }


    public void readPlantsFromFile(String filename) throws PlantException {
        String nextLine = null;
        int lineNumber = 0;
//        Plant newPlant = null;
        String name = null;
        String notes = null;
        LocalDate planted = null;
        LocalDate watering = null;
        int frequencyOfWatering = 0;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                nextLine = scanner.nextLine();
                System.out.println(nextLine);

//                Plant newPlant = new Plant(name, notes, planted, watering, frequencyOfWatering);
//                plants.add(newPlant);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException(
                    "Nepodařilo se najít soubor "+filename);
        }
    }

    public void writePlantsToFile(String filename) throws PlantException {
        String lineOfFile = null;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Plant tmp : plants) {
                lineOfFile = tmp.name+" "+tmp.notes+" "+tmp.planted+
                        " "+tmp.watering+" "+tmp.frequencyOfWatering;
                writer.write(lineOfFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

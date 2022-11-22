import java.time.LocalDate;

public class Plant {

    String name;
    String notes;
    LocalDate planted;
    LocalDate watering;
    int frequencyOfWatering;
    LocalDate nextWatering;

    // První konstruktor
    public Plant(String name, String notes, LocalDate planted,
                 LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    // Druhý konstruktor
    public Plant(String notes, LocalDate watering) {
        this.notes = notes = null;
        this.watering = watering = LocalDate.now();
    }

    // Třetí konstruktor
    public Plant(String notes,LocalDate planted,
                 LocalDate watering, int frequencyOfWatering) {
        this.notes = notes = null;
        this.planted = planted = LocalDate.now();
        this.watering = watering = LocalDate.now();
        this.frequencyOfWatering = frequencyOfWatering = 7;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    // Ošetření zadávání poslední zálivky:
    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isAfter(planted)) {
            throw new PlantException(
                    "Datum poslední zálivky nesmí být starší než datum zasazení rostliny. ");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    // Ošetření zadávání frekvence zálivky:
    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException{
        if (frequencyOfWatering <= 0) {
            throw new PlantException(
                    "Frekvence zálivky nesmí nést hodnotu 0 nebo záporné číslo. "
                            +" (Zadal jsi: "+frequencyOfWatering+")");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public LocalDate getNextWatering() {
        return nextWatering;
    }

    public void setNextWatering(LocalDate nextWatering) {
        this.nextWatering = nextWatering;
    }

    // Metoda getWateringInfo
    public void getWateringInfo() {
        System.out.println(name+", "+watering+", "+nextWatering);
    }
}

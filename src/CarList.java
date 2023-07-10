
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class CarList extends ArrayList<Car> {
    BrandList brandList;
    public CarList( BrandList bList){
        brandList = bList;
    }
    public boolean loadFromFile(String filename) {
    File f = new File(filename);
    if (!f.exists()) {
        return false;
    } else {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String carID = parts[0].trim();
                    String brandID = parts[1].trim();
                    String color = parts[2].trim();
                    String frameID = parts[3].trim();
                    String engineID = parts[4].trim();
                    
                    int pos = brandList.searchID(brandID);
                    Brand b = brandList.get(pos);
                    
                    Car newCar = new Car(carID, b, color, frameID, engineID);
                    this.add(newCar);
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
   public boolean saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Car car : this) {
                writer.write(car.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }

        return true;
    }
   
}

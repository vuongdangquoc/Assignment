package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CarList extends ArrayList<Car> {

    private BrandList brandList;
    private List<Car> cars;

    public CarList(BrandList bList) {
        brandList = bList;
        cars = new ArrayList<>();
    }

    public boolean loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return false;
        } else {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    String carID = parts[0].trim();
                    String brandID = parts[1].trim();
                    String color = parts[2].trim();
                    String frameID = parts[3].trim();
                    String engineID = parts[4].trim();
                    int pos = brandList.searchID(brandID);
                    if (pos >= 0) {
                        Brand brand = brandList.getBrand(pos);
                        Car car = new Car(carID, brand, color, frameID, engineID);
                        cars.add(car);
                    }
                }
                scanner.close();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean saveToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Car car : cars) {
                writer.write(car.toString() + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int searchID(String carID) {
        int N = cars.size();
        for (int i = 0; i < N; i++) {
            if (cars.get(i).getCarID().equals(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        int N = cars.size();
        for (int i = 0; i < N; i++) {
            if (cars.get(i).getFrameID().equals(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        int N = cars.size();
        for (int i = 0; i < N; i++) {
            if (cars.get(i).getEngineID().equals(eID)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter car ID: ");
        String carID = scanner.nextLine();

        // Check for duplicate car ID
        int carIndex = searchID(carID);
        if (carIndex != -1) {
            System.out.println("Car ID already exists. Please enter a unique car ID.");
            return;
        }

        // Choose a brand from the brand list
        System.out.println("Choose a brand:");
        Brand chosenBrand = brandList.getUserChoice();

        System.out.print("Enter car color: ");
        String color = scanner.nextLine().trim();

        // Check for blank color
        if (color.isEmpty()) {
            System.out.println("Color cannot be blank. Please enter a valid color.");
            return;
        }

        System.out.print("Enter frame ID: ");
        String frameID = scanner.nextLine().trim();

        // Check for duplicate frame ID
        int frameIndex = searchFrame(frameID);
        if (frameIndex != -1) {
            System.out.println("Frame ID already exists. Please enter a unique frame ID.");
            return;
        }

        System.out.print("Enter engine ID: ");
        String engineID = scanner.nextLine().trim();

        // Check for duplicate engine ID
        int engineIndex = searchEngine(engineID);
        if (engineIndex != -1) {
            System.out.println("Engine ID already exists. Please enter a unique engine ID.");
            return;
        }

        Car newCar = new Car(carID, chosenBrand, color, frameID, engineID);
        cars.add(newCar);
        System.out.println("Car added successfully.");
    }

    public void printBasedBrandName(String aPartOfBrandName) {
        int count = 0;

        for (Car car : cars) {
            if (car.getBrand().getBrandName().contains(aPartOfBrandName)) {
                System.out.println(car.screenString());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar(String removedID) {
        int pos = searchID(removedID);

        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            cars.remove(pos);
            return true;
        }
    }

    public boolean updateCar(String updatedID) {
        int pos = searchID(updatedID);

        Scanner scanner = new Scanner(System.in);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            Menu<Brand> menu = new Menu<>();
            Brand brand = menu.ref_getChoice(brandList);
            System.out.print("Enter color: ");
            String color = scanner.nextLine();
            if (color.isEmpty()) {
                System.out.println("Color cannot be blank!");
                return false;
            }

            System.out.print("Enter frame ID (in the format F0000): ");
            String frameID = scanner.nextLine();
            if (!frameID.matches("F\\d{4}")) {
                System.out.println("Invalid frame ID format!");
                return false;
            }
            if (searchFrame(frameID) >= 0) {
                System.out.println("Frame ID already exists!");
                return false;
            }

            System.out.print("Enter engine ID (in the format E0000): ");
            String engineID = scanner.nextLine();
            if (!engineID.matches("E\\d{4}")) {
                System.out.println("Invalid engine ID format!");
                return false;
            }
            if (searchEngine(engineID) >= 0) {
                System.out.println("Engine ID already exists!");
                return false;
            }

            Car car = cars.get(pos);
            car.setBrand(brand);
            car.setColor(color);
            car.setFrameID(frameID);
            car.setEngineID(engineID);

            return true;
        }
    }
    
        public void listCars() {
        Collections.sort(this); // Sắp xếp danh sách xe theo tên brand tăng dần

        int N = size();
        if (N > 0) {
            for (int i = 0; i < N; i++) {
                Car car = get(i);
                System.out.println(car.screenString());
            }
        } else {
            System.out.println("No cars found!");
        }
    }

}



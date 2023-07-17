
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CarList extends ArrayList<Car> {

    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    BrandList brandList;

    public CarList(BrandList bList) {
        brandList = bList;
    }

    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return false;
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while ((line = bf.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String carID = stk.nextToken().trim();
                    String brandID = stk.nextToken().trim();
                    String color = stk.nextToken().trim();
                    String frameID = stk.nextToken().trim();
                    String engineID = stk.nextToken().trim();

                    int pos = brandList.searchID(brandID);
                    Brand b = brandList.get(pos);

                    Car newCar = new Car(carID, b, color, frameID, engineID);
                    this.add(newCar);
                }
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public boolean saveToFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            PrintWriter pw = new PrintWriter(fw);

            for (Car car : this) {
                pw.println(car);
            }
            pw.close();
            fw.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public int searchID(String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (carID.equals(this.get(i).getCarID())) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngineID(String searchEngineID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchEngineID.equals(this.get(i).getEngineID())) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrameID(String searchFrameID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchFrameID.equals(this.get(i).getFrameID())) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        String carID, color, frameID, engineID;
        boolean duplicate = false;
        do {
            System.out.println("Input car ID: ");
            carID = scanner.nextLine();
            for (Car car : this) {
                if (car.getCarID().equals(carID)) {
                    duplicate = true;
                    System.out.println("This car ID is existed. Try another carID!");
                    break;
                } else {
                    duplicate = false;
                }
            }
        } while (duplicate);
        Brand brand = (Brand) menu.ref_getChoice(brandList);
        do {
            System.out.println("Input color: ");
            color = scanner.nextLine();
            if (color != null) {
                break;
            }
            System.out.println("The color must not be null. Try again!");
        } while (true);

        do {
            System.out.print("Input frame ID: ");
            frameID = scanner.nextLine();
            if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                break;
            }
            System.out.println("The frame ID must be in F00000 format and not be duplicated. Try again !");
        } while (true);
        do {
            System.out.print("Input engine ID: ");
            engineID = scanner.nextLine();
            if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                break;
            }
            System.out.println("The engine ID must be in E00000 format and not be duplicated. Try again !");
        } while (true);
        this.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("Car has added successfully !");

    }

    public void printBaseBrandName(String aPartOfBrandName) {
        int count = 0;
        for (Car c : this) {
            if (c.getBrand().getBrandName().contains(aPartOfBrandName)) {
                System.out.println(c.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        String removedID;
        System.out.println("Input carID to removed: ");
        removedID = scanner.nextLine();
        int pos = searchID(removedID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            this.remove(pos);
        }
        return true;
    }

    public boolean updateCar() {
        String updateID;
        System.out.println("Input carID to update:");
        updateID = scanner.nextLine();
        int pos = searchID(updateID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            String carID, color, frameID, engineID;
            carID = updateID;
            Brand brand = (Brand) menu.ref_getChoice(brandList);
            do {
                System.out.print("Input color: ");
                color = scanner.nextLine();
                if (color.equals("") != true) {
                    break;
                }
                System.out.println("The color must not be null. Try again !");
            } while (true);

            do {
                System.out.print("Input frame ID: ");
                frameID = scanner.nextLine();
                if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                    break;
                }
                System.out.println("The frame ID must be in F00000 format and not be duplicated. Try again !");
            } while (true);
            do {
                System.out.print("Input engine ID: ");
                engineID = scanner.nextLine();
                if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                    break;
                }
                System.out.println("The engine ID must be in E00000 format and not be duplicated. Try again !");
            } while (true);
            Car car = new Car(carID, brand, color, frameID, engineID);
            this.set(pos, car);
        }
        return true;
    }

    public void listCar() {
        Collections.sort(this);
        for (Car car : this) {
            System.out.println(car.screenString());
        }
    }

    public void brandName(String partialBrandName) {
        int count = 0;
        for (Car car : this) {
            if (car.getBrand().getBrandName().contains(partialBrandName)) {
                System.out.println(car.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No result");
        }
    }
}


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CarManager {

    public static void main(String[] args) throws IOException {
        int choice;
        boolean checkSuccessful;
        String fileCarsName = "C:\\Users\\dell\\Desktop\\Assignment\\src\\cars.txt";
        String fileBrandsName = "C:\\Users\\dell\\Desktop\\Assignment\\src\\brands.txt";
        BrandList brandList = new BrandList();
        CarList carList = new CarList(brandList);
        brandList.loadFromFile(fileBrandsName);
        carList.loadFromFile(fileCarsName);
        String bID, brandCarID;
        ArrayList<String> ops = new ArrayList<>(11);

        ops.add("1 - List all brands");
        ops.add("2 - Add a new brand");
        ops.add("3 - Search a brand based on its ID");
        ops.add("4 - Update a brand");
        ops.add("5 - Save brands to the file, named brands.txt");
        ops.add("6 - List all cars in ascending order of brand names");
        ops.add("7 - List cars based on a part of an input brand name");
        ops.add("8 - Add a car");
        ops.add("9 - Remove a car based on its ID");
        ops.add("10 - Update a car based on its ID");
        ops.add("11 - Save cars to file, named cars.txt");
        ops.add("0- Exits\n");
        Menu menu = new Menu();

        do {
            choice = menu.int_getChoice(ops);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    System.out.println("\n");
                    break;
                case 2:
                    brandList.addBrand();
                    System.out.println("\n");
                    break;
                case 3:
                    System.out.print("\nInput brand ID: ");
                    bID = new Scanner(System.in).nextLine();
                    if (brandList.searchID(bID) == -1) {
                        System.out.println("Brand ID not found !");
                    } else {
                        System.out.println(brandList.get(brandList.searchID(bID)).toString());
                    }
                    System.out.println("\n");
                    break;
                case 4:
                    brandList.updateBrand();
                    System.out.println("\n");
                    break;
                case 5:
                    brandList.saveToFile(fileBrandsName);
                    System.out.println("\n");
                    break;
                case 6:
                    carList.listCar();
                    System.out.println("\n");
                    break;
                case 7:
                    System.out.print("\nInput brand name: ");
                    String partBrandName = new Scanner(System.in).nextLine();
                    carList.printBaseBrandName(partBrandName);
                    System.out.println("\n");
                    break;
                case 8:
                    carList.addCar();
                    System.out.println("\n");
                    break;
                case 9:
                    checkSuccessful = carList.removeCar();
                    if (checkSuccessful) {
                        System.out.println("Car removed successfully !");
                    } else {
                        System.out.println("Car removed unsuccessfully !");
                    }
                    System.out.println("\n");
                    break;
                case 10:
                    checkSuccessful = carList.updateCar();
                    if (checkSuccessful) {
                        System.out.println("Car updated successfully !");
                    } else {
                        System.out.println("Car updated unsuccessfully !");
                    }
                    System.out.println("\n");
                    break;
                case 11:
                    carList.saveToFile(fileCarsName);
                    System.out.println("\n");
                    break;
                case 0:
                    System.out.println("Goodbye!!!");
                    break;
                default:
                    System.out.println("Wrong input! Please try again!");
            }
        } while (choice != 0);
    }

}


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrandList extends ArrayList<Brand>{

    public BrandList() {
        super();
    }

    public void addBrand(String brandID, String brandName, String soundBrand, double price) {
        // Kiểm tra brandID không tồn tại trong danh sách
        for (Brand brand : this) {
            if (brand.getBrandID().equals(brandID)) {
                System.out.println("Brand ID already exists.");
                
            }
        }

        // Kiểm tra brandName không trống
        if (brandName.isEmpty()) {
            System.out.println("Brand name cannot be blank.");
            
        }

        // Kiểm tra soundBrand không trống
        if (soundBrand.isEmpty()) {
            System.out.println("Sound brand cannot be blank.");
            
        }

        // Kiểm tra price > 0
        if (price <= 0) {
            System.out.println("Price must be greater than 0.");
           
        }

        Brand brand = new Brand(brandID, brandName, soundBrand, price);
        this.add(brand);
    }

    public boolean loadFromFile(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String brandID = parts[0].trim();
                    String brandName = parts[1].trim();
                    String soundBrand = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    addBrand(brandID, brandName, soundBrand, price);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Brand brand : this) {
                writer.write(brand.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }

        return true;
    }

    public int searchID(String bID) {
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).getBrandID().equals(bID)) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menu menu = new Menu<>();
        return (Brand) menu.ref_getChoice(this);
    }

    public void updateBrand() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the brand ID: ");
        String brandID = scanner.nextLine();

        int pos = searchID(brandID);
        if (pos < 0) {
            System.out.println("Brand not found!");
        } else {
            System.out.print("Enter the new brand name: ");
            String brandName = scanner.nextLine();

            System.out.print("Enter the new sound brand: ");
            String soundBrand = scanner.nextLine();

            System.out.print("Enter the new price: ");
            double price = scanner.nextDouble();

            scanner.nextLine(); // Clear the newline character from the input buffer

            Brand brand = this.get(pos);
            brand.setBrandName(brandName);
            brand.setSoundBrand(soundBrand);
            brand.setPrice(price);

            System.out.println("Brand updated successfully.");
        }
    }
    
        public void listBrands() {
        int N = this.size();
        for (int i = 0; i < N; i++) {
            System.out.println(this.get(i));
        }
    }
}


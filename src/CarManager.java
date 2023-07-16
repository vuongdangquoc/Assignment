
import Controller.CarShop;
import Controller.Validate;


public class CarManager {

    public static void main(String[] args) {
        
        boolean check = true;
        Menu mn = new Menu(); 
        CarShop cs = new CarShop();
        Validate vl = new Validate();
        mn.add("1- List all brands");
        mn.add("2- Add a new brand");
        mn.add("3- Search a brand based on its ID");
        mn.add("4- Update a brand");
        mn.add("5- Save brands to the file, named brands.txt");
        mn.add("6- List all cars in ascending order of brand names");
        mn.add("7- List cars based on a part of an input brand name");
        mn.add("8- Add a car");
        mn.add("9- Remove a car based on its ID");
        mn.add("10- Update a car based on its ID");
        mn.add("11- Save cars to file, named cars.txt");
        mn.add("12- Exits");
        cs.loadfile();
        while (check) {
            for (String mnn : mn) {
                System.out.println(mnn);
            }
            int choice = vl.inputInt("What is your choice? ", 1, 12);
            switch (choice) {
                case 1:
                    cs.ListAllBrands();
                    break;
                case 2:
                    cs.AddANewBrand();
                    break;
                case 3:
                    cs.SearchABrandBasedOnTtsID();
                    break;
                case 4:
                    cs.UpdateABrand();
                    break;
                case 5:
                    cs.Savebrands();
                    break;
                case 6:
                    cs.Listascending();
                    break;
                case 7:
                    cs.Listcarsbasedname();
                    break;
                case 8:
                    cs.AddCar();
                    break;
                case 9:
                    cs.RemoveCaronID();
                    break;
                case 10:
                    cs.updateCar();
                    break;
                case 11:
                    cs.saveCar();
                    break;
                case 12:
                    System.out.println("Thank you ");
                    check = false;
                    break;

            }

        }
    }
}

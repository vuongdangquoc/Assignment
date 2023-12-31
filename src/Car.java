
public class Car implements Comparable<Car> {

    String carID;
    Brand brand;
    String color;
    String engineID;
    String frameID;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.engineID = engineID;
        this.frameID = frameID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    @Override
    public int compareTo(Car car) {
        int val = this.getBrand().getBrandName().compareTo(car.getBrand().getBrandName());
        if (val == 0) {
            val = this.getCarID().compareTo(car.getCarID());
        }
        return val;
    }

    public String toString() {
        return carID + ", " + brand.brandID + ", " + color + ", " + frameID + ", " + engineID;
    }

    public String screenString() {
        return brand + "\n" + carID + "," + color + "," + frameID + "," + engineID;
    }

}

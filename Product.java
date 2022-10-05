public class Product {
    private static int counter = 1;

    private int id;
    private String name;
    private Double price;

    public Product(String name, Double price) {
        this.id = counter;
        this.name = name;
        this.price = price;

        Product.counter = counter += 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        return "\nId: " + this.getId() +
                "\nName: " + this.getName() +
                "\nPrice: " + this.getPrice();

    }

}
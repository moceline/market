import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Market {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Product> products;
    private static Map<Product, Integer> cart;

    public static void main(String[] args) {
        products = new ArrayList<>();
        cart = new HashMap<>();
        menu();
    }

    private static void menu() {
        System.out.println("********** Market Global *************");
        System.out.println("**********    Welcome!   *************");
        System.out.println("*   Choose one of the options below  *");
        System.out.println("*   1 - Register a new product       *");
        System.out.println("*   2 - List product in stock        *");
        System.out.println("*   3 - Buy  product                 *");
        System.out.println("*   4 - See cart                     *");
        System.out.println("*   5 - Exit                         *");
        System.out.println("**************************************");

        try {
            int option = input.nextInt();

            switch (option) {
                case 1:
                    registerProduct();
                    break;
                case 2:
                    productList();
                    break;
                case 3:
                    buyProduct();
                    break;
                case 4:
                    seeCart();
                    break;
                case 5:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
                    menu();
                    break;
            }

        } catch (Exception e) {
            System.out.println("Invalid option. Try again.");

        }

    }

    private static void registerProduct() {
        try {
            System.out.println("\nRegister the name product:");
            String name = input.next();
            System.out.println("\nRegister the price product:");
            Double price = input.nextDouble();

            Product product = new Product(name, price);
            products.add(product);
        } catch (Exception e) {
            System.out.println("Error");

        } finally {
            menu();
        }

    }

    private static void productList() {
        if (products.size() != 0) {
            for (Product p : products) {
                System.out.println(p);
            }

        }

        menu();
    }

    private static void buyProduct() {
        System.out.println("Write the Id of product:");
        System.out.println("******  Products available   ******");

        // Verify the produtcs available
        if (products.size() != 0) {
            for (Product p : products) {
                System.out.println(p);
            }
        }

        System.out.println("Id of product:");
        int id = input.nextInt();
        Boolean isExists = false;

        for (Product p : products) {

            if (p.getId() == id) {

                int quantity = 0;

                // If the product is available, add in the cart
                try {
                    quantity = cart.get(p);
                    // Add
                    cart.put(p, quantity + 1);

                } catch (NullPointerException e) {
                    cart.put(p, 1);
                }

                isExists = true;

                if (isExists) {
                    System.out.println("product added to cart.");
                    System.out.println("If want to add another product enter 1 or 0 to complete phurchase");
                    int option = input.nextInt();

                    if (option == 1) {
                        buyProduct();
                    } else {
                        finalizePurchase();
                    }
                }

            }

        }

        if (isExists == false) {
            System.out.println("Product not found!");
            menu();
        }
    }

    private static void seeCart() {
        // Check cart
        if (cart.size() > 0) {
            for (Product p : cart.keySet()) {
                System.out.println("Product:" + p.getName() + " The amount.:" + cart.get(p));
            }
        } else {
            System.out.println("There is no product in the cart");
        }

    }

    private static void finalizePurchase() {
        System.out.println("******** Get products ***********");
        Double buyValue = 0.0;
        for (Product p : cart.keySet()) {
            int quantity = cart.get(p);
            buyValue += p.getPrice() * quantity;
            System.out.println("Product:" + p.getName());
            System.out.println("Amount:" + quantity);
        }
        System.out.println("Buy amount is :" + Utility.doubleToString(buyValue));
    }

}

import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Market {
    // private static Scanner input = new Scanner(System.in);
    private static ArrayList<Product> products;
    private static Map<Product, Integer> cart;

    public static void main(String[] args) {
        products = new ArrayList<>();
        cart = new HashMap<>();
        menu();
    }

    private static void menu() {

        try {
            int option = Integer.parseInt(JOptionPane.showInputDialog("********** Market Global *************" +
                    "\nChoose one of the options below " +
                    "   \n1 - Register a new product" +
                    "   \n2 - List product in stock " +
                    "   \n3 - Buy  product" +
                    "   \n4 - See cart    " +
                    "   \n5 - Exit        "));

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
                    JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
                    menu();
                    break;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid option. Try again.");

        }

    }

    private static void registerProduct() {
        try {
            String name = JOptionPane.showInputDialog("\nRegister the name product:");
            Double price = Double.parseDouble(JOptionPane.showInputDialog("\nRegister the price product"));

            Product product = new Product(name, price);
            products.add(product);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");

        } finally {
            menu();
        }

    }

    private static void productList() {
        String newProducts = "";
        if (products.size() != 0) {
            for (Product p : products) {
                newProducts += p.getId() + " - " + p.getName() + " - " + p.getPrice() + "\n";
            }
            JOptionPane.showMessageDialog(null, newProducts);
        }

        menu();
    }

    private static void buyProduct() {
        String newProducts = "";
        // Verify the produtcs available
        if (products.size() != 0) {
            for (Product p : products) {
                newProducts += p.getId() + " - " + p.getName() + " - " + p.getPrice() + "\n";
            }

            int id = Integer
                    .parseInt(JOptionPane.showInputDialog(null, newProducts + "\n" + "Register the Id of product:"));

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
                        JOptionPane.showMessageDialog(null, "product added to cart!!!");

                        int option = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "If want to add another product enter 1 or 0 to complete phurchase"));
                        if (option == 1) {
                            buyProduct();
                        } else {
                            finalizePurchase();
                        }
                    }

                }

            }

            if (isExists == false) {
                JOptionPane.showMessageDialog(null, "Product not found!");
                menu();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Product not found!");
        }
    }

    private static void seeCart() {
        // Check cart
        if (cart.size() > 0) {
            for (Product p : cart.keySet()) {
                JOptionPane.showMessageDialog(null, "Product:" + p.getName() + " The amount.:" + cart.get(p));
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no product in the cart!!!");
        }

    }

    private static void finalizePurchase() {
        Double buyValue = 0.0;
        String strBuy = "";
        for (Product p : cart.keySet()) {
            int quantity = cart.get(p);
            buyValue += p.getPrice() * quantity;
            strBuy += "Product:" + p.getName() + " - Quantity:" + quantity + "\n";
        }
        JOptionPane.showMessageDialog(null,
                "Get Products\n\n" + strBuy + "\nAmount is :" + Utility.doubleToString(buyValue));
    }

}

import java.util.Scanner;
import java.util.List;

// add products
// view all products, list all products from database
// search product by id
// search product by name
// update product
// delete product

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        productDBC Mainproduct = new productDBC();

        int choice;

        do {
            System.out.println("Inventory Management System");
            System.out.println("1. Add products");
            System.out.println("2. View All Products");
            System.out.println("3. Search Product By ID");
            System.out.println("4. Search Product By Name");
            System.out.println("5. Update Product");
            System.out.println("6. Delete Product");
            System.out.println("7. Exit");

            System.out.print("Enter choice : ");
            choice = scan.nextInt();

            scan.nextLine();

            switch (choice) {
                case 1 :
                    System.out.print("Enter name : ");
                    String name = scan.nextLine();

                    System.out.print("Enter price : ");
                    Double price = scan.nextDouble();

                    System.out.print("Enter quantity : ");
                    int quantity = scan.nextInt();

                    Mainproduct.addProducts(new product(name, price, quantity));
                    break;

                case 2:
                    List<product> list = Mainproduct.getAllProducts();
                    if(list.isEmpty()){
                        System.out.print("Products not found");
                    }else{
                        for(product p : list) {
                            System.out.println(p.getId() + " | " + p.getName() + " | "
                                    + p.getPrice() + " | " + p.getQuantity());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to search : ");
                    int id = scan.nextInt();

                    product productById = Mainproduct.getSearchProductByID(id);
                    if(productById != null){
                        System.out.println(productById.getId() + " | "
                                + productById.getName() + " | " + productById.getQuantity());
                    }else {
                        System.out.print("Not Found");
                    }
                    break;

                case 4:
                    System.out.print("Enter Name to search : ");
                    String searchName = scan.nextLine();

                    product productByName = Mainproduct.getSearchByName(searchName);
                    if(productByName != null){
                        System.out.println(productByName.getId() + " | "
                                + productByName.getName() + " | " + productByName.getQuantity());
                    }else {
                        System.out.print("Not Found");
                    }
                    break;

                case 5 :
                    System.out.print("Enter Product ID to update : ");
                    int UpdateId = scan.nextInt();

                    System.out.print("Enter new price : ");
                    Double UpdatePrice = scan.nextDouble();

                    System.out.print("Enter new quantity : ");
                    int UpdateQuantity = scan.nextInt();

                    Mainproduct.updateProduct(UpdateId, UpdatePrice, UpdateQuantity);

                    break;

                case 6 :
                    System.out.print("Enter ID you want to delete : ");
                    int deleteId = scan.nextInt();

                    Mainproduct.deleteProduct(deleteId);
                    break;

                case 7:
                    System.out.println("Exiting program");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        }while (choice != 7);




    }
}
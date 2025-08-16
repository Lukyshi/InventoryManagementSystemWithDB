public class product {
    private int id, quantity;
    private String name;
    private double price;

    product(int id, String name, double price, int quantity){ // connect to add product to database
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    product (String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

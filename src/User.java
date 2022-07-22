import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private float amountMoney;

    private List<Product> products = new ArrayList<Product>();

    public User(int id, String firstName, String lastName, float amountMoney) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountMoney = amountMoney;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public float getAmountMoney() {

        float basketPrice = 0f;

        for(Product product:products) {
            basketPrice += product.getPrice();
        }

        return amountMoney - basketPrice;
    }

    public void setAmountMoney(float amountMoney) {
        this.amountMoney = amountMoney;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", First name: '" + firstName + '\'' +
                ", Last name: '" + lastName + '\'' +
                ", Amount of money: " + getAmountMoney() +
                '}';
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public boolean isProductInBasket(int productId){

        for(Product product:products) {
            if(product.getId() == productId){
                return true;
            }
        }

        return false;
    }

    public void deleteProduct(int productId){
       int index = 0;
       while(index < products.size()){
           if(products.get(index).getId() == productId){
               products.remove(index);
           }
           ++index;
       }
    }

    public List<Product> getProducts() {
        return products;
    }
}

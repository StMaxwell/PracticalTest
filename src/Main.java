import java.util.*;

public class Main {

    private Scanner readConsole;
    private List<User> users = new ArrayList<User>();
    private List<Product> products = new ArrayList<Product>();

    public static void main(String[] args) throws Exception {

        new Main().mainMenu();

    }
    public void mainMenu() throws Exception {

        int selection = -1;

        readConsole = new Scanner(System.in);

        do {
            System.out.println("********* Main menu **********");
            System.out.println("[0] Create demo users and products");
            System.out.println("[1] List of users");
            System.out.println("[2] List of products");
            System.out.println("[3] Add new user");
            System.out.println("[4] Add new product");
            System.out.println("[5] Buy product");
            System.out.println("[6] List of user products by user id");
            System.out.println("[7] List of users that bought product by product id");
            System.out.println("[8] Delete product");
            System.out.println("[9] Delete user");
            System.out.println("[10] Quit");

            System.out.print("Insert selection: ");

            selection = readConsole.nextInt();
            switch (selection) {
                case 0: createDemo();
                    continue;
                case 1: usersList();
                    continue;
                case 2: productsList();
                    continue;
                case 3: addUser();
                    continue;
                case 4: addProduct();
                    continue;
                case 5: buyProduct();
                    continue;
                case 6: listUsersProducts();
                    continue;
                case 7: listUsersByProductId();
                    continue;
                case 8: deleteProduct();
                    continue;
                case 9: deleteUser();
                    continue;
                case 10: break;
                default:
                    System.out.println("The selection was invalid!");
            }
        } while (selection != 10);

        readConsole.close();

    }

    private void usersList(){

        for(User user:users) {
            System.out.println(user);
        }

}

    private void productsList(){

        for(Product product:products) {
            System.out.println(product);
        }
    }

    private void addUser() throws Exception {

        System.out.println("Input first name:");
        readConsole.nextLine(); //cursor to a new line
        String firstName = readConsole.nextLine();
        System.out.println("Input last name:");
        String lastName = readConsole.nextLine();
        System.out.println("Input amount of money (float):");
        //catch exception
        float money = readConsole.nextFloat();

        if(firstName.isEmpty() || lastName.isEmpty()){
            System.out.println("First name and Last name must be filled");
            return;
        }

        User user = new User(getNewUsersID(), firstName, lastName, money);
        users.add(user);
    }

    private void addProduct() throws Exception {

        System.out.println("Input name:");
        readConsole.nextLine(); //cursor to a new line
        String name = readConsole.nextLine();

        System.out.println("Input price (float):");
        //catch exception
        float price = readConsole.nextFloat();

        if(name.isEmpty() || price == 0f){
            System.out.println("Name and Price must be filled");
            return;
        }

        Product product = new Product(getNewProductsID(), name, price);
        products.add(product);

    }

    private int getNewUsersID(){

        int maxId = 0;

        for(User user:users) {
            if(user.getId() > maxId){
                maxId = user.getId();
            }
        }

        return maxId + 1;
    }

    private int getNewProductsID(){

        int maxId = 0;

        for(Product product:products) {
            if(product.getId() > maxId){
                maxId = product.getId();
            }
        }

        return maxId + 1;
    }

    private void buyProduct(){

        System.out.println("Input user's id:");
        readConsole.nextLine(); //cursor to a new line
        int userId = readConsole.nextInt();
        System.out.println("Input product's id:");
        int productId = readConsole.nextInt();

        User user = getUserById(userId);

        if(user == null){
            System.out.println("Don't find User with ID =" + userId);
            return;
        }

        Product product = getProductById(productId);

        if(product == null){
            System.out.println("Don't find Product with ID =" + productId);
            return;
        }

        if(user.getAmountMoney() - product.getPrice() < 0){
            System.out.println("User have no money for " + product.getName());
            return;
        }

        user.addProduct(product);

        System.out.println("user successfully bought the product");

    }

    private User getUserById(int userId){

        for(User user:users) {
            if(user.getId() == userId){
                return user;
            }
        }

        return null;
    }

    private Product getProductById(int productId){

        for(Product product:products) {
            if(product.getId() == productId){
                return product;
            }
        }

        return null;
    }

    private void listUsersByProductId(){

        readConsole.nextLine(); //cursor to a new line
        System.out.println("Input product's id:");
        int productId = readConsole.nextInt();

        for(User user:users) {
            if(user.isProductInBasket(productId)){
                System.out.println(user);
            }
        }

    }

    private void listUsersProducts(){

        readConsole.nextLine(); //cursor to a new line
        System.out.println("Input user's id:");
        int userId = readConsole.nextInt();

        User user = getUserById(userId);

        if(user == null){
            System.out.println("Don't find User with ID =" + userId);
            return;
        }

        for(Product product:user.getProducts()) {
            System.out.println(product);
        }

    }

    private void createDemo(){

        users.add(new User(getNewUsersID(), "Bruce", "Banner", 1000));
        users.add(new User(getNewUsersID(), "Peter", "Parker", 800));
        users.add(new User(getNewUsersID(), "Doctor", "Doom", 500));

        products.add(new Product(getNewProductsID(), "Apple", 200));
        products.add(new Product(getNewProductsID(), "Sword", 300));
        products.add(new Product(getNewProductsID(), "Axe", 400));
    }

    private void deleteProduct(){

        readConsole.nextLine(); //cursor to a new line
        System.out.println("Input product's id:");
        int productId = readConsole.nextInt();

        for(User user:users) {
            user.deleteProduct(productId);
        }

        for(Product product:products) {
            if(product.getId() == productId){
                products.remove(product);
                break;
            }
        }
    }

    private void deleteUser(){

        readConsole.nextLine(); //cursor to a new line
        System.out.println("Input product's id:");
        int userId = readConsole.nextInt();

        for(User user:users) {
            if(user.getId() == userId){
                users.remove(user);
                break;

            }
        }

    }
}

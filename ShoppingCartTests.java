public class ShoppingCartTests {

    public static void main(String args[])
    {
        System.out.println("Creating cart");
        ShoppingCart cart = new ShoppingCart();

        System.out.println("Test add product");
        cart.addItem(1, 2, 3.5);
        assert cart.findProduct(1) != null;
        assert cart.getTotalPrice() == 2 * 3.5;
        assert cart.getTotalQuantity() == 2;
    }

}

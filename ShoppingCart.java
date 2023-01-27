import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {

    //@ spec_public
    private final List<Item> items;

    //@ spec_public
    private double totalPrice;

    //@ spec_public
    private int totalQuantity;

    /*@ public invariant items != null;
            public invariant totalPrice >= 0;
            public invariant totalQuantity >= 0;
            */
    public ShoppingCart() {
        items = new ArrayList<Item>();
    }

    /*@ requires productId > 0;
        requires quantity > 0;
        requires price > 0;
        ensures totalQuantity == \old(totalQuantity) + quantity;
        ensures totalPrice == \old(totalPrice) + (price * quantity);
    */
    public void addItem(int productId, int quantity, double price) {
        Item item = new Item(productId, quantity, price);
        //@ assert !items.contains(item);
        items.add(item);
    }

    /*@ requires productId > 0;
        ensures totalQuantity == \old(totalQuantity) - \old(findProduct(productId)).getQuantity();
        ensures totalPrice == \old(totalPrice) - \old(findProduct(productId)).getTotalPrice();
    */
    public void removeItem(int productId) {
        Item item = findProduct(productId);
        //@ assert items.contains(item);
        items.remove(item);
        //@ assert !items.contains(item);
        totalPrice = computeTotalPrice();
        totalQuantity = computeTotalQuantity();
    }

    /*@ requires productId > 0;
        requires newQuantity > 0;
        ensures items.contains(findProduct(productId));
        ensures findProduct(productId).getQuantity() == newQuantity;
        ensures totalQuantity == \old(totalQuantity) + newQuantity - findProduct(productId).getQuantity();
        ensures totalPrice == \old(totalPrice) + (newQuantity*findProduct(productId).getPrice()) - findProduct(productId).getTotalPrice();
    */
    public void updateItemQuantity(int productId, int newQuantity) {
        Item item = findProduct(productId);
        //@ assert items.contains(item);
        item.setQuantity(newQuantity);
        //@ assert item.getQuantity() == newQuantity;
        totalPrice = computeTotalPrice();
        totalQuantity = computeTotalQuantity();
    }

    /*@ requires productId > 0;
        requires newPrice > 0;
        ensures totalPrice == \old(totalPrice) + (findProduct(productId).getQuantity()*newPrice) - findProduct(productId).getTotalPrice();
    */
    public void updateItemPrice(int productId, double newPrice) {
        Item item = findProduct(productId);
        //@ assert items.contains(item);
        item.setPrice(newPrice);
        //@ assert item.getPrice() == newPrice;
        totalPrice = computeTotalPrice();
        totalQuantity = computeTotalQuantity();
    }

    //@ pure
    public Item findProduct(int productId) {
        Item item = null;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == productId) {
                item = items.get(i);
            }
        }
        return item;
    }

    /*@ pure
        spec_public
    */
    private int computeTotalQuantity() {
        int quantity = 0;
        for (Item item : items) {
            quantity = item.getQuantity();
        }
        return quantity;
    }

    /*@ pure
        spec_public
    */
    private double computeTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total = item.getTotalPrice();
        }
        return total;
    }

    //@ pure
    public double getTotalPrice() {
        return totalPrice;
    }

    //@ pure
    public int getTotalQuantity() {
        return totalQuantity;
    }

    //@ pure
    public Item[] getItems() {
        return (Item[]) items.toArray();
    }

    public static class Item {
        private final int id;
        private int quantity;
        private double price;

        public Item(int id, int quantity, double price) {
            this.id = id;
            this.quantity = quantity;
            this.price = price;
        }

        //@ pure
        public int getId() {
            return id;
        }

        //@ pure
        public int getQuantity() {
            return quantity;
        }

        //@ pure
        public double getPrice() {
            return price;
        }

        //@ pure
        public double getTotalPrice() {
            return price * quantity;
        }

        private void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        private void setPrice(double price) {
            this.price = price;
        }

        //@ pure
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return id == item.id;
        }

        //@ pure
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}


package modul_5.TASK1;

public class InventoryItem {
    private int stock;
    private double price;

    public InventoryItem(int stock, double price) {
        this.stock = stock;
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public double getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "| Stok: " + stock + " | Harga: Rp" + String.format("%,.0f", price);
    }
}

public class Sale {
    private String productName;
    private double unitPrice;
    private int quantity;
    private double totalPrice = unitPrice * quantity;
    private String date;
    private int productId;

    public Sale(String productName, double unitPrice, int quantity, double totalPrice, String date, int productId) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public int getProductId() {
        return productId;
    }

    public String toLine() {
        return productName + ";" + unitPrice + ";" + quantity + ";" + totalPrice + ";" + date + ";" + productId;
    }

    public static Sale fromSale(String line) {
        String[] dataSale = line.split(";");

        String name = dataSale[0];
        double unitPrice = Double.parseDouble(dataSale[1]);
        int quantity = Integer.parseInt(dataSale[2]);
        double totalPrice = Double.parseDouble(dataSale[3]);
        String date = dataSale[4];
        int productId = Integer.parseInt(dataSale[5]);

        return new Sale(name, unitPrice, quantity, totalPrice, date, productId);
    }

    @Override
    public String toString() {
        return String.format("""
                ----------------------
                produto: %s
                preço: %.2f
                quantidade: %d
                total da compra: %.2f
                data: %s
                id: %d
                ----------------------
                """, productName, unitPrice, quantity, totalPrice, date, productId);
    }
}

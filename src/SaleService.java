public class SaleService {
    private SaleRepository repository = new SaleRepository();

    public boolean registerSale(Sale sale) {
        if (repository.findById(sale.getProductId()) != null) {
            System.out.println("id da venda já registada no sistema");
            return false;
        }
        repository.save(sale);
        return true;
    }

    public boolean updateSale(Sale sale) {
        if (repository.findById(sale.getProductId()) == null) {
            System.out.println("venda não registrada no sistema");
            return false;
        }
        repository.update(sale);
        return true;
    }

    public boolean deleteSale(int productId) {
        if (repository.findById(productId) == null) {
            System.out.println("venda não registrada no sistema");
            return false;
        }
        repository.deleteById(productId);
        return true;
    }

    public void listSales() {
        for (Sale sale : repository.findAll()) {
            System.out.println(sale.getProductName() + " - " + sale.getUnitPrice() + " - " + sale.getQuantity() + " - " + sale.getTotalPrice() + " - " + sale.getDate() + " - " + sale.getProductId());
        }
    }

    public Sale searchById(int id) {
        return repository.findById(id);
    }
}

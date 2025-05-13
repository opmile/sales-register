import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaleRepository {
    private static final String SALES_FILE = "sales.txt";

    public void save(Sale sale) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SALES_FILE, true))) {
            bw.write(sale.toLine());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("erro ao salvar");
        }
    }

    public List<Sale> findAll() {
        List<Sale> sales = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SALES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                sales.add(Sale.fromSale(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("erro ao salvar");
        }
        return sales;
    }

    public void update(Sale updatedSale) {
        List<Sale> sales = findAll();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SALES_FILE))) {
            for (Sale sale : sales) {
                if (sale.getProductId() == updatedSale.getProductId()) {
                    bw.write(updatedSale.toLine());
                } else {
                    bw.write(sale.toLine());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("erro ao salvar");
        }
    }

    public void deleteById(int productId) {
        List<Sale> sales = findAll();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SALES_FILE))) {
            for (Sale sale : sales) {
                if (sale.getProductId() != productId) {
                    bw.write(sale.toLine());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("erro ao salvar");
        }
    }

    public Sale findById(int productId) {
        for (Sale sale : findAll()) {
            if (sale.getProductId() == productId) {
                return sale;
            }
        }
        return null;
    }
}

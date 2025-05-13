import java.util.Scanner;

public class SaleController {
    private SaleService service = new SaleService();
    private Scanner scanner = new Scanner(System.in);
    private static final String PASSCODE_ADMIN = "milena@email.com";

    public void run() {
        int option = 1;
        System.out.println("""
                    1 - registrar nova venda
                    2 - listar vendas
                    3 - atualizar venda
                    4 - excluir venda
                    0 - encerrar programa
                    """);
        do {
            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        System.out.println("nome do produto: ");
                        String productName = scanner.nextLine();
                        System.out.println("preço: ");
                        double unitPrice = Double.parseDouble(scanner.nextLine());
                        System.out.println("quantidade: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        double totalPrice = unitPrice * quantity;
                        System.out.println("data da compra: ");
                        String date = scanner.nextLine();
                        System.out.println("id do produto: ");
                        int productId = Integer.parseInt(scanner.nextLine());
                        Sale newSale = new Sale(productName, unitPrice, quantity, totalPrice, date, productId);
                        if (service.registerSale(newSale)) {
                            System.out.println("venda registrada: ");
                            System.out.println(newSale);
                        }
                        break;
                    case 2:
                        System.out.println("registro de vendas: ");
                        service.listSales();
                        break;
                    case 3:
                        System.out.println("digite o id da venda: ");
                        int updateById = Integer.parseInt(scanner.nextLine());

                        if (service.searchById(updateById) == null) {
                            System.out.println("venda não encontrada");
                            break;
                        } else {
                            System.out.println("venda encontrada!");
                            System.out.println(service.searchById(updateById));
                        }

                        System.out.println("novo nome: ");
                        String newName = scanner.nextLine();
                        System.out.println("novo preço: ");
                        double newUnitPrice = Double.parseDouble(scanner.nextLine());
                        System.out.println("nova quantidade: ");
                        int newQuantity = Integer.parseInt(scanner.nextLine());
                        double newTotalPrice = newUnitPrice * newQuantity;
                        System.out.println("nova data: ");
                        String newDate = scanner.nextLine();
                        Sale updatedSale = new Sale(newName, newUnitPrice, newQuantity, newTotalPrice, newDate, updateById);
                        service.updateSale(updatedSale);
                        break;
                    case 4:
                        System.out.println("digite o id da venda: ");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        System.out.println("digite sua senha de admin: ");
                        String authPasscode = scanner.nextLine();
                        if (authPasscode.equals(PASSCODE_ADMIN)) {
                            service.deleteSale(deleteId);
                            System.out.println("venda deletada com sucesso");
                        } else {
                            System.out.println("autenticação falhou! tente novamente");
                        }
                        break;
                    default:
                        System.out.println("encerrando programa");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("digite uma entrada válida");
            } catch (NullPointerException e) {
                System.out.println("não foi possível salvar a venda");
            }
        }   while (option != 0);
    }
}
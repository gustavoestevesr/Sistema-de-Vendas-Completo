package Aplicativos;
import java.io.IOException;
import java.util.Scanner;

import Service.SistemaImpl;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner ler = new Scanner(System.in);
        String opcao;
        SistemaImpl controller = new SistemaImpl();

        do{            
            opcao = mostrarMenu(ler);

            switch (opcao) {
                case "1":
                    controller.incluirProduto(ler);
                    break;

                case "2":
                    controller.consultarProduto(ler);
                    break;

                case "3":
                    controller.listagemProdutos(ler);
                    break;

                case "4":
                    controller.relatorio(ler);
                    break;

                case "5":
                    controller.realizarVenda(ler);
                    break;

                case "0":
                    System.out.println("Saindo do sistema ... ");
                    break;
            
                default:
                    System.out.println("Opçao invalida ... ");
                    ler.nextLine(); // aguardar um ENTER
                    break;
            }
        } while( !opcao.equals("0") );
        

        ler.close();
    }

    public static String mostrarMenu(Scanner ler) throws InterruptedException, IOException{
        limparTela();
        System.out.println("1 - Incluir produto");
        System.out.println("2 - Consultar produto");
        System.out.println("3 - Listagem de produtos");
        System.out.println("4 - Relatorio");
        System.out.println("5 - Realizar venda");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
        String opcao = ler.nextLine();
        return opcao;
    }

    public static void limparTela() throws InterruptedException, IOException{
        // Limpa toda a tela
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}

package Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

import Model.Produto;
import Model.Venda;

public class SistemaImpl implements Funcionalidades {

    List<Produto> lista_de_produtos = new ArrayList<>();
    List<Venda> lista_de_vendas = new ArrayList<>();
    LocalDate hoje = LocalDate.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void incluirProduto(Scanner ler) {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("       ##################### CADASTRO DE PRODUTO #####################");
        System.out.println("---------------------------------------------------------------------------------");

        try {
            System.out.print("CODIGO DO PRODUTO: ");
            String codigo = ler.nextLine();
            System.out.print("NOME DO PRODUTO: ");
            String nome = ler.nextLine();
            System.out.print("VALOR DO PRODUTO: ");
            double valor = ler.nextDouble();
            System.out.print("QUANTIDADE EM ESTOQUE DO PRODUTO: ");
            int qtdEstoque = ler.nextInt();

            ler.nextLine(); // Remover um ENTER

            Produto produto = new Produto(codigo, nome, valor, qtdEstoque); // criando um unico produto

            lista_de_produtos.add(produto); // produto adicionado na lista

            System.out.println("Produto cadastrado com sucesso!");
            System.out.println("\nPressione ENTER para voltar ao menu ... ");
            ler.nextLine(); // Aguardar um ENTER
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage() );
            ler.nextLine(); // Aguardar um ENTER
        }
    }

    public void consultarProduto(Scanner ler) {

        if (lista_de_produtos.isEmpty()) {
            System.out.println("Lista de produtos está vazia!");
            ler.nextLine(); // Aguardar um ENTER
        } else {
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("       ##################### CONSULTAR PRODUTO #####################");
            System.out.println("---------------------------------------------------------------------------------");

            System.out.print("DIGITE O CODIGO DO PRODUTO: ");
            String codigo = ler.nextLine();

            boolean achei = false;
            Produto produtoProcurando = null;
            for (Produto produto : lista_de_produtos) {
                if (produto.getCodigo().equals(codigo)) {
                    achei = true;
                    produtoProcurando = produto;
                    continue;                    
                }
            }            

            if (achei) {
                System.out.printf("%s %25s %20s %25s\n", "CODIGO", "NOME", "VALOR", "QTD ESTOQUE");
                System.out.println("---------------------------------------------------------------------------------");
                System.out.printf("%3s %28s %20.2f %23d\n", produtoProcurando.getCodigo(), produtoProcurando.getNome(),
                        produtoProcurando.getValor(), produtoProcurando.getQtdEstoque());
            } else {
                System.out.println("Produto nao encontrado ...");
            }

            System.out.println("\nPressione ENTER para voltar ao menu ... ");
            ler.nextLine(); // Aguardar um ENTER
        }

    }

    public void listagemProdutos(Scanner ler) {

        if (lista_de_produtos.isEmpty()) {
            System.out.println("Lista de produtos está vazia!");
            ler.nextLine(); // Aguardar um ENTER
        } else {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("       ##################### LISTAGEM DE PRODUTOS #####################");
            System.out.println("---------------------------------------------------------------------------------");

            System.out.printf("%s %25s %20s %25s\n", "CODIGO", "NOME", "VALOR", "QTD ESTOQUE");
            System.out.println("---------------------------------------------------------------------------------");
            lista_de_produtos.stream()
                    .forEach(
                            item -> System.out.printf("%3s %28s %20.2f %23d\n", item.getCodigo(), item.getNome(),
                                    item.getValor(), item.getQtdEstoque()));
        }

        System.out.println("---------------------------------------------------------------------------------");
        DoubleSummaryStatistics resumo = lista_de_produtos.stream()
                .collect(Collectors.summarizingDouble(Produto::getValor));
        System.out.println("# RESUMO:");
        System.out.printf("Valor médio %.2f, máximo %.2f e mínimo %.2f", resumo.getAverage(), resumo.getMax(),
                resumo.getMin());

        System.out.println("\n\nPressione ENTER para voltar ao menu ... ");
        ler.nextLine(); // Aguardar um ENTER
    }

    public void relatorio(Scanner ler) throws DataFormatException {

        if (lista_de_vendas.isEmpty()) {
            System.out.println("Lista de vendas está vazia!");
            ler.nextLine(); // Aguardar um ENTER
        } else {
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("       ##################### RELATORIO DETALHADO #####################");
            System.out.println("---------------------------------------------------------------------------------");

            LocalDate dataVendaInicioData;
            LocalDate dataVendaFimData;

            String dataVendaInicioString;
            String dataVendaFimString;

            try {
                System.out.print("DIGITE A DATA INICIO DO RELATORIO / APERTE 'ENTER' PARA PEGAR AUTOMATICAMENTE: ");
                dataVendaInicioString = ler.nextLine();

                if (dataVendaInicioString.equals("")) {
                    dataVendaInicioData = LocalDate.now();
                    dataVendaInicioData.format(dtf);
                } else {
                    dataVendaInicioData = LocalDate.parse(dataVendaInicioString);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("\n\nPressione ENTER para voltar ao menu ... ");
                ler.nextLine(); // Aguardar um ENTER
                return;
            }

            try {
                System.out.print("DIGITE A DATA FINAL DO RELATORIO / APERTE 'ENTER' PARA PEGAR AUTOMATICAMENTE: ");
                dataVendaFimString = ler.nextLine();

                if (dataVendaFimString.equals("")) {
                    dataVendaFimData = LocalDate.now();
                    dataVendaFimData.format(dtf);
                } else {
                    dataVendaFimData = LocalDate.parse(dataVendaFimString);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("\n\nPressione ENTER para voltar ao menu ... ");
                ler.nextLine(); // Aguardar um ENTER
                return;
            }

            if (dataVendaInicioData.compareTo(dataVendaFimData) == 1) {
                throw new DataFormatException("A data inicial deve ser menor ou igual à data final");
            }
            System.out.print("---------------------------------------------------------------------------------");
            System.out.printf("\n############### Período de emissao: %s até %s #################\n",
                    dataVendaInicioData.format(dtf), dataVendaFimData.format(dtf));
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("%5s\t%20s\t%15s\t%20s\t%20s\n", "Data", "Produto", "Quantidade", "Valor (R$)",
                    "Valor total (R$)");
            System.out.println(
                    "---------------------------------------------------------------------------------------------");
            lista_de_vendas.stream()
                    .filter(v -> v.getDataVenda().compareTo(dataVendaInicioData) >= 0
                            && v.getDataVenda().compareTo(dataVendaFimData) <= 0)
                    .forEach(v -> System.out.printf("%10s\t%12s\t%15d\t%20.2f\t%20.2f\n", v.getDataVenda().format(dtf),
                            v.getProduto().getNome(), v.getQtdVendida(), v.getProduto().getValor(),
                            v.getQtdVendida() * v.getProduto().getValor()));

            System.out.println(
                    "---------------------------------------------------------------------------------------------");
            System.out.println("# RESUMO:");
            Double media = lista_de_vendas.stream()
                    .filter(v -> v.getDataVenda().compareTo(dataVendaInicioData) >= 0
                            && v.getDataVenda().compareTo(dataVendaFimData) <= 0)
                    .collect(Collectors.averagingDouble(
                            venda -> venda.getProduto().getValor() * venda.getQtdVendida()));
            System.out.printf("Valor médio das vendas no período: R$ %.2f\n", media);

            System.out.println("\n\nPressione ENTER para voltar ao menu ... ");
            ler.nextLine(); // Aguardar um ENTER
        }

    }

    public void realizarVenda(Scanner ler) {
        if (lista_de_produtos.isEmpty()) {
            System.out.println("Lista de produtos está vazia!");
            ler.nextLine(); // Aguardar um ENTER
        } else {
            LocalDate dataVendaData;

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("       ##################### REALIZAR VENDA #####################");
            System.out.println("---------------------------------------------------------------------------------");

            System.out.print("DIGITE O CODIGO DO PRODUTO QUE DESEJA VENDER: ");
            String codigo = ler.nextLine();

            System.out.print("DIGITE A QUANTIDADE DO PRODUTO QUE DESEJA VENDER: ");
            int qtdVenda = ler.nextInt();

            ler.nextLine(); // remover enter

            try {
                System.out.print(
                        "DIGITE A DATA DA VENDA DO PRODUTO / APERTE 'ENTER' PARA PEGAR AUTOMATICAMENTE QUE DESEJA VENDER: ");
                String dataVendaString = ler.nextLine();

                if (dataVendaString.equals("")) {
                    dataVendaData = LocalDate.now();
                    dataVendaData.format(dtf);
                } else {
                    dataVendaData = LocalDate.parse(dataVendaString, dtf);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("\n\nPressione ENTER para voltar ao menu ... ");
                ler.nextLine(); // Aguardar um ENTER
                return;
            }

            boolean achei = false;
            Produto produtoProcurando = null;
            for (Produto produto : lista_de_produtos) {
                if (produto.getCodigo().equals(codigo)) {
                    achei = true;
                    produtoProcurando = produto;
                }
            }

            boolean temEstoque = false;
            if (achei) {
                if (produtoProcurando.getQtdEstoque() >= qtdVenda) {
                    temEstoque = true;
                }
            } else {
                System.out.println("Produto nao encontrado ...");
            }

            if (temEstoque) {
                // subtraindo o estoque
                int qtdEstoque = produtoProcurando.getQtdEstoque() - qtdVenda;
                produtoProcurando.setQtdEstoque(qtdEstoque);

                // registrando venda
                Venda venda = new Venda(dataVendaData, produtoProcurando, qtdVenda);
                lista_de_vendas.add(venda);

                System.out.println("Venda realizada com sucesso!");
            } else {
                System.out.printf("\nEstoque insuficiente! Estoque atual %d", produtoProcurando.getQtdEstoque());
            }

            System.out.println("\nPressione ENTER para voltar ao menu ... ");
            ler.nextLine(); // Aguardar um ENTER
        }

    }

}
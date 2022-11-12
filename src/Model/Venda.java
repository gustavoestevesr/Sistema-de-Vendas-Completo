package Model;

import java.time.LocalDate;

public class Venda {

    // atributos
    private LocalDate dataVenda;
    private Produto produto;
    private int qtdVendida;
    private static LocalDate hoje;

    public Venda(LocalDate hoje) {
        this.setHoje(LocalDate.now());
    }

    public static LocalDate getHoje() {
        return hoje;
    }

    public void setHoje(LocalDate hoje) {
        Venda.hoje = hoje;
    }

    private double valorTotal;
    
    // construtor
    public Venda(LocalDate dataVenda, Produto produto, int qtdVendida) {
        this.dataVenda = dataVenda;
        this.produto = produto;
        this.qtdVendida = qtdVendida;
        this.valorTotal += qtdVendida * produto.getValor();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    // imprimir os atributos da venda
    // "DATA DA VENDA", "NOME", "QUANTIDADE VENDIDA", "VALOR UNITARIO", "VALOR TOTAL");   
    @Override
    public String toString() {
        return produto.getNome() + " " + getQtdVendida() + " " + produto.getValor() + " " + produto.getValor() * getQtdVendida();
    }

    // encapsulamento
    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtdVendida() {
        return qtdVendida;
    }

    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }


}

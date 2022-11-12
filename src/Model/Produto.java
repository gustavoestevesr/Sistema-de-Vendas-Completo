package Model;

import java.util.InputMismatchException;
import java.util.Scanner;

// classe
public class Produto {

    // atributos
    private String codigo;
    private String nome;
    private double valor;
    private int qtdEstoque;
    private Scanner ler;
    
    // construtor
    public Produto(String codigo, String nome, double valor, int qtdEstoque) {
        setCodigo(codigo);
        setNome(nome);
        setValor(valor);
        setQtdEstoque(qtdEstoque);
    }

    public Scanner getLer() {
        return ler;
    }

    public void setLer(Scanner ler) {
        this.ler = ler;
    }

    // Imprimir atributos do objeto
    @Override
    public String toString() {
        //return String.format(format, args)
        return "Produto [codigo=" + codigo + ", nome=" + nome + ", qtdEstoque=" + qtdEstoque + ", valor=" + valor + "]";
    }

    // Encapsulamento
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (!codigo.matches("[0-9]+")) {
            throw new InputMismatchException("Código inválido, necessário ser númerico");
        }
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if ( nome.isEmpty() ) {
            throw new InputMismatchException("Não é possível cadastrar um produto sem nome!");
        }
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if( valor < 0 ){
            throw new IllegalArgumentException("Permitido apenas valores positivos!");
        }
        this.valor = valor;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        if( qtdEstoque < 0 ){
            throw new IllegalArgumentException("Permitido apenas valores positivos!");
        }
        this.qtdEstoque = qtdEstoque;
    }

}

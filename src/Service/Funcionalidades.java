package Service;

import java.util.Scanner;
import java.util.zip.DataFormatException;

public interface Funcionalidades {
    public void incluirProduto(Scanner ler) ;
    public void consultarProduto(Scanner ler);
    public void listagemProdutos(Scanner ler);
    public void relatorio(Scanner ler) throws DataFormatException;
    public void realizarVenda(Scanner ler);
}

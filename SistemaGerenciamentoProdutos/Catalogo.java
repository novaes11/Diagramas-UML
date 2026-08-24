package src.POO.SistemaGerenciamentoProdutos;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe genérica responsável pelo gerenciamento de uma coleção de produtos.
 * Utiliza Generics para permitir a manipulação de qualquer tipo de objeto que estenda a classe {@link Produto}.
 *
 * @param <T> Tipo do produto gerenciado pelo catálogo.
 * @author Joao Vitor Novaes
 * @version 1.0
 */
public class Catalogo<T extends Produto> {

    /**
     * Lista interna que armazena os produtos do catálogo.
     */
    private List<T> lista;

    /**
     * Construtor padrão da classe Catalogo.
     * Inicializa a lista de produtos como um ArrayList vazio.
     */
    public Catalogo() {
        this.lista = new ArrayList<>();
    }

    /**
     * Adiciona um novo produto ao catálogo.
     *
     * @param produto O objeto produto a ser adicionado à lista.
     */
    public void adicionar(T produto) {
        lista.add(produto);
    }

    /**
     * Remove um produto do catálogo com base no seu código único.
     * Busca o produto e, caso encontrado, remove-o da lista.
     *
     * @param codigo O código do produto a ser removido.
     * @throws IllegalArgumentException Caso o produto não seja encontrado na lista.
     */
    public void removerPorCodigo(String codigo) {
        if (verificadorCodigo(codigo)) {
            System.out.print("Removido em instantes! " + buscarPorCodigo(codigo));
            // Utiliza stream para localizar e remover o objeto específico
            lista.remove(lista.stream()
                    .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                    .findFirst()
                    .get());
            System.out.println("Produto removido com sucesso!");
        } else {
            throw new IllegalArgumentException("Produto não encontrado!");
        }
    }

    /**
     * Busca um produto no catálogo pelo seu código.
     *
     * @param codigo O código identificador do produto.
     * @return O objeto produto correspondente ao código informado.
     * @throws IllegalArgumentException Caso nenhum produto com o código informado seja encontrado.
     */
    public T buscarPorCodigo(String codigo) {
        if (!verificadorCodigo(codigo)) {
            throw new IllegalArgumentException("Produto não encontrado!");
        }
        System.out.println("Produto encontrado! ");
        return lista.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .get();
    }

    /**
     * Verifica se existe algum produto cadastrado com o código fornecido.
     *
     * @param codigo O código a ser verificado.
     * @return true se o código existir no catálogo, false caso contrário.
     */
    public boolean verificadorCodigo(String codigo) {
        return lista.stream().anyMatch(p -> p.getCodigo().equalsIgnoreCase(codigo));
    }

    /**
     * Exibe no console todos os produtos atualmente cadastrados no catálogo.
     * Utiliza o método toString() de cada produto para a impressão.
     */
    public void listarProdutos() {
        lista.forEach(System.out::println);
    }
}
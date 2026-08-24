package src.POO.SistemaGerenciamentoProdutos;

/**
 * Classe abstrata que representa a base de um produto no sistema.
 * Implementa a interface {@link Vendavel}, definindo comportamentos comuns
 * como cálculo de valor total e registro de vendas, além de gerenciar
 * atributos básicos como código, descrição e preço.
 *
 * @author Joao Vitor Novaes
 * @version 1.0
 */
public abstract class Produto implements Vendavel {

    private String codigo;
    private String descricao;
    private double preco;

    /**
     * Construtor da classe Produto.
     * Inicializa os atributos básicos e valida o preço inicial.
     *
     * @param codigo    Código único identificador do produto.
     * @param descricao Breve descrição ou nome do produto.
     * @param preco     Preço unitário do produto.
     * @throws IllegalArgumentException Caso o preço informado seja menor ou igual a zero.
     */
    public Produto(String codigo, String descricao, double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Valor inserido invalido! Preço deve ser maior que 0.");
        }
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Atualiza o preço do produto.
     *
     * @param preco Novo preço a ser definido.
     * @throws IllegalArgumentException Caso o novo preço seja menor ou igual a zero.
     */
    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que 0.");
        }
        this.preco = preco;
    }

    /**
     * Obtém o código do produto.
     *
     * @return O código identificador.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return A descrição do produto.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obtém o preço atual do produto.
     *
     * @return O preço unitário.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Método abstrato para cálculo de descontos.
     * Cada subclasse deve implementar sua própria regra de negócio para aplicação de desconto.
     *
     * @param percentual O percentual de desconto a ser aplicado.
     * @return O novo preço após o desconto.
     */
    abstract double calcularDesconto(double percentual);

    /**
     * Registra a venda de uma determinada quantidade do produto.
     * Exibe no console os detalhes da transação e o valor total a pagar.
     *
     * @param quantidade A quantidade de itens a serem vendidos.
     * @throws IllegalArgumentException Caso a quantidade seja negativa.
     */
    @Override
    public void vender(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Valor inserido invalido! Quantidade dever ser maior que 0.");
        }
        System.out.println("Venda registrada! " + quantidade + " unidade(s) de " + getDescricao() +
                ". Total unitário: R$" + String.format("%.2f", getPreco()) +
                "\nTotal a pagar: R$" + String.format("%.2f", calcularValorTotal(quantidade)));
    }

    /**
     * Calcula o valor monetário total com base na quantidade informada.
     *
     * @param quantidade A quantidade de itens.
     * @return O valor total (preço unitário * quantidade).
     * @throws IllegalArgumentException Caso a quantidade seja negativa.
     */
    @Override
    public double calcularValorTotal(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Valor inserido invalido! Quantidade dever ser maior que 0.");
        }
        return getPreco() * quantidade;
    }

    /**
     * Retorna uma representação em String do produto.
     *
     * @return String formatada contendo código, descrição e preço.
     */
    @Override
    public String toString() {
        return "[" + codigo + "]" +
                " " + descricao + " - " +
                "R$ " + String.format("%.2f", preco);
    }
}
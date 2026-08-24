package src.POO.SistemaGerenciamentoProdutos;

/**
 * Interface que define o contrato para objetos que podem ser comercializados no sistema.
 * Garante que qualquer item vendável possua comportamentos para efetivar uma venda
 * e calcular valores totais com base em quantidades.
 *
 * @author Joao Vitor Novaes
 * @version 1.0
 */
public interface Vendavel {

    /**
     * Realiza o procedimento de venda de uma determinada quantidade de itens.
     * Implementações devem tratar a validação da quantidade e o registro da operação.
     *
     * @param quantidade A quantidade de itens a serem vendidos.
     */
    void vender(int quantidade);

    /**
     * Calcula o valor monetário total para uma quantidade específica de itens.
     * Útil para orçamentos ou para exibir o total a pagar antes da efetivação da venda.
     *
     * @param quantidade A quantidade de itens para o cálculo.
     * @return O valor total calculado (Preço Unitário * Quantidade).
     */
    double calcularValorTotal(int quantidade);
}
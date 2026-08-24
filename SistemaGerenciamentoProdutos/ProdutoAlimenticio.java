package src.POO.SistemaGerenciamentoProdutos;

/**
 * Representa um produto do tipo Alimentício no sistema.
 * Estende a classe abstrata {@link Produto}, adicionando o atributo de data de validade
 * e implementando regras específicas de desconto para esta categoria.
 *
 * @author Joao Vitor Novaes
 * @version 1.0
 */
public class ProdutoAlimenticio extends Produto {

    private String dataValidade;

    /**
     * Construtor da classe ProdutoAlimenticio.
     *
     * @param codigo       Código único do produto.
     * @param descricao    Descrição do produto.
     * @param preco        Preço unitário.
     * @param dataValidade Data de validade do produto (formato texto, ex: "31/12/2025").
     */
    public ProdutoAlimenticio(String codigo, String descricao, double preco, String dataValidade) {
        super(codigo, descricao, preco);
        this.dataValidade = dataValidade;
    }

    /**
     * Calcula e aplica um desconto ao preço do produto alimentício.
     * Esta categoria possui uma restrição de negócio onde o desconto máximo permitido é de 15%.
     *
     * @param percentual O percentual de desconto a ser aplicado (entre 1 e 15).
     * @return O novo preço do produto após a aplicação do desconto.
     * @throws IllegalArgumentException Caso o percentual seja menor que 1 ou maior que 15.
     */
    @Override
    double calcularDesconto(double percentual) {
        if (percentual > 15 || percentual < 1) {
            throw new IllegalArgumentException("Percentual de desconto permitido apenas entre 0 e 15.");
        }

        // Aplica o desconto atualizando o estado do objeto
        setPreco(getPreco() - (getPreco() * percentual / 100));

        System.out.println("Desconto aplicado com sucesso! Novo preço do produto '" + getDescricao() + "': " + String.format("%.2f", getPreco()));
        return getPreco();
    }

    /**
     * Retorna a representação textual do produto alimentício.
     * Adiciona o prefixo [ALIMENTICIO] e a data de validade à representação padrão.
     *
     * @return String formatada com os dados do produto.
     */
    @Override
    public String toString() {
        return "[ALIMENTICIO] " + super.toString() + " (Validade: " + dataValidade + ")";
    }
}
package src.POO.SistemaGerenciamentoProdutos;

/**
 * Representa um produto do tipo Eletrônico no sistema.
 * Estende a classe abstrata {@link Produto}, adicionando o controle de garantia em meses
 * e implementando regras específicas de desconto para esta categoria.
 *
 * @author Joao Vitor Novaes
 * @version 1.0
 */
public class ProdutoEletronico extends Produto {

    private int garantiaMeses;

    /**
     * Construtor da classe ProdutoEletronico.
     *
     * @param codigo        Código único do produto.
     * @param descricao     Descrição do produto.
     * @param preco         Preço unitário.
     * @param garantiaMeses Tempo de garantia do produto em meses.
     */
    public ProdutoEletronico(String codigo, String descricao, double preco, int garantiaMeses) {
        super(codigo, descricao, preco);
        this.garantiaMeses = garantiaMeses;
    }

    /**
     * Calcula e aplica um desconto ao preço do produto eletrônico.
     * Esta categoria permite uma margem de desconto maior, de até 30%.
     *
     * @param percentual O percentual de desconto a ser aplicado (entre 1 e 30).
     * @return O novo preço do produto após a aplicação do desconto.
     * @throws IllegalArgumentException Caso o percentual seja menor que 1 ou maior que 30.
     */
    @Override
    double calcularDesconto(double percentual) {
        if (percentual > 30 || percentual < 1) {
            throw new IllegalArgumentException("Percentual de desconto permitido apenas entre 0 e 30.");
        }

        // Aplica o desconto atualizando o estado do objeto
        setPreco(getPreco() - (getPreco() * percentual / 100));

        System.out.println("Desconto aplicado com sucesso! Novo preço do produto '" + getDescricao() + "': R$" + String.format("%.2f", getPreco()));
        return getPreco();
    }

    /**
     * Retorna a representação textual do produto eletrônico.
     * Adiciona o prefixo [ELETRONICO] e a informação de garantia à representação padrão.
     *
     * @return String formatada com os dados do produto.
     */
    @Override
    public String toString() {
        return "[ELETRONICO] " + super.toString() + " (Garantia: " + garantiaMeses + " meses)";
    }
}
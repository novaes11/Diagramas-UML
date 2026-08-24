package src.POO.SistemaPagamento;

/**
 * Interface que define o contrato para diferentes métodos de pagamento.
 * Estabelece os comportamentos essenciais que toda forma de pagamento deve ter.
 */
public interface Pagamento {
    /**
     * Armazena o timestamp formatado no momento da criação da implementação.
     */
    String dataHoraLimpa = GerenciadorTimestamp.getTimestampFormatado();

    /**
     * Método abstrato para processar um pagamento.
     * A implementação deve definir como o valor é deduzido do saldo.
     *
     * @param valor O valor a ser pago.
     */
    void pagar(double valor);

    /**
     * Gera um recibo formatado para a transação.
     * Este é um método padrão que pode ser usado por todas as implementações.
     *
     * @param valor O valor original do pagamento.
     * @return Uma string contendo os detalhes da transação.
     */
    default String recibo(double valor){
        return "\n--RECIBO--" +
                "\nValor: " + String.format("%.2f", valor) +
                "\nTaxa: " + String.format("%.1f", taxaAplicada() * 100) + "%" +
                "\nTotal: " + String.format("%.2f", valor * (1 + taxaAplicada())) +
                "\nMeio de Pagamento: " + this +
                "\nData e hora: " + dataHoraLimpa;
    }

    /**
     * Retorna a taxa de transação aplicável.
     * O método padrão retorna 0, indicando nenhuma taxa.
     * As implementações podem sobrescrever este método para definir taxas específicas.
     *
     * @return A taxa de transação como um valor double (ex: 0.05 para 5%).
     */
    default double taxaAplicada(){
        return 0.0;
    }
}

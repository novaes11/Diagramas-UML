package src.POO.SistemaPagamento;

/**
 * Implementação da interface de Pagamento para transações com Cartão.
 * Aplica uma taxa fixa de 2.9% sobre o valor do pagamento.
 */
public class Cartao implements Pagamento{
    private double saldo;
    String dataHoraLimpa = GerenciadorTimestamp.getTimestampFormatado();

    /**
     * Construtor que inicializa o pagamento com Cartão com um saldo inicial.
     *
     * @param saldo O saldo inicial da conta.
     */
    public Cartao(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtém o saldo atual da conta.
     *
     * @return O saldo atual.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Define o saldo da conta.
     *
     * @param saldo O novo saldo.
     * @return A própria instância de Cartao.
     */
    public Cartao setSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    }

    /**
     * Processa um pagamento, deduzindo o valor mais a taxa do saldo.
     *
     * @param valor O valor a ser pago.
     * @throws IllegalArgumentException Se o saldo for insuficiente para cobrir o valor e a taxa.
     */
    @Override
    public void pagar(double valor) {
        if(this.saldo - (valor * (1 + taxaAplicada())) < 0){
            throw new IllegalArgumentException("Transação invalida. Saldo insuficiente.");
        }

        this.saldo -= valor * (1 + taxaAplicada());
        System.out.println(recibo(valor) +
                "\nNovo saldo: " + String.format("%.2f", this.saldo));
    }

    /**
     * Retorna a taxa aplicada a este método de pagamento.
     *
     * @return 0.029 (2.9%).
     */
    @Override
    public double taxaAplicada() {
        return 0.029;
    }

    /**
     * Retorna o nome do método de pagamento.
     *
     * @return A string "Cartão".
     */
    @Override
    public String toString() {
        return "Cartão";
    }
}

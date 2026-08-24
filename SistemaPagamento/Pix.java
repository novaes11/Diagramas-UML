package src.POO.SistemaPagamento;

/**
 * Implementação da interface de Pagamento para transações via Pix.
 * O Pix não possui taxa.
 */
public class Pix implements Pagamento{
    private double saldo;
    String dataHoraLimpa = GerenciadorTimestamp.getTimestampFormatado();

    /**
     * Construtor que inicializa o pagamento Pix com um saldo inicial.
     *
     * @param saldo O saldo inicial da conta.
     */
    public Pix(double saldo) {
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
     * @return A própria instância de Pix.
     */
    public Pix setSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    }

    /**
     * Processa um pagamento, deduzindo o valor do saldo.
     *
     * @param valor O valor a ser pago.
     * @throws IllegalArgumentException Se o saldo for insuficiente.
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
     * Retorna a taxa aplicada a este método de pagamento. Para Pix, é zero.
     *
     * @return 0.0.
     */
    @Override
    public double taxaAplicada() {
        return 0;
    }

    /**
     * Retorna o nome do método de pagamento.
     *
     * @return A string "Pix".
     */
    @Override
    public String toString() {
        return "Pix";
    }
}

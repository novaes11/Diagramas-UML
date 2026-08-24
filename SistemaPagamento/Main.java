package src.POO.SistemaPagamento;

/**
 * Classe principal para demonstrar o sistema de pagamento.
 * Cria instâncias de diferentes métodos de pagamento (Pix e Cartão)
 * e simula a realização de pagamentos.
 */
public class Main {
    /**
     * Ponto de entrada da aplicação.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Pagamento pix = new Pix(100);
        Pagamento cartao = new Cartao(100);

        pix.pagar(50);
        cartao.pagar(50);
    }
}

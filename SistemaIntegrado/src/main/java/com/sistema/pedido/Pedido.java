package com.sistema.pedido;

import com.sistema.produto.Produto;
import com.sistema.pagamento.Pagamento;

import java.util.ArrayList;
import java.util.List;

/**
 * Camada de Integração Central.
 * O Pedido atua como a entidade intermediária que acopla de forma fraca
 * o domínio de Produtos e o domínio de Pagamentos.
 * Ele consome os produtos para obter valores e repassa o total para ser
 * processado pelo meio de pagamento.
 */
public class Pedido {
    private List<Produto> itens;
    private Pagamento meioPagamento;
    private double totalDescontoAplicado;

    public Pedido() {
        this.itens = new ArrayList<>();
        this.totalDescontoAplicado = 0;
    }

    public void adicionarProduto(Produto produto) {
        this.itens.add(produto);
    }

    public void removerProduto(Produto produto) {
        this.itens.remove(produto);
    }

    public List<Produto> getItens() {
        return itens;
    }

    /**
     * Calcula o valor total do pedido com base no preço atual dos produtos.
     * @return O valor total.
     */
    public double calcularTotal() {
        return itens.stream().mapToDouble(Produto::getPreco).sum();
    }

    /**
     * Define o meio de pagamento a ser utilizado.
     * @param pagamento Instância de uma classe que implemente a interface Pagamento.
     */
    public void setMeioPagamento(Pagamento pagamento) {
        this.meioPagamento = pagamento;
    }

    /**
     * Finaliza o pedido acionando o módulo de pagamentos.
     */
    public void finalizarPedido() {
        if (itens.isEmpty()) {
            throw new IllegalStateException("O pedido não possui itens.");
        }
        if (meioPagamento == null) {
            throw new IllegalStateException("Meio de pagamento não definido.");
        }

        double total = calcularTotal();
        System.out.println("\n--- FINALIZANDO PEDIDO ---");
        System.out.println("Total de itens: " + itens.size());
        System.out.println("Valor Total: R$ " + String.format("%.2f", total));
        
        // Integração com o módulo de pagamentos
        meioPagamento.pagar(total);
        System.out.println("Pedido finalizado com sucesso!");
    }
}

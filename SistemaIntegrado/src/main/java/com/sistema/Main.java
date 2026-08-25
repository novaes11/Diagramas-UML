package com.sistema;

import com.sistema.produto.ProdutoAlimenticio;
import com.sistema.produto.ProdutoEletronico;
import com.sistema.produto.Catalogo;
import com.sistema.produto.Produto;
import com.sistema.pagamento.Pix;
import com.sistema.pagamento.Cartao;
import com.sistema.pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA INTEGRADO ===");

        // 1. Inicializando o Catálogo (Módulo de Produtos)
        Catalogo<Produto> catalogo = new Catalogo<>();
        ProdutoEletronico notebook = new ProdutoEletronico("E001", "Notebook Gamer", 5000.00, 24);
        ProdutoAlimenticio arroz = new ProdutoAlimenticio("A001", "Arroz 5kg", 25.90, "31/12/2025");
        
        catalogo.adicionar(notebook);
        catalogo.adicionar(arroz);

        System.out.println("\nProdutos disponíveis:");
        catalogo.listarProdutos();

        // 2. Criando o Pedido (Camada de Integração)
        Pedido pedido = new Pedido();
        System.out.println("\nAdicionando itens ao pedido...");
        pedido.adicionarProduto(notebook);
        pedido.adicionarProduto(arroz);

        // 3. Processando Pagamento (Módulo de Pagamento)
        // Simulando saldo na conta/cartão
        Pix pix = new Pix(10000.00); 
        Cartao cartao = new Cartao(6000.00);

        System.out.println("\nDefinindo meio de pagamento (Pix)...");
        pedido.setMeioPagamento(pix);

        // Finaliza o pedido
        pedido.finalizarPedido();
    }
}

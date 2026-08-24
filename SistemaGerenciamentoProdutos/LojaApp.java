package src.POO.SistemaGerenciamentoProdutos;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal responsável pela execução e interação do Sistema de Gerenciamento de Produtos.
 * Esta classe gerencia o fluxo da aplicação, permitindo cadastro, listagem, vendas
 * e aplicação de descontos em produtos eletrônicos e alimentícios.
 * * @author Joao Vitor Novaes
 * @version 1.0
 */
public class LojaApp {

    /**
     * Método de entrada da aplicação.
     * Inicializa o catálogo, popula com dados iniciais e gerencia o menu de interação com o usuário.
     * * @param args Argumentos de linha de comando (não utilizados neste contexto).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Catalogo<Produto> catalogo = new Catalogo<>();

        // Inicialização de dados pré-definidos para teste
        List<Produto> iniciais = Arrays.asList(
                new ProdutoEletronico("E001", "Notebook Gamer", 5000.00, 24),
                new ProdutoEletronico("E002", "Smartphone X", 2500.00, 12),
                new ProdutoEletronico("E003", "Smart TV 50\"", 3200.00, 18),
                new ProdutoEletronico("E004", "Fone Bluetooth", 350.00, 6),
                new ProdutoEletronico("E005", "Mouse Sem Fio", 90.00, 6),
                // Produtos Alimentícios
                new ProdutoAlimenticio("A001", "Arroz 5kg", 25.90, "31/12/2025"),
                new ProdutoAlimenticio("A002", "Leite 1L", 4.50, "15/01/2025"),
                new ProdutoAlimenticio("A003", "Feijão 1kg", 8.90, "30/11/2025"),
                new ProdutoAlimenticio("A004", "Café 500g", 15.00, "20/10/2025"),
                new ProdutoAlimenticio("A005", "Macarrão 500g", 6.50, "05/09/2025")
        );

        // Adiciona a lista inicial ao catálogo
        iniciais.forEach(p -> catalogo.adicionar(p));

        try {
            System.out.println("=== PRODUTOS CADASTRADOS (INICIAIS) ===");
            catalogo.listarProdutos();

            // --- Início do Fluxo de Cadastro ---
            System.out.println("=== CADASTRO DE NOVO PRODUTO ===");
            System.out.print("Informe o tipo de produto (E - Eletrônico / A - Alimenticio): ");
            String tipo = sc.next();

            // Validação do tipo de produto
            if (tipo.equalsIgnoreCase("a") || tipo.equalsIgnoreCase("e")) {

                System.out.print("Digite o código do novo produto: ");
                String codigo = sc.next();
                sc.nextLine(); // Limpeza de buffer após leitura de token simples

                // Verifica duplicidade de código
                if (catalogo.verificadorCodigo(codigo)) {
                    throw new IllegalArgumentException("Codigo já cadastrado!");
                } else {
                    System.out.print("Digite a descrição do produto: ");
                    String descricao = sc.nextLine();

                    System.out.print("Digite o preco do produto: ");
                    double preco = sc.nextDouble();

                    // Lógica específica para cada tipo de produto
                    if (tipo.equalsIgnoreCase("e")) {
                        System.out.print("Digite a quantidade de meses de garantia: ");
                        int garantia = sc.nextInt();

                        catalogo.adicionar(new ProdutoEletronico(
                                codigo,
                                descricao,
                                preco,
                                garantia
                        ));
                    } else {
                        System.out.print("Digite a validade do produto: ");
                        sc.nextLine(); // Limpeza de buffer obrigatória antes de ler String
                        String validade = sc.nextLine();

                        catalogo.adicionar(new ProdutoAlimenticio(
                                codigo,
                                descricao,
                                preco,
                                validade
                        ));
                    }

                    System.out.println("Novo produto cadastrado com sucesso!");
                }

                System.out.println("=== LISTA DE PRODUTOS APOS CADASTRO ===");
                catalogo.listarProdutos();

                // --- Fluxo de Aplicação de Desconto ---
                System.out.println("=== APLICAR DESCONTO EM UM PRODUTO ===");
                System.out.print("Digite o codigo do produto para aplicar o desconto: ");
                sc.nextLine(); // Limpeza de buffer de segurança
                codigo = sc.next();

                System.out.println(catalogo.buscarPorCodigo(codigo));
                System.out.print("Digite o percentual de desconto a ser aplicado: ");
                double percentual = sc.nextDouble();

                catalogo.buscarPorCodigo(codigo).calcularDesconto(percentual);

                System.out.println("=== LISTA FINAL DE PRODUTOS (APÓS DESCONTO) === ");
                catalogo.listarProdutos();

                // --- Fluxo de Registro de Venda ---
                System.out.println("=== REGISTRO DE VENDA ===");
                System.out.print("Digite o codigo do produto para a venda: ");
                sc.nextLine(); // Limpeza de buffer
                codigo = sc.next();

                System.out.print("Digite a quantidade a ser vendida: ");
                int quantidade = sc.nextInt();

                catalogo.buscarPorCodigo(codigo).vender(quantidade);

                // --- Fluxo de Remoção ---
                System.out.println("=== TESTE DE REMOÇÃO DE PRODUTO ===");
                System.out.print("Insira o codigo do produto a ser removido : ");
                sc.nextLine(); // Limpeza de buffer
                codigo = sc.nextLine();

                catalogo.removerPorCodigo(codigo);

                System.out.println("=== LISTA APÓS REMOÇÃO ===");
                catalogo.listarProdutos();

            } else {
                throw new IllegalArgumentException("Tipo do produto deve ser A ou E.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ação invalida! Motivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        } finally {
            sc.close(); // Boa prática: fechar o scanner ao encerrar
        }
    }
}
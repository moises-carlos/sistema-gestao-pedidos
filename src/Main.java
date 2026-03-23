import com.moises.pedidos.model.*;
import com.moises.pedidos.model.enums.*;
import com.moises.pedidos.repository.*;
import com.moises.pedidos.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ClienteRepository clienteRepo = new ClienteRepository();
        ProdutoRepository produtoRepo = new ProdutoRepository();
        PedidoRepository pedidoRepo = new PedidoRepository();

        ClienteService clienteService = new ClienteService(clienteRepo);
        ProdutoService produtoService = new ProdutoService(produtoRepo);
        PedidoService pedidoService = new PedidoService(pedidoRepo);

        int opcao = -1;

        while (opcao != 8) {
            System.out.println("\n=== 🍔 SISTEMA DE PEDIDOS 🍔 ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Produto");
            System.out.println("3. Criar Pedido (Abrir Comanda)");
            System.out.println("4. Adicionar Produtos ao Pedido");
            System.out.println("5. Listar Todos os Pedidos");
            System.out.println("6. Atualizar Status do Pedido");
            System.out.println("7. Ver Fila de Prioridade (Cozinha)");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Digite um número válido!");
                scanner.nextLine(); continue;
            }

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Nome do Cliente: ");
                        String nome = scanner.nextLine();
                        System.out.print("Telefone: ");
                        Long tel = scanner.nextLong(); scanner.nextLine();
                        clienteService.cadastrar(new Cliente(nome, tel));
                        System.out.println("Cliente cadastrado!");
                        break;

                    case 2:
                        System.out.print("Nome do Produto: ");
                        String nomeProd = scanner.nextLine();
                        System.out.print("Preço: R$ ");
                        Double preco = scanner.nextDouble(); scanner.nextLine();
                        System.out.print("Categoria: ");
                        String cat = scanner.nextLine();
                        System.out.print("Quantidade em Estoque: ");
                        Integer est = scanner.nextInt(); scanner.nextLine();
                        produtoService.cadastrar(new Produto(nomeProd, preco, cat, est));
                        System.out.println("Produto cadastrado!");
                        break;

                    case 3: // ABRIR COMANDA VAZIA
                        List<Cliente> clientes = clienteService.listarTodos();
                        if(clientes.isEmpty()) { System.out.println("⚠️ Cadastre um cliente primeiro!"); break; }

                        System.out.println("Clientes:");
                        for(int i=0; i<clientes.size(); i++) System.out.println((i+1) + " - " + clientes.get(i).getNome());
                        System.out.print("Escolha o número do cliente: ");
                        Cliente cli = clientes.get(scanner.nextInt() - 1); scanner.nextLine();

                        System.out.print("Prioridade (1-Urgente, 2-Normal, 3-Baixa): ");
                        int prio = scanner.nextInt(); scanner.nextLine();
                        Prioridade p = (prio == 1) ? Prioridade.URGENTE : (prio == 2) ? Prioridade.NORMAL : Prioridade.BAIXA;

                        Pedido novoPedido = new Pedido(cli, new ArrayList<>(), p, StatusPedido.NOVO);
                        pedidoService.cadastrar(novoPedido);
                        System.out.println("✅ Comanda aberta com sucesso! Vá na opção 4 para adicionar itens.");
                        break;

                    case 4: // ADICIONAR ITENS
                        List<Pedido> pedidosAbertos = pedidoService.listarTodos();
                        List<Produto> cardapio = produtoService.listarTodos();
                        if(pedidosAbertos.isEmpty() || cardapio.isEmpty()) { System.out.println("⚠️ Faltam pedidos ou produtos!"); break; }

                        System.out.println("Pedidos Abertos:");
                        for(int i=0; i<pedidosAbertos.size(); i++) System.out.println((i+1) + " - Pedido de: " + pedidosAbertos.get(i).getCliente().getNome());
                        System.out.print("Escolha o pedido: ");
                        Pedido pedAdd = pedidosAbertos.get(scanner.nextInt() - 1); scanner.nextLine();

                        System.out.println("Cardápio:");
                        for(int i=0; i<cardapio.size(); i++) System.out.println((i+1) + " - " + cardapio.get(i).getNome() + " (R$ " + cardapio.get(i).getPreco() + ")");
                        System.out.print("Escolha o produto: ");
                        Produto prodAdd = cardapio.get(scanner.nextInt() - 1);
                        System.out.print("Quantidade: ");
                        int qtd = scanner.nextInt(); scanner.nextLine();

                        pedidoService.adicionarProdutoAoPedido(pedAdd, new ItemPedido(prodAdd, qtd));
                        System.out.println("✅ Produto adicionado! Valor Total da Comanda: R$ " + pedAdd.calcularValorTotal());
                        break;

                    case 5:
                        System.out.println("\n--- LISTA DE PEDIDOS ---");
                        for(Pedido ped : pedidoService.listarTodos()) {
                            System.out.println("Cliente: " + ped.getCliente().getNome() + " | Status: " + ped.getStatus() + " | Total: R$ " + ped.calcularValorTotal());
                        }
                        break;

                    case 6:
                        System.out.println("\n--- ATUALIZAR STATUS ---");
                        List<Pedido> listaStat = pedidoService.listarTodos();
                        for(int i=0; i<listaStat.size(); i++) System.out.println((i+1) + " - Pedido de " + listaStat.get(i).getCliente().getNome() + " [" + listaStat.get(i).getStatus() + "]");
                        System.out.print("Escolha o pedido: ");
                        Pedido pedStat = listaStat.get(scanner.nextInt() - 1); scanner.nextLine();

                        System.out.print("Novo status (1-Preparo, 2-Pronto, 3-Finalizado, 4-Cancelado): ");
                        int stat = scanner.nextInt(); scanner.nextLine();
                        StatusPedido novoStat = (stat==1) ? StatusPedido.EM_PREPARO : (stat==2) ? StatusPedido.PRONTO : (stat==3) ? StatusPedido.FINALIZADO : StatusPedido.CANCELADO;

                        pedidoService.atualizarStatus(pedStat, novoStat);
                        System.out.println("✅ Status atualizado!");
                        break;

                    case 7:
                        System.out.println("\n--- FILA DA COZINHA (PRIORIDADE) ---");
                        for(Pedido ped : pedidoService.listarPorPrioridade()) {
                            System.out.println("🔥 Prioridade: " + ped.getPrioridade() + " | Cliente: " + ped.getCliente().getNome() + " | Status: " + ped.getStatus());
                        }
                        break;

                    case 8: System.out.println("Encerrando o sistema..."); break;
                    default: System.out.println("❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("\n❌ " + e.getMessage());
            }
        }
        scanner.close();
    }
}
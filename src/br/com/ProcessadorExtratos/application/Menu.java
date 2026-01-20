package br.com.ProcessadorExtratos.application;

import br.com.ProcessadorExtratos.repository.RepositorioTransacoes;

import java.util.Scanner;

public class Menu {

    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                \n1. Listar pedidos Pendentes
                2. Listar Pedidos feitos no Cartão (Débito e Crédito)
                3. Valor total das Transações
                4. Listar Produtos Repetidos
                """);
    }

    public void escolhaMenu(int i, RepositorioTransacoes rt){
        if (i == 1){
            rt.pedidosPendentes().forEach(System.out::println);
        }
        else if (i == 2) {
            rt.trasacoesCartoes().forEach(System.out::println);
        }
        else if (i == 3) {
            System.out.printf("\nValor total das Transações finalizadas: R$ %.2f\n", rt.somaTransacoes());
        }
        else if (i == 4) {
            rt.produtosRepetidos().forEach((nome, quantidade) -> System.out.println("Produto: " + nome + " | Vezes comprado: " + quantidade));
        }
    }
}

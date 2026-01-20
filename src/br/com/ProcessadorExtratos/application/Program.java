package br.com.ProcessadorExtratos.application;

import br.com.ProcessadorExtratos.entities.Transacao;
import br.com.ProcessadorExtratos.entities.enums.MetodoPagamento;
import br.com.ProcessadorExtratos.entities.enums.StatusTransacao;
import br.com.ProcessadorExtratos.entities.enums.TipoProduto;
import br.com.ProcessadorExtratos.repository.RepositorioTransacoes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String path = "C:\\temp\\MOCK_DATA.csv";
        Scanner sc = new Scanner(System.in);
        RepositorioTransacoes rt = new RepositorioTransacoes();
        Menu menu = new Menu();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();

            while (line != null){
                String[] fields = line.split(",");

                int id = Integer.parseInt(fields[0]);
                LocalDate timestamp = LocalDate.parse(fields[1]);
                String nomeProduto = fields[2];
                String emailClient = fields[3];
                TipoProduto categoriaProduto = TipoProduto.valueOf(fields[4].toUpperCase());
                Double valor = Double.parseDouble(fields[5]);
                MetodoPagamento metodoPagamento = MetodoPagamento.valueOf(fields[6].toUpperCase());
                StatusTransacao statusTransacao = StatusTransacao.valueOf(fields[7].toUpperCase());

                Transacao transacao = new Transacao(id, timestamp, nomeProduto, emailClient, categoriaProduto, valor, metodoPagamento, statusTransacao);
                rt.salvarTransacao(transacao);

                line = br.readLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true){
            menu.menuPrincipal();
            System.out.print("Escolha a opção desejada: ");
            int escolha = sc.nextInt();
            menu.escolhaMenu(escolha, rt);
        }

    }
}
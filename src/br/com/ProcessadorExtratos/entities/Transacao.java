package br.com.ProcessadorExtratos.entities;

import br.com.ProcessadorExtratos.entities.enums.MetodoPagamento;
import br.com.ProcessadorExtratos.entities.enums.StatusTransacao;
import br.com.ProcessadorExtratos.entities.enums.TipoProduto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private LocalDate timestamp;
    private String descricao;
    private String emailCliente;
    private Double valor;
    private Double imposto;
    private TipoProduto tipoProduto;
    private MetodoPagamento metodoPagamento;
    private StatusTransacao statusTransacao;

    public Transacao(int id, LocalDate timestamp, String descricao, String emailCliente, TipoProduto tipoProduto,Double valor, MetodoPagamento metodoPagamento, StatusTransacao statusTransacao) {
        this.descricao = descricao;
        this.emailCliente = emailCliente;
        this.id = id;
        this.metodoPagamento = metodoPagamento;
        this.statusTransacao = statusTransacao;
        this.timestamp = timestamp;
        this.tipoProduto = tipoProduto;
        this.valor = valor;
    }

    public double valorLiquido(Transacao t){
        double taxa = 0.0;

        if (t.metodoPagamento == MetodoPagamento.CREDIT_CARD){
            return taxa = 0.05;
        }
        else if (t.metodoPagamento == MetodoPagamento.DEBIT_CARD) {
            return taxa = 0.03;
        }
        else if (t.metodoPagamento == MetodoPagamento.PIX) {
            return taxa = 0.01;
        }
        else if (t.metodoPagamento == MetodoPagamento.CASH) {
            return taxa;
        }
        return t.valor - taxa;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Data: %s | %s | %s | Valor: R$ %.2f | Status: %s | Payment: %s",
                id, timestamp, descricao, tipoProduto, valor, statusTransacao, metodoPagamento);
    }
}

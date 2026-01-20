package br.com.ProcessadorExtratos.repository;

import br.com.ProcessadorExtratos.entities.Transacao;
import br.com.ProcessadorExtratos.entities.enums.MetodoPagamento;
import br.com.ProcessadorExtratos.entities.enums.StatusTransacao;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorioTransacoes {
    private List<Transacao> transacaoList = new ArrayList<>();

    public void salvarTransacao(Transacao t){
        transacaoList.add(t);
    }

    public List<Transacao> listarTransacao(){
        return Collections.unmodifiableList(transacaoList);
    }

    public List<Transacao> pedidosPendentes(){
        return transacaoList.stream()
                .filter(x -> x.getStatusTransacao() == StatusTransacao.PENDING)
                .sorted(Comparator.comparing(Transacao::getTimestamp))
                .collect(Collectors.toList());
    }

    public Double somaTransacoes(){
        return transacaoList.stream()
                .filter(x -> x.getStatusTransacao() == StatusTransacao.COMPLETED)
                .mapToDouble(x -> x.getValor())
                .sum();
    }

    public List<Transacao> trasacoesCartoes(){
        return transacaoList.stream()
                .filter(x -> x.getMetodoPagamento() == MetodoPagamento.CREDIT_CARD || x.getMetodoPagamento() == MetodoPagamento.DEBIT_CARD)
                .collect(Collectors.toList());
    }

    public Map<String, Long> produtosRepetidos(){
        return transacaoList.stream()
                .collect(Collectors.groupingBy(x -> x.getDescricao(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // Sorted organiza, e o comparingByValue organiza pelo valor, no caso o Long que siginifica a quantidade de vezes pedidas.
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}

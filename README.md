# 📑 Processador de Transações Financeiras

### 🚀 Introdução
Este projeto foi desenvolvido com o intuito de aplicar e consolidar meus conhecimentos fundamentais em **Java**, explorando principalmente a manipulação de arquivos, Programação Orientada a Objetos (POO) e, especialmente, o uso da **Stream API** para processamento inteligente de dados.

O sistema funciona como um motor de análise de extratos: ele lê uma base de dados externa em formato CSV, processa as informações em memória e oferece ao usuário um menu interativo para consultas estatísticas e financeiras.

---

### 📂 Leitura do Arquivo e Dados
O projeto processa arquivos estruturados no formato `.csv`.
* **Base de dados:** Um arquivo de exemplo `MOCK_DATA.csv` está disponível na raiz do repositório para testes imediatos.
* **Fluxo de Dados:** 1. O arquivo é aberto via `FileReader`.
    2. A primeira linha (cabeçalho) é ignorada.
    3. Cada linha subsequente é convertida em um objeto `Transacao`.
    4. Os objetos são armazenados no `RepositorioTransacoes`.



---

### ⚙️ Funcionalidades principais
O coração do projeto reside no `RepositorioTransacoes`, que utiliza o poder das **Streams** para entregar resultados rápidos e limpos:

* **Listagem Geral:** Exibe todas as transações formatadas de forma legível.
* **Pedidos Pendentes:** Filtra instantaneamente apenas o que ainda não foi processado (`StatusTransacao.PENDING`).
* **Análise de Pagamentos:** Filtra vendas específicas por cartão de Débito ou Crédito (`MetodoPagamento.CREDIT_CARD || MetodoPagamento.DEBIT_CARD`).
* **Cálculo de Faturamento:** Soma os valores de todas as compras finalizadas (`COMPLETED`) usando `.mapToDouble()` e `.sum()`.
* **Produtos Recorrentes:** Identifica quais itens foram comprados mais de uma vez, apresentando a contagem exata e ordenando do mais vendido para o menos vendido através de `Collectors.groupingBy`.



---

### 💻 Como Executar
1. Clone o repositório:
   ```bash
   git clone [https://github.com/arthurgomes7/ProcessadorTransacoes.git](https://github.com/arthurgomes7/ProcessadorTransacoes.git)
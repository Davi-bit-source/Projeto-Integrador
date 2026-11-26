
# Documentação



## Visão Geral

### Problema

ONGs e bancos de alimentos recebem doações de itens perecíveis e não perecíveis, mas costumam controlar o estoque de forma manual (cadernos ou planilhas soltas). Sem o registro da validade de cada lote, alimentos vencem no estoque e são descartados, enquanto outros são distribuídos sem critério, o que gera desperdício e reduz a ajuda que chega a quem precisa.
### Solução Proposta

Sistema em Java que registra a entrada de doações por lote, controla a validade, alerta sobre itens próximos do vencimento, orienta a saída pela regra FEFO (primeiro a vencer, primeiro a sair) e gera relatórios de distribuição. Os dados ficam em arquivos CSV, acessados por meio do padrão DAO.



## Requisitos Funcionais


- RF01 - Cadastrar Produto
- RF02 - Validar Código de Barras
- RF03 - Gerar Código Interno
- RF04 - Buscar Produto por Código
- RF05 - Registrar Doações
- RF06 - Validar Dados de Entrada
- RF07 - Registrar Distribuição
- RF08 - Aplicar FEFO na saída
- RF09 - Bloquear Saída Inválida
- RF10 - Consultar Estoques
- RF11 - Alertar Vencimentos próximos
- RF12 - Listar itens vencidos
- RF13 - Registrar descarte
- RF14 - Relatório de distribuição
- RF15 - Relatório de arrecadação
- RF16 - Estatísticas descritivas
- RF17 - Carga inicial do catálogo
- RF18 - Persistir dados
- RF19 - Editar e excluir produto
- RF20 - Histórico de movimentações
- RF21 - Exportar relatório
- RF22 - Leitura por webcam
- RF23 - Controle de acesso
- RF24 - Buscar produtos em catálogo externo


## Requisitos Não Funcionais

- RNF01 - Usabilidade
- RNF02 - Desempenho
- RNF03 - Confiabilidade
- RNF04 - Portabilidade
- RNF05 - Manutenibilidade


## Regras de Negócio


- RN01 - A saída de itens segue a regra FEFO: sempre se baixa primeiro o lote de validade mais próxima, usando o lote seguinte quando o primeiro não for suficiente.
- RN02 - Um código de barras só é aceito se tiver 8 (EAN-8) ou 13 (EAN-13) dígitos numéricos e o dígito verificador estiver correto.
- RN03 - Códigos com prefixo de 20 a 29 (uso interno de lojas) são tratados como item sem código de barras e recebem código interno. Códigos EAN-13 que não começam com 789 ou 790 geram apenas um aviso (podem ser produtos importados), sem bloquear o cadastro.
- RN04 - O código de cada produto é único. Os códigos internos (`INT-0001`) são sequenciais e nunca reutilizados, mesmo que o produto seja excluído.
- RN05 - Todo lote deve estar vinculado a um produto já cadastrado. Não existe lote sem produto.
- RN06 - Um lote só pode ser registrado com data de validade igual ou posterior à data atual. Doações já vencidas não entram no estoque 
- RN07 - A quantidade de uma doação ou de uma saída deve ser maior que zero. Unidades contáveis (un, pacote) aceitam apenas números inteiros; unidades de peso e volume (kg, L) aceitam decimais.
- RN08 - Uma saída não pode superar o saldo disponível do produto, e lotes vencidos nunca são considerados na distribuição.
- RN09 - Um lote entra em alerta quando faltam N dias ou menos para o vencimento. O valor de N é configurável (sugestão inicial: 15 dias).
- RN10 - Itens vencidos não podem ser distribuídos. Devem ser descartados com registro do motivo "vencido", e o descarte é contabilizado separadamente da distribuição.
- RN11 - Um produto só pode ser excluído se não tiver lotes com saldo. Lotes com saldo zero são mantidos para o histórico e não são apagados.
- RN12 - Os relatórios de período consideram a data de entrada (arrecadação) ou a data da saída (distribuição), com o período informado pelo usuário e datas inclusivas.




## Restrições

- RS01 - O sistema deve ser desenvolvido em Java, com orientação a objetos (encapsulamento, herança e polimorfismo quando pertinentes), tratamento de exceções e coleções 
- RS02 - A interface deve ser gráfica, em Swing, preferencialmente com arquitetura MVC 
- RS03 - A persistência será feita em arquivos CSV, acessados por meio de DAO. O banco de dados só entra depois da finalização do MVP 
- RS04 - O MVP aceita códigos EAN-8 e EAN-13. O DUN-14 (caixas e fardos) não é suportado e fica registrado como limitação 
- RS05 - O sistema não terá login nem armazenará dados pessoais de doadores ou beneficiários 


## Análise de Requisitos



### Requisitos Funcionais


| Categoria | Requisitos                                                                         | Justificativa                                                                                                                                                             |
| --------- | ---------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Must      | RF01, RF02, RF03, RF04, RF05, RF06, RF07, RF08, RF09, RF10, RF11, RF14, RF17, RF18 | Cobrem o ciclo central do problema: cadastrar produto, registrar doação, distribuir por FEFO, alertar vencimento, gerar o relatório de distribuição e persistir os dados. |
| Should    | RF12, RF13, RF15, RF16, RF19                                                       | Agregam valor (itens vencidos, descarte, relatório de arrecadação, estatísticas, edição de produto), mas o sistema funciona sem eles.                                     |
| Could     | RF20, RF21                                                                         | Histórico de movimentações e exportação de relatórios melhoram a experiência, mas não afetam o problema central.                                                          |
| Won't     | RF22, RF23, RF24                                                                   | Leitura por Webcam, controle de acesso e consulta online ficam como trabalhos futuros.                                                                                    |

### Requisitos Não funcionais

| Categoria | Requisitos          | Justificativa                                                                                                                                                          |
| --------- | ------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Must      | RNF01, RNF03, RNF04 | Usabilidade, integridade dos dados e execução em Windows e Linux afetam diretamente a demonstração e a confiabilidade do MVP.                                          |
| Should    | RNF02, RNF05        | Desempenho e manutenibilidade (MVC e DAO) são importantes, mas o primeiro depende do volume de dados e o segundo é uma boa prática que se constrói junto com o código. |


## Modelo de Processo

Para conferir o modelo de Processo vá para [[Modelo de Processos]]


## Mapeamento de Processos

Para conferir o Mapeamento de Processos em BPMN vá para [[Mapeamento de Processos]]


## Quadro Scrum




## KPIS do Processo

Para conferir os KPIS do processo vá para [[KPIS do Processo]]


## Rastreabilidade

| Requisito | Classe | Testes | Status |
| --------- | ------ | ------ | ------ |
|           |        |        |        |



## Histórico de Versões


| Versão | Data       | Autor                   | Descrição                                                                      |
| ------ | ---------- | ----------------------- | ------------------------------------------------------------------------------ |
| 0.1    | 24/11/2026 | Davi                    | Criação do Documento                                                           |
| 0.2    | 25/11/2026 | Davi                    | Inclusão de Requisitos, regras de negocio, restrições e priorização por MoSCoW |
| 0.3    | 25/11/2026 | Davi, Gustavo e Ronaldo | Criação do Modelo de Processos                                                 |
| 0.4    | 26/11/2026 | Davi                    | Criação do Mapeamento de Processos                                             |
|        |            |                         |                                                                                |
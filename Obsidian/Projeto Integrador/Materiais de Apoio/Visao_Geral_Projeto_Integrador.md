# Projeto Integrador 2026/2 --- Visão Geral e Entendimento do Projeto

> **Finalidade deste documento:** reunir em um único lugar o
> entendimento geral do Projeto Integrador, para que os integrantes da
> equipe possam consultar o contexto, os objetivos, os requisitos e as
> decisões já documentadas.
>
> **Importante:** este documento organiza o conteúdo das documentações
> existentes. Ele não substitui os documentos oficiais do projeto e não
> antecipa decisões de implementação que ainda precisam ser tomadas.

------------------------------------------------------------------------

## 1. Contexto acadêmico

O Projeto Integrador 2026/2 pertence ao 2º período do curso Superior de
Engenharia de Software da Faculdade SENAI FATESG.

A proposta da faculdade é que equipes de 3 a 5 estudantes identifiquem
um problema real ou simulado e desenvolvam uma solução de software. O
trabalho deve integrar os conhecimentos das unidades curriculares do
período e resultar em um **MVP demonstrável**, acompanhado de
documentação e evidências verificáveis.

### Unidades curriculares envolvidas

-   Técnicas de Programação;
-   Processo de Engenharia de Software;
-   Sistemas Operacionais;
-   Fundamentos Matemáticos I;
-   Circuitos Digitais e Sistemas Microcontrolados.

### Principais exigências da faculdade

**Técnicas de Programação** - Desenvolver o software em Java e
utilizando orientação a objetos. - Aplicar encapsulamento, herança e
polimorfismo quando pertinentes. - Utilizar tratamento de exceções e
coleções. - Desenvolver uma interface gráfica em Swing ou JavaFX. -
Implementar persistência de dados em arquivo CSV/TXT ou banco de
dados. - Entregar código-fonte, diagrama de classes e instruções de
execução.

**Processo de Engenharia de Software** - Definir e justificar o modelo
de processo/ciclo de vida adotado. - Mapear o processo em BPMN 2.0, com
raias por papel e pelo menos um gateway de decisão. - Manter requisitos
priorizados por MoSCoW e versionados. - Registrar mudanças nos
requisitos. - Acompanhar as iterações em quadro Scrum ou Kanban. -
Definir pelo menos cinco KPIs utilizando GQM e apresentar os valores
medidos.

**Sistemas Operacionais** - Elaborar um estudo comparativo entre Linux e
Windows. - Comparar custo/licenciamento, segurança, desempenho,
estabilidade, usabilidade, suporte e compatibilidade de software e
hardware. - Apresentar dados de participação de mercado e citar as
fontes.

**Fundamentos Matemáticos I** - Aplicar os fundamentos matemáticos
quando o domínio exigir, apresentando memória de cálculo. - Quando não
houver aplicação pertinente, justificar a não aplicação.

**Circuitos Digitais e Sistemas Microcontrolados** - Quando houver
hardware/IoT, integrar um sistema microcontrolado e apresentar diagrama
de ligação e evidências. - Em soluções puramente de software, documentar
conceitualmente a interface com dispositivos ou justificar a não
aplicação.

### Critérios e entregas gerais

A faculdade também solicita: - Ata de abertura do projeto; - Modelagem
BPMN; - Quadro de acompanhamento das iterações; - KPIs efetivamente
medidos; - Software funcionando e com validações; - Código-fonte; -
Comparativo Linux x Windows; - Relatório acadêmico conforme as
orientações da ABNT; - Relatório técnico dos softwares e hardwares
utilizados; - Registro do uso de IA (prompts, resultados, revisão e
decisões humanas) ou declaração de ausência.

A avaliação é composta por 50% de atividades em grupo e 50% de
desempenho individual.

### Cronograma informado no documento da faculdade

  Entrega/atividade                                 Data
  ------------------------------------ -----------------
  Prévia da Ata de Abertura                   23/11/2026
  Relatório Técnico                           03/12/2026
  Software funcionando                        04/12/2026
  Entrega de todos os artefatos               06/12/2026
  Apresentação do Projeto Integrador     07 e 08/12/2026

------------------------------------------------------------------------

## 2. Visão geral do projeto da equipe

### Tema

**Sistema de Controle de Doações e Validade de Alimentos para ONG.**

### Problema identificado

ONGs e bancos de alimentos recebem doações de produtos perecíveis e não
perecíveis. Em muitos casos, o controle é feito manualmente, por meio de
cadernos ou planilhas separadas.

Quando não há um registro adequado da validade de cada lote, alimentos
podem vencer no estoque. Também pode ocorrer distribuição sem um
critério adequado de prioridade, aumentando o desperdício e reduzindo a
quantidade de alimentos que chega aos beneficiários.

### Solução proposta

Desenvolver um sistema desktop em Java para:

-   Cadastrar produtos;
-   Registrar a entrada de doações por lote;
-   Controlar quantidades e datas de validade;
-   Alertar sobre produtos próximos do vencimento;
-   Orientar a saída dos alimentos pela regra FEFO;
-   Registrar distribuições;
-   Registrar descartes;
-   Consultar estoques;
-   Gerar relatórios de arrecadação e distribuição;
-   Produzir estatísticas descritivas, conforme o escopo priorizado.

A documentação prevê persistência em arquivos CSV, acessados por meio do
padrão DAO.

### Tecnologias e decisões documentadas

  Elemento                    Definição documentada
  --------------------------- ------------------------
  Linguagem                   Java
  Paradigma                   Orientação a objetos
  Interface gráfica           Java Swing
  Arquitetura desejada        MVC, preferencialmente
  Persistência do MVP         Arquivos CSV
  Acesso à persistência       Padrão DAO
  Controle de versão          Git e GitHub
  Documentação colaborativa   Obsidian

A documentação também estabelece que banco de dados fica para depois da
finalização do MVP.

------------------------------------------------------------------------

## 3. Requisitos do sistema

A documentação registra **24 requisitos funcionais** e **5 requisitos
não funcionais**.

### 3.1 Requisitos funcionais

  Código   Descrição
  -------- -------------------------------------
  RF01     Cadastrar Produto
  RF02     Validar Código de Barras
  RF03     Gerar Código Interno
  RF04     Buscar Produto por Código
  RF05     Registrar Doações
  RF06     Validar Dados de Entrada
  RF07     Registrar Distribuição
  RF08     Aplicar FEFO na saída
  RF09     Bloquear Saída Inválida
  RF10     Consultar Estoques
  RF11     Alertar Vencimentos próximos
  RF12     Listar itens vencidos
  RF13     Registrar descarte
  RF14     Relatório de distribuição
  RF15     Relatório de arrecadação
  RF16     Estatísticas descritivas
  RF17     Carga inicial do catálogo
  RF18     Persistir dados
  RF19     Editar e excluir produto
  RF20     Histórico de movimentações
  RF21     Exportar relatório
  RF22     Leitura por webcam
  RF23     Controle de acesso
  RF24     Buscar produtos em catálogo externo

### 3.2 Requisitos não funcionais

  Código   Descrição
  -------- ------------------
  RNF01    Usabilidade
  RNF02    Desempenho
  RNF03    Confiabilidade
  RNF04    Portabilidade
  RNF05    Manutenibilidade

### 3.3 Priorização MoSCoW

  -----------------------------------------------------------------------
  Prioridade              Requisitos              Interpretação no
                                                  projeto
  ----------------------- ----------------------- -----------------------
  **Must**                RF01, RF02, RF03, RF04, Funcionalidades
                          RF05, RF06, RF07, RF08, essenciais para o ciclo
                          RF09, RF10, RF11, RF14, central do sistema e
                          RF17, RF18              para o MVP.

  **Should**              RF12, RF13, RF15, RF16, Funcionalidades
                          RF19                    importantes, mas que
                                                  não impedem o
                                                  funcionamento do núcleo
                                                  do sistema se não forem
                                                  entregues.

  **Could**               RF20, RF21              Melhorias que agregam
                                                  valor, mas não são
                                                  essenciais ao problema
                                                  central.

  **Won't**               RF22, RF23, RF24        Funcionalidades
                                                  deixadas fora do escopo
                                                  atual e registradas
                                                  como trabalhos futuros.
  -----------------------------------------------------------------------

Para os requisitos não funcionais, a documentação classifica RNF01,
RNF03 e RNF04 como Must; RNF02 e RNF05 como Should.

------------------------------------------------------------------------

## 4. Regras de negócio

As regras de negócio definem condições que o sistema deve respeitar
durante suas operações.

  -----------------------------------------------------------------------
  Código                              Regra
  ----------------------------------- -----------------------------------
  RN01                                A saída segue FEFO: o lote com
                                      validade mais próxima é baixado
                                      primeiro. Se ele não for
                                      suficiente, utiliza-se o lote
                                      seguinte.

  RN02                                O código de barras só é aceito se
                                      tiver 8 dígitos (EAN-8) ou 13
                                      dígitos (EAN-13), todos numéricos,
                                      e dígito verificador correto.

  RN03                                Códigos com prefixo de 20 a 29 são
                                      tratados como itens sem código de
                                      barras e recebem código interno.
                                      EAN-13 que não começa com 789 ou
                                      790 gera aviso, mas não bloqueia o
                                      cadastro.

  RN04                                O código de cada produto é único.
                                      Códigos internos INT-0001 são
                                      sequenciais e nunca reutilizados,
                                      mesmo após exclusão do produto.

  RN05                                Todo lote deve estar vinculado a um
                                      produto cadastrado. Não existe lote
                                      sem produto.

  RN06                                Um lote só pode ser registrado com
                                      validade igual ou posterior à data
                                      atual. Doações vencidas não entram
                                      no estoque.

  RN07                                A quantidade de doação ou saída
                                      deve ser maior que zero. Unidades
                                      contáveis aceitam inteiros;
                                      unidades de peso e volume aceitam
                                      decimais.

  RN08                                Uma saída não pode superar o saldo
                                      disponível do produto, e lotes
                                      vencidos não são considerados na
                                      distribuição.

  RN09                                Um lote entra em alerta quando
                                      faltam N dias ou menos para vencer.
                                      N é configurável, com sugestão
                                      inicial de 15 dias.

  RN10                                Itens vencidos não podem ser
                                      distribuídos. Devem ser descartados
                                      com motivo "vencido",
                                      contabilizados separadamente da
                                      distribuição.

  RN11                                Um produto só pode ser excluído se
                                      não tiver lotes com saldo. Lotes
                                      com saldo zero são mantidos para
                                      histórico.

  RN12                                Relatórios por período consideram a
                                      data de entrada para arrecadação ou
                                      a data de saída para distribuição,
                                      com datas inicial e final
                                      inclusivas.
  -----------------------------------------------------------------------

### Conceito operacional importante: FEFO

FEFO significa **First Expire, First Out** --- primeiro que vence,
primeiro que sai.

No sistema, isso significa que, ao registrar uma distribuição, os lotes
elegíveis devem ser considerados pela ordem de proximidade do
vencimento. Lotes vencidos não podem participar da distribuição.

------------------------------------------------------------------------

## 5. Restrições do projeto

A documentação registra as seguintes restrições:

-   **RS01:** o sistema deve ser desenvolvido em Java, com orientação a
    objetos, tratamento de exceções e coleções; encapsulamento, herança
    e polimorfismo devem ser aplicados quando pertinentes.
-   **RS02:** a interface deve ser gráfica, em Swing, preferencialmente
    com arquitetura MVC.
-   **RS03:** a persistência do MVP será feita em arquivos CSV,
    acessados por DAO. Banco de dados fica para depois da finalização do
    MVP.
-   **RS04:** o MVP aceita EAN-8 e EAN-13. DUN-14, utilizado em caixas e
    fardos, não é suportado e fica registrado como limitação.
-   **RS05:** o sistema não terá login nem armazenará dados pessoais de
    doadores ou beneficiários.

------------------------------------------------------------------------

## 6. Modelo de processo de desenvolvimento

### Modelo escolhido

A equipe documentou a adoção de um **modelo híbrido: Scrum com práticas
do XP**.

A escolha foi justificada considerando porte, previsibilidade dos
requisitos, criticidade, capacidade da equipe e prazo.

### Como cada prática será utilizada

**Scrum** - Organizar o desenvolvimento em sprints curtas; - Definir uma
meta para cada sprint; - Planejar e acompanhar o trabalho; - Manter o
backlog do produto priorizado por MoSCoW; - Revisar os incrementos
entregues.

**XP --- Pair Programming** - Desenvolvedores trabalham em duplas; - A
prática busca nivelar o conhecimento e reduzir erros.

**XP --- Integração frequente** - Realizar commits pequenos e frequentes
no Git; - Integrar as alterações continuamente, reduzindo o tamanho dos
conflitos e facilitando o acompanhamento.

### Justificativa registrada

  -----------------------------------------------------------------------
  Critério                Característica          Efeito na escolha
                          documentada             
  ----------------------- ----------------------- -----------------------
  Porte                   Projeto pequeno: MVP    Não exige processo
                          desktop, cerca de 24    pesado; comporta
                          requisitos funcionais e framework leve como
                          5 não funcionais        Scrum.

  Previsibilidade         Requisitos documentados Permite planejar
                          e priorizados desde o   sprints, mantendo
                          início                  espaço para registrar
                                                  mudanças.

  Criticidade             Projeto acadêmico, sem  Não exige o rigor
                          risco à vida ou a dados documental de modelos
                          sensíveis               mais pesados.

  Capacidade da equipe    Cinco integrantes, com  Pair Programming ajuda
                          níveis mistos de Java e a nivelar o
                          Swing e sem experiência conhecimento.
                          em testes automatizados 

  Prazo                   Cerca de duas semanas   Incrementos curtos
                          até o software          permitem entregas
                          funcionar               parciais.
  -----------------------------------------------------------------------

### Papéis documentados

  -----------------------------------------------------------------------
  Papel                   Responsável             Atribuições
  ----------------------- ----------------------- -----------------------
  Product Owner           Davi                    Manter e priorizar o
                                                  backlog (MoSCoW) e
                                                  aceitar os itens
                                                  entregues.

  Scrum Master            Davi                    Conduzir cerimônias,
                                                  manter o quadro
                                                  atualizado e remover
                                                  impedimentos.

  Desenvolvedores         Gustavo, Luan, Ronaldo  Implementar, testar e
                          e Vitor                 documentar em duplas.
  -----------------------------------------------------------------------

### Planejamento das sprints

  -----------------------------------------------------------------------
  Sprint                  Período                 Meta
  ----------------------- ----------------------- -----------------------
  1                       25/11 a 27/11           Estrutura MVC e
                                                  cadastro de produtos
                                                  com persistência em
                                                  CSV.

  2                       28/11 a 01/12           Ciclo completo de
                                                  doação e distribuição
                                                  com FEFO.

  3                       02/12 a 04/12           Alertas, relatórios e
                                                  MVP pronto para
                                                  demonstração.

  4                       05/12 a 06/12           Consolidação dos
                                                  artefatos e envio da
                                                  pasta.
  -----------------------------------------------------------------------

------------------------------------------------------------------------

## 7. Mapeamento de processos em BPMN

A documentação de mapeamento apresenta três perspectivas de processo.

### 7.1 Processo de desenvolvimento

Representa o fluxo de trabalho da equipe, desde os requisitos
documentados até a entrega dos artefatos.

O fluxo inclui atividades relacionadas a: - Priorização do backlog; -
Registro e versionamento de mudanças; - Definição da meta da Sprint; -
Planejamento; - Atualização do quadro; - Quebra e execução dos itens; -
Desenvolvimento e integração; - Revisão; - Retrospectiva; - Medição de
KPIs; - Consolidação e entrega dos artefatos.

### 7.2 Processo de negócio AS-IS

Representa o processo atual de recebimento, armazenamento e distribuição
de alimentos pela ONG, conforme o cenário documentado pela equipe.

O cenário evidencia o controle manual por cadernos ou planilhas e os
problemas associados ao acompanhamento de estoque e validade.

### 7.3 Processo de negócio TO-BE

Representa o processo proposto com apoio do sistema.

O fluxo documentado contempla atividades como: - Receber a solicitação
ou os alimentos; - Identificar e buscar produtos; - Validar códigos; -
Cadastrar produto quando necessário; - Informar validade e quantidade; -
Registrar lote; - Verificar saldo e validade; - Selecionar lotes pela
regra FEFO; - Registrar saída; - Tratar falta de estoque; - Identificar
itens em alerta ou vencidos; - Registrar descarte quando aplicável.

Os diagramas BPMN completos devem ser consultados no arquivo
**Mapeamento de Processos.pdf**, pois este resumo descreve seus
objetivos e principais elementos, não substituindo os desenhos.

------------------------------------------------------------------------

## 8. KPIs do processo --- abordagem GQM

A equipe documentou seis indicadores para acompanhar o processo de
desenvolvimento.

  -----------------------------------------------------------------------
  KPI                     Métrica                 Fórmula documentada
  ----------------------- ----------------------- -----------------------
  KPI 1                   Taxa de cumprimento do  (Itens feitos ÷ itens
                          planejado               planejados na Sprint) ×
                                                  100

  KPI 2                   Cobertura dos           (Must aceitos pelo PO ÷
                          requisitos Must         total de Must) × 100

  KPI 3                   Taxa de aprovação nos   (Casos de teste
                          testes                  aprovados ÷ casos
                                                  executados) × 100

  KPI 4                   Defeitos por item       Defeitos registrados na
                          entregue                Sprint ÷ itens
                                                  entregues

  KPI 5                   Volatilidade dos        (Mudanças registradas ÷
                          requisitos              total de requisitos) ×
                                                  100, acumulado

  KPI 6                   Itens desenvolvidos em  (Itens feitos em dupla
                          dupla                   ÷ itens feitos) × 100
  -----------------------------------------------------------------------

Os indicadores estão associados a objetivos como previsibilidade das
entregas, cobertura dos requisitos essenciais, qualidade dos
incrementos, controle do escopo e aplicação do Pair Programming.

**Atenção:** não basta registrar os nomes e as fórmulas dos KPIs. A
entrega da faculdade solicita que os valores sejam medidos ao longo do
projeto. Portanto, a equipe precisa registrar os dados de origem e a
periodicidade de medição.

------------------------------------------------------------------------

## 9. Artefatos pendentes ou que precisam ser completados

Com base no material analisado até o momento:

-   **Quadro Scrum:** ainda não está pronto, conforme informado pela
    equipe.
-   **Rastreabilidade:** a documentação do projeto possui a seção, mas a
    tabela de requisito, classe, testes e status ainda está sem
    preenchimento.
-   **Medições dos KPIs:** as definições e fórmulas estão documentadas;
    os valores medidos precisam ser registrados conforme o andamento das
    sprints.

Esses pontos devem ser atualizados à medida que o projeto evoluir.

------------------------------------------------------------------------

## 10. Preparação para o diagrama de classes

O diagrama de classes é uma das entregas exigidas pela unidade
curricular de Técnicas de Programação.

Ele deverá representar a estrutura estática do sistema, de acordo com as
responsabilidades e relações que forem definidas para a solução.

Antes de desenhá-lo, a equipe precisa compreender os conceitos do
domínio e decidir como cada responsabilidade será representada na
implementação.

Alguns termos presentes na documentação merecem análise cuidadosa:

-   Produto;
-   Lote;
-   Doação;
-   Distribuição;
-   Descarte;
-   Estoque;
-   Movimentação;
-   Relatório.

**Este documento não define antecipadamente que cada termo deverá
obrigatoriamente se tornar uma classe.** Essa decisão será tomada
durante a análise e a modelagem, considerando os requisitos, as regras
de negócio e as responsabilidades do sistema.

Também será importante distinguir: - O produto cadastrado no catálogo; -
A quantidade física disponível; - Os lotes que possuem validade
própria; - Os eventos de entrada e saída; - Os registros de distribuição
e descarte.

A regra FEFO, a manutenção de lotes com saldo zero para histórico e as
restrições de validade e quantidade serão referências importantes nessa
etapa.

------------------------------------------------------------------------

## 11. Referências documentais da equipe

Os documentos que fundamentam este resumo são:

1.  **Projeto_Integrador_ES2_PI2026-2.pdf** --- orientações oficiais da
    faculdade, competências, entregas, avaliação e cronograma.
2.  **Documentação do Projeto.pdf** --- visão geral da solução,
    requisitos, regras de negócio, restrições, priorização e histórico
    de versões.
3.  **Modelo de Processos.pdf** --- modelo híbrido, justificativa,
    papéis e planejamento das sprints.
4.  **Mapeamento de Processos.pdf** --- diagramas BPMN do processo de
    desenvolvimento e dos processos de negócio.
5.  **KPIS do Processo.pdf** --- objetivos, perguntas, métricas,
    fórmulas e fontes dos indicadores.

O Quadro Scrum será acrescentado quando estiver concluído.

------------------------------------------------------------------------

## 12. Resumo executivo

O projeto consiste no desenvolvimento de um sistema desktop em Java para
apoiar uma ONG no controle de doações e estoque de alimentos. A solução
deverá registrar produtos e lotes, controlar validade, orientar a
distribuição pela regra FEFO e apoiar o acompanhamento de arrecadações,
saídas e descartes.

O MVP está planejado com interface Swing, arquitetura MVC como
preferência, persistência em CSV e acesso aos arquivos por meio do
padrão DAO. A equipe adotou Scrum com práticas do XP, organizando o
trabalho em quatro sprints e utilizando Git/GitHub para versionamento.

Além do software, a equipe precisa entregar documentação de requisitos,
BPMN, quadro de acompanhamento, KPIs medidos, diagrama de classes,
relatório técnico e os demais artefatos solicitados pela faculdade.

> **Regra de consulta:** em caso de dúvida sobre o escopo ou uma decisão
> já documentada, consulte primeiro os documentos originais listados
> acima. Se uma informação ainda não estiver definida, ela deve ser
> tratada como uma decisão pendente da equipe, e não como uma regra já
> estabelecida.

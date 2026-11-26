# Conceitos do Domínio — Sistema de Controle de Doações e Validade de Alimentos

## Introdução

Antes de construir o diagrama de classes, precisamos compreender o domínio do sistema: os conceitos do problema que o software precisa representar e como eles se relacionam.

Os exemplos abaixo utilizam o cenário da ONG. É importante lembrar que **um conceito do negócio não necessariamente precisa virar uma classe Java**. Primeiro entendemos seu significado; depois decidimos como representá-lo na modelagem.

---

## 1. Produto

O **Produto** representa um tipo de alimento cadastrado no catálogo da ONG.

Imagine que a ONG receba arroz de diferentes doadores, em diferentes datas e com diferentes validades. Apesar dessas diferenças, todos esses itens podem ser do mesmo produto: arroz.

Exemplo:

| Informação | Valor |
|---|---|
| Nome | Arroz |
| Marca | Tio João |
| Unidade de medida | Pacote |
| Peso por unidade | 1 kg |
| Código | 7891234567890 |

O produto representa a identificação e as características gerais do alimento.

**Ponto principal:** o produto não representa uma quantidade específica que chegou à ONG. Ele representa o item cadastrado no catálogo.

Pense nele como uma ficha que descreve o alimento.

## 2. Lote

O **Lote** representa uma quantidade de determinado produto recebida em uma entrada específica, com sua própria quantidade e data de validade.

Esse conceito é um dos mais importantes do projeto, principalmente por causa da regra FEFO.

Imagine que a ONG receba arroz em dois momentos diferentes:

| Informação | Lote 001 | Lote 002 |
|---|---|---|
| Produto | Arroz | Arroz |
| Quantidade recebida | 50 pacotes | 30 pacotes |
| Validade | 10/12/2026 | 20/01/2027 |
| Data de entrada | 25/11/2026 | 27/11/2026 |

Temos **um único produto, mas dois lotes diferentes**.

Isso importa porque cada lote possui uma validade própria. Se a ONG precisar distribuir 60 pacotes de arroz, não poderá simplesmente retirar 60 unidades de qualquer lugar.

Ela deverá respeitar a regra FEFO:

**First Expire, First Out — primeiro que vence, primeiro que sai.**

Nesse exemplo:

1. Primeiro, utiliza os 50 pacotes do Lote 001.
2. Ainda faltam 10 pacotes.
3. Então utiliza 10 pacotes do Lote 002.

Resultado:

| Lote | Quantidade inicial | Quantidade retirada | Saldo |
|---|---:|---:|---:|
| Lote 001 | 50 | 50 | 0 |
| Lote 002 | 30 | 10 | 20 |

A regra RN05 da documentação estabelece que todo lote precisa estar vinculado a um produto cadastrado.

**Diferença fundamental:** Produto identifica o que é o alimento; Lote identifica uma entrada específica daquele alimento, com quantidade e validade próprias.

## 3. Doação

A **Doação** representa o recebimento de alimentos pela ONG.

Imagine que uma pessoa ou empresa entregue os seguintes itens:

- 50 pacotes de arroz;
- 20 pacotes de feijão;
- 15 litros de leite.

Isso representa um evento de recebimento.

A partir desse recebimento, o sistema precisa registrar os produtos, as quantidades e as respectivas validades, criando os lotes correspondentes.

Podemos visualizar assim:

**Recebimento de uma doação**
- Arroz → 50 pacotes → Lote 001.
- Feijão → 20 pacotes → Lote 002.
- Leite → 15 litros → Lote 003.

Perceba a diferença:

- **Doação:** representa o acontecimento do recebimento.
- **Lote:** representa a quantidade recebida de um produto específico, com sua validade.

Uma doação pode envolver vários produtos e, consequentemente, vários lotes.

**Atenção à documentação:** ela exige o registro de doações por lote, mas não especifica todos os dados de uma entidade Doação, como identificação do doador ou número de protocolo. O projeto também prevê não armazenar dados pessoais de doadores e beneficiários.

Portanto, ainda precisaremos decidir se faz sentido representar Doação como uma entidade própria ou se o registro de entrada será tratado de outra maneira.

## 4. Distribuição

A **Distribuição** representa a saída de alimentos do estoque para atendimento aos beneficiários da ONG.

Imagine que a ONG precise distribuir:

- 60 pacotes de arroz;
- 10 pacotes de feijão.

O sistema precisa verificar se existe quantidade disponível e quais lotes devem ser utilizados.

No caso do arroz, considerando nosso exemplo anterior:

| Lote | Saldo antes da distribuição |
|---|---:|
| Lote 001 | 50 |
| Lote 002 | 30 |

Para distribuir 60 pacotes:

- Retira 50 do Lote 001.
- Retira 10 do Lote 002.

O sistema registra a saída e atualiza os saldos.

A documentação estabelece duas regras importantes:

- A saída deve respeitar a ordem FEFO.
- A quantidade distribuída não pode superar o saldo disponível.

Além disso, lotes vencidos não podem participar da distribuição.

**Diferença importante:** a distribuição é o evento de saída; os lotes são as origens das quantidades que estão sendo retiradas.

Uma única distribuição pode consumir quantidades de vários lotes.

## 5. Descarte

O **Descarte** representa a retirada de alimentos do estoque que não serão distribuídos.

Imagine que um lote tenha 20 pacotes de arroz e que esses alimentos tenham vencido.

Esses pacotes não podem continuar sendo considerados disponíveis para distribuição.

A ONG precisa registrar o descarte para manter o controle do que aconteceu com aquela quantidade.

Exemplo:

| Informação | Valor |
|---|---|
| Produto | Arroz |
| Lote | Lote 003 |
| Quantidade descartada | 20 pacotes |
| Motivo | Vencido |

Observe que descarte e distribuição são coisas diferentes:

- **Distribuição:** alimento sai do estoque para ser entregue.
- **Descarte:** alimento sai do estoque porque não pode ou não deve ser aproveitado.

A regra RN10 determina que itens vencidos devem ser descartados com o motivo "vencido" e contabilizados separadamente das distribuições.

Isso é importante para que os relatórios não confundam alimentos entregues aos beneficiários com alimentos perdidos.

## 6. Estoque

O **Estoque** representa a situação das quantidades de alimentos que a ONG possui em determinado momento.

Imagine:

| Produto | Lote | Saldo |
|---|---|---:|
| Arroz | 001 | 20 |
| Arroz | 002 | 30 |
| Feijão | 003 | 15 |

O estoque atual seria:

| Produto | Quantidade total |
|---|---:|
| Arroz | 50 |
| Feijão | 15 |

O estoque pode ser consultado por produto, mas as quantidades estão distribuídas entre os lotes.

Isso é importante porque o sistema precisa conhecer não apenas a quantidade total de arroz, mas também:

- Quanto existe em cada lote.
- Qual é a validade de cada lote.
- Quanto ainda pode ser distribuído.
- Quais lotes estão vencidos ou próximos do vencimento.

### Estoque físico x estoque disponível

Imagine que a ONG tenha 100 pacotes de arroz:

- 70 estão dentro da validade.
- 30 estão vencidos.

O estoque físico pode indicar 100 pacotes, mas o estoque disponível para distribuição é de apenas 70.

Essa distinção ajuda a evitar que o sistema considere alimentos vencidos como disponíveis.

**Detalhe de modelagem:** estoque pode ser simplesmente uma informação calculada a partir dos saldos dos lotes. Não significa necessariamente que precisaremos criar uma classe chamada `Estoque`.

## 7. Movimentação

A **Movimentação** é um conceito que representa uma alteração na quantidade de produtos armazenados.

Pense nas operações que podem acontecer:

| Acontecimento | Efeito no estoque |
|---|---|
| Recebimento de doação | Aumenta |
| Distribuição | Diminui |
| Descarte | Diminui |

Exemplo de linha do tempo:

1. A ONG recebe 50 pacotes de arroz.
2. Depois distribui 20 pacotes.
3. Posteriormente descarta 5 pacotes vencidos.

O saldo final será:

**50 − 20 − 5 = 25 pacotes.**

A movimentação permite compreender o histórico dessas alterações.

Entretanto, existe um detalhe importante no projeto: o RF20, referente ao histórico de movimentações, foi classificado como **Could** no MoSCoW.

Isso significa que o histórico completo de movimentações não é essencial para o MVP atual.

Não devemos, portanto, assumir que uma classe `Movimentacao` precisa obrigatoriamente existir na primeira versão.

## 8. Relatório

O **Relatório** representa uma apresentação organizada de informações registradas pelo sistema.

A documentação prevê, entre outros:

- Relatório de distribuição.
- Relatório de arrecadação.
- Estatísticas descritivas.

Por exemplo, um relatório de arrecadação poderia apresentar:

| Produto | Quantidade recebida |
|---|---:|
| Arroz | 100 pacotes |
| Feijão | 50 pacotes |
| Leite | 40 litros |

Já um relatório de distribuição apresentaria as quantidades que efetivamente saíram para os beneficiários.

A documentação também estabelece que:

- A arrecadação considera a data de entrada.
- A distribuição considera a data de saída.
- Os filtros por período incluem as datas inicial e final.

Relatório, portanto, é diferente de uma movimentação: ele apresenta e organiza informações que o sistema já registrou.

---

## 9. Como esses conceitos se relacionam? — Exemplo completo

Vamos juntar tudo em um exemplo fictício.

A ONG recebe uma doação contendo 50 pacotes de arroz.

### Etapa 1 — Cadastro

O produto Arroz já está cadastrado no catálogo.

### Etapa 2 — Recebimento

A ONG registra uma doação de 50 pacotes de arroz.

### Etapa 3 — Formação do lote

O sistema registra um lote com:

- Produto: Arroz.
- Quantidade inicial: 50.
- Validade: 10/12/2026.
- Saldo atual: 50.

### Etapa 4 — Consulta ao estoque

O sistema passa a apresentar 50 pacotes de arroz disponíveis.

### Etapa 5 — Distribuição

A ONG distribui 20 pacotes.

O saldo do lote passa a ser 30.

### Etapa 6 — Descarte

Imagine que, posteriormente, os 30 pacotes restantes vençam e sejam descartados.

O saldo disponível passa a ser zero.

### Etapa 7 — Relatórios

O sistema consegue apresentar:

- Quantidade arrecadada: 50 pacotes.
- Quantidade distribuída: 20 pacotes.
- Quantidade descartada: 30 pacotes.

Assim, conseguimos acompanhar o destino dos alimentos.

---

## 10. Resumo dos conceitos

| Conceito | O que representa |
|---|---|
| Produto | O alimento cadastrado no catálogo. |
| Lote | Uma quantidade de determinado produto, recebida em uma entrada específica e com validade própria. |
| Doação | O acontecimento de recebimento de alimentos. |
| Distribuição | O acontecimento de saída de alimentos para os beneficiários. |
| Descarte | A retirada de alimentos que não serão aproveitados. |
| Estoque | A situação das quantidades armazenadas e disponíveis. |
| Movimentação | Uma alteração na quantidade de estoque. |
| Relatório | Uma apresentação organizada dos dados registrados. |

---

## 11. O que precisamos compreender antes do diagrama?

Existem três distinções especialmente importantes:

1. **Produto não é Lote:** um produto pode possuir vários lotes, cada um com sua própria validade e quantidade.
2. **Doação não é Lote:** a doação representa o recebimento; o lote representa os itens específicos recebidos.
3. **Estoque não é necessariamente uma entidade:** ele pode ser calculado a partir dos saldos dos lotes.

Também precisamos lembrar que uma distribuição pode utilizar vários lotes e que o descarte não deve ser confundido com a distribuição.

### Próxima etapa

Antes de desenhar as classes, vamos analisar as relações entre esses conceitos. Por exemplo:

- Um Produto pode ter vários Lotes?
- Uma Doação pode gerar vários Lotes?
- Uma Distribuição pode consumir vários Lotes?

Essa análise vai nos preparar para identificar as associações, multiplicidades e responsabilidades do futuro diagrama de classes.

> **Importante:** este material é uma explicação conceitual para apoiar a análise do domínio. Ele não determina antecipadamente que cada conceito apresentado deverá se tornar uma classe Java. Essa decisão será feita durante a modelagem, considerando os requisitos e as regras de negócio do projeto.

# Análise das Relações entre os Conceitos do Domínio

## Introdução

Depois de compreender os principais conceitos do sistema, precisamos analisar como eles se relacionam. Essa etapa prepara a construção do diagrama de classes UML.

Na análise das relações, devemos identificar:

- Quais conceitos possuem relação entre si.
- Qual é a natureza dessa relação.
- Quantos objetos podem participar dela (multiplicidade).
- Se existe algum conceito intermediário necessário para registrar informações da relação.

**Importante:** algumas relações abaixo decorrem diretamente das regras documentadas; outras são propostas de modelagem que ainda precisam ser confirmadas pela equipe.

---

## 1. O que são associações?

Uma associação representa uma relação entre dois conceitos.

Por exemplo: um Produto possui Lotes.

Além da associação, precisamos definir sua **multiplicidade**, que indica quantas instâncias de uma classe podem estar relacionadas a uma instância da outra classe.

### Multiplicidades mais comuns

| Multiplicidade | Significado                      |
| -------------- | -------------------------------- |
| `1`            | Exatamente um                    |
| `0..1`         | Nenhum ou um                     |
| `*` ou `0..*`  | Nenhum ou muitos                 |
| `1..*`         | Um ou muitos                     |
| `2..5`         | No mínimo dois e no máximo cinco |

## 2. Produto e Lote

**Relação: um para muitos (1:N).**

Um produto pode possuir nenhum ou vários lotes. Um lote, por sua vez, pertence obrigatoriamente a um único produto.

Exemplo: o produto Arroz pode possuir os lotes 001, 002 e 003.

| Lado | Multiplicidade | Justificativa |
|---|---|---|
| Produto | `1` | Todo lote precisa pertencer a um produto. |
| Lote | `0..*` | Um produto pode não ter lotes ou possuir vários. |

Representação conceitual:

`Produto (1) — (0..*) Lote`

Essa relação é fundamentada na regra RN05: todo lote deve estar vinculado a um produto cadastrado.

Ela permite consultar todos os lotes de determinado produto, calcular seu saldo total e identificar quais lotes devem ser utilizados primeiro pela regra FEFO.

## 3. Doação e Lote

**Relação proposta: um para muitos (1:N).**

Imagine que uma empresa faça uma única doação contendo:

- 50 pacotes de arroz;
- 30 pacotes de feijão;
- 20 litros de leite.

Essa doação pode gerar três lotes diferentes, um para cada produto recebido.

- Uma Doação pode gerar um ou vários Lotes (`1..*`).
- Cada Lote estaria associado a uma única Doação (`1`).

**Observação:** essa é uma proposta de modelagem coerente com o funcionamento descrito. A documentação exige registrar doações por lote, mas não define explicitamente uma entidade `Doacao` com todos esses relacionamentos. A equipe ainda precisa confirmar essa decisão.

## 4. Distribuição e Lote

**Relação proposta: muitos para muitos (N:N).**

Imagine que a ONG faça uma distribuição de 60 pacotes de arroz. Para atender à quantidade, ela utiliza:

- 50 pacotes do Lote 001;
- 10 pacotes do Lote 002.

Portanto:

- Uma Distribuição pode utilizar vários Lotes.
- Um Lote pode participar de várias Distribuições ao longo do tempo.

Essa relação permite saber exatamente de quais lotes saiu cada quantidade.

### Possível conceito intermediário: ItemDistribuicao

Podemos precisar de um conceito intermediário, como `ItemDistribuicao`, para registrar a quantidade retirada de cada lote.

Exemplo:

| Distribuição | Lote | Quantidade |
|---|---|---:|
| 001 | Lote 001 | 50 |
| 001 | Lote 002 | 10 |
| 002 | Lote 002 | 5 |

Assim, uma distribuição pode ter vários itens de distribuição, e cada item registra a quantidade retirada de um lote específico.

## 5. Descarte e Lote

**Relação proposta: muitos para muitos (N:N).**

Imagine que a ONG descarte:

- 10 pacotes do Lote 001;
- 5 pacotes do Lote 002.

Um único descarte pode envolver vários lotes. Além disso, um mesmo lote pode sofrer descartes em momentos diferentes, caso apenas parte de seu saldo seja descartada em cada ocasião.

### Possível conceito intermediário: ItemDescarte

Assim como na distribuição, podemos precisar de um conceito intermediário, por exemplo, `ItemDescarte`, para registrar a quantidade retirada de cada lote.

## 6. Produto e Estoque

Não precisamos necessariamente criar uma associação entre `Produto` e uma classe `Estoque`.

Um produto possui vários lotes, e cada lote possui um saldo. O estoque total de um produto pode ser obtido somando os saldos de seus lotes.

Exemplo:

| Produto | Lote | Saldo |
|---|---|---:|
| Arroz | 001 | 20 |
| Arroz | 002 | 30 |
| Arroz | 003 | 10 |

Estoque total de arroz: **60 pacotes**.

O estoque pode ser calculado a partir dos lotes. Portanto, não devemos criar uma classe `Estoque` sem antes identificar uma responsabilidade própria que justifique sua existência.

## 7. Lote e Movimentação

**Relação proposta: um para muitos (1:N).**

Uma movimentação representa uma alteração no saldo de um lote.

Exemplo de operações relacionadas a um lote:

- Entrada de 50 pacotes.
- Saída de 20 pacotes por distribuição.
- Saída de 5 pacotes por descarte.

Um lote pode possuir várias movimentações ao longo do tempo.

**Atenção ao escopo:** o histórico de movimentações corresponde ao RF20, classificado como **Could** no MoSCoW. Portanto, o histórico completo não é essencial para o MVP atual e pode ficar fora da primeira versão, caso não seja implementado.

---

## 8. Resumo geral das relações

| Relação | Multiplicidade | Observação |
|---|---|---|
| Produto — Lote | 1:N | Relação definida pela regra RN05. |
| Doação — Lote | 1:N | Proposta de modelagem. |
| Distribuição — Lote | N:N | Pode exigir `ItemDistribuicao`. |
| Descarte — Lote | N:N | Pode exigir `ItemDescarte`. |
| Produto — Estoque | Não necessariamente existe | Estoque pode ser calculado pelos lotes. |
| Lote — Movimentação | 1:N | Relacionada ao RF20, classificado como Could. |

## 9. Conceitos intermediários importantes

Além das relações, dois conceitos podem aparecer na modelagem:

### ItemDistribuicao

Representa a quantidade retirada de um lote em determinada distribuição.

### ItemDescarte

Representa a quantidade retirada de um lote em determinado descarte.

Esses conceitos podem resolver as relações muitos para muitos, pois armazenam informações próprias, como a quantidade movimentada.

---

## 10. Preparação para o diagrama de classes

A análise das relações oferece uma base para começar a identificar as classes candidatas e suas responsabilidades.

A proposta inicial é avaliar:

- `Produto`
- `Lote`
- `Doacao`
- `Distribuicao`
- `Descarte`
- `ItemDistribuicao`
- `ItemDescarte`

Não devemos transformar automaticamente cada substantivo em uma classe. Para cada candidato, será necessário avaliar:

- Se possui informações próprias.
- Se possui responsabilidades.
- Se participa de regras de negócio.
- Se precisa manter estado ou histórico.
- Se é necessário para os requisitos priorizados do MVP.

**Importante:** as relações e classes propostas neste documento são uma base de análise. A modelagem final deverá ser confirmada pela equipe e compatibilizada com os requisitos, as regras de negócio e o escopo efetivo do MVP.

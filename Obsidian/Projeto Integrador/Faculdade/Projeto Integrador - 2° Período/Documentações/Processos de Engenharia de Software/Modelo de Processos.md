

# Modelo de Processos


## Modelo Adotado


Modelo Híbrido: Scrum com práticas do XP (Pair programming e integração frequente).

- Scrum: desenvolvimento incremental em sprints curtas, cada uma com meta e entrega de um incremento funcional. O backlog do produto é o documento de requisitos priorizado por MoSCoW.
- XP (Pair Programming): os desenvolvedores trabalham em duplas no código, para nivelar o conhecimento e reduzir erros.
- XP(Integração pequena): commits pequenos e frequentes no Git.

## Justificativa

| Critério                       | Característica do Projeto                                                                               | Efeito na escolha                                                                               |
| ------------------------------ | ------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| Porte                          | Pequeno: MVP de um sistema desktop, cerca de 24 requisitos funcionais e 5 não funcionais                | Não exige processo pesado e comporta um framework leve como o Scrum                             |
| Previsibilidade dos requisitos | Alta: requisitos documentados e priorizados desde o início                                              | Permite planejar as sprints cedo. Ainda assim, o Scrum acomoda ajustes via registro de mudanças |
| Criticidade                    | Baixa: projeto acadêmico, sem risco a vidas ou a dados sensíveis                                        | Dispensa rigor de modelos orientados a documentação, como cascata ou V                          |
| Capacidade da equipe           | Cinco integrantes com níveis mistos de domínio em Java e Swing, sem experiência em testes automatizados | O pair programming nivela o conhecimento                                                        |
| Prazo                          | Cerca de duas semanas até o software funcionando (04/12)                                                | Incrementos curtos garantem entrega parcial funcional mesmo se houver atraso                    |


## Papeis

| Papel           | Responsável                    | Atribuições                                                            |
| --------------- | ------------------------------ | ---------------------------------------------------------------------- |
| Product Owner   | Davi                           | Mantém e prioriza o backlog (MoSCoW) e aceita os itens entregues       |
| Scrum Master    | Davi                           | Conduz as cerimônias, mantém o quadro atualizado e remove impedimentos |
| Desenvolvedores | Gustavo, Luan, Ronaldo e Vitor | Implementam, testam e documentam, em duplas                            |



## Sprints




| Sprint | Período       | Meta                                                         |
| ------ | ------------- | ------------------------------------------------------------ |
| 1      | 25/11 a 27/11 | Estrutura MVC e cadastro de produtos com persistência em CSV |
| 2      | 28/11 a 01/12 | Ciclo completo de doação e distribuição com FEFO             |
| 3      | 02/12 a 04/12 | Alertas, relatórios e MVP pronto para demonstração           |
| 4      | 05/12 a 06/12 | Consolidação dos artefatos e envio da pasta                  |



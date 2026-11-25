

# Git e GitHub na prática: uma história sobre desenvolvimento colaborativo

> [!abstract] Objetivo desta documentação  
> Aprender como funciona o desenvolvimento colaborativo utilizando Git e GitHub, compreendendo branches locais e remotas, clone, fetch, pull, push, commits, Pull Requests, Code Review e merge.
> 
> Em vez de apenas memorizar comandos, vamos acompanhar uma equipe fictícia desenvolvendo um projeto do início ao fim.

---

# 1. Conhecendo a equipe

Imagine que quatro estudantes de Engenharia de Software decidiram desenvolver um sistema de gerenciamento de biblioteca para um projeto integrador da faculdade.

A equipe é composta por:

|Integrante|Responsabilidade|
|---|---|
|Luan|Líder da equipe e responsável pela configuração inicial do repositório.|
|Gustavo|Desenvolvedor responsável pela funcionalidade de cadastro de livros.|
|Ronaldo|Desenvolvedor responsável pela funcionalidade de empréstimos.|
|Marina|Desenvolvedora responsável pela documentação e testes.|

A equipe decidiu utilizar:

- **Git:** para controlar as versões do código e acompanhar as modificações.
    
- **GitHub:** para hospedar o repositório e permitir o trabalho colaborativo.
    
- **Branches:** para que cada integrante possa desenvolver funcionalidades independentemente.
    
- **Pull Requests:** para revisar o código antes de incorporá-lo à branch principal.
    

Agora vamos acompanhar essa equipe durante o desenvolvimento.

---

# 2. Luan cria o repositório no GitHub

O primeiro passo é criar um repositório remoto para armazenar o projeto.

Luan acessa o GitHub e cria um repositório chamado:

`Sistema-Biblioteca`

Ele configura o repositório como público e adiciona um README.md com uma breve descrição do projeto.

A estrutura inicial fica assim:

```
Sistema-Biblioteca/
│
└── README.md
```

No GitHub, a branch principal é chamada de `main`.

> [!info] O que é um repositório remoto?  
> É o repositório hospedado em uma plataforma como o  EleGitHub. permite que diferentes desenvolvedores compartilhem o histórico e as versões do projeto.

## 2.1. Luan adiciona os arquivos do projeto

Luan decide começar o desenvolvimento localmente.

Ele cria uma pasta para o projeto e inicializa o Git:

```
git init
```

Esse comando transforma a pasta em um repositório Git local.

Depois, ele adiciona os arquivos:

```
Sistema-Biblioteca/
│
├── README.md
├── src/
│   └── Main.java
│
└── docs/
    └── requisitos.md
```

Para preparar os arquivos para o próximo commit:

```
git add .
```

Depois, registra as alterações:

```
git commit -m "Inicialização do projeto de biblioteca"
```

Por fim, Luan configura o endereço do repositório remoto:

```
git remote add origin https://github.com/luan/Sistema-Biblioteca.git
```

Aqui temos um conceito importante:

- `origin`: é o apelido dado ao repositório remoto.
    
- O endereço após `origin` é a URL do repositório no GitHub.
    

Luan envia os arquivos:

```
git push -u origin main
```

O parâmetro `-u` configura o acompanhamento entre a branch local `main` e a branch remota `origin/main`.

Pronto! O projeto está disponível no GitHub.

---

# 3. Gustavo clona o repositório

Agora Gustavo precisa começar a trabalhar.

Ele acessa o GitHub, copia a URL do repositório e executa:

```
git clone https://github.com/luan/Sistema-Biblioteca.git
```

O que aconteceu?

O Git criou uma cópia do repositório no computador de Gustavo.

Essa operação também traz o histórico de commits e as referências das branches remotas.

A estrutura no computador dele fica assim:

```
Sistema-Biblioteca/
│
├── README.md
├── src/
│   └── Main.java
│
└── docs/
    └── requisitos.md
```

Gustavo entra na pasta:

```
cd Sistema-Biblioteca
```

Agora ele executa:

```
git branch
```

Resultado:

```
* main
```

Aparece somente a branch local `main`.

Entretanto, se ele executar:

```
git branch -a
```

Poderá visualizar:

```
* main
  remotes/origin/HEAD -> origin/main
  remotes/origin/main
```

## 3.1. Entendendo o que aconteceu

Gustavo possui uma branch local chamada `main`.

Mas também possui uma referência chamada `origin/main`.

Essas duas referências não são a mesma coisa.

|Referência|Significado|
|---|---|
|`main`|Branch local de Gustavo.|
|`origin/main`|Referência local que representa o estado conhecido da branch main no GitHub.|

> [!important] Atenção!  
> Uma branch remota não é automaticamente uma branch local.
> 
> O Git mantém referências locais que representam o estado conhecido das branches existentes no repositório remoto.

---

# 4. Gustavo cria uma nova branch

Gustavo não quer desenvolver diretamente na `main`.

Ele conversa com Luan e decide criar uma branch específica para desenvolver o cadastro de livros.

Ele executa:

```
git switch -c cadastro-livros
```

O que esse comando faz?

- `switch`: muda de branch.
    
- `-c`: cria uma nova branch.
    
- `cadastro-livros`: nome da nova branch.
    

Agora a estrutura local de Gustavo é:

```
Branches locais:

* cadastro-livros
  main
```

Gustavo começa a desenvolver a funcionalidade.

Ele modifica o arquivo `Main.java` e cria uma classe chamada `Livro.java`.

A estrutura passa a ser:

```
Sistema-Biblioteca/
│
├── README.md
├── src/
│   ├── Main.java
│   └── Livro.java
│
└── docs/
    └── requisitos.md
```

Depois de terminar uma parte da funcionalidade, ele executa:

```
git add .
```

Registra um commit:

```
git commit -m "Criação da classe Livro"
```

E continua trabalhando.

Depois de implementar o cadastro:

```
git add .
git commit -m "Implementação do cadastro de livros"
```

Agora Gustavo possui dois novos commits em sua branch local.

---

# 5. Gustavo envia sua branch para o GitHub

Gustavo quer compartilhar seu trabalho com os outros integrantes.

Ele executa:

```
git push -u origin cadastro-livros
```

Vamos entender o comando:

|Parte|Significado|
|---|---|
|`git push`|Envia commits para o repositório remoto.|
|`-u`|Configura o acompanhamento da branch remota.|
|`origin`|Identifica o repositório remoto.|
|`cadastro-livros`|Identifica a branch que será enviada.|

Agora a branch também existe no GitHub.

Visualmente:

```
                 GITHUB
                    |
          +---------+---------+
          |                   |
         main          cadastro-livros
          |                   |
      Commit 1             Commit 1
                            Commit 2
                            Commit 3
```

Gustavo enviou seus commits, mas ainda não incorporou seu trabalho à `main`.

Essa distinção será importante mais adiante.

---

# 6. Luan quer visualizar o trabalho de Gustavo

Luan está trabalhando em seu computador e decide verificar as atualizações do repositório.

Ele executa:

```
git branch
```

Resultado:

```
* main
```

Luan percebe que não possui uma branch local chamada `cadastro-livros`.

Ele então pensa:

"Vou executar git pull para baixar as alterações do Gustavo!"

Mas Ronaldo, que está acompanhando o desenvolvimento, recomenda que ele espere.

**Ronaldo:**

"Calma, Luan! Antes de utilizar o pull, precisamos entender o que você realmente quer fazer."

**Luan:**

"Como assim? Eu só quero atualizar meu repositório."

**Ronaldo:**

"Você quer incorporar as alterações à sua main ou apenas descobrir o que o Gustavo enviou?"

**Luan:**

"Por enquanto, quero apenas descobrir o que ele enviou. Não quero modificar minha main."

**Ronaldo:**

"Então vamos utilizar git fetch!"

---

# 7. Entendendo git fetch

Ronaldo explica que o comando:

```
git fetch origin
```

Consulta o repositório remoto e atualiza as referências remotas conhecidas pelo Git local.

Isso significa que Luan poderá descobrir a existência da branch `cadastro-livros` e conhecer os novos commits enviados por Gustavo.

Entretanto, sua branch local `main` não será modificada.

## 7.1. Antes do fetch

Imagine que o computador de Luan esteja assim:

```
COMPUTADOR DO LUAN

Branches locais:

main
```

O GitHub possui:

```
GITHUB

main
cadastro-livros
```

Luan ainda não atualizou as informações sobre a nova branch.

## 7.2. Depois do fetch

Luan executa:

```
git fetch origin
```

Agora ele executa:

```
git branch
```

Resultado:

```
* main
```

Luan fica confuso.

**Luan:**

"Ronaldo, executei o fetch, mas a branch cadastro-livros não apareceu!"

**Ronaldo:**

"Isso é normal! O fetch não cria automaticamente uma branch local. Ele atualiza as referências remotas."

Luan executa:

```
git branch -r
```

Resultado:

```
origin/HEAD -> origin/main
origin/main
origin/cadastro-livros
```

Agora ele consegue visualizar a branch remota!

Se executar:

```
git branch -a
```

Resultado:

```
* main
  remotes/origin/HEAD -> origin/main
  remotes/origin/main
  remotes/origin/cadastro-livros
```

## 7.3. Afinal, o que são branches locais e remotas?

Ronaldo aproveita para explicar:

**Branches locais:**

São branches existentes no repositório Git do computador.

Exemplo:

```
main
cadastro-livros
```

**Branches remotas (remote-tracking branches):**

São referências locais que representam o estado conhecido das branches remotas.

Exemplo:

```
origin/main
origin/cadastro-livros
```

A palavra `origin` é apenas o apelido do repositório remoto.

> [!important] Cuidado!  
> `origin/cadastro-livros` não é uma branch que você está editando diretamente no GitHub.
> 
> É uma referência mantida pelo seu Git local para acompanhar a branch remota.

---

# 8. Luan pergunta: qual é a diferença entre fetch e pull?

**Luan:**

"Agora entendi o fetch. Mas ainda não entendi por que não poderia simplesmente utilizar o pull."

**Ronaldo:**

"Porque os dois comandos possuem comportamentos diferentes!"

Ronaldo decide demonstrar.

## 8.1. Utilizando git fetch

```
git fetch origin
```

O Git:

1. Consulta o repositório remoto.
    
2. Baixa os novos commits e objetos necessários.
    
3. Atualiza as referências remotas.
    
4. Não integra automaticamente as alterações à branch local atual.
    

Visualmente:

```
ANTES:

main local
   |
Commit A
Commit B


origin/cadastro-livros
   |
Commit A
Commit B
Commit C
Commit D
```

Depois do fetch:

```
main local
   |
Commit A
Commit B


origin/cadastro-livros
   |
Commit A
Commit B
Commit C
Commit D
```

Observe que a `main` continua igual.

## 8.2. Utilizando git pull

Agora Ronaldo apresenta outra situação.

Imagine que Luan e Gustavo estejam trabalhando na mesma branch `main`, e Gustavo tenha enviado novos commits para ela.

No computador de Luan:

```
main local

Commit A
Commit B
```

No GitHub:

```
origin/main

Commit A
Commit B
Commit C
Commit D
```

Luan executa:

```
git pull origin main
```

O Git realiza duas operações:

1. Fetch: busca as atualizações do repositório remoto.
    
2. Integração: incorpora as alterações à branch local atual.
    

Por padrão, essa integração utiliza merge, embora o pull também possa ser configurado para utilizar rebase.

Resultado:

```
main local

Commit A
Commit B
Commit C
Commit D
```

Agora a `main` local de Luan recebeu as atualizações da `origin/main`.

## 8.3. Comparação definitiva

|Característica|git fetch|git pull|
|---|---|---|
|Consulta o GitHub|Sim|Sim|
|Busca novos commits|Sim|Sim|
|Atualiza referências remotas|Sim|Sim|
|Integra alterações à branch atual|Não|Sim|
|Pode modificar a branch atual|Não|Sim|

**Ronaldo conclui:**

"Use fetch quando quiser descobrir o que mudou no remoto sem integrar essas mudanças à sua branch atual. Use pull quando quiser buscar e integrar as atualizações à branch em que está trabalhando."

---

# 9. Luan quer trabalhar na branch de Gustavo

Agora que Luan conhece a branch remota `origin/cadastro-livros`, ele decide acessar o código de Gustavo.

Ele executa:

```
git switch --track origin/cadastro-livros
```

Esse comando cria uma branch local chamada `cadastro-livros`, baseada na referência remota, e configura o acompanhamento entre elas.

Agora, se Luan executar:

```
git branch
```

Resultado:

```
* cadastro-livros
  main
```

Pronto!

Luan possui uma cópia local da branch de Gustavo e pode examinar seu código.

A estrutura agora é:

```
COMPUTADOR DO LUAN

Branches locais:

main
cadastro-livros


Referências remotas:

origin/main
origin/cadastro-livros
```

> [!tip] Alternativa mais simples  
> Nas versões atuais do Git, se existir uma única branch remota correspondente, também é possível utilizar:
> 
> `git switch cadastro-livros`
> 
> O Git normalmente consegue criar a branch local e configurar o acompanhamento automaticamente.

---

# 10. Marina começa a trabalhar na documentação

Enquanto isso, Marina decide criar a documentação do projeto.

Ela também clonou o repositório:

```
git clone https://github.com/luan/Sistema-Biblioteca.git
```

Entra na pasta:

```
cd Sistema-Biblioteca
```

Cria uma branch:

```
git switch -c documentacao
```

Adiciona os arquivos de documentação:

```
docs/
│
├── requisitos.md
├── arquitetura.md
└── manual-do-usuario.md
```

Depois registra as alterações:

```
git add .
git commit -m "Criação da documentação inicial"
```

E envia a branch:

```
git push -u origin documentacao
```

Agora temos três branches de trabalho no GitHub:

```
GITHUB

main
│
├── cadastro-livros
├── documentacao
└── (outras branches futuras)
```

Cada integrante pode desenvolver sua funcionalidade independentemente.

---

# 11. Ronaldo começa a trabalhar na funcionalidade de empréstimos

Ronaldo decide criar sua própria branch:

```
git switch -c emprestimos
```

Ele desenvolve a funcionalidade e registra seus commits:

```
git add .
git commit -m "Criação da classe Emprestimo"
```

Depois:

```
git add .
git commit -m "Implementação da funcionalidade de empréstimos"
```

Por fim:

```
git push -u origin emprestimos
```

Agora temos:

```
GITHUB

main
│
├── cadastro-livros
├── documentacao
└── emprestimos
```

Cada integrante possui uma branch independente.

Isso evita que todos precisem modificar diretamente a branch principal.

---

# 12. Chegou a hora de integrar as funcionalidades: Pull Request

Depois de alguns dias, Gustavo conclui sua funcionalidade de cadastro de livros.

Ele conversa com Luan:

**Gustavo:**

"Terminei o cadastro de livros e já enviei meus commits para o GitHub. Podemos integrar minha funcionalidade à main?"

**Luan:**

"Claro! Abra um Pull Request para que possamos revisar seu código."

Gustavo acessa o GitHub.

Ele abre o repositório e encontra a opção de criar um Pull Request.

Seleciona:

```
Base: main

Compare: cadastro-livros
```

O que isso significa?

- `Base`: branch que receberá as alterações.
    
- `Compare`: branch que contém as alterações que queremos incorporar.
    

Gustavo cria o Pull Request.

## 12.1. O que é um Pull Request?

Pull Request, também conhecido como PR, é uma solicitação para integrar alterações de uma branch em outra.

Ele permite que outros integrantes:

- Analizem as modificações.
    
- Visualizem os arquivos alterados.
    
- Comentem trechos específicos do código.
    
- Solicitem correções.
    
- Aprovaram as alterações antes do merge.
    

> [!important] Pull Request não é a mesma coisa que Pull!  
> Apesar dos nomes semelhantes, são conceitos diferentes.
> 
> `git pull` é um comando do Git utilizado para buscar e integrar alterações.
> 
> Pull Request é um recurso do GitHub utilizado para propor e revisar a integração de alterações entre branches.

---

# 13. Luan realiza o Code Review

Luan abre o Pull Request de Gustavo.

Ele analisa as alterações e percebe que existe um pequeno problema na implementação da classe `Livro`.

Em vez de aprovar imediatamente, ele comenta no Pull Request:

"Precisamos validar se o ISBN informado já existe antes de cadastrar um novo livro."

Gustavo recebe a notificação.

Ele volta ao computador, corrige o código e registra um novo commit:

```
git add .
git commit -m "Validação de ISBN duplicado"
```

Depois envia a atualização:

```
git push
```

Como a branch já está vinculada à branch remota, não é necessário informar novamente `origin` e o nome da branch.

O Pull Request é atualizado automaticamente!

Luan analisa novamente e aprova as alterações.

---

# 14. Realizando o Merge

Agora que o código foi revisado e aprovado, Luan pode realizar o Merge pelo GitHub.

O Merge integra os commits da branch `cadastro-livros` à branch `main`.

Visualmente:

**Antes do merge:**

```
main
 |
 A
 |
 B


cadastro-livros
 |
 A
 |
 B
 |
 C
 |
 D
```

**Depois do merge:**

```
main
 |
 A
 |
 B
 |
 C
 |
 D
```

Agora a funcionalidade de cadastro de livros faz parte da branch principal.

> [!info] Importante  
> O Merge pode ser realizado por diferentes estratégias, como Merge Commit, Squash and Merge e Rebase and Merge. Cada uma organiza o histórico de commits de uma maneira diferente.

---

# 15. Como os outros integrantes atualizam suas branches?

Agora que Gustavo integrou sua funcionalidade à `main`, os outros integrantes precisam atualizar suas próprias branches com as alterações mais recentes.

Imagine que Ronaldo esteja trabalhando na branch `emprestimos`.

Ele executa:

```
git switch main
```

Atualiza sua `main` local:

```
git pull origin main
```

Depois volta para sua branch:

```
git switch emprestimos
```

Agora precisa incorporar as atualizações recentes da `main` à sua branch de trabalho.

Uma maneira de fazer isso é:

```
git merge main
```

Assim, Ronaldo incorpora à sua branch as alterações que já foram integradas à `main`.

Se houver conflitos, ele precisará resolvê-los antes de concluir a integração.

Depois pode continuar desenvolvendo normalmente.

> [!warning] Atenção aos conflitos!  
> Conflitos podem acontecer quando diferentes desenvolvedores modificam trechos incompatíveis de um mesmo arquivo.
> 
> O Git identifica os conflitos, mas os desenvolvedores precisam decidir como combinar as alterações.

---

# 16. O fluxo completo da equipe

Agora que acompanhamos toda a história, vamos organizar o fluxo de trabalho.

```
                 GITHUB
                    |
                 main
                    |
          +---------+---------+
          |         |         |
          |         |         |
     cadastro   documentacao  emprestimos
     -livros
          |         |         |
          |         |         |
       Gustavo    Marina    Ronaldo
          |
          |
     Pull Request
          |
          |
      Code Review
          |
          |
        Aprovação
          |
          |
         Merge
          |
          |
         main
```

Cada integrante trabalha em sua própria branch.

Quando termina uma funcionalidade, envia seus commits para o GitHub e abre um Pull Request.

A equipe revisa o código e, depois da aprovação, integra as alterações à branch principal.

---

# 17. Tabela de comandos utilizados

|Comando|Função|
|---|---|
|`git init`|Inicializa um repositório Git local.|
|`git clone URL`|Clona um repositório remoto.|
|`git status`|Mostra o estado dos arquivos e alterações.|
|`git add .`|Adiciona as alterações à área de preparação.|
|`git commit -m "mensagem"`|Registra um commit.|
|`git remote add origin URL`|Configura um repositório remoto.|
|`git remote -v`|Lista os endereços dos repositórios remotos.|
|`git push`|Envia commits ao repositório remoto.|
|`git push -u origin branch`|Envia a branch e configura seu acompanhamento.|
|`git fetch origin`|Busca atualizações e atualiza referências remotas.|
|`git pull`|Busca e integra atualizações à branch atual.|
|`git branch`|Lista branches locais.|
|`git branch -r`|Lista referências de branches remotas.|
|`git branch -a`|Lista branches locais e referências remotas.|
|`git switch branch`|Troca de branch.|
|`git switch -c branch`|Cria e troca para uma nova branch.|
|`git switch --track origin/branch`|Cria uma branch local acompanhando uma referência remota.|
|`git merge branch`|Integra outra branch à branch atual.|
|`git log`|Exibe o histórico de commits.|

---

# 18. Resumo: conceitos que todos precisam entender

## Repositório local e remoto

- Local: repositório existente no computador.
    
- Remoto: repositório hospedado no GitHub.
    

## Branch local e referência remota

- `main`: branch local.
    
- `origin/main`: referência local que acompanha a branch remota.
    

## Fetch e Pull

- `fetch`: busca atualizações sem integrá-las à branch atual.
    
- `pull`: busca atualizações e integra à branch atual.
    

## Push e Pull Request

- `push`: envia commits para o GitHub.
    
- Pull Request: solicita a integração de uma branch em outra, permitindo revisão.
    

## Merge

Integra as alterações de uma branch em outra.

---

# 19. Fluxo recomendado para nosso projeto integrador

Minha sugestão é que nossa equipe utilize o seguinte procedimento:

**1. Atualizar a branch principal:**

```
git switch main
git pull origin main
```

**2. Criar uma branch para a funcionalidade:**

```
git switch -c nome-da-funcionalidade
```

**3. Desenvolver a funcionalidade.**

**4. Preparar as alterações:**

```
git add .
```

**5. Criar um commit:**

```
git commit -m "Descrição da alteração"
```

**6. Enviar a branch para o GitHub:**

```
git push -u origin nome-da-funcionalidade
```

**7. Abrir um Pull Request.**

**8. Aguardar o Code Review.**

**9. Corrigir eventuais problemas apontados.**

**10. Após a aprovação, realizar o Merge na main.**

**11. Atualizar a main local dos integrantes:**

```
git switch main
git pull origin main
```

E o ciclo recomeça para a próxima funcionalidade!

---

# 20. Conclusão

O Git não serve apenas para armazenar versões de código. Ele permite que diferentes desenvolvedores trabalhem simultaneamente, organizem suas modificações e mantenham um histórico de tudo que foi realizado.

Já o GitHub complementa esse processo, oferecendo ferramentas para colaboração, revisão e integração das alterações.

A principal lição desta história é:

**Cada integrante desenvolve em sua própria branch, envia suas alterações para o repositório remoto e utiliza Pull Requests para solicitar a integração do código à branch principal.**

Dessa maneira, a equipe consegue trabalhar de forma organizada, colaborativa e com maior controle sobre a qualidade do software.

# Classe de Modelo



## Produto


```java
package models;

public class Produto {

    private int id;
    private String codigo;
    private String nome;
    private String marca;
    private String unidadeMedida;

    public Produto(int id, String codigo, String nome, String marca, String unidadeMedida) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.unidadeMedida = unidadeMedida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
    
    
    // Métodos da Classe Produto
    
    public void validarCodigo(){
        
        /*
        O método matches verifica se uma String corresponde a uma expressão regular, no caso
        esse método garante que o código tenha obrigatoriamente 8 ou 13 digitos, se não tiver ele lança uma exceção.
        */
        if (codigo == null || !codigo.matches("\\d{8}\\d{13}")){
            throw new IllegalArgumentException("O código deve ter entre 8 e 13 digítos");
        }
        
        int soma = 0;
        int peso = 3;
        // i = codigo.length() - 2, porque o i tem que começar pelo penultimo digito, pois o ultimo dígito é verificar, qua vai ser utilizado posteriormente
        for (int i = codigo.length() - 2; i >= 0; i--){
            soma += Character.getNumericValue(codigo.charAt(i) * peso);
            peso = (peso == 3) ? 1 : 3;
            
            int digitoCorreto = (10 - (soma % 10)) % 10;
            int digitoInformado = Character.getNumericValue(codigo.charAt(codigo.length() - 1));
            
            if(digitoCorreto != digitoInformado){
                throw new IllegalArgumentException("Digito verificador do Código Inválido.");
            }
        }
    }
}
```




## Lote


```java
package models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Lote {

    private int id;
    private LocalDate dataValidade;
    private BigDecimal quantidadeInicial;
    private BigDecimal saldoAtual;
    private Produto produto;
    // "private Doacao doacao;" quando a classe Doacao for criada

    public Lote(int id, LocalDate dataValidade, BigDecimal quantidadeInicial, Produto produto) {
        this.id = id;
        this.dataValidade = dataValidade;
        this.quantidadeInicial = quantidadeInicial;
        this.saldoAtual = quantidadeInicial; 
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public BigDecimal getQuantidadeInicial() {
        return quantidadeInicial;
    }

    public void setQuantidadeInicial(BigDecimal quantidadeInicial) {
        this.quantidadeInicial = quantidadeInicial;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    // Métodos da Classe Lote
    
    public boolean estaVencido() {
        return dataValidade.isBefore(LocalDate.now());
    }
    
    public boolean estaDisponivel() {
        return saldoAtual.compareTo(BigDecimal.ZERO) > 0 && !estaVencido();
    }
    
    
    public void baixarQuantidade(BigDecimal quantidade) {
        if (quantidade == null || quantidade.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (quantidade.compareTo(saldoAtual) > 0) {
            throw new IllegalArgumentException("Quantidade maior que o saldo do lote.");
        }
        saldoAtual = saldoAtual.subtract(quantidade);
    }
    
    public long diasParaVencer() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dataValidade);
    }
    
}
```
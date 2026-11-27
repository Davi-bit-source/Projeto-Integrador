package models;

import java.math.BigDecimal;
import java.time.LocalDate;

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
}

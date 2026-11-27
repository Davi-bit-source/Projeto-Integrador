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

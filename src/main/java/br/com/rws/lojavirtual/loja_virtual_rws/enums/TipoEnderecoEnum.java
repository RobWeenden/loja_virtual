package br.com.rws.lojavirtual.loja_virtual_rws.enums;

public enum TipoEnderecoEnum {

    COBRANCA("Cobrança"), ENTREGA("Entrega");

    private String descricao;

    private TipoEnderecoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

}

package br.com.rws.lojavirtual.loja_virtual_rws.enums;

public enum StatusContaReceberEnum {

    COBRANCA("Pagar"), VENCIDA("Vencida"), ABERTA("Aberta"), QUITADA("Quitada");

    private String descricao;

    private StatusContaReceberEnum(String descricao) {
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

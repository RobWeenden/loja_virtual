package br.com.rws.lojavirtual.loja_virtual_rws.ContaPagar;

public enum StatusContaPagar {

    COBRANCA("Pagar"), VENCIDA("Vencida"), ABERTA("Aberta"), QUITADA("Quitada"), RENEGOCIDA("Renegociada");

    private String descricao;

    private StatusContaPagar(String descricao) {
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

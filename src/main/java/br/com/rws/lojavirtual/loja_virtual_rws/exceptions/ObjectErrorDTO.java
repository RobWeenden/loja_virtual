package br.com.rws.lojavirtual.loja_virtual_rws.exceptions;

import java.io.Serializable;

public class ObjectErrorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String error;
    private String codeError;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }

}

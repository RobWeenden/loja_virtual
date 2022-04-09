package br.com.rws.lojavirtual.loja_virtual_rws.TestUsuario;

import br.com.rws.lojavirtual.loja_virtual_rws.Util.ValidationCNPJ;
import br.com.rws.lojavirtual.loja_virtual_rws.Util.ValidationCPF;

public class TesteCpfCnpj {

    public static void main(String[] args) {
        boolean isCNPJ = ValidationCNPJ.isCNPJ("56.194.071/0001-28");
        boolean isCPF = ValidationCPF.isCPF("476.417.390-53");

        System.out.println("Cnpj Válido: "+isCNPJ);
        System.out.println("Cpf Válido: "+isCPF);

        //continue 23:40
    }
    
}

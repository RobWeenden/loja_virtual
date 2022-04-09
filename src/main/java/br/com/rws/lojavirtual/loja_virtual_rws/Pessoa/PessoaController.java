package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rws.lojavirtual.loja_virtual_rws.Exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.Util.ValidationCNPJ;

@RestController
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaService pessoaService;
    
    @PostMapping(value = "**/save/pessoa-juridica")
    public ResponseEntity<PessoaJuridicaModel> savePessoaJuridica(@RequestBody PessoaJuridicaModel pessoaJuridica)
            throws CustomExceptions {
        
        if (pessoaJuridica == null) {
            throw new CustomExceptions("Pesso juridica não pode ter valores nulos");
        }
        if (pessoaJuridica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
            throw new CustomExceptions("O CNPJ já se encontra cadastrado com o numero: "+pessoaJuridica.getCnpj());
        }
        if (pessoaJuridica.getId() == null && pessoaRepository.existeInscEstadual(pessoaJuridica.getInscEstadual()) != null) {
            throw new CustomExceptions("A inscrição Estadual já existe cadastrado com o numero: "+pessoaJuridica.getInscEstadual());
        }
        if (!ValidationCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
            throw new CustomExceptions("Cnpj: "+ pessoaJuridica.getCnpj() + " está inválido");
        }
        
        pessoaJuridica = pessoaService.savePessoaJuridica(pessoaJuridica);
        return new ResponseEntity<PessoaJuridicaModel>(pessoaJuridica, HttpStatus.OK);
    }
}

package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rws.lojavirtual.loja_virtual_rws.Exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.Util.ValidationCNPJ;
import br.com.rws.lojavirtual.loja_virtual_rws.Util.ValidationCPF;

@RestController
public class PessoaController {

	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	PessoaService pessoaService;

	@PostMapping(value = "**/save/pessoa-juridica")
	public ResponseEntity<PessoaJuridicaModel> savePessoaJuridica(@RequestBody @Valid PessoaJuridicaModel pessoaJuridica)
			throws CustomExceptions {

		if (pessoaJuridica == null) {
			throw new CustomExceptions("Pesso juridica não pode ter valores nulos");
		}
		
		if (pessoaJuridica.getId() == null
				&& pessoaJuridicaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
			throw new CustomExceptions("O CNPJ já se encontra cadastrado com o numero: " + pessoaJuridica.getCnpj());
		}
		
		if (pessoaJuridica.getId() == null
				&& pessoaJuridicaRepository.existeInscEstadual(pessoaJuridica.getInscEstadual()) != null) {
			throw new CustomExceptions(
					"A inscrição Estadual já existe cadastrado com o numero: " + pessoaJuridica.getInscEstadual());
		}
		
		if (!ValidationCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
			throw new CustomExceptions("Cnpj: " + pessoaJuridica.getCnpj() + " está inválido");
		}

		pessoaJuridica = pessoaService.savePessoaJuridica(pessoaJuridica);
		return new ResponseEntity<>(pessoaJuridica, HttpStatus.OK);
	}

	@PostMapping(value = "**/save/pessoa-fisica")
	public ResponseEntity<PessoaFisicaModel> savePessoaFisica(@RequestBody @Valid PessoaFisicaModel pessoaFisica)
			throws CustomExceptions {

		if (pessoaFisica == null) {
			throw new CustomExceptions("Pessoa física não pode ter valores nulos");
		}

		if (pessoaFisica.getId() == null && pessoaFisicaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
			throw new CustomExceptions("O CPF já se encontra cadastrado com o numero: " + pessoaFisica.getCpf());
		}

		if (!ValidationCPF.isCPF(pessoaFisica.getCpf())) {
			throw new CustomExceptions("CPF: " + pessoaFisica.getCpf() + " está inválido");
		}

		pessoaFisica = pessoaService.savePessoaFisica(pessoaFisica);
		return new ResponseEntity<>(pessoaFisica, HttpStatus.OK);
	}
}

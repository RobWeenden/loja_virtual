package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rws.lojavirtual.loja_virtual_rws.Endereco.CepDTO;
import br.com.rws.lojavirtual.loja_virtual_rws.Endereco.EnderecoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Endereco.EnderecoRepository;
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
	EnderecoRepository enderecoRepository;

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
		
		if (pessoaJuridica.getId() == null || pessoaJuridica.getId() <= 0) {
			for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
				CepDTO cepDto = pessoaService.consultaCep(pessoaJuridica.getEnderecos().get(i).getCep());
				pessoaJuridica.getEnderecos().get(i).setBairro(cepDto.getBairro());
				pessoaJuridica.getEnderecos().get(i).setCidade(cepDto.getLocalidade());
				pessoaJuridica.getEnderecos().get(i).setComplemento(cepDto.getComplemento());
				pessoaJuridica.getEnderecos().get(i).setLogradouro(cepDto.getLogradouro());
				pessoaJuridica.getEnderecos().get(i).setUf(cepDto.getUf());
			}
		} else {
			for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
				EnderecoModel enderecoTemp = enderecoRepository.findById(pessoaJuridica.getEnderecos().get(i).getId()).get();

				if (!enderecoTemp.getCep().equals(pessoaJuridica.getEnderecos().get(i).getCep())) {

					CepDTO cepDto = pessoaService.consultaCep(pessoaJuridica.getEnderecos().get(i).getCep());
					pessoaJuridica.getEnderecos().get(i).setBairro(cepDto.getBairro());
					pessoaJuridica.getEnderecos().get(i).setCidade(cepDto.getLocalidade());
					pessoaJuridica.getEnderecos().get(i).setComplemento(cepDto.getComplemento());
					pessoaJuridica.getEnderecos().get(i).setLogradouro(cepDto.getLogradouro());
					pessoaJuridica.getEnderecos().get(i).setUf(cepDto.getUf());

				}
			}
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
	
	@ResponseBody
	@GetMapping(value = "**/consulta/cep/{cep}")
	public ResponseEntity<CepDTO> consultaCep(@PathVariable("cep") String cep){
		
		return new ResponseEntity<>(pessoaService.consultaCep(cep), HttpStatus.OK);
	}
}

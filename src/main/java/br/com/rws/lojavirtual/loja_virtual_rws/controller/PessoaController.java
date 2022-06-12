package br.com.rws.lojavirtual.loja_virtual_rws.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rws.lojavirtual.loja_virtual_rws.Util.ValidationCNPJ;
import br.com.rws.lojavirtual.loja_virtual_rws.Util.ValidationCPF;
import br.com.rws.lojavirtual.loja_virtual_rws.dto.CepDTO;
import br.com.rws.lojavirtual.loja_virtual_rws.dto.ConsultaCnpjDTO;
import br.com.rws.lojavirtual.loja_virtual_rws.enums.TipoPessoaEnum;
import br.com.rws.lojavirtual.loja_virtual_rws.exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.model.EnderecoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaFisicaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaJuridicaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.EnderecoRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.PessoaFisicaRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.PessoaJuridicaRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.service.EndpointContService;
import br.com.rws.lojavirtual.loja_virtual_rws.service.PessoaService;

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
	
	@Autowired
	private EndpointContService endpointService;

	@PostMapping(value = "**/save/pessoa-juridica")
	public ResponseEntity<PessoaJuridicaModel> savePessoaJuridica(@RequestBody @Valid PessoaJuridicaModel pessoaJuridica)
			throws CustomExceptions {

		if (pessoaJuridica == null) {
			throw new CustomExceptions("Pessoa juridica não pode ter valores nulos");
		}
		
		if (pessoaJuridica.getTipoPessoa() == null) {
			throw new CustomExceptions("Informe o tipo Juridico ou Fornecedor da Loja");
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
		
		if (pessoaFisica.getTipoPessoa() == null) {
			pessoaFisica.setTipoPessoa(TipoPessoaEnum.FISICA.getDescricao());
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
	
	@ResponseBody
	@GetMapping(value = "**/consulta/cnpj/{cnpj}")
	public ResponseEntity<ConsultaCnpjDTO> consultaCnpjReceitaWS(@PathVariable("cnpj") String cnpj){
		
		return new ResponseEntity<>(pessoaService.consultaCnpjReceitaWS(cnpj), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/consultar-nome-pessoa-fisica/{nome}")
	public ResponseEntity<List<PessoaFisicaModel>> consultaPFNome(@PathVariable("nome") String nome){
		List<PessoaFisicaModel> listPF = pessoaFisicaRepository.pesquisaPorNomePF(nome.trim().toUpperCase());
		endpointService.atualizarAcessoEndpoint();
		return new ResponseEntity<List<PessoaFisicaModel>>(listPF, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/consultar-cpf/{cpf}")
	public ResponseEntity<List<PessoaFisicaModel>> consultaPfCpf(@PathVariable("cpf") String cpf){
		List<PessoaFisicaModel> listPF = pessoaFisicaRepository.pesquisarCpfFromList(cpf);
		
		return new ResponseEntity<>(listPF, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/consultar-nome-pessoa-juridica/{nome}")
	public ResponseEntity<List<PessoaJuridicaModel>> consultaPJNome(@PathVariable("nome") String nome){
		List<PessoaJuridicaModel> listPJ = pessoaJuridicaRepository.pesquisaPorNomePJ(nome.trim().toUpperCase());
		
		return new ResponseEntity<>(listPJ, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/consultar-cnpj/{cnpj}")
	public ResponseEntity<List<PessoaJuridicaModel>> consultaPJCnpj(@PathVariable("cnpj") String cnpj){
		List<PessoaJuridicaModel> listPJ = pessoaJuridicaRepository.pesquisarCNPJ(cnpj);
		
		return new ResponseEntity<>(listPJ, HttpStatus.OK);
	}
	
}

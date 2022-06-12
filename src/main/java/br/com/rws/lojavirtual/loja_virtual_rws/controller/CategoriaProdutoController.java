package br.com.rws.lojavirtual.loja_virtual_rws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rws.lojavirtual.loja_virtual_rws.dto.CategoriaProdutoDto;
import br.com.rws.lojavirtual.loja_virtual_rws.exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.CategoriaProdutoRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.service.CategoriaProdutoService;

@RestController
public class CategoriaProdutoController {
	
	@Autowired
	private CategoriaProdutoRepository categoriaRepository;
	
	@Autowired
	private  CategoriaProdutoService categoriaService;
	
	@ResponseBody
	@PostMapping(value = "**/categoria-produto/save")
	public ResponseEntity<CategoriaProdutoDto> salvarCategoriaProduto(
			@RequestBody CategoriaProdutoDto categoriaProduto) throws CustomExceptions {
		
		if (categoriaProduto.getEmpresa().getId() == null || categoriaProduto.getEmpresa().getId() <= 0) {
			throw new CustomExceptions("A empresa deve ser informada!");
		}
		
		if (categoriaProduto.getId() == null && categoriaRepository.existeDescricaoCategoria(categoriaProduto.getDescricao().toUpperCase().trim())) {
			throw new CustomExceptions("Já existe a categoria cadastrada");
		}
		//Continue 1:14:03
		CategoriaProdutoDto categoriaRetorno = categoriaService.saveCategoria(categoriaProduto);

		return new ResponseEntity<>(categoriaRetorno, HttpStatus.OK);
	}

}

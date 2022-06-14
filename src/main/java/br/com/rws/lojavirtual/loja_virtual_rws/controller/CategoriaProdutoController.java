package br.com.rws.lojavirtual.loja_virtual_rws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rws.lojavirtual.loja_virtual_rws.dto.CategoriaProdutoDto;
import br.com.rws.lojavirtual.loja_virtual_rws.exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.model.CategoriaProdutoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.CategoriaProdutoRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.service.CategoriaProdutoService;

@RestController
public class CategoriaProdutoController {

	@Autowired
	private CategoriaProdutoRepository categoriaRepository;

	@Autowired
	private CategoriaProdutoService categoriaService;

	@ResponseBody
	@PostMapping(value = "**/categoria-produto/save")
	public ResponseEntity<CategoriaProdutoDto> salvarCategoriaProduto(@RequestBody CategoriaProdutoDto categoriaProduto)
			throws CustomExceptions {

		if (categoriaProduto.getEmpresa().getId() == null || categoriaProduto.getEmpresa().getId() <= 0) {
			throw new CustomExceptions("A empresa deve ser informada!");
		}

		if (categoriaProduto.getId() == null
				&& categoriaRepository.existeDescricaoCategoria(categoriaProduto.getDescricao().toUpperCase().trim())) {
			throw new CustomExceptions("Já existe a categoria cadastrada");
		}
		CategoriaProdutoDto categoriaRetorno = categoriaService.saveCategoria(categoriaProduto);

		return new ResponseEntity<>(categoriaRetorno, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "**/categoria-produto/delete")
	public ResponseEntity<String> deleteRole(@RequestBody CategoriaProdutoModel categoriaModel) {
		categoriaRepository.deleteById(categoriaModel.getId());

		return new ResponseEntity<>("Categoria do produto removido", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/categoria-produto/searchAll")
	public ResponseEntity<List<CategoriaProdutoModel>> searchAll() {
		List<CategoriaProdutoModel> listCategoria = categoriaRepository.findAll();

		return new ResponseEntity<>(listCategoria, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/consultar-categoria-produto/{nome}")
	public ResponseEntity<List<CategoriaProdutoModel>> consultaCategoriaProdNome(@PathVariable("nome") String nome){
		List<CategoriaProdutoModel> listCat = categoriaRepository.pesquisarCategoriaByNome(nome.trim().toUpperCase());
		
		return new ResponseEntity<>(listCat, HttpStatus.OK);
	}
}

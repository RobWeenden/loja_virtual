package br.com.rws.lojavirtual.loja_virtual_rws.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rws.lojavirtual.loja_virtual_rws.exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.model.ProdutoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.ProdutoRepository;

@Controller
@RestController
public class ProdutoController {

	@Autowired
	private ProdutoRepository protudoRepository;

	@ResponseBody
	@PostMapping(value = "**/produto-save")
	public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody @Valid ProdutoModel produtoModel)
			throws CustomExceptions {

		if (produtoModel.getEmpresa() == null || produtoModel.getEmpresa().getId() <= 0) {
			throw new CustomExceptions("A empresa responsável deve ser informada.");
		}

		if (produtoModel.getId() == null) {
			List<ProdutoModel> produtoBusca = protudoRepository
					.pesquisarProdutoByNome(produtoModel.getNome().toUpperCase(), produtoModel.getEmpresa().getId());

			if (!produtoBusca.isEmpty()) {
				throw new CustomExceptions("Já existe Produto com o nome: " + produtoModel.getNome());
			}
		}

		if (produtoModel.getCategoriaProduto() == null || produtoModel.getCategoriaProduto().getId() <= 0) {
			throw new CustomExceptions("A categoria deve ser informada.");
		}

		if (produtoModel.getMarcaProduto() == null || produtoModel.getMarcaProduto().getId() <= 0) {
			throw new CustomExceptions("A marca deve ser informada.");
		}

		ProdutoModel produto = protudoRepository.save(produtoModel);

		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "**/produto-delete")
	public ResponseEntity<String> deleteProduto(@RequestBody ProdutoModel produtoModel) {
		protudoRepository.deleteById(produtoModel.getId());

		return new ResponseEntity<>("Produto Removido", HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/produto-delete/{id}")
	public ResponseEntity<String> deleteByIdProduto(@PathVariable("id") Long id) {
		protudoRepository.deleteById(id);

		return new ResponseEntity<>("Produto Removido por ID", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/produto-buscar/{id}")
	public ResponseEntity<ProdutoModel> searchProduto(@PathVariable("id") Long id) throws CustomExceptions {
		ProdutoModel prod = protudoRepository.findById(id).orElse(null);

		if (prod == null) {
			throw new CustomExceptions("Não foi encontrado Produto com código: " + id);
		}

		return new ResponseEntity<>(prod, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/produto-buscar-descricao/{desc}")
	public ResponseEntity<List<ProdutoModel>> searchDescriptionProduto(@PathVariable("desc") String desc) {
		List<ProdutoModel> prod = protudoRepository.pesquisarProdutoByNome(desc.toUpperCase());

		return new ResponseEntity<>(prod, HttpStatus.OK);
	}
}

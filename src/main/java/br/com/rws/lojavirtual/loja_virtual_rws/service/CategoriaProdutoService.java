package br.com.rws.lojavirtual.loja_virtual_rws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rws.lojavirtual.loja_virtual_rws.dto.CategoriaProdutoDto;
import br.com.rws.lojavirtual.loja_virtual_rws.model.CategoriaProdutoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaJuridicaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.CategoriaProdutoRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.PessoaJuridicaRepository;

@Service
public class CategoriaProdutoService {
	
	@Autowired
	private CategoriaProdutoRepository categoriaRepository;
	
	@Autowired
	private PessoaJuridicaRepository  pjRepository;

	public CategoriaProdutoDto saveCategoria(CategoriaProdutoDto categoriaProduto) {
		
		Optional<PessoaJuridicaModel> pjModel = pjRepository.findById(categoriaProduto.getEmpresa().getId());
				
		CategoriaProdutoModel catModel = new CategoriaProdutoModel();
		catModel.setDescricao(categoriaProduto.getDescricao());
		catModel.setEmpresa(pjModel.get());
		
		catModel = categoriaRepository.save(catModel);
		
		CategoriaProdutoDto ctDto = new CategoriaProdutoDto();
		ctDto.setDescricao(catModel.getDescricao());
		ctDto.setEmpresa(catModel.getEmpresa());
		ctDto.setId(catModel.getId());
		
		return ctDto;
	}
	
	

}

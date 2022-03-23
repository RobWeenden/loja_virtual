package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioRepository;

@Service
public class PessoaService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaJuridicaModel savePessoaJuridica(PessoaJuridicaModel pessoaJuridica) {
        pessoaJuridica = pessoaRepository.save(pessoaJuridica);
        UsuarioModel usuarioPj = usuarioRepository.findByUsuarioByPessoa(pessoaJuridica.getId(),
                pessoaJuridica.getEmail());

        if (usuarioPj == null) {
        //continue 24:41 
        }
        return pessoaJuridica;
    }
}

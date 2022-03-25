package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioRepository;

@Service
public class PessoaService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private JdbcTemplate jdbc;

    public PessoaJuridicaModel savePessoaJuridica(PessoaJuridicaModel pessoaJuridica) {

        for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
            pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
            pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
        }
        
        pessoaJuridica = pessoaRepository.save(pessoaJuridica);
        UsuarioModel usuarioPj = usuarioRepository.findByUsuarioByPessoa(pessoaJuridica.getId(),
                pessoaJuridica.getEmail());

        if (usuarioPj == null) {
            //REMOVER RESTRIÇÃO NO DB
            String constraint = usuarioRepository.consultaConstraintRoleAcesso();
            if (constraint != null) {
                //EXECUÇÃO DE UMA TRANSAÇÃO PARA REMOÇÃO DA RESTRIÇÃO
                jdbc.execute("begin; alter table tb_usuario_role_acesso drop constraint " + constraint + "; commit;");
            }

            usuarioPj = new UsuarioModel();
            usuarioPj.setDataAtualPassword(Calendar.getInstance().getTime());
            usuarioPj.setEmpresa(pessoaJuridica);
            usuarioPj.setPessoa(pessoaJuridica);
            usuarioPj.setLogin(pessoaJuridica.getEmail());

            String passwordEnvioEmail = "" + Calendar.getInstance().getTimeInMillis();
            String passwordCrypt = new BCryptPasswordEncoder().encode(passwordEnvioEmail);

            usuarioPj.setPassword(passwordCrypt);
            usuarioPj = usuarioRepository.save(usuarioPj);
            usuarioRepository.inserirRoleAcessoPj(usuarioPj.getId());

        }
        return pessoaJuridica;
    }
}

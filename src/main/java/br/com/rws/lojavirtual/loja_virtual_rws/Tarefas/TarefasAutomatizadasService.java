package br.com.rws.lojavirtual.loja_virtual_rws.Tarefas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioRepository;

@Component
@Service
public class TarefasAutomatizadasService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Scheduled(initialDelay = 2000, fixedDelay = 86400000)
    //@Scheduled(cron = "0 0 11 * * *", zone="America/Sao_Paulo")
    public void notificarTrocaSenhaUsuario(){

        List<UsuarioModel> usuarios = usuarioRepository.usuarioDataPasswordVencida();
        for(UsuarioModel usuario : usuarios){
            StringBuilder msg = new StringBuilder();
            //continue 44:45
        }
    }
}

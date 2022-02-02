package br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAcessoService {
    
    @Autowired
    private RoleAcessoRepository roleRepository;

    public RoleAcessoModel save(RoleAcessoModel roleModel) {

        return roleRepository.save(roleModel);
    }
    
    
}

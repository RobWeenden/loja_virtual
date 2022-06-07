package br.com.rws.lojavirtual.loja_virtual_rws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rws.lojavirtual.loja_virtual_rws.model.RoleAcessoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.RoleAcessoRepository;

@Service
public class RoleAcessoService {
    
    @Autowired
    private RoleAcessoRepository roleRepository;

    public RoleAcessoModel save(RoleAcessoModel roleModel) {

        return roleRepository.save(roleModel);
    }
    
    
}

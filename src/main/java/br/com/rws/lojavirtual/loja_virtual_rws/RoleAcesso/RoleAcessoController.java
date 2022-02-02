package br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoleAcessoController {
    
    @Autowired
    private RoleAcessoService roleService;

    @PostMapping("/save")
    public RoleAcessoModel salvarRole(RoleAcessoModel roleModel){
        return roleService.save(roleModel);
    }
}

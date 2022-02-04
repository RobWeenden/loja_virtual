package br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class RoleAcessoController {

    @Autowired
    private RoleAcessoService roleService;

    @Autowired
    private RoleAcessoRepository roleRepository;

    @ResponseBody
    @PostMapping(value = "**/save")
    public ResponseEntity<RoleAcessoModel> salvarRole(@RequestBody RoleAcessoModel roleModel) {
        RoleAcessoModel role = roleService.save(roleModel);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "**/delete")
    public ResponseEntity deleteRole(@RequestBody RoleAcessoModel roleModel) {
        roleRepository.deleteById(roleModel.getId());

        return new ResponseEntity("Acesso Removido", HttpStatus.OK);
    }
}

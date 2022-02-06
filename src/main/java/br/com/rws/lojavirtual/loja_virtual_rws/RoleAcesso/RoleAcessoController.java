package br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso;

import java.util.List;

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

    @ResponseBody
    @DeleteMapping(value = "**/deleteById/{id}")
    public ResponseEntity deleteByIdRole(@PathVariable("id") Long id) {
        roleRepository.deleteById(id);

        return new ResponseEntity("Acesso Removido por Id", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/searchRole/{id}")
    public ResponseEntity<RoleAcessoModel> searchRole(@PathVariable("id") Long id) {
        RoleAcessoModel role = roleRepository.findById(id).get();

        return new ResponseEntity<RoleAcessoModel>(role, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/searchDescriptionRole/{desc}")
    public ResponseEntity<List<RoleAcessoModel>> searchDescriptionRole(@PathVariable("desc") String desc) {
        List<RoleAcessoModel> role = roleRepository.buscarRoleDescricao(desc);

        return new ResponseEntity<List<RoleAcessoModel>>(role, HttpStatus.OK);
    }
}

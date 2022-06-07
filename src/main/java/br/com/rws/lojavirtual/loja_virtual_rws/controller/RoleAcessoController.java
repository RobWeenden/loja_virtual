package br.com.rws.lojavirtual.loja_virtual_rws.controller;

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

import br.com.rws.lojavirtual.loja_virtual_rws.Exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.model.RoleAcessoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.RoleAcessoRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.service.RoleAcessoService;

@Controller
@RestController
public class RoleAcessoController {

    @Autowired
    private RoleAcessoService roleService;

    @Autowired
    private RoleAcessoRepository roleRepository;

    @ResponseBody
    @PostMapping(value = "**/acesso-save")
    public ResponseEntity<RoleAcessoModel> salvarRole(@RequestBody RoleAcessoModel roleModel) throws CustomExceptions {

        if (roleModel.getId() == null) {
            List<RoleAcessoModel> roleSearch = roleRepository
                    .buscarRoleDescricao(roleModel.getDescricao().toUpperCase());
            if (!roleSearch.isEmpty()) {
                throw new CustomExceptions("Já existe Acesso com a descrição: " + roleModel.getDescricao());
            }
        }

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
    public ResponseEntity<RoleAcessoModel> searchRole(@PathVariable("id") Long id) throws CustomExceptions {
        RoleAcessoModel role = roleRepository.findById(id).orElse(null);

        if (role == null) {
            throw new CustomExceptions("Não foi encontrado Acesso com código: " + id);
        }

        return new ResponseEntity<RoleAcessoModel>(role, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/searchDescriptionRole/{desc}")
    public ResponseEntity<List<RoleAcessoModel>> searchDescriptionRole(@PathVariable("desc") String desc) {
        List<RoleAcessoModel> role = roleRepository.buscarRoleDescricao(desc.toUpperCase());

        return new ResponseEntity<List<RoleAcessoModel>>(role, HttpStatus.OK);
    }
}

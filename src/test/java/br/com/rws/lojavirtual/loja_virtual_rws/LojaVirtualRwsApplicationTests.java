package br.com.rws.lojavirtual.loja_virtual_rws;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoController;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoService;

@SpringBootTest(classes = LojaVirtualRwsApplication.class)
class LojaVirtualRwsApplicationTests {

	@Autowired
	private RoleAcessoService roleService;

	@Autowired
	private RoleAcessoRepository roleRepository;

	@Autowired
	private RoleAcessoController roleController;

	@Test
	void testCadastraAcesso() {
		RoleAcessoModel role = new RoleAcessoModel();

		role.setDescricao("ROLE_GESTOR");

		roleController.salvarRole(role);
	}

}

package br.com.rws.lojavirtual.loja_virtual_rws;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoController;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoService;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualRwsApplication.class)
class LojaVirtualRwsApplicationTests extends TestCase {

	@Autowired
	private RoleAcessoService roleService;

	@Autowired
	private RoleAcessoRepository roleRepository;

	@Autowired
	private RoleAcessoController roleController;

	@Test
	void testCadastraAcesso() {
		RoleAcessoModel role = new RoleAcessoModel();

		role.setDescricao("ROLE_TECNICO");
		assertEquals(true, role.getId() == null);
		role = roleController.salvarRole(role).getBody();
		assertEquals(true, role.getId() > 0);
		assertEquals("ROLE_TECNICO", role.getDescricao());

		RoleAcessoModel role2 = new RoleAcessoModel();

		role2 = roleRepository.findById(role.getId()).get();
		assertEquals(role.getId(), role2.getId());

		roleRepository.deleteById(role2.getId());

		roleRepository.flush();

		RoleAcessoModel role3 = new RoleAcessoModel();

		role3 = roleRepository.findById(role2.getId()).orElse(null);
		assertEquals(true, role3 == null);

		role = new RoleAcessoModel();
		role.setDescricao("ROLE_ALUNO");
		role = roleController.salvarRole(role).getBody();

		List<RoleAcessoModel> listRole = roleRepository.buscarRoleDescricao("ALUNO".trim().toUpperCase());
		assertEquals(1, listRole.size());
		roleRepository.deleteById(role.getId());
	}

}

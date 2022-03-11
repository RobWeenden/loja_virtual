package br.com.rws.lojavirtual.loja_virtual_rws;

import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.rws.lojavirtual.loja_virtual_rws.Exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoController;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoService;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualRwsApplication.class)
class LojaVirtualRwsApplicationTests extends TestCase {

	@Autowired
	private RoleAcessoRepository roleRepository;

	@Autowired
	private RoleAcessoController roleController;

	@Autowired
	private WebApplicationContext wac;

	@Test
	public void testRestApiCadastroRoleAcesso() throws JsonProcessingException, Exception{
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		String descricao = "ROLE_COMPRADOR"+Calendar.getInstance().getTimeInMillis();
		RoleAcessoModel role = new RoleAcessoModel();
		role.setDescricao(descricao);

		ObjectMapper obM = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/acesso-save")
									.content(obM.writeValueAsString(role))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));

		System.out.println("Retorno da API: "+ retornoApi.andReturn().getResponse().getContentAsString());
		//CONVERT RETURN API TO OBJECT ACCESS
		RoleAcessoModel objetoRetorno = obM.readValue(retornoApi.andReturn().getResponse().getContentAsString(), RoleAcessoModel.class);

		assertEquals(role.getDescricao(),objetoRetorno.getDescricao());

	}

	@Test
	public void testRestApiDeleteRoleAcesso() throws JsonProcessingException, Exception{
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		RoleAcessoModel role = new RoleAcessoModel();
		role.setDescricao("ROLE_TESTE_DELETE");
		role =roleRepository.save(role);

		ObjectMapper obM = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/delete")
									.content(obM.writeValueAsString(role))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));

		//CONVERT RETURN API TO OBJECT ACCESS
		System.out.println("Retorno da API: "+ retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Status de Retorno: "+ retornoApi.andReturn().getResponse().getStatus());
		assertEquals("Acesso Removido", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());


	}

	@Test
	public void testRestApiDeleteByIdRoleAcesso() throws JsonProcessingException, Exception{
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		RoleAcessoModel role = new RoleAcessoModel();
		role.setDescricao("ROLE_TESTE_DELETE");
		role =roleRepository.save(role);

		ObjectMapper obM = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/deleteById/" + role.getId())
									.content(obM.writeValueAsString(role))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));

		//CONVERT RETURN API TO OBJECT ACCESS
		System.out.println("Retorno da API: "+ retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Status de Retorno: "+ retornoApi.andReturn().getResponse().getStatus());
		assertEquals("Acesso Removido por Id", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());


	}

	@Test
	public void testRestApiSearchRoleAcesso() throws JsonProcessingException, Exception{
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		RoleAcessoModel role = new RoleAcessoModel();
		role.setDescricao("ROLE_TESTE_GET_ID");
		role =roleRepository.save(role);

		ObjectMapper obM = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/searchRole/" + role.getId())
									.content(obM.writeValueAsString(role))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));

		//CONVERT RETURN API TO OBJECT ACCESS
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

		RoleAcessoModel roleRetorno = obM.readValue(retornoApi.andReturn().getResponse().getContentAsString(), RoleAcessoModel.class);
		assertEquals(role.getDescricao(), roleRetorno.getDescricao());
		assertEquals(role.getId(), roleRetorno.getId());


	}

	@Test
	public void testRestApiSearchDescriptionRoleAcesso() throws JsonProcessingException, Exception{
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		RoleAcessoModel role = new RoleAcessoModel();
		 role.setDescricao("ROLE_GET_TESTE_DESC");
		 role =roleRepository.save(role);

		ObjectMapper obM = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/searchDescriptionRole/ROLE_GET_TESTE_DESC")
									.content(obM.writeValueAsString(role))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));

		//CONVERT RETURN API TO OBJECT ACCESS
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		List<RoleAcessoModel> retornoApiList = obM.readValue(retornoApi.andReturn().getResponse().getContentAsString(), new TypeReference<List<RoleAcessoModel>>() {});
		assertEquals(1, retornoApiList.size());
		assertEquals(role.getDescricao(), retornoApiList.get(0).getDescricao());
		roleRepository.deleteById(role.getId());


	}

	@Test
	void testCadastraAcesso() throws CustomExceptions {

		String descricao1 = "ROLE_FUNCIONARIO"+Calendar.getInstance().getTimeInMillis();


		RoleAcessoModel role = new RoleAcessoModel();

		role.setDescricao(descricao1);
		assertEquals(true, role.getId() == null);
		role = roleController.salvarRole(role).getBody();
		assertEquals(true, role.getId() > 0);
		assertEquals(descricao1, role.getDescricao());

		RoleAcessoModel role2 = new RoleAcessoModel();

		role2 = roleRepository.findById(role.getId()).get();
		assertEquals(role.getId(), role2.getId());

		roleRepository.deleteById(role2.getId());

		roleRepository.flush();

		RoleAcessoModel role3 = new RoleAcessoModel();

		role3 = roleRepository.findById(role2.getId()).orElse(null);
		assertEquals(true, role3 == null);

		role = new RoleAcessoModel();
		role.setDescricao(descricao1);
		role = roleController.salvarRole(role).getBody();

		List<RoleAcessoModel> listRole = roleRepository.buscarRoleDescricao(descricao1.trim().toUpperCase());
		assertEquals(1, listRole.size());
		roleRepository.deleteById(role.getId());
	}

}

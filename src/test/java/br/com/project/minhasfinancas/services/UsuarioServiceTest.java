package br.com.project.minhasfinancas.services;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.project.minhasfinancas.domain.Usuario;
import br.com.project.minhasfinancas.exceptions.ErroAutenticacao;
import br.com.project.minhasfinancas.exceptions.RegraNegocioException;
import br.com.project.minhasfinancas.repository.UsuarioRepository;
import br.com.project.minhasfinancas.services.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@SpyBean // spy metodos mocados originais
	UsuarioServiceImpl usuarioService;

	@MockBean
	UsuarioRepository usuarioRepository;
	/*
	 * // metodo setup
	 * 
	 * @Before public void setup() { usuarioService =
	 * Mockito.spy(UsuarioServiceImpl.class);
	 * 
	 * }
	 * 
	 */

	@Test
	public void deveAutenticarUmUsuarioComSucesso() {

		// cenario

		String email = "email@email.com";
		String senha = "senha";

		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1L).build();

		Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

		// ação

		Usuario result = usuarioService.autenticar(email, senha);

		// verificação

		Assertions.assertThat(result).isNotNull();

	}

	@Test
	public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComEmailInformado() {

		// cenario
		Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

		// acao
		Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticar("email@email.com", "senha"));

		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class)
				.hasMessage("Usuario não está presente para email informado");

	}

	@Test
	public void deveLancarErroQuandoSenhaNoaBater() {

		// cenario

		String senha = "senha";

		Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).build();

		Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

		// acao
		Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticar("email@email.com", "123"));

		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha Inválida");

	}

	@Test
	public void deveValidarEmail() {

		// CENARIO

		// (Quando chamar usuario mock repository passando qualquer string como
		// paramentro deve retornar falso )
		Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

		// AÇÂO

		usuarioService.validarEmail("email@email.com");

	}

	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {

		// CENARIO

		// (Quando chamar usuario mock repository passando qualquer string como
		// paramentro deve retornar true )
		Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

		// ação

		Throwable exception = Assertions.catchThrowable(() -> usuarioService.validarEmail("email@email.com"));

		Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class)
				.hasMessage("Já existe um usuário cadastrado com esse email");

	}
	
	
	
	public void deveSalvarUmUsuario() {
		
		//cenario
		                            //doNothing  (não faça nada)
		Mockito.doNothing().when(usuarioService).validarEmail(Mockito.anyString());
		
		Usuario usuario=Usuario.builder().id(1L)
				.nome("nome")
				.email("email@email.com")
				.senha("senha").build();
		
		Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		
		//ação
		
	Usuario usuarioSalvo=	usuarioService.salvarUsuario(usuario);
		
		
		// verificação
	
	Assertions.assertThat(usuarioSalvo).isNotNull();
	Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1L);
	Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
	Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("email@email.com");
	Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");
		
		
		
		
	}
	
	
	
	

}

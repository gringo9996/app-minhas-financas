package br.com.project.minhasfinancas.services;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.project.minhasfinancas.domain.Usuario;
import br.com.project.minhasfinancas.exceptions.RegraNegocioException;
import br.com.project.minhasfinancas.repository.UsuarioRepository;
import br.com.project.minhasfinancas.services.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@SpyBean
	UsuarioServiceImpl usuarioService;

	@MockBean
	UsuarioRepository usuarioRepository;

	// metodo setup

	@Before
	public void setup() {
		usuarioService = new UsuarioServiceImpl(usuarioRepository);

	}

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
	public void deveValidarEmail() {

		// CENARIO

		// (Quando chamar usuario mock repository passando qualquer string como
		// paramentro deve retornar falso )
		Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

		// AÇÂO

		usuarioService.validarEmail("email@email.com");

	}

	@org.junit.Test(expected = RegraNegocioException.class)
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {

		// CENARIO

		// (Quando chamar usuario mock repository passando qualquer string como
		// paramentro deve retornar true )
		Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

		// ação

		usuarioService.validarEmail("email@email.com");
	}

}

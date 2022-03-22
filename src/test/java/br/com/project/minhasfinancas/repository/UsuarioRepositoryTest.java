package br.com.project.minhasfinancas.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.project.minhasfinancas.domain.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {

		// cenario

		Usuario usuario = criarUsuario();

		entityManager.persist(usuario);

		// acao /execucao

		boolean result = usuarioRepository.existsByEmail("usuario@email.com");

		// verificacao

		Assertions.assertThat(result).isTrue();

	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {

		// cenario

		// ação

		boolean result = usuarioRepository.existsByEmail("usuario@email.com");

		// verificacao

		Assertions.assertThat(result).isFalse();

	}

	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {

		// cenario
		Usuario usuario = criarUsuario();

		// ação
		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		// verificacao

		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();

	}

	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {

		// cenario

		// ação

		// verificação

		Optional<Usuario> result = usuarioRepository.findByEmail("usuario@email.com");

		Assertions.assertThat(result.isPresent()).isFalse();

	}

	@Test
	public void deveBuscarUmUsuarioPorEmail() {

		// cenario
		Usuario usuario = criarUsuario();

		// ação

		entityManager.persist(usuario);

		// verificação

		Optional<Usuario> result = usuarioRepository.findByEmail("usuario@email.com");

		Assertions.assertThat(result.isPresent());

	}

	public static Usuario criarUsuario() {

		return Usuario.builder().nome("usuario").email("usuario@email.com").senha("senha").build();
	}

}

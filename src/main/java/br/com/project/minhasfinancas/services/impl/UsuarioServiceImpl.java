package br.com.project.minhasfinancas.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.project.minhasfinancas.domain.Usuario;
import br.com.project.minhasfinancas.exceptions.ErroAutenticacao;
import br.com.project.minhasfinancas.exceptions.RegraNegocioException;
import br.com.project.minhasfinancas.repository.UsuarioRepository;
import br.com.project.minhasfinancas.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if (!usuario.isPresent()) {

			throw new ErroAutenticacao("Usuario não está presente para email informado");
		}
		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha Inválida");
		}

		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());

		return usuarioRepository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {

		boolean existeEmail = usuarioRepository.existsByEmail(email);

		if (existeEmail) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com esse email");

		}

	}

}

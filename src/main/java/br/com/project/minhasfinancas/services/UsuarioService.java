package br.com.project.minhasfinancas.services;

import br.com.project.minhasfinancas.domain.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);

	Usuario salvarUsuario(Usuario usuario);

	void validarEmail(String email);

}

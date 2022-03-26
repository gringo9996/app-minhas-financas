package br.com.project.minhasfinancas.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.minhasfinancas.domain.Usuario;
import br.com.project.minhasfinancas.dtos.UsuarioDto;
import br.com.project.minhasfinancas.exceptions.ErroAutenticacao;
import br.com.project.minhasfinancas.exceptions.RegraNegocioException;
import br.com.project.minhasfinancas.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
	
	
	private UsuarioService usuarioService;
	
	private UsuarioResource(UsuarioService usuarioService) {
		this.usuarioService=usuarioService;
	}
	
	
	
	
	@PostMapping("/autenticar")
	public ResponseEntity<?> autenticar(@RequestBody UsuarioDto usuarioDto){
		
		try {
		Usuario usuarioAutenticado=	usuarioService.autenticar(usuarioDto.getEmail(), usuarioDto.getSenha());
		
		return ResponseEntity.ok(usuarioAutenticado);

			
		} catch (ErroAutenticacao e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
			
			
		}
		
		
		
		
		
	}
	
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody UsuarioDto usuarioDto){
		
		Usuario usuario=Usuario.builder()
				.nome(usuarioDto.getNome())
				.email(usuarioDto.getEmail())
				.senha(usuarioDto.getSenha()).build();
		
		try {
		Usuario usuarioSalvo=	usuarioService.salvarUsuario(usuario);
		
		return  new ResponseEntity(usuarioSalvo,HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
		
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
		
		
	}
	
	

}

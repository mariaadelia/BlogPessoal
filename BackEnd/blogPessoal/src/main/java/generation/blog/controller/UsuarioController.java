package generation.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import generation.blog.model.Usuario;
import generation.blog.model.UsuarioLogin;
import generation.blog.repository.UsuarioRepository;
import generation.blog.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	//Injeção de dependencia - Classe Service
	@Autowired
	private UsuarioService usuarioService;
	
	//Injeção de dependência - Classe Repository
	@Autowired
	private UsuarioRepository repositorio;
	
	//Find All Usuários
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	//Find By Id
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id){
		return repositorio.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Cadastrar usuário
	@PostMapping("/cadastrar")
	public ResponseEntity<Optional<Usuario>> cadastrar(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
	}
	
	//Alterar usuário
	@PutMapping("/alterar")
	public ResponseEntity<Usuario> alterar(@RequestBody Usuario usuario){
		Optional<Usuario> user = usuarioService.atualizarUsuario(usuario);
		
		try {
			
			return ResponseEntity.ok(user.get());
			
		}catch (Exception e) {
			
			return ResponseEntity.badRequest().build();
			
		}
	}
	
	//Deletar usuário
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticacao(@RequestBody Optional<UsuarioLogin> user){
		return usuarioService.userlogar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

}

package generation.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import generation.blog.model.Postagem;
import generation.blog.repository.PostagemRepository;
import generation.blog.service.PostagemService;

@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {
	
	//Inicializando o repositório
	@Autowired
	private PostagemRepository repositorio;
	
	//Inicializando o service
	@Autowired
	private PostagemService postagemService;
	
	
	//Find all Postagem
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	//Find by Id
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		
		return repositorio.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Find by Titulo
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
		
		return ResponseEntity.ok(repositorio.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//Post
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(postagem));
	}
	
	//Put
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(postagem));
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id) {
		repositorio.deleteById(id);
	}
	
	//Like
	@PutMapping("/curtir/{id}")
	public ResponseEntity<Postagem> curtirPostagem (@PathVariable long id){
		return ResponseEntity.status(HttpStatus.OK).body(postagemService.curtir(id));
	}
	
	//Dislike
	@PutMapping("/dislike/{id}")
	public ResponseEntity<Postagem> dislikePostagem(@PathVariable long id){
		return ResponseEntity.status(HttpStatus.OK).body(postagemService.dislike(id));
	}
	
}

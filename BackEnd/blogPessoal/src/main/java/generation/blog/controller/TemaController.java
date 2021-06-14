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

import generation.blog.model.Tema;
import generation.blog.repository.TemaRepository;
/*import generation.blog.service.TemaService;*/

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	//Injetando as dependencias
	@Autowired
	private TemaRepository repositorio;
	
	/*@Autowired
	private  TemaService temaService;*/
	
	@GetMapping
	public ResponseEntity<List<Tema>> findAllTema(){
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> findByIdTema(@PathVariable long id){
		return repositorio.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tema/{tema}")
	public ResponseEntity<List<Tema>> findByTema(@PathVariable String tema){
		return ResponseEntity.ok(repositorio.findAllByTemaContainingIgnoreCase(tema));
	}
	
	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> putTema(@RequestBody Tema tema){
		return ResponseEntity.ok(repositorio.save(tema));
	}
	
	@DeleteMapping("/{id}")
	public void deleteTema(@PathVariable long id) {
		repositorio.deleteById(id);
	}
	
	/*
	//Trendtopics
	@GetMapping("/trendtopics")
	public ResponseEntity<List<Tema>> getTrendTopics(){
		return ResponseEntity.ok(temaService.trendTopics());
	}*/

}

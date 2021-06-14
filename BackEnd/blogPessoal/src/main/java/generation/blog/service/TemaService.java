package generation.blog.service;
/*
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; */
import org.springframework.stereotype.Service;
/*
import generation.blog.model.Tema;
import generation.blog.repository.PostagemRepository;
import generation.blog.repository.TemaRepository;
*/
@Service
public class TemaService {
	
	/*
	//Injetar os dois repositórios, o de Tema e o de Postagem
	@Autowired
	private TemaRepository temaRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	public List<Tema> trendTopics(){
		
		//criar primeiro uma lista contendo todos os temas cadastrados
		List<Tema> temas = temaRepository.findAll();
		
		//com o for, iremos percorrer toda a lista e contar os posts de cada tema e guardar no atributo qtdTemas
		for(Tema tema : temas) {
			tema.setQtdTema(postagemRepository.countPosts(tema.getId()));
		}
		
		//Ordenando a List gerada
		Collections.sort(temas, Collections.reverseOrder(Comparator.comparing(Tema::getQtdTema)));
		
		return temas;
	
	} */

}

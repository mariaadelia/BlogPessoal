package generation.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import generation.blog.model.Postagem;
import generation.blog.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	
	//Criando o método de buscar a postagem pelo Id
	private Postagem buscarPostagemPeloId(Long id) {
		
		//Buscar a postagem pelo id, caso não ache, retornar nulo
		Postagem postagemSalva = postagemRepository.findById(id).orElse(null);
		
		//Se  postagem for nulo, retorna uma exception, se não for, retorna a postagem 
		if (postagemSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return postagemSalva;
	}
	
	
	//Criando os métodos do like e do dislike
	public Postagem curtir (Long id) {
		
		//Buscar a postagem no banco de dados pelo id
		Postagem postagem = buscarPostagemPeloId(id);
		
		//Se retornar uma postagem, soma +1 na curtida
		postagem.setCurtir(postagem.getCurtir() + 1);
		
		//Retorna para o controller a postagem com +1 curtida
		return postagemRepository.save(postagem);
		
	}
	
	public Postagem dislike (Long id) {
			
		//Buscar a postagem no banco de dados pelo id
		Postagem postagem = buscarPostagemPeloId(id);
			
		//Se retormar a postagem, verifica se o número de likes é maior que zero pois não existe -1 like
		if (postagem.getCurtir() > 0) {
				
				//Sendo maior que 0, tiro 1 das curtidas 
				postagem.setCurtir(postagem.getCurtir() - 1);
		}
		else {
			
			//Sendo menor que 0, retorno 0 para o curtir
			postagem.setCurtir(0);
		}
		
		
		//Retorna para o controller a postagem com -1 ou 0 curtida
		return postagemRepository.save(postagem);
			
		}

}

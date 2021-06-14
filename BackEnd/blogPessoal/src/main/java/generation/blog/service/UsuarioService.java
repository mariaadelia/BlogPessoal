package generation.blog.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import generation.blog.model.Usuario;
import generation.blog.model.UsuarioLogin;
import generation.blog.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorio;
	
	//Cadastrar usuário
	public Optional<Usuario> cadastrarUsuario (Usuario usuario) {
		
		//Verificar se o usuário já existe
		if(repositorio.findByUsuario(usuario.getUsuario()).isPresent())
			return null;
		
		//Verificar se o email já existe
		if(repositorio.findByEmail(usuario.getEmail()).isPresent())
			return null;
		
		//Verificar se cumpre o requisito de idade mínima para se cadastrar
		int idade = Period.between(usuario.getDataAniversario(), LocalDate.now()).getYears();
		
		if(idade < 13)
			return null;
					
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(repositorio.save(usuario));
	}
	
	
	
	//Atualizar a senha 
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(repositorio.save(usuario));
	}
	
	
	//Logar usuario
	public Optional<UsuarioLogin> userlogar(Optional<UsuarioLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repositorio.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				
				String authHeader = "Basic " + new String (encodeAuth);
				
				user.get().setToken(authHeader);
				
				user.get().setNome(usuario.get().getNome());
			
				return user;
			}
		}
		
		return null;
	}

}

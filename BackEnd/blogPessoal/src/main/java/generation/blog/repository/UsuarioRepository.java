package generation.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import generation.blog.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional<Usuario> findByUsuario(String usuario);
	//Para checar lá no usuário service seo email já foi cadastrado (na parte de cadastro)
	public Optional<Usuario> findByEmail(String email);

}

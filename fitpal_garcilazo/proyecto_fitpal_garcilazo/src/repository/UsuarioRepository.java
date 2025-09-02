package repository;

import java.util.List;
import BLL.Usuario;

public interface UsuarioRepository {
    void agregarUsuario(Usuario usuario);
    List<Usuario> mostrarUsuarios();
	<T> T login(String email, String password);
}

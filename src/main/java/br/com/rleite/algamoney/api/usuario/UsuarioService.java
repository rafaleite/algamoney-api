package br.com.rleite.algamoney.api.usuario;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.rleite.algamoney.api._base.abstracts.AbstractService;

@Service
public class UsuarioService extends AbstractService<Usuario, Long, UsuarioRepository> {

	private static final long serialVersionUID = 1L;
	
	public Optional<Usuario> findByEmail(String email) {
		return this.repository.findByEmail(email);
	}

}

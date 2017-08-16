package br.com.rleite.algamoney.api.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.rleite.algamoney.api.usuario.Usuario;
import lombok.Getter;

public class UserSystem extends User {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Usuario usuario;

	public UserSystem(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

}

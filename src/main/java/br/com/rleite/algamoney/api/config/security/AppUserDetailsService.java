package br.com.rleite.algamoney.api.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rleite.algamoney.api.usuario.Usuario;
import br.com.rleite.algamoney.api.usuario.UsuarioService;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> userOptional = this.usuarioService.findByEmail(email);
		Usuario usuario = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválido"));

		return new User(email, usuario.getSenha(), getPermissions(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissions(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		usuario.getPermissoes()
				.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));

		return authorities;
	}

}

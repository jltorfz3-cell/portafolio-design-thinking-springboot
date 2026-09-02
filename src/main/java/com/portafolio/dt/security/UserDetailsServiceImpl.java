package com.portafolio.dt.security;
import com.portafolio.dt.model.Usuario; import com.portafolio.dt.repository.UsuarioRepository; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
@Service public class UserDetailsServiceImpl implements UserDetailsService{
 private final UsuarioRepository repo; public UserDetailsServiceImpl(UsuarioRepository r){repo=r;}
 public UserDetails loadUserByUsername(String u){Usuario x=repo.findByUsername(u).orElseThrow(()->new UsernameNotFoundException(u));return User.withUsername(x.getUsername()).password(x.getPasswordHash()).disabled(!Boolean.TRUE.equals(x.getActivo())).authorities(x.getRoles().stream().map(r->"ROLE_"+r.getNombre()).toArray(String[]::new)).build();}
}

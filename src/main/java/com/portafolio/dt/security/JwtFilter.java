package com.portafolio.dt.security;
import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter;
@Component public class JwtFilter extends OncePerRequestFilter{
 private final JwtService jwt; private final UserDetailsServiceImpl uds; public JwtFilter(JwtService j,UserDetailsServiceImpl u){jwt=j;uds=u;}
 protected void doFilterInternal(HttpServletRequest q,HttpServletResponse r,FilterChain c)throws ServletException,java.io.IOException{
  String h=q.getHeader("Authorization"); if(h!=null&&h.startsWith("Bearer ")){String t=h.substring(7);if(jwt.valid(t)){var d=uds.loadUserByUsername(jwt.username(t));SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(d,null,d.getAuthorities()));}}
  c.doFilter(q,r);
 }
}

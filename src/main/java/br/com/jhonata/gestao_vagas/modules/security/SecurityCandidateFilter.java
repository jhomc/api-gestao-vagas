package br.com.jhonata.gestao_vagas.modules.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.jhonata.gestao_vagas.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityCandidateFilter extends OncePerRequestFilter {

  @Autowired
  JWTCandidateProvider jwtCandidateProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String header = request.getHeader("Authorization");

    if (header != null) {
      var token = this.jwtCandidateProvider.validateToken(header);

      if (token == null) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }

      request.setAttribute("candidate_id", token.getSubject());
    }

    filterChain.doFilter(request, response);
  }

}

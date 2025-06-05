package br.com.jhonata.gestao_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.jhonata.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.jhonata.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.jhonata.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret.key}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsernameOrEmail(authCompanyDTO.getUsername(), null)
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("Login ou senha inválidos");
        });
    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException("Login ou senha inválidos");
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expires_in = Instant.now().plus(Duration.ofMinutes(10));

    var token = JWT.create().withIssuer("TestCompany")
        .withExpiresAt(expires_in)
        .withSubject(company.getId().toString())
        .withClaim("roles", Arrays.asList("COMPANY"))
        .sign(algorithm);

    var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
        .access_token(token)
        .expires_in(expires_in.toEpochMilli())
        .build();

    return authCompanyResponseDTO;
  }
}

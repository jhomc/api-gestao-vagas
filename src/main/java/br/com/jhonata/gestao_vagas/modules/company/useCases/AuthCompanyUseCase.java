package br.com.jhonata.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.jhonata.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.jhonata.gestao_vagas.modules.company.repositories.CompanyRepository;

public class AuthCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsernameOrEmail(authCompanyDTO.getUsername(), null)
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("Login ou senha inválidos");
        });
    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException("Login ou senha inválidos");
    }
  }
}

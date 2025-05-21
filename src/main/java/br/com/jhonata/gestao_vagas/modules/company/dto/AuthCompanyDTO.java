package br.com.jhonata.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {

  private String username;
  private String password;
}

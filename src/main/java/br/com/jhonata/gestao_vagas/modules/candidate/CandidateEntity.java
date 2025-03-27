package br.com.jhonata.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

  private UUID id;
  private String name;

  @Pattern(regexp = "\\S+", message = "O nome de usuário não pode conter espaços")
  private String username;

  @Email(message = "Email inválido")
  private String email;

  @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
  private String password;
  private String description;
  private String curriculum;

}

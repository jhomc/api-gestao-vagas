package br.com.jhonata.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "company")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @Pattern(regexp = "\\S+", message = "O nome de usuário não pode conter espaços")
  @NotEmpty(message = "O nome de usuário não pode ser vazio")
  private String username;

  @Email(message = "Email inválido")
  private String email;

  @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
  private String password;
  private String description;
  private String website;

  @CreationTimestamp
  private LocalDateTime createdAt;
}

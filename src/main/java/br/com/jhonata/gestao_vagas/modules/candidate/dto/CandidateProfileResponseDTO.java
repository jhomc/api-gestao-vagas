package br.com.jhonata.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateProfileResponseDTO {
  private String name;
  private String username;
  private String email;
  private String description;
  private UUID id;
}

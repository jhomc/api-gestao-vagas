package br.com.jhonata.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jhonata.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.jhonata.gestao_vagas.modules.candidate.dto.CandidateProfileResponseDTO;

@Service
public class CandidateProfileUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateProfileResponseDTO execute(UUID id) {
    var candidate = this.candidateRepository.findById(id).orElseThrow(() -> {
      throw new UsernameNotFoundException("Usuário não encontrado");
    });

    var candidateDTO = CandidateProfileResponseDTO.builder()
        .description(candidate.getDescription())
        .email(candidate.getEmail())
        .name(candidate.getName())
        .username(candidate.getUsername())
        .id(candidate.getId())
        .build();

    return candidateDTO;
  }
}

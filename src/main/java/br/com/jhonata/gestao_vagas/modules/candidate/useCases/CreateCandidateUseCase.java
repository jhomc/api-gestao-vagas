package br.com.jhonata.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonata.gestao_vagas.exceptions.UserFoundException;
import br.com.jhonata.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.jhonata.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException("Já existe um usuário com este username ou email");
        });
    return this.candidateRepository.save(candidateEntity);
  }
}

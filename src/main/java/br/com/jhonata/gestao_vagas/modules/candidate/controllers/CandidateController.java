package br.com.jhonata.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhonata.gestao_vagas.exceptions.UserFoundException;
import br.com.jhonata.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.jhonata.gestao_vagas.modules.candidate.dto.CandidateProfileResponseDTO;
import br.com.jhonata.gestao_vagas.modules.candidate.useCases.CandidateProfileUseCase;
import br.com.jhonata.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private CandidateProfileUseCase candidateProfileUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = this.createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (UserFoundException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var candidateId = request.getAttribute("candidate_id");

    try {
      var profile = this.candidateProfileUseCase.execute(UUID.fromString(candidateId.toString()));
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}

package br.com.jhonata.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jhonata.gestao_vagas.modules.company.entities.JobEntity;
import br.com.jhonata.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/job")
public class JobController {
  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  public ResponseEntity<JobEntity> create(@Valid @RequestBody JobEntity jobEntity) {
    try {
      var result = this.createJobUseCase.execute(jobEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

  }
}

package com.jamesorban.ecommerceapplicationbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamesorban.ecommerceapplicationbackend.dto.ResponseDTO;
import com.jamesorban.ecommerceapplicationbackend.models.Applicant;
import com.jamesorban.ecommerceapplicationbackend.models.ApplicationReport;
import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import com.jamesorban.ecommerceapplicationbackend.services.ApplicantService;
import com.jamesorban.ecommerceapplicationbackend.validators.CatalogueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ApplicantController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CatalogueValidator catalogueValidator;

    @Resource
    private ApplicantService applicantService;

    @GetMapping("/{applicationId}")
    public Mono<Applicant> getApplication(@PathVariable int applicationId) {
        return applicantService.getByID(applicationId);
    }

    @GetMapping("/{applicationId}/synods/{synodId}")
    public Mono<Applicant> getApplication(@PathVariable int applicationId, @PathVariable int synodId) {
        return applicantService.getApplication(applicationId, synodId);
    }

    @GetMapping("/synods/{synodId}/users/{userId}")
    public Flux<Applicant> getApplications(@PathVariable int userId, @PathVariable int synodId) {
        return applicantService.getByUserAndSynod(synodId, userId);
    }

    @GetMapping("/synods/{synodId}/registrar/{registrarId}")
    public Mono<ResponseEntity<ResponseDTO>> getApplicationsForReview(@PathVariable int synodId, @PathVariable int registrarId) {
        return applicantService.getApplicationsForRegistrar(synodId, registrarId)
                .collectList().map(applications -> ResponseEntity.ok(new ResponseDTO(applications, "")))
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest()
                        .body(new ResponseDTO(null, error.getMessage()))));
    }

    @GetMapping("/{applicationId}/reports")
    public ResponseEntity<Flux<ApplicationReport>> getReportsForApplication(@PathVariable int applicationId) {
        return ResponseEntity.ok(applicantService.getReportForApplication(applicationId));
    }

    @PostMapping("/{applicationId}/reports")
    public Mono<ResponseEntity<ApplicationReport>> addReportForApplication(@RequestBody ApplicationReport report) {
        return applicantService.createReport(report).map(ResponseEntity::ok);
    }

    @DeleteMapping("/reports/{reportId}/users/{userId}")
    public Mono<ResponseEntity<Void>> deleteReport(@PathVariable int reportId, @PathVariable int userId) {
        return applicantService.deleteReport(reportId, userId).map(ResponseEntity::ok);
    }

    @PostMapping("/{applicationId}/close")
    public Mono<ResponseEntity<Void>> closeApplication(@PathVariable int applicationId) {
        return applicantService.closeApplication(applicationId).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{applicationId}/delete")
    public Mono<ResponseEntity<Void>> deleteApplication(@PathVariable int applicationId) {
        return applicantService.deleteApplication(applicationId).map(ResponseEntity::ok);
    }

    @PostMapping("/{applicationId}/finish")
    public Mono<ResponseEntity<ResponseDTO>> finishApplication(@PathVariable int applicationId) {
        return applicantService.finishApplication(applicationId)
                .map(finishedApp -> ResponseEntity.ok(new ResponseDTO(finishedApp, "")))
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest()
                        .body(new ResponseDTO(null, error.getMessage()))));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<ResponseDTO>> createApplication(@RequestPart("catalogue") String jsonDocument,
                                                         @RequestPart(value = "file", required = false) FilePart file) {

        return DataBufferUtils.join(file.content())
                .map(this::getBytes)
                .flatMap(bytes -> Mono.fromCallable(() -> objectMapper.readValue(jsonDocument, Catalogue.class))
                        .flatMap(catalogue -> validateCatalogueAndCreateApplication(catalogue,
                                bytes,
                                file)));
    }

    private byte[] getBytes(DataBuffer dataBuffer) {
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        DataBufferUtils.release(dataBuffer);
        return bytes;
    }

    private Mono<ResponseEntity<ResponseDTO>> validateCatalogueAndCreateApplication(Catalogue catalogue, byte[] bytes, FilePart file) {
        Errors errors = new BeanPropertyBindingResult(catalogue, "Catalogue");
        catalogueValidator.validate(catalogue, errors);

        if (errors.hasErrors() && Objects.nonNull(errors.getFieldError())) {
            return Mono.just(ResponseEntity.badRequest().body(new ResponseDTO(null, errors.getFieldError().getDefaultMessage())));
        }

        return applicantService.activeApplicationNotExist(catalogue).flatMap(applicationNotExist -> {
            if (applicationNotExist) {
                catalogue.setFile(bytes);
                catalogue.setFileName(file.filename());
                return applicantService.createApplication(catalogue).map(apply -> ResponseEntity.ok(new ResponseDTO(apply, "")));
            }
            return Mono.just(ResponseEntity.badRequest().body(new ResponseDTO(null, "Application already exist")));
        });
    }
}

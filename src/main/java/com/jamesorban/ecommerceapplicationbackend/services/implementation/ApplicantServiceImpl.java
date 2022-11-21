package com.jamesorban.ecommerceapplicationbackend.services.implementation;

import com.jamesorban.ecommerceapplicationbackend.dao.ApplicantDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.ApplicationReportDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.ReviewDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.SectionDAO;
import com.jamesorban.ecommerceapplicationbackend.models.Applicant;
import com.jamesorban.ecommerceapplicationbackend.models.ApplicationReport;
import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import com.jamesorban.ecommerceapplicationbackend.models.Review;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ApplicantStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ReviewStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.services.ApplicantService;
import com.jamesorban.ecommerceapplicationbackend.services.CatalogueService;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SynodService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

import javax.annotation.Resource;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Resource
    private ApplicantDAO applicantDAO;

    @Resource
    private CatalogueService catalogueService;

    @Resource
    private ApplicationReportDAO applicationReportDAO;

    @Resource
    private ReviewDAO reviewDAO;

    @Resource
    private SectionDAO sectionDAO;

    @Resource
    private SynodService synodService;

    @Override
    public Mono<Applicant> createApplication(Catalogue catalogue1) {
        return catalogueService.createCatalogue(catalogue1).flatMap(catalogue -> applicantDAO.save(createApplicant(catalogue)));
    }

    @Override
    public Mono<Boolean> activeApplicationNotExist(Catalogue catalogue) {
        return applicantDAO.findByUserAndSynod(catalogue.getSynod().getId(), catalogue.getUser().getId())
                .map(Applicant::getStatus)
                .all(status -> Objects.equals(status, ApplicantStatusEnum.DECLINED));
    }

    @Override
    public Flux<Applicant> getByUserAndSynod(int synodId, int userId) {
        return applicantDAO.findByUserAndSynod(synodId, userId);
    }

    @Override
    public Flux<Applicant> getAll() {
        return applicantDAO.findAll();
    }

    @Override
    public Mono<Applicant> getByID(int id) {
        return applicantDAO.findById(id);
    }

    @Override
    public Mono<Applicant> getApplication(int applicantId, int synodId) {
        return applicantDAO.findByIdAndSynod(applicantId, synodId);
    }

    @Override
    public Flux<ApplicationReport> getReportForApplication(int applicationId) {
        return applicationReportDAO.findByApplication(applicationId);
    }

    @Override
    public Mono<ApplicationReport> createReport(ApplicationReport report) {
        return applicationReportDAO.save(report);
    }

    @Override
    public Mono<Void> deleteReport(int reportId, int userId) {
        return applicationReportDAO.findById(reportId)
                .filter(report -> report.getUser().getId() == userId)
                .flatMap(report -> applicationReportDAO.delete(report));
    }

    @Override
    public Mono<Void> closeApplication(int applicantId) {
        return applicantDAO.updateStatus(ApplicantStatusEnum.CLOSED.toString(), applicantId);
    }

    @Override
    public Mono<Void> deleteApplication(int applicationId) {
        return applicantDAO.deleteById(applicationId);
    }

    @Override
    public Mono<Void> finishApplication(int applicationId) {
        return reviewDAO.findByApplicant(applicationId)
                .map(Review::getStatus)
                .filter(status -> status.equals(ReviewStatusEnum.APPROVED))
                .count()
                .map(result -> result >= 2)
                .flatMap(approved -> {
                    if (approved) {
                        return applicantDAO.updateStatus(ApplicantStatusEnum.APPLIED.toString(), applicationId);
                    } else {
                        return Mono.error(new Exception("You must have 2 approvals"));
                    }
                });
    }

    @Override
    public Flux<Applicant> getApplicationsForRegistrar(int synodId, int registrarId) {
        return synodService.isUserRegisterToSynod(synodId, registrarId).flatMapMany(isRegister -> {
            if (isRegister) {
                return sectionDAO.findSectionsForRegistrar(registrarId)
                        .filterWhen(section -> isSectionBelongsToSynod(synodId, section.getId()))
                        .flatMap(section -> applicantDAO.findBySynodSection(synodId, section.getId()))
                        .filterWhen(this::filterApplicationsForReview);
            } else {
                return Mono.error(new Exception("User is not has not register for the synod"));
            }
        });
    }


    private Mono<Boolean> isSectionBelongsToSynod(int synodId, int sectionId) {
        return sectionDAO.isSectionBelongsToSynod(synodId, sectionId).map(count -> count.equals(1));
    }

    private Mono<Boolean> filterApplicationsForReview(Applicant applicant) {
        return Mono.just(applicant)
                .flatMap(application -> {
                    if (application.getStatus().equals(ApplicantStatusEnum.OPENED) || application.getStatus()
                            .equals(ApplicantStatusEnum.IN_REVIEW)) {
                        return reviewDAO.findByApplicant(application.getId())
                                .count()
                                .map(count -> count < 2);
                    } else {
                        return Mono.just(false);
                    }
                });
    }

    private Applicant createApplicant(Catalogue catalogue) {
        Applicant applicant = new Applicant();
        applicant.setCatalogue(catalogue);
        applicant.setSynodMember(catalogue.getUser());
        applicant.setStatus(ApplicantStatusEnum.OPENED);
        return applicant;
    }
}

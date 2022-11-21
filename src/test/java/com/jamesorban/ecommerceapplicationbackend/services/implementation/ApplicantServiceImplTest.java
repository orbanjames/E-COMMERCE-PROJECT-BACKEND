package com.jamesorban.ecommerceapplicationbackend.services.implementation;

import com.jamesorban.ecommerceapplicationbackend.dao.ApplicantDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.ApplicationReportDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.ReviewDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.SectionDAO;
import com.jamesorban.ecommerceapplicationbackend.models.*;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ApplicantStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ReviewStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.services.CatalogueService;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SynodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest()
@ExtendWith(MockitoExtension.class)
class ApplicantServiceImplTest {

    @InjectMocks
    private ApplicantServiceImpl applicantService;

    @Mock
    private ApplicantDAO applicantDAO;

    @Mock
    private CatalogueService catalogueService;

    @Mock
    private ApplicationReportDAO applicationReportDAO;

    @Mock
    private ReviewDAO reviewDAO;

    @Mock
    private SectionDAO sectionDAO;

    @Mock
    private SynodService synodService;

    private Catalogue catalogue;
    private Applicant applicationModel;
    private Applicant applicationModel2;
    private ApplicationReport applicationReport;
    private Review reviewModel;
    private Review reviewModel2;
    private SynodSection synodSection;
    private SynodSection synodSection2;

    @BeforeEach
    void setUp() {
        byte[] file = new byte[10];

        Role role = Role.builder().id(1).name("ROLE_USER").build();
        User userModel = User.builder().id(1).name("User 1").surname("Surname 1").role(role).build();
        User userModel2 = User.builder().id(2).name("User 2").surname("Surname 2").role(role).build();

        catalogue = Catalogue.builder()
                .id(10)
                .name("Test document")
                .description("Description")
                .fileName("Test file")
                .file(file)
                .synod(Synod.builder().id(1).build())
                .user(userModel)
                .build();

        Catalogue catalogue2 = Catalogue.builder()
                .id(10)
                .name("Test document1")
                .description("Description")
                .fileName("Test file")
                .file(file)
                .user(userModel2)
                .build();

        User committeeUserModel = User.builder().id(2).username("regUsername").build();
        User committeeUserModel2 = User.builder().id(23).username("regUsername23").build();
        applicationModel =
                Applicant.builder().id(1).synodMember(userModel).catalogue(catalogue).status(ApplicantStatusEnum.IN_REVIEW).build();
        applicationModel2 =
                Applicant.builder().id(2).synodMember(userModel2).catalogue(catalogue2).status(ApplicantStatusEnum.IN_REVIEW).build();
        applicationReport = ApplicationReport.builder().id(1).text("Text 1").applicant(applicationModel).user(userModel).build();
        reviewModel = Review.builder()
                .id(1)
                .applicant(applicationModel)
                .registrar(committeeUserModel)
                .status(ReviewStatusEnum.APPROVED)
                .build();
        reviewModel2 = Review.builder()
                .id(1)
                .applicant(applicationModel)
                .registrar(committeeUserModel2)
                .status(ReviewStatusEnum.IN_PROGRESS)
                .build();

        SynodCategory
                synodCategory = SynodCategory.builder().id(1).name("PASTORAL WORK").description("Description").build();
        synodSection = SynodSection.builder().id(1).name("Pastoral Administration section").category(synodCategory).build();
        synodSection2 = SynodSection.builder().id(2).name("Biblical and Words of God Section").category(synodCategory).build();
    }

    @Test
    void testCreateApplication() {
        when(catalogueService.createCatalogue(catalogue)).thenReturn(Mono.just(catalogue));
        when(applicantDAO.save(any())).thenReturn(Mono.just(applicationModel));

        StepVerifier.create(applicantService.createApplication(catalogue))
                .expectSubscription()
                .expectNext(applicationModel)
                .expectComplete()
                .verify();
    }

    @Test
    void testActiveApplicationNotExist() {
        applicationModel.setStatus(ApplicantStatusEnum.DECLINED);
        applicationModel2.setStatus(ApplicantStatusEnum.DECLINED);
        when(applicantDAO.findByUserAndSynod(1, 1)).thenReturn(Flux.just(applicationModel, applicationModel2));

        StepVerifier.create(applicantService.activeApplicationNotExist(catalogue))
                .expectSubscription()
                .expectNext(true)
                .expectComplete()
                .verify();
    }

    @Test
    void testActiveApplicationNotExistFalse() {
        when(applicantDAO.findByUserAndSynod(1, 1)).thenReturn(Flux.just(applicationModel, applicationModel2));

        StepVerifier.create(applicantService.activeApplicationNotExist(catalogue))
                .expectSubscription()
                .expectNext(false)
                .expectComplete()
                .verify();
    }

    @Test
    void testGetByUserAndSynod() {
        when(applicantDAO.findByUserAndSynod(1, 1)).thenReturn(Flux.just(applicationModel, applicationModel2));

        StepVerifier.create(applicantService.getByUserAndSynod(1, 1))
                .expectSubscription()
                .expectNext(applicationModel)
                .expectNext(applicationModel2)
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }

    @Test
    void testDeleteReport() {
        when(applicationReportDAO.findById(1)).thenReturn(Mono.just(applicationReport));
        when(applicationReportDAO.delete(applicationReport)).thenReturn(Mono.empty().then());

        StepVerifier.create(applicantService.deleteReport(1, 1))
                .expectSubscription()
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }

//    @Test
//    void testDeleteReportBadId() {
//        when(applicationReportDAO.findById(1)).thenReturn(Mono.just(applicationReport));
//        when(applicationReportDAO.delete(applicationReport)).thenReturn(Mono.empty().then());
//        applicantService.deleteReport(1, 2);
//        verify(applicationReportDAO, times(0)).delete(applicationReport);
//    }

    @Test
    void testFinishApplicationError() {
        when(reviewDAO.findByApplicant(1)).thenReturn(Flux.just(reviewModel, reviewModel2));

        StepVerifier.create(applicantService.finishApplication(1))
                .expectSubscription()
                .expectErrorMessage("You must have 2 approvals")
                .verify();
    }

    @Test
    void testGetApplicationsForRegistrarError() {
        when(synodService.isUserRegisterToSynod(1, 2)).thenReturn(Mono.just(false));

        StepVerifier.create(applicantService.getApplicationsForRegistrar(1, 2))
                .expectSubscription()
                .expectErrorMessage("User is not part of the conference")
                .verify();
    }

    @Test
    void testGetApplicationsForRegistrar() {
        when(synodService.isUserRegisterToSynod(1, 2)).thenReturn(Mono.just(true));
        when(sectionDAO.findSectionsForRegistrar(2)).thenReturn(Flux.just(synodSection, synodSection2));
        when(sectionDAO.isSectionBelongsToSynod(1, 1)).thenReturn(Mono.just(1));
        when(sectionDAO.isSectionBelongsToSynod(1, 2)).thenReturn(Mono.just(1));
        when(applicantDAO.findBySynodSection(1, 1)).thenReturn(Flux.just(applicationModel));
        when(applicantDAO.findBySynodSection(1, 2)).thenReturn(Flux.just(applicationModel2));
        when(reviewDAO.findByApplicant(1)).thenReturn(Flux.just(reviewModel));
        when(reviewDAO.findByApplicant(2)).thenReturn(Flux.just(reviewModel2));

        StepVerifier.create(applicantService.getApplicationsForRegistrar(1, 2))
                .expectSubscription()
                .expectNext(applicationModel)
                .expectNext(applicationModel2)
                .expectComplete()
                .verify();
    }

//    @Test
//    void testGetApplicationsForCommitteeMemberBadStatus() {
//        applicationModel.setStatus(ApplicantStatusEnum.DECLINED);
//        when(synodService.isUserRegisterToSynod(1, 2)).thenReturn(Mono.just(true));
//        when(sectionDAO.findSectionsForSynod(2)).thenReturn(Flux.just(synodSection, synodSection2));
//        when(sectionDAO.isSectionBelongsToSynod(1, 1)).thenReturn(Mono.just(1));
//        when(sectionDAO.isSectionBelongsToSynod(1, 2)).thenReturn(Mono.just(1));
//        when(applicantDAO.findBySynodSection(1, 1)).thenReturn(Flux.just(applicationModel));
//        when(applicantDAO.findBySynodSection(1, 2)).thenReturn(Flux.just(applicationModel2));
//        when(reviewDAO.findByApplicant(1)).thenReturn(Flux.just(reviewModel));
//        when(reviewDAO.findByApplicant(2)).thenReturn(Flux.just(reviewModel2));
//
//        StepVerifier.create(applicantService.getApplicationsForRegistrar(1, 2))
//                .expectSubscription()
//                .expectNext(applicationModel2)
//                .expectComplete()
//                .verify();
//    }
}
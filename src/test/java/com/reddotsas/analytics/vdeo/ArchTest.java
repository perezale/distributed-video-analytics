package com.reddotsas.analytics.vdeo;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.reddotsas.analytics.vdeo");

        noClasses()
            .that()
            .resideInAnyPackage("com.reddotsas.analytics.vdeo.service..")
            .or()
            .resideInAnyPackage("com.reddotsas.analytics.vdeo.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.reddotsas.analytics.vdeo.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}

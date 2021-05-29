package com.brain.declaration;

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
            .importPackages("com.brain.declaration");

        noClasses()
            .that()
            .resideInAnyPackage("com.brain.declaration.service..")
            .or()
            .resideInAnyPackage("com.brain.declaration.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.brain.declaration.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}

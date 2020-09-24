package au.com.softsmart.myapp;

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
            .importPackages("au.com.softsmart.myapp");

        noClasses()
            .that()
            .resideInAnyPackage("au.com.softsmart.myapp.service..")
            .or()
            .resideInAnyPackage("au.com.softsmart.myapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..au.com.softsmart.myapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}

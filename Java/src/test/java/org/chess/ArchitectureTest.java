package org.chess;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = {"org.chess.domain"},
        importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    @ArchTest
    public static final ArchRule dependenciesShouldPointInward =
            noClasses()
                    .that().resideInAPackage("org.chess.domain.*")
                    .should().dependOnClassesThat().resideInAnyPackage(
                            "org.chess.input", "org.chess.rendering", "org.chess.ruleengine");
}

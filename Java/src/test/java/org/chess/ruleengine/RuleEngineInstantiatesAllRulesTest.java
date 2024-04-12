package org.chess.ruleengine;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaConstructorCall;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.chess.domain.rules.Rule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packages = {"org.chess.ruleengine", "org.chess.domain.rules"},
        importOptions = ImportOption.DoNotIncludeTests.class)
class RuleEngineInstantiatesAllRulesTest {

    private static final DescribedPredicate<JavaClass> areRules = JavaClass.Predicates.implement(Rule.class);
    private static final ArchCondition<JavaClass> beInstantiatedByRuleEngine = new BeInstantiatedByRuleEngine();

    @ArchTest
    public static final ArchRule shouldInstantiateAllRules =
            classes()
                    .that(areRules)
                    .should(beInstantiatedByRuleEngine);

    private static class BeInstantiatedByRuleEngine extends ArchCondition<JavaClass> {

        private final Class<RuleEngine> ruleEngineClass = RuleEngine.class;

        public BeInstantiatedByRuleEngine() {
            super("be instantiated by rule engine");
        }

        @Override
        public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
            var constructorCalls = javaClass.getConstructorCallsToSelf();
            var isInstantiated = constructorCalls.stream().anyMatch(this::isInstantiatedByRuleEngine);
            conditionEvents.add(new SimpleConditionEvent(javaClass, isInstantiated, formatViolation(javaClass)));
        }

        private boolean isInstantiatedByRuleEngine(JavaConstructorCall constructorCall) {
            return constructorCall
                    .getOriginOwner()
                    .getSimpleName()
                    .equals(ruleEngineClass.getSimpleName());
        }

        private String formatViolation(JavaClass javaClass) {
            return "Rule %s is not being instantiated by %s."
                    .formatted(javaClass.getSimpleName(), ruleEngineClass.getSimpleName());
        }
    }
}

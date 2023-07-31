using Chess.Domain.Rules;

namespace Chess.Domain
{
    public class RuleEngine
    {
        private readonly IEnumerable<IRule> _rules = new List<IRule>()
        {
            new SourcePositionMustNotBeEmpty()
        };

        public RuleEngine()
        {
        }

        public MoveResult Execute(Move move, GameState gameState)
        {
            IEnumerable<RuleViolation> ruleViolations = _rules
                .Select(rule => rule.Execute(move, gameState))
                .Where(ruleViolationOrNull => ruleViolationOrNull.HasValue)
                .Select(ruleViolation => ruleViolation!.Value);

            return ruleViolations.Any()
                ? new(ruleViolations, gameState)
                : new(Enumerable.Empty<RuleViolation>(), gameState);
        }
    }
}

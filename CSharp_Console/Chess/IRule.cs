namespace Chess.Domain.Rules
{
    public interface IRule
    {
        RuleViolation? Execute(Move move, GameState gameState);
    }
}

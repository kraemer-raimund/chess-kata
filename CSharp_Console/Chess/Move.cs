namespace Chess.Domain
{
    public readonly struct Move
    {
        public Move(Position from, Position to)
        {
            From = from;
            To = to;
        }

        public Position From { get; }
        public Position To { get; }

        public override string ToString() =>
            $"Move from ({From.File}, {From.Rank}) to ({To.File}, {To.Rank})";
    }
}

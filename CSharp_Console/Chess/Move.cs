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
    }
}

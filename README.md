# Chess Kata

A coding kata that focuses on applying TDD in an existing (unit-tested) code base.

## Intro

1. Familiarize yourself with the architecture
   
   Have a look at the package structure and file names. **But: Do not open any files yet!** It is often not realistic to look deeply into every part of the code base before starting to work with it. How much can you derive from the package structure and class names? Discuss with your peers.

1. Execute the program

   Build and execute the program. Have a look at the prompt in the command line and get an understanding of the current state of the chess game.

1. Start working on new functionality (see below)
   
   How do you decide which parts of the code base you will probably have to change in order to implement new functionality? Try to fit your new code into the existing architecture and design. To decide *how to use* existing code, prefer looking into existing tests as examples before deciding whether to look into the implementation details.

## Requirements

Basic movement of the chess pieces on the board is already implemented. Now we want to implement some more rules to iterate toward a full chess game. Note: It is not the goal (and typically unlikely) to finish all of these in a single kata session! Focus on execution over speed. Over time, with [deliberate practice](https://en.wikipedia.org/wiki/Practice_(learning_method)#Deliberate_practice), the output quantity will increase automatically.

1. Each player is only allowed to move their own pieces.
1. A piece can not be moved to the position it already stands on.
1. [The pawn ♟︎ can move](https://en.wikipedia.org/wiki/Pawn_(chess)#Placement_and_movement) 1 square forward (i.e. towards the enemy). In its first move it can (optionally) move 2 forward instead, but it can not jump over another piece of any color.
   <details>
   <summary>Hint 1</summary>
   In this architecture, who is logically responsible for knowing how the pawn is allowed to move? We want to hide this detail within an object and not make it publically accessible. We also do not want to repeat the information. We might be tempted to put that knowledge into a `Pawn` class somewhere, but at second glance, this might not be the right choice. Who decides whether a move is legal?
   </details>
   <details>
   <summary>Hint 2</summary>
   Do we need to track somehow whether a pawn has already moved, or is there a simpler way?
   </details>
   <details>
   <summary>Hint 3</summary>
   The pawn can move 1 forward. In its first move it can optionally move 2 forward; but it cannot jump over another piece. It captures diagonally (see below). Is this all the same rule, or are they more (3? 4?) rules? Discuss!
   </details>
1. [The pawn ♟︎ can capture](https://en.wikipedia.org/wiki/Pawn_(chess)#Capturing) an enemy piece diagonally, but **not** through its normal forward movement. (There is a special capturing rule for the pawn called [en passant](https://en.wikipedia.org/wiki/En_passant), which we ignore for now.)
1. [The bishop ♝ can move](https://en.wikipedia.org/wiki/Bishop_(chess)#Placement_and_movement) only diagonally, but in any direction and arbitrarily far until it is blocked by another piece.
1. [The knight ♞ can move](https://en.wikipedia.org/wiki/Knight_(chess)#Movement) 2 squares forward and 1 to the side (forming the shape of an "L"). The knight ♞ is the only piece that can jump over other pieces (both ally and enemy).
1. [The rook ♜ can move](https://en.wikipedia.org/wiki/Rook_(chess)#Placement_and_movement) horizontally and vertically, in any direction until it is blocked by another piece.
1. [The queen ♛ can move](https://en.wikipedia.org/wiki/Queen_(chess)#Placement_and_movement) straight and diagonally in any direction until it is blocked by another piece.
   <details>
   <summary>Hint</summary>
   At this point, you might be tempted to reuse some code from the bishop's and the rook's movement patterns; possibly even make use of inheritance for code reuse. Carefully weigh the trade-off between coupling and reusing a few lines of otherwise independent, conceptually unrelated, relatively simple, and unit-tested code. Often it is worth it having similar pieces of code that do not depend on each other and are independently unit tested. There is an important difference between duplication of code that does and means the same, and code that works similarly but is conceptually different.
   
   Otherwise, where do we stop? "The queen moves like the rook and the bishop combined. But the rook moves like the king, only repeated. The king moves like the pawn, but in every direction. The knight moves like the pawn in its first move, but in every direction, followed by a move of the king, but to the side."
   </details>

## Optional more challenging features

Some ideas in case you are looking for a challenge.

1. Look up [the king's movement rules](https://en.wikipedia.org/wiki/King_(chess)). The movement pattern is very simple, but: The king is not allowed to move into check.
1. [Castling](https://en.wikipedia.org/wiki/Castling)
1. [En passant](https://en.wikipedia.org/wiki/En_passant)
1. [Check](https://en.wikipedia.org/wiki/Check_(chess)) and [checkmate](https://en.wikipedia.org/wiki/Checkmate)
1. [Pawn promotion](https://en.wikipedia.org/wiki/Promotion_(chess))
1. Special commands: Restarting the game, ending the game, ...
1. Undo history: Revert the last move, arbitrarily often until the initial state.
1. Saving the game state to a file to continue the same game later.
___

© 2023 Raimund Krämer - Use with attribution.

Links to third party sites are included for convenience only and I am not responsible for their contents.

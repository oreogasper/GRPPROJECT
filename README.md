# Group Project README

## Team Member Names
- Valerie Kim
- Emma Chow
- Jashvir Rathore
- Halsten Tilk
- Samjir Bhuiyan

## GitHub Usernames 
- ValKim712 (Valerie)
- oreogasper (Emma)
- jashvirr (Jashvir)
- halstent (Halsten)
- samjirbh (Samjir)


## Project description
A new platform in which you are able to gamble virtual tokens in 3 different games.
A user is able to get daily tokens and check their stats against other players.
It's time to gamble!

***

## User Stories
**Team:** 
- As a new user, I want to sign up for a membership with a personalized username and a password.
- As a returning user, I want to log in with my username and password.
- As a returning user, I want to be able to change my password if I forget it (but remember my username).
- As a user, I want to be able to close return to the login page from the main application menu screen.
- As a user, I want to be able to navigate between the signup/login pages if I forget/remember my credentials.

**Halsten:** 
- As a user, I want to get more tokens! I want to go to the shop to increase my balance.
- As a user, I want to claim my hourly tokens by spinning the chance wheel, which will give me some amount 
between 1-100 tokens.
- As a user, I want a non-timed method to increase my token count, so I want to click a button which adds 0.1 to my 
token count (only adding whole numbers).

**Samjir:** 
- A user wants to play the over/under card game (guess if the next card will be higher or lower than the 
current card). The user must enter a bet (must be at least the minimum bet) prior to playing the game. 
Depending on whether they win or lose, the bet will be deducted or added to their total tokens

**Val:**
- As a user, I want to play the 50/50 gauntlet game, so I can have the opportunity to win tokens. 
- As a player, I want to be able to make a guess of either heads or tails, so that I can place my first bet. 
- As a player, I want to make a guess for the dice roll from 1-6, so I can place my second bet. 
- As a player, I want to make a guess from rock, paper or scissors, so I can make my final bet. 
- As a user, I want to see my results, so I can see if I won my bet.


**Jashvir:** 
- As a user, I want to play blackjack (standard rules). 
- As a player, I want to bet a certain amount that I either win or lose depending on the outcome of the game. 
- As a player, I want to be able to see my hand and be able to stand, ending my turn, or hit, adding a card to my hand. 
- As a player, I want to be able to see the outcome of the game depending on the moves I have made and my hand.


**Emma:** 
- As a user, I want to be able to see my profile, so I can view all my statistics (current tokens, wins, losses, win %, etc.)
- As a user, I also want to check the leaderboard, so I can compare my own winnings to other players

***

## Proposed Entities

### User
- UserID: int
- Username: String
- Password: String
- Age: int
- Stats card: StatsCard
- Wallet: float
- PrevSpin: DateTime
### Game
- Bet: int
- Rules: String(?)
- Minimum: int
### Leaderboard
- Users: List[Users]
### Stats card
- UserID: int
- Wins: int
- Losses: int
- TotalPlays: int
- Wallet: int
- WinRate: float
- AverageBet: float

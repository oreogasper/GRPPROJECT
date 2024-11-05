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

## User Stories
- Team: A welcome page where a new user signs up for our system or logs in if they already have an account. 
The user can log out after logging in.

**Halsten:** 
- A user wants to get tokens, so they navigate to the shop and can either spin the hourly token wheel to 
receive different bundles of tokens (can range from 1-100 tokens) or click a button for one token.

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
- As a user, I want to play blackjack (standard rules). As a player, I want to bet a certain amount that I 
either win or lose depending on the outcome of the game. As a player, I want to be able to see my hand and be able to
stand, ending my turn, or hit, adding a card to my hand. As a player, I want to be able to see the outcome of the game
depending on the moves I have made and my hand.


**Emma:** 
- As a user, I want to be able to see my profile, so I can view all my statistics (current tokens, wins, losses, win %, etc.)
- As a user, I also want to check the leaderboard, so I can compare my own winnings to other players

***

# Proposed Entities

### User
- UserID: int
- Username: String
- Password: String
- Age: int
- Stats card: StatsCard
- Wallet: float
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

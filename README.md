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

- Halsten: A user wants to get tokens, so they navigate to the shop and can either spin the hourly token wheel to 
receive different bundles of tokens (can range from 1-100 tokens) or click a button for one token.

- Samjir: A user wants to play the over/under card game (guess if the next card will be higher or lower than the 
current card). The user must enter a bet (must be at least the minimum bet) prior to playing the game. 
Depending on whether they win or lose, the bet will be deducted or added to their total tokens

- Val: A user wants to play the 50/50 gauntlet game (coinflip + dice roll + rock, paper, and scissors in sequence, 
the user must win all minigames) The user must guess each occurance correctly to win the game and receive their 
initial bet.

- Jashvir: A user wants to play blackjack (standard rules). Once again, the user will make a bet and depending on the
outcome of the game, they can win or lose tokens

- Emma: A user wants to see their stats (current tokens, wins, losses, win %, etc.). The user also wants to check the 
leaderboard to compare their stats against the top users.

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

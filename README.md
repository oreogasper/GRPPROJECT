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
- As a user, I want to play the over/under card game, so I can test my luck and prediction skills.
- As a user, I want to place a bet before playing, ensuring my bet meets the minimum requirement, so I can start the game with a valid wager.
- As a user, I want the game to deduct or add my bet amount to my total tokens based on whether I win or lose, so my tokens reflect the outcome of the game.

**Val:**
- As a user, I want to make a bet in the 50/50 gauntlet game, so I can have the opportunity to win tokens. 
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

***

## Steps to Project Completion

## Task 0 - Planning

Meet as a group and discuss project goals as well as individual tasks. 
Assign self-regulated deadlines and modes of communication.
Fill out the given blueprint and make sure the idea passes TA checking.

## Task 1 - Framework

### Task 1.1 - Something tangible!

Recycle code from previous labs and recommended programs from the course files.
Lab 5 gives an incomplete implementation of a signup-login system, which can be finished and polished.

### Task 1.2 - Individual Use cases

Each individual should add the required files for their use case and the group should 
ensure the signup-login case works as it is supposed to. Similar files should generally
follow the same format for cohesive programming, but some variation is necessary for function.

### Task 1.3 - Navigation

With all necessary files added, the menus and JSwing screen displays can be linked through 
action listener buttons. A program of this size will be vast but UML diagrams should lay out
everything that needs to have a connecting path.

## Task 2 - Functionality implementation

### Task 2.1 - Entity creation

Entities listed in the README and the blueprint should be constructed and their files properly located.
Necessary attributes and functions should be accessible for their relevant use cases.
The signup-login system should be the first to interact with certain entities and create the
references for later use cases.

### Task 2.2 - Entity incorporation

Entities should be infused into their related use cases. After this step, the program should have full 
functionality albeit lacking in visual design. Note: testing will come shortly after and has been delayed
until the next steps as the code builder design patterns generally limit bugs through the creation process.

### Task 2.3 - Quick debugging

Group members should play around with the system and check that all functionality works 
as intended. Reflection back to the blueprint and initial UML diagrams will give the opportunity
to check for any missing files, classes, interfaces, etc.

## Task 3 - Testing

### Task 3.1 - Unit tests

Short unit tests should be written for each use case throughout the system. Each individual
is responsible for testing their own use cases thoroughly and proving full code coverage.

### Task 3.2 - Integration tests

The next level up in testing, integration test should be written for use cases and class-linking.
Again written by the relevant individual who wrote a certain section of the program.

### Task 3.3 - End-to-end tests

Only a few end-to-end tests will need to be written. They should be constructed by several group
members as the test cross through most if not all files created for the project.

## Task 4 - Visuals

Visual designs can be implemented for the project, with a consistent design scheme throughout the 
JSwing screens. Transition between the different use case button locations, interaction types, and
other design choices should be compatible and easy for the user to understand (intuitiveness is key).

## Task 5 - Quintuple-checking

Go over all previous steps to ensure correct, efficient, functional implementation of the domain
which follows the specified software design patterns.
# Business House Game
Business house is a board game which requires minimum two players. Player uses a random number between 1-12 and moves on the board accordingly. Each player has some amount at the start. Below image represents a game board.

<img height='331' src='/business_house.png?raw=true' alt="Business house board image"/>

Board will have a central Bank, which will have a initial money, e.g: Rs. 5000. Every player has to pay to bank whenever they land on jail and similarly bank will pay to players if they land on lottery cell. The board cell may be one of the following types.

- Jail: When the user lands on it, a defined amount, for e.g. Rs 150, will be deducted from user's money and send to bank.  
- Lottery: When the user lands on it, a defined amount, for e.g. Rs 200, will be added to user's money and deducted from bank.
-  Hotel: This is a special type of entity.
    - It has three types.
        * Silver -> Value = 200, Rent = 50
        * Gold -> Value = 300, Rent = 150
        * Platinum -> Value = 500, Rent = 300
    - When the user lands on it and has required money, he has to buy it by paying the bank the required money to buy a silver hotel.
    - If the user lands on its pre-owned hotel and has required money, the user needs to upgrade hotel by paying required delta value.
         * Silver to Gold -> 100
         * Gold to Platinum -> 200
    - If any other user lands on a pre-owned hotel, the user needs to pay rent as per hotel state (Silver, Gold, Platinum) to hotel owner.
### How To Play :
1. Two+ users will start from starting point with initial money.
2. They have to move as per random number between 1-12.
3. Every move has to follow cell type rules defined above.
4. Maximum ten chances will be awarded to each player.
5. After ten chances, player with maximum money, will be declared as the winner.
   
### Inputs :
You can hard code below values in code, no need to parse them.
   
Initial money in bank : 5000
Initial money for each player : 1000
   
- Hotels :
   - Silver -> Value = 200, Rent = 50
   - Gold -> Value = 300, Rent = 150
   - Platinum -> Value = 500, Rent = 300 
- Jail Fine: 150
- Lottery Value: 200 

J- Jail, H- Hotel, L- Lottery, E- Empty Cell
   
#### Input set one:
   Cells (10 cells only): `J,H,L,H,E,L,H,L,H,J`  
   Dice Output : `2,2,1, 4,4,2, 4,4,2, 2,2,1, 4,4,2, 4,4,2, 2,2,1`  
   Player : `3`  
   Result :  
   ```
   Player-1 has total money 600 and assets of amount : 600 -> 1100, 500  
   Player-3 has total money 350 and assets of amount : 500 -> 1150, 0  
   Player-2 has total money 350 and assets of amount : 0 -> 600, 0  
   Balance at Bank : 6700 -> 5150
   ```
#### Input set two:
   Cells (10 cells only): `J,H,L,H,E,L,H,L,H,J`  
   Dice Output : `2,2,1, 4,2,3, 4,1,3, 2,2,7, 4,7,2, 4,4,2, 2,2,2`  
   Player : `3`  
   Result :
   ```
   Player-1 has total money 500 and asset of amount : 800
   Player-2 has total money 350 and asset of amount : 200
   Player-3 has total money 250 and asset of amount : 0
   Balance at Bank : 6900
   ```

# KungFuMemory2
Java source files for the Kung Fu themed memory card game that uses the GWT framework.

/**********************************************************/
/*  Author:      Brendan Lilly                            */
/*  Course:      CSC421                                   */
/*  Professor:   Spiegel                                  */
/*  Assignment:  2                                        */
/*  Due Date:    3/22/2016                                */
/*  Start Date:  3/10/2016                                */
/*  Filename:    readme.txt                               */
/*  Purpose:     Explain key points of the project 2      */
/**********************************************************/

How to Play
-----------------------------------------------------------

The game itself is playable at : 
http://acad.kutztown.edu/~blill253/CSC421/Proj2/MemoryCard.html

and the Javadocs can be found at :

http://acad.kutztown.edu/~blill253/CSC421/Proj2/Javadocs/

Design Decisions 
-----------------------------------------------------------

This iteration of the game came with a few enhancements mostly to the way
      that the game itself is played/controlled but also with the player types.
      Now when the game is started the user has the option to play against an 
      opponent that can either be human or computer. To handle this I
      created two new sub classes HumanPlayer and ComputerPlayer both of 
      which extends Player. There is not much different in the HumanPlayer 
      and the main additions were done to the ComputerPlayer. The 
      ComputerPlayer has an ArrayList that acts as it's memory. Along with 
      this ArrayList are functions that allow for memory to be set and 
      checked. This allows for the game to easily be expanded if
       needed to accommodate for multiple AI opponents.

The other major decisions came in how the board is displayed. I chose to use
    the grid panel in order to display the cards in an organized fashion. I 
    then took advantage of the toggle button widget and placed one in each of
    the cells. When you create a toggle button you can create it with an 
    image for each of its faces. This allowed me to generate a board of 
    cards with a card back on one side and a symbol on the other. The nice 
    feature of the toggle button is that it already supports changing the image
    displayed based on a users click input and can also be changed with a 
    method to set the value of the button which was used by the computer
    player.I just needed to modify the click handler to keep track of which 
    cards were actually being clicked and how many had already been clicked.

One of the drawbacks I ran into when using the grid on the front end but having
    the cards stored in an ArrayList in my Layout class was converting between
    a linear index to an X and Y coordinate in the grid. This turned into a 
    minor problem and was solved using some simple math equations. To get from
    row and column to a singular index was : (Width*row)+col and the reverse
    was done with : Row = index/6 and Column = index%6.

The bulk of the game is controlled by the mainGameLoop function. This is called
    every time a toggle button is clicked. When a card is clicked it is added
    to an ArrayList of cards. If the mainGameLoop detects that two clicks have
    occurred it can compare the first two indexes to see if there is a match.
    The CPU I designed is fairly simple but effective. Every time a card is
    flipped whether it be by a human or computer player , there is a 45% 
    chance that it will add that card into its memory. Then when it goes to 
    chose its cards during its turn it will first check if there is a pair
    present in memory.If this is the case the Computer will select the pair 
    and flip it over. If there is no pair it will flip a random card over 
    and then check its memory to see if there is a match. If so then it will 
    chose the matched card from memory and if not it will chose a random 
    card. While it is the CPUs turn the user cannot click on any of the cards.
     This is done by a boolean flag that disables user input whenever the 
     CPUs turn begins. The flag is checked in the click handler of the 
     toggle button and disables the click of a user causing it to have 
     no impact on the game. I found that 45% was a good difficulty for most 
     of the matches that I played but I would like to add in difficulty 
     settings in the next project.
As a bonus I added in some sound effects into the game. The first is a 
   background sound that plays starting at the home screen. I also added
   in a shuffle sound effect that plays when the grid is generated. Also when
   the cards are flipped either by the Human or Cpu player a card flip sound 
   effect can be heard. I though this made the game a little more enjoyable
   to play.


How To Play
----------------------------------------------------------------------------
The game play got even more simplistic in this version of the game. At the 
home screen the user is given a Start Button to press to begin. Then they 
have the option of playing Single or Two Player games. Both options leads 
to pages with fields to enter players names. If Two player is selected 
the user can change the toggle for the type of the second player and 
switch between Human or Computer. The first players toggle always remains 
on Human. After entering names and selecting types the user can select to 
begin playing.  

One Player
-----------------------------------------------------------------------------
The players stats can be seen on the left hand side of the screen in a tab 
window. Also in that tab screen in the toggle to turn the cheat off and 
on. From here gameplay is self explanatory. The user can select 2 cards. 
They will remain face up after the second card is flipped and compared. If 
they are a matched the cards will be disabled and left face up. If they 
are not a match they will be flipped back face down. This continues 
until all of the cards are matched. Once all of the cards are matched an
alert will pop up displaying the winner and number of matches. Then the 
window will refresh and the user has the option to start again.

Two Player
------------------------------------------------------------------------------
The Gameplay for two players is the same as one player except for a few items.
    The current players turn is show by the player whose name has the white 
    background in the stats table on the left hand side. The user's turn
    lasts until they get a card wrong. In other words you can keep playing 
    until you make a mistake and then its the next players turn. Play will 
    continue until all of the cards have been matched. Then the alert will 
    pop displaying the winner and causing the page to refresh.		


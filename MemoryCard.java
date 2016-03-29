/**********************************************************/
/*  Author:      Brendan Lilly                            */
/*  Course:      CSC421                                   */
/*  Professor:   Spiegel                                  */
/*  Assignment:  1                                        */
/*  Due Date:    TBD                                      */
/*  Start Date:  2/2/2016                                 */
/*  Filename:    Player.java                              */
/*  Purpose:     Player class for the memory card game    */
/*  Time:        Write: Way to damn long.                 */
/*               Debug: Way to damn long.                 */
/*               Test:  Way to damn long.                 */
/**********************************************************/

package com.client;

import com.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import  com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.media.client.Audio;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.dom.client.Style.Unit;

import java.util.*;



/**
	* This class contains all of the methods that drive the Memory Card game
	* and control the stages of the game.
	* @author Brendan Lilly
*/	
public class MemoryCard implements EntryPoint {
 
  //Creates the ArrayList to hold the players 		
  static List<Player> playerList = new ArrayList<Player>();
  //Creates the ArrayList to hold the clicked cards
  static List clickedCards = new ArrayList();
  //Creates the Grid that holds the toggle buttons
  static Grid cardGrid = new Grid(5,6);
  //Creates the board object 
  static Layout board = generateCards();

  //Sets up counters at 0
  static int numClicks = 0;
  static int matchedCards = 0;
  static int currentPlayer =0;

  //Sets up the boolean flags that control game state
  static boolean gameStart = false;
  static boolean userControl = true;
  static boolean cheatToggle = true;


  /**
   * Method that runs on load
*/
  public void onModuleLoad() {
    	
    	//Calls the newGame method and backgroundMusic methos
		newGame();  
		backgroundMusic();		
  }


  /**
	* Creates a new board and shuffles it.
	* @return The newly created and shuffled board.
*/	
	public static Layout generateCards(){
		
		boolean noCheat = true;
		
		//Create a new Layout object
		Layout board = new Layout();
		
		//Create new Card objects and add them
		// to the board object.
		
		//Each card objects symbol is the path to the image file
		board.addCard(new Card("CardImages/masterkan.jpg",noCheat));
		board.addCard(new Card("CardImages/caine.jpg",noCheat));
		board.addCard(new Card("CardImages/david.jpg",noCheat));
		board.addCard(new Card("CardImages/grasshopper.jpg",noCheat));
		board.addCard(new Card("CardImages/hat.jpg",noCheat));
		board.addCard(new Card("CardImages/horse.jpg",noCheat));
		board.addCard(new Card("CardImages/kungfu.jpg",noCheat));
		board.addCard(new Card("CardImages/kungfu3.jpg",noCheat));
		board.addCard(new Card("CardImages/masterpo.jpg",noCheat));
		board.addCard(new Card("CardImages/masterpowithcandles.jpg",noCheat));
		board.addCard(new Card("CardImages/nicekungfu.jpg",noCheat));
		board.addCard(new Card("CardImages/training.jpg",noCheat));
		board.addCard(new Card("CardImages/twopeople.jpg",noCheat));
		board.addCard(new Card("CardImages/wantedposter.jpg",noCheat));
		board.addCard(new Card("CardImages/younggrasshoper.jpg",noCheat));
		
		//Once all of the cards are added
		// shuffle the board to randomize it.
		board.shuffleBoard();		
		
		//Return the fully populated board
		return board;
	}
  /**
	* Creates a new grid to hold the cards
	* @param board The shuffled board
*/		
	public static void generateGrid(Layout board){


	  		 int numRows = cardGrid.getRowCount();
	     	 int numColumns = cardGrid.getColumnCount();
	     	 int cardCount = 0;

	     	 //Start of for loops to iterate through entire grid
		     for (int row = 0; row < numRows; row++) {
		        for (int col = 0; col < numColumns; col++) {

		        	final int row1= row;
		        	final int col1 = col;
		        	//Sets the widget at each cell in the grid to a 
		        	// toggle button with one side being the card back image
		        	// and the other side being the card symbol image.
		            cardGrid.setWidget(row, col, 
		            new ToggleButton(new Image("CardImages/cardback.jpg"),
		            		new Image(board.getSymbolByLocation(cardCount))
		            		,new ClickHandler(){
		            			public void onClick(ClickEvent event) {

		            				//Gets the source of the event
			            			 ToggleButton tb = 
			            			 	(ToggleButton)(event.getSource());

			            			 //If its the CPUs turn
			            			 // disallow the click and return
			            			 if(!userControl)
			            				{
			            					tb.setDown(false);
			            					return;
			            				}
			            			 //Iterate through the grid 
			            			 // until the clicked cell is found
			            			 int tempR = cardGrid.getRowCount();
			            			 int tempC = cardGrid.getColumnCount();
			            			 for (int row = 0; row < tempR; row++) {
			       						 for (int col = 0; col < tempC; col++) 
			       						 {
							        		if(cardGrid.getWidget(row,col)==tb)
							        		{
							        			//Once the cell is found
							        			//call the addCard 
							        			// and recordCard 
							        			// methods.
							        			addCard((6*row)+col);
							        			recordCard((6*row)+col);

							        		}

			        					}
			        				}
			        				flipSound();
			        				//Iterate the current number of clicks
		     						 numClicks++;
		     						 //Call the mainGameLoop();
		  							 mainGameLoop();
		  						  
	  							}

		            		}));
		            //Increase card count
		            cardCount++;

		         }
		      } 

		  //Create a new Decorator panel and add the grid to it	

		  DecoratorPanel dPanel = new DecoratorPanel();
	      dPanel.add(cardGrid);
	      // Add the panel to the root panel.
	      RootPanel.get("section").add(dPanel);

	}

/**
	* Prompts the user to start a new game.
*/	
	public static void newGame(){

	  //Create a new button
	  Button start = new Button("Start New Game!");

      // Add button to the Horizontal panel
      HorizontalPanel hPanel = new HorizontalPanel();
      hPanel.add(start);
      //Add the horizontal panel to the decorator panel
      DecoratorPanel dPanel = new DecoratorPanel();
      dPanel.add(hPanel);
      //Add the decorator panel to the root panel
      RootPanel.get("section").add(dPanel);

      //Add an click handler to the button
      start.addClickHandler(new ClickHandler() {
         
         public void onClick(ClickEvent event) {
         	//Clears the root panel
         	RootPanel.get("section").clear();
         	//Calls the displaySetup function
           	displaySetup();

	         }
	      });
     }

/**
	* Prompts the user to choose number of players.
*/	
    public static void displaySetup(){

     //Creates two new buttons
       Button one = new Button("One Player");
       Button two = new Button("Two Player");

     //Adds the two buttons to the vertical panel and then to the 
     // decorator panel 
       VerticalPanel vPanel = new VerticalPanel();
       vPanel.setSpacing(10);
       vPanel.add(one);
       vPanel.add(two);

       DecoratorPanel dPanel = new DecoratorPanel();
       dPanel.add(vPanel);
       RootPanel.get("section").add(dPanel);

      //Add the click handler to the first button
       one.addClickHandler(new ClickHandler(){
       		public void onClick(ClickEvent event){
       			//Clears the root panel
       			RootPanel.get("section").clear();
       			//calls createPlayers with 1 passed to it
       			createPlayers(1);
       		}
       });

      //Add the click handler to the second button 
       two.addClickHandler(new ClickHandler(){
       		public void onClick(ClickEvent event){
       			//Clears the Root panel
       			RootPanel.get("section").clear();
       			//Calls createPlayers with 2 passed to it
       			createPlayers(2);
       		}
       });

    }

/**
	* Prompts the user to enter a name and type for each player.
	* @param numPlayers The number of players needed to be set up.  
*/	
    public static void createPlayers(int numPlayers){

    	//Create the grid to get player info in.
  		final Grid grid = new Grid(numPlayers+1,2);

  		
    	 final int numRows = grid.getRowCount();
     	 final int numColumns = grid.getColumnCount();
     	 //Add labels to the top of the grid
     	 grid.setWidget(0, 0, new Label("Player Name: " ));
     	 grid.setWidget(0, 1, new Label("Human or Computer"));
	    
	     //Add Text boxes and toggle buttons to the grid
	     for (int row = 1; row < numRows; row++) {
	        for (int col = 0; col < numColumns; col++) {
	        	//First col is text boxes for names
	        	if(col == 0){
	        	 grid.setWidget(row, col, new TextBox());
	        	 ((TextBox)grid.getWidget(row,col)).setText("Player"+row);
	        	}
	        	//Second col and row 1 needs to be Human only
	        	else if(col == 1 && row == 1){
	          	  grid.setWidget(row, col, new ToggleButton("Human"));
	         	}
	         	//Otherwise add a toggle button with options for human or cpu
	         	else if(col==1)
	         	{
	         	grid.setWidget(row, col, new ToggleButton("Human","Computer"));
	         	}

	         }
	      } 

	      //Create a new button 
	      Button begin = new Button("Begin");

	      //Add a click handler to this button
	      begin.addClickHandler(new ClickHandler(){
       		public void onClick(ClickEvent event){
       			//Iterate through the grid and get the names and types
       			 String name = " ", type = " ";
			     for (int row = 1; row < numRows; row++) {
			        for (int col = 0; col < numColumns; col++) {
			   			
			   			if(col == 0){
			   			name = ((TextBox)grid.getWidget(row,col)).getText();
			   			}
			   			else if(col == 1){
			   				type = grid.getText(row,col);
			   			}
			        	
			         }
			        //If the type is human create a human player
		         	if(type == "Human"){
		         		//And add the human player to the playerList
		   				playerList.add(new HumanPlayer(name,type));
		   			}
		   			else
		   			{
		   				//Else they are computer player
		   				//Add them to the same player list
		   				playerList.add(new ComputerPlayer(name,type));
		   			}
			      } 
			    //Clear the root panel  
       			RootPanel.get("section").clear();
       			//Generate the side panel for stats 
       			generateSidePanel();
       			//Generate the main game grid
      			generateGrid(board);
      			gridSound();

       		}
       	  });

	      //Add the grid to a decorator panel and the button to another
	      DecoratorPanel dPanel = new DecoratorPanel();
	      DecoratorPanel beginP = new DecoratorPanel();
          dPanel.add(grid);
          beginP.add(begin);
          //Add them both to the root panel
	      RootPanel.get("section").add(dPanel);
	      RootPanel.get("section").add(beginP);
	      
    }



/**
	* When a card is clicked this adds it to the clickedCards array list
	* @param location The location of the card that was clicked   
*/	
    public static void addCard(int location){
    	clickedCards.add(location);
    }

/**
	* The Main Game Loop that is checked after each click to determine 
	* the current state of the game.                              
*/	
    public static void mainGameLoop(){

    		if(numClicks == 1){
    			//If there has been one click 
    			// and the player is human then disable the card they clicked
    			// so they cannot reselect it.
    			if(!(playerList.get(currentPlayer) instanceof ComputerPlayer)){
    			enabled(false);
    			}
    		}
    		// If there have been two clicks recorded 
    		if(numClicks == 2){
    			//And the player is human , re enable the first card they 
    			//clicked.
    			if(!(playerList.get(currentPlayer) instanceof ComputerPlayer)){
    			enabled(true);
    			}
    			
    			//Check if the cards are a match
    			if(checkMatch((int)clickedCards.get(0) , 
    											(int)clickedCards.get(1)))
    			{
    				//If yes then set the cards matched for the 
    				// current player
    				playerList.get(currentPlayer).setCardsMatched();
    				//Set the pair as matched on the board
    				board.getCard((int)clickedCards.get(0)).setMatched(true);
    				board.getCard((int)clickedCards.get(1)).setMatched(true);
    				//Update the side panel with the new stats
    				updateSidePanel();

    				//If the player is a cpu then start another cpu turn
    				if(playerList.get(currentPlayer) instanceof ComputerPlayer)
    				{
    					cpuTurn();
    				}
    				else
    				{
    					//User remains control
    					userControl = true;
    				}
    			}
    			//IF the cards are not a match
    			else
    			{
    				//The rounds played gets increased for the current
    				// player
    				playerList.get(currentPlayer).setRoundsPlayed();
    				//If there is more than one person playing 
    				if(playerList.size()!=1){
    					//Change to the next player
    					changePlayer();
    				}
    				//Update the side panel with the new stats
    				updateSidePanel();
    				
    			}
    			//Clear the arraylist of clicked cards
    			clickedCards.clear();
    		
    		}
    		//If all cards are matched call the gameOverState()
    		if(matchedCards==15)
    		{
    			gameOverState();
    		}
    	
    }




/**
	* Checks if a match has been found.
	* @param cardOne The location of the first card.
	* @param cardTwo The location of the second card.             
	* @return True if a correct match is made false otherwise.            
*/
	public static boolean checkMatch(int cardOne, int cardTwo)
	{

		//If the symbols on the two cards passed in are equal
		if(board.getSymbolByLocation(cardOne)
		   ==board.getSymbolByLocation(cardTwo))
		{
			//Reset the number of clicks
			numClicks =0;
			//Increase the number of matches
			matchedCards++;
			//Call disableCards so that these two 
			//cannot be clicked again
			disableCards(cardOne,cardTwo);
			//Return true
			return true;

			
		}
		//Otherwise the symbols did not match
		else
		{
			//Flip the cards down
			resetCards(cardOne,cardTwo);
			//Return false due to no match
			// and set numClicks to 0
			numClicks =0;
			return false;
		}
	}



	/**
	* Sets the cards passed to disabled      
	* @param cardOne The first card in the match.
	* @param cardTwo The second card in the match.     
*/	
	public static void disableCards(int cardOne, int cardTwo)
	{
		//Gets the X and Y values based on the index in the array list
		// 6 is the width of the board
		// x is equal to index/6
		// y is equal to index%6

		int cardOneX = (cardOne/6);
		int cardOneY = (cardOne%6);
		int cardTwoX = (cardTwo/6);
		int cardTwoY = (cardTwo%6);
		
		//Gets the Widget at the x and y in the board 
		//Casts it as a Toggle button and sets it to disabled.
		((ToggleButton)cardGrid.getWidget(cardOneX,cardOneY)).setEnabled(false);
		((ToggleButton)cardGrid.getWidget(cardTwoX,cardTwoY)).setEnabled(false);
		
		//Goes through all current players
		for(int i = 0; i < playerList.size();i++)
		{
			//If any of them are Computer Players
			if(playerList.get(i) instanceof ComputerPlayer)
			{
				//Remove the matched cards from their memory
				((ComputerPlayer)playerList.get(i)).removeFromMem(cardOne
																,cardTwo);
			}
		}
		
	}


	/**
	* Sets the cards passed to face down      
	* @param card1 The first card 
	* @param card2 The second card  
*/	
	public static void resetCards(int card1, int card2){

		final int cardOne = card1;
		final int cardTwo = card2;
		//Start a timer 
		Timer timer = new Timer(){

		      @Override
		      public void run() {
		      	//When the time runs turn the cards face down

	        	((ToggleButton)cardGrid.getWidget((cardOne/6),
	        								(cardOne%6))).setValue(false);
				((ToggleButton)cardGrid.getWidget((cardTwo/6),
											(cardTwo%6))).setValue(false);
					
		      }
   		 };
		//Call the time on a 1.5 second delay.
		timer.schedule(1500);
	
	}

	/**
	* Generates the panel on the left hand side.    
*/	
	public static void generateSidePanel()
	{

		//Creates a new tab panel and grid
		 TabPanel dPanel = new TabPanel();
		 Grid infoGrid = new Grid(playerList.size()+1,4);
		 infoGrid.setBorderWidth(3);
		 infoGrid.setCellSpacing(2);

	 	 final int numRows = infoGrid.getRowCount();
     	 final int numColumns = infoGrid.getColumnCount();

     	 //Set the labels on the top row of the info grid
     	 infoGrid.setWidget(0, 0, new Label("Name" ));
     	 infoGrid.setWidget(0, 1, new Label("Type"));
     	 infoGrid.setWidget(0,2,new Label("Card Matches"));
     	 infoGrid.setWidget(0,3,new Label("Rounds Played"));
	     
	     //Iterate through all of the cells in the info grid
	     for (int row = 1; row < numRows; row++) {
	        for (int col = 0; col < numColumns; col++) {
	        	
	        	//If it is col 0
	        	if(col == 0){
	        		//If the current player is equal to the row of the label
	        		// being added , set the style to currentToken
	        		// This adds a background
	        		if((row-1)==currentPlayer){
		        	 	infoGrid.setWidget(row, col,
		        	 			 new Label(playerList.get(row-1).getName()));
		        	 	((Label)infoGrid.getWidget(row,
		        	 					col)).setStyleName("currentToken");
	        		}
	        		else
	        		{
	        			//Else just print the name
	        			infoGrid.setWidget(row, col, 
	        					new Label(playerList.get(row-1).getName()));
	        		}
	        	}
	        	//Set the label with the players type
	        	if(col == 1)
	        	{
	        	 infoGrid.setWidget(row,col,
	        	 			new Label(playerList.get(row-1).getType()));
	        	}
	        	//Sets the label with the players cards matched
	        	if(col ==2){
	        		infoGrid.setWidget(row,col,
	        			new Label(Integer.toString(
	        			playerList.get(row-1).getCardsMatched())));
	        	}
	        	//Sets the label with the players rounds played
	        	if(col == 3){
	        		infoGrid.setWidget(row,col,
	        			new Label(Integer.toString(
	        				playerList.get(row-1).getRoundsPlayed())));
	        	}
	       

	         }
	      //End of the outer for loop
	      } 

	  //Creates a button to toggle cheats
	  Button cheater = new Button("Click to Enabled Cheats");
	  
	  //Addss a click handler
	  cheater.addClickHandler(new ClickHandler() {
         
         public void onClick(ClickEvent event) {
         	//When clicked call the enabledCheats() method
         	enableCheats();

         }
      });    



	  //Create a decorator panel and add the infoGrid
	  DecoratorPanel decoratorPanel = new DecoratorPanel();
      decoratorPanel.add(infoGrid);
      
      // Configure the tab panel
      dPanel.add(decoratorPanel, "Game Info");
      dPanel.add(cheater, " Cheat Toggle");
      dPanel.setStyleName("side");
      dPanel.setWidth("100");
      dPanel.selectTab(0);
      // Add the widgets to the root panel
      RootPanel.get("nav").add(dPanel);

	}

	/**
	* Clears the nav section of the root panel
	* and generates a new side panel with
	* updated information.      
*/	
	public static void updateSidePanel()
	{
		RootPanel.get("nav").clear();
		generateSidePanel();
	}


	/**
	* Changes the current player to the next player    
*/	
	public static void changePlayer()
	{
		currentPlayer++;
		//If the current player is greater than number of players
		if(currentPlayer > playerList.size()-1){
			//Set it back to the start
			currentPlayer =0;
		}

		//If the current player is a computer
		if(playerList.get(currentPlayer) instanceof ComputerPlayer){
			//Start a computer turn
			cpuTurn();
		}
		else
		{
			userControl = true;
		}

	}


	/**
	* Sets the cards passed to face down      
	* @param flag True to enable the card , false to disable it  
*/	
	public static void enabled(boolean flag){
		//Gets the toggle button at the position saved in the 
		// 0 index of the clickedCards array and call setEnabled on it.
		((ToggleButton)cardGrid.getWidget(((int)clickedCards.get(0)/6),
			((int)clickedCards.get(0)%6))).setEnabled(flag);
	}


	/**
	* Starts the Game Over state at the end of the game     
*/	
	public static void gameOverState()
	{
		int mostMatches =0;
		String winner = "Spiegel";
		//Loops through all players and finds the one with the most matches
		for(int i=0;i < playerList.size();i++)
		{
			if(mostMatches < playerList.get(i).getCardsMatched())
			{
				mostMatches = playerList.get(i).getCardsMatched();
				winner = playerList.get(i).getName();
			}
		}

		//Pop a window alert showing the winner and how many matches
		Window.alert("The Winner is: " + winner + 
			"\n With " + mostMatches + " Matches");
		//Call reload on the window to reload the page and start a new game
		Window.Location.reload();
	}


	/**
	* Enables the cheat to show the facedown cards    
*/	
	public static void enableCheats()
	{

		 int numRows = cardGrid.getRowCount();
     	 int numColumns = cardGrid.getColumnCount();
     	 if(cheatToggle == true){
     	 	//If the toggle is on 
		     for (int row = 0; row < numRows; row++) {
		        for (int col = 0; col < numColumns; col++) {
		        	if(!board.getCard((row*6)+col).getMatched()) {
		        	//Set the widget to true
		        	((ToggleButton)cardGrid.getWidget(row,col)).setValue(true);
		        	
		        	}
		        }
				}
			cheatToggle = !cheatToggle;
		}
		else
		{
			//If the cheat toggle is off
			for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numColumns; col++) {
	        	if(!board.getCard((row*6)+col).getMatched()) {
	        	//Set the widget to false
	        	((ToggleButton)cardGrid.getWidget(row,col)).setValue(false);
	        	
	        	}
	        	}
			}
			cheatToggle = !cheatToggle;

		}
		

	}

	/**
	* Controls what the cpu does during their turn    
*/	
	public static void cpuTurn(){

		//Sets user control to false
		userControl = false;

		//Checks if the game is over 
		if(matchedCards == 15){
			//Return without looking for more cards
			return;
		}

		//Get the first and second cards to flip
		final int cardOne = getChoiceOne();
		final int cardTwo = getChoiceTwo(cardOne);

		//Record both of these cards in memory	
		recordCard(cardOne);
		recordCard(cardTwo);

		//Create new timer
		Timer timer1 = new Timer(){

			      @Override
			      public void run() {
			      		//Add the cards to the cardList
			      		addCard(cardOne);
	    				addCard(cardTwo);
	    				//Increase number of clicks to two to simulate 
	    				// the computer clicking twice
			      		numClicks = 2;
			      		flipSound();
			      		//Flip the cards up
			        	((ToggleButton)cardGrid.getWidget((cardOne/6)
			        			,(cardOne%6))).setValue(true);
						((ToggleButton)cardGrid.getWidget((cardTwo/6)
								,(cardTwo%6))).setValue(true);
						//Call the main game loop
						mainGameLoop();



			      }
	    };
		
		//Schedule the timer for 2 seconds
		timer1.schedule(2000);		

	}

	/**
	* Gets the first card choice for the cpu
	* @return The index of the first card choice   
*/	
	public static int getChoiceOne()
	{
		//Assign pair the value returned from pairFound
		int pair = ((ComputerPlayer)playerList.get(currentPlayer)).pairFound();
	   
	   //Checks that there are more than 1 pairs left
	   if(matchedCards != 14)
	   {
	   		//Checks that the value of pair is valid
			if(pair != -1 && board.getCard(pair).getMatched() != true){
				//Returns the valid index
				return pair;
			}
			else{
				//Returns a random index that is not matched
				Random rand = new Random();
				int randomNum = rand.nextInt((29 - 0) + 1) + 0;
				while(board.getCard(randomNum).getMatched()){
					randomNum = rand.nextInt((29 - 0) + 1) + 0;
				}
				return randomNum;
			}
		}
		//If there is only one pair left
		else
		{
			int i = 0;
			//Search the board for the unmatched card
				while(board.getCard(i).getMatched() == true)
				{
					i++;
				}
			return i;
			
		}

	}

	/**
	* Gets the second card choice for the cpu.
	* @param cardOne The first card choice.
	* @return The second card choice.    
*/	
	public static int getChoiceTwo(int cardOne){

		//Generate a new randon
		Random rand= new Random();
	    int randomNum = rand.nextInt((29 - 0) + 1) + 0;

	    //Try and get the matching card to cardOne
	    int cardTwo = ((ComputerPlayer)playerList.get(
	    		currentPlayer)).getMatchingCard(cardOne);
	    //Check that there are more than one pairs left
	   if(matchedCards != 14)
	   {
	   		//If the second card is valid
		    if(cardTwo != -1 && board.getCard(cardTwo).getMatched() 
		    				!= true && matchedCards < 15){
		    	//Return it
				return cardTwo;

			}
		    else
		    {	//Return a random index that is not already matched
				while(randomNum == cardOne 
						|| board.getCard(randomNum).getMatched()){
					 randomNum = rand.nextInt((29 - 0) + 1) + 0;
				}
				return randomNum;
			}
		}
		else
			{
				int i =0;
				//Choose the last unmatched card
					while(board.getCard(i).getMatched() == true || i == cardOne)
					{
						i++;
					}
				return i;
			}	
	}

	/**
	* Records the flipped card in the cpus memory
	* @param location The location to add.    
*/		
	public static void recordCard(int location){

		//Generate a new random object
		Random rand= new Random();
	    int randomNum; 

	    for(int i=0; i < playerList.size();i++){
	    	if(playerList.get(i) instanceof ComputerPlayer)
	    	{
	    		//Iterate through the list of computer players
	    		//select a new random number from 1-100
	    		randomNum =rand.nextInt((100 - 1) + 1) + 1;
	    		//If the number is greater than 30 
		    	if(randomNum > 55){
		    		//Add the index and symbol to the memory
		    		((ComputerPlayer)playerList.get(i)).setPair(
		    			location , board.getSymbolByLocation(location));
		    	}
		    }
	    }
	}


/**
	* Starts the background music that plays during gameplay 
*/	
    public static void backgroundMusic(){
    	//Creates an audio object if it is supported by the browser
    	Audio background = Audio.createIfSupported();
    	//Sets the source of the music
    	background.setSrc("Sounds/caineTheme.mp3");
    	//Play the music and set it to loop
    	background.setVolume(.15);
    	background.play();
    	background.setLoop(true);
    }


/**
	* Plays shuffling noise on grid creation   
*/	
    public static void gridSound(){
    	//Creates an audio object if it is supported by the browser
    	Audio gridShuffle = Audio.createIfSupported();
    	//Sets the source of the music
    	gridShuffle.setSrc("Sounds/cardShuffle.wav");
    	//Play the music and set it to loop
    	gridShuffle.setVolume(1.0);
    	gridShuffle.play();
    }

/**
	* Plays card flip sound
*/	
    public static void flipSound(){
    	//Creates an audio object if it is supported by the browser
    	Audio flipSound = Audio.createIfSupported();
    	//Sets the source of the music
    	flipSound.setSrc("Sounds/cardSlide1.wav");
    	//Play the music and set it to loop
    	flipSound.setVolume(1.0);
    	flipSound.play();
    }





}
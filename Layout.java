/**********************************************************/
/*  Author:      Brendan Lilly                            */
/*  Course:      CSC421                                   */
/*  Professor:   Spiegel                                  */
/*  Assignment:  1                                        */
/*  Due Date:    TBD                                      */
/*  Start Date:  2/2/2016                                 */
/*  Filename:    Layout.java                              */
/*  Purpose:     Layout class for the memory card game    */
/*  Time:        Write:1  Hours                           */
/*               Debug:1    Hour                          */
/*               Test:20   min                            */
/**********************************************************/
package com.client;

import java.util.*;
import java.util.Random;

/**
	* This class contains all of the methods to interact with the 
	* layout of the memory card game.   
	* @author Brendan Lilly
*/	
public class Layout
{
	//Initialize card count to 0
	int cardCount = 0;
	//Create a new ArrayList of Cards 
	List<Card> board = new ArrayList<Card>();
/**
	* Default constructor for Layout class . Clears the board.                                  
*/
	public Layout(){
		//Clears the board out	
		board.clear();
	}

/**
	* Adds a Card to the array and then duplicates the card and does it again.       
	* @param card The card to be added to the layout.                                                                
*/
	public void addCard(Card card){
		
		//Add the card to the board array list
		board.add(card);
		//Increment the card count
		cardCount++;
		
		//Make a hard copy of the card
		Card temp = new Card(card.getSymbol(),card.getFaceDown());
		//Add the new card object to the board
		board.add(temp);
		//Increment the card count again
		cardCount++;
	
	}

/**
	* Gets the current card count                                          
	* @return The current number of cards.                        
*/	
	public int getCount()
	{
		return cardCount;
	}

/**
	* Sets the current card count            
	* @param count The number to set cardCount to.                                                            
*/	
	public void setCount(int count)
	{
		cardCount = count;
	}	


/**
	* Shuffles the board to randomize cards  
*/	
    public void shuffleBoard()
    {
    int n = board.size();
    Random random = new Random();
    random.nextInt();
	    for (int i = 0; i < n; i++) {
	      int change = i + random.nextInt(n - i);
	       Card helper = board.get(i);
		   board.set(i, board.get(change));
		   board.set(change, helper);
	    }

    }


/**
	* Returns the card object at a location in the array                              
	* @param location The location in the array.                           
	* @return The Card object.                           
*/
	public Card getCard(int location){
		//Get the object at the location in the array
		// that's passed in and return it.
		return board.get(location);	
	}

/**
	* Gets the symbol at a location in the array.				       
	* @param location The location in the board for the Card.                         
	* @return The symbol found at that location on the board.           
*/
	public String getSymbolByLocation(int location){
		//Get the card object based on location from 
		// the game board and then call getSymbol 
		// on that card and return it.
		return board.get(location).getSymbol();
	}

/**
	* Sets the cards passed to matched       
	* @param cardOne The first card in the match.
	* @param cardTwo The second card in the match.     
*/	
	public void updateMatch(int cardOne, int cardTwo)
	{
		//Gets the card object from the array and sets
		// matched to true.
		board.get(cardOne-1).setMatched(true);
		board.get(cardTwo-1).setMatched(true);
		
	}



}

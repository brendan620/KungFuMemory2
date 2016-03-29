/**********************************************************/
/*  Author:      Brendan Lilly                            */
/*  Course:      CSC421                                   */
/*  Professor:   Spiegel                                  */
/*  Assignment:  2                                        */
/*  Due Date:    3/22/2016                                */
/*  Start Date:  3/14/2016                                */
/*  Filename:    Player.java                              */
/*  Purpose:     Player class for the memory card game    */
/*  Time:        Write: 25 Minutes                        */
/*               Debug: 0 Minutes                         */
/*               Test: 	5 Minutes                         */
/**********************************************************/


package com.client;

/**
	* This class contains all of the methods to interact with the 
	* player in the Memory Card game.   
	* @author Brendan Lilly
*/	
public class Player
{
	//Variable to keep track of the players name 
	String playerName;
	String playerType;
	int roundsPlayed=0;
	int cardsMatched=0;
/**                               
	* Default constructor for Player class   
	* @param name The player's name.                                  
*/	
	public Player(String name, String type){
	    //Assigns the String passed in to the playerName
	    playerName = name;
		playerType = type;
	}

/**
	* Gets the player's name                                                  
	* @return The player's name.                    
*/	
	public String getName(){
		return playerName;
	}

/**	                                   
	* Sets the player's name   			   
	* @param name The player's name.                                
*/
	public void setName(String name){
		playerName = name;
	}

/**
	* Gets the player's type                                                  
	* @return The player's type.                    
*/	
	public String getType(){
		return playerType;
	}

/**	                                   
	* Sets the player's type   			   
	* @param name The player's type.                                
*/
	public void setType(String type){
		playerType = type;
	}


/**                            
	* Gets the number of rounds played                                       
	* @return The number of rounds played.                 
*/
	public int getRoundsPlayed(){
		return roundsPlayed;
	}

/**                         
	* Increments the number of rounds played                                 
*/	
	public void setRoundsPlayed(){
		roundsPlayed++;
	}

/**
	* Gets the number of cards matched                                   
	* @return The number of cards matched.                    
*/	
	public int getCardsMatched(){
		return cardsMatched;
	} 

/**
	* Increments the number of matched cards                              
**/	
	public void setCardsMatched(){
		cardsMatched++;
	}	
	
}
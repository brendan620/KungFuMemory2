/**********************************************************/
/*  Author:      Brendan Lilly                            */
/*  Course:      CSC421                                   */
/*  Professor:   Spiegel                                  */
/*  Assignment:  2                                        */
/*  Due Date:    3/22/2016                                */
/*  Start Date:  2/2/2016                                 */
/*  Filename:    Card.java                                */
/*  Purpose:     Card class for the memory card game      */
/*  Time:        Write:1  Hours                           */
/*               Debug:30 Minutes                         */
/*               Test: 1 Hour                             */
/**********************************************************/

package com.client;

import java.awt.*;
import java.util.*;

/**
	* This class contains all of the methods to interact with the 
	* playing cards.   
	* @author Brendan Lilly
*/	
public class Card
{
	
	//The symbol associated with each card
	public String symbol;
	//Whether the card is faceDown
	public boolean faceDown;
    //Boolean to track if the card is matched 
    public boolean matched;
	
/**	                         
	* Default constructor for Cards class
	* Sets the passed in parameters and sets the matched value to false.
	* @param newSymbol Symbol for the card  
	* @param newFaceDown Orientation of card                                    
*/	
	public Card(String newSymbol,boolean newFaceDown)
	{
		//Assigns the symbol to the card
		symbol=newSymbol;
		//Sets whether the card is face up or down
		//Used originally for testing purposes 
		faceDown=newFaceDown;
		//Matched defaults to false 
		matched=false;
	}

/**                                 
	* Flips the card over to the inverse side.                                  
*/	
	public void flipCard()
	{
		//If the card is face up
		if (faceDown == false)
		{
			//Set the card to face down
			faceDown=true;
		}
		else
		{
			//Set the card to face up
			faceDown = false;
		}
	}

/**                              
	* Returns symbol for the card                                               
	* @return The symbol associated with the Card object                       
*/	
	public String getSymbol(){
			return symbol;
		
	}

/**                                  
	* Set symbol for the card                
	* @param newSymbol The new symbol for the Card                                                          
*/	
	public void setSymbol(String newSymbol){
			symbol = newSymbol;
		
	}

/**                               
	* Returns if the card is face up or down                                        
	* @return True if the card is face down , false if the card is face up.
*/	
	public boolean getFaceDown(){
		return faceDown;
	}

/**                                
	* Determines if the card is matched                                        
	* @return True if the card is matched , false if the card is unmatched.        
*/
    public boolean getMatched(){
		return matched;
    }

/**                          
	* Sets the card as matched               
	* @param newMatch Should always be true.                                                  
*/	
	public void setMatched(boolean newMatch)
	{
		//If a card is matched true is passed 
		//This method is only used if a card has 
		//become matched.
		matched = newMatch;
	}

/**
	* Print function for testing purposes.                                
*/
	public void print(){
		System.out.println("Symbol:" + symbol +
				" FaceDown:" + faceDown);
	}
}
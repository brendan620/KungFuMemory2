/**********************************************************/
/*  Author:      Brendan Lilly                            */
/*  Course:      CSC421                                   */
/*  Professor:   Spiegel                                  */
/*  Assignment:  2                                        */
/*  Due Date:    3/22/2016                                */
/*  Start Date:  3/15/2016                                */
/*  Filename:    ComputerPlayer.java                      */
/*  Purpose:     CPU Player class for the memory card game*/
/*  Time:        Write: 1 Hour                            */
/*               Debug: 45Minutes                         */
/*               Test: 	45 Minutes                        */
/**********************************************************/


package com.client;
import com.google.gwt.user.client.Window;
import java.util.*;

/**
	* This class contains all of the methods to interact with the 
	* cpu player in the Memory Card game.   
	* @author Brendan Lilly
*/	
public class ComputerPlayer extends Player
{
	//Variable to keep track of the CPU's memory 
	List cpuMem = new ArrayList(30);

	/**                               
	* Default constructor for ComputorPlayer class   
	* @param name The player's name.     
	* @param type The type of player ( Human or Computer).                             
*/	
	public ComputerPlayer(String name, String type){
	    //Assigns the String passed in to the playerName
	   super(name,type);

	   //Populate the array with the String " "
	   for (int i = 0; i < 30; i++) {
		    cpuMem.add(" ");
		}

	}

	/**                               
	* Sets matching pair in the CPU's Memory  
	* @param key The index to store the pair.     
	* @param value The symbol of the card.                          
*/	
    public void setPair(int key , String value){
    	
		cpuMem.set(key,value);
    }

	/**                               
	* Gets the index the value is found at. 
	* @param value The symbol to search for.     
	* @return The index if the symbol is found , else -1.                             
*/	
    public int getIndexValue(String value)
    {
    	for(int i =0; i < cpuMem.size(); i++)
    	{
    		if(cpuMem.get(i) == value)
    		{
    			return i;
    		}
    	}
    	return -1;
    }

	/**                               
	* Gets the value at the passed index. 
	* @param key The index in the ArrayList     
	* @return The value found at that index.                         
*/	
	public String getValue(int key)
	{
		return String.valueOf(cpuMem.get(key));
	}

	/**                               
	* Determines if the card is stored in memory.   
	* @param value The symbol of the card     
	* @return True if the card is stored in memory false otherwise.                             
*/	
	public boolean cardFound(String value){
		return cpuMem.contains(value);
	}

	/**                               
	* Removes the two cards passed from memory if they are stored. 
	* @param cardOne The first card to delete.     
	* @param cardTwo The second card to delete.                           
*/	
	public void removeFromMem(int cardOne, int cardTwo)
	{
		if(cpuMem.indexOf(cardOne) != -1){
			cpuMem.remove(cardOne);
		}
		if(cpuMem.indexOf(cardTwo) != -1)
		{
			cpuMem.remove(cardTwo);
		}
	}

	/**                               
	* Checks if there is a pair in the memory array list.  
	* @return If there is a pair the index of the first card , otherwise -1.                            
*/	
	public int pairFound(){
		for(int i = 0; i < cpuMem.size();i++)
		{
			for(int j =1; j < cpuMem.size()-1; j++)
			{
				if(cpuMem.get(i)==cpuMem.get(j) && cpuMem.get(i) != " ")
				{
					return i;
				}
			}
		}
		return -1;
	}

	/**                               
	* Default constructor for ComputorPlayer class   
	* @param cardOne The first card found.     
	* @return The index of the card that matches the first card. If no match 
	* is found -1 is returned.                             
*/	
	public int getMatchingCard(int cardOne){
		for(int i = 0; i < cpuMem.size();i++)
		{
			
			if((cpuMem.get(i)==cpuMem.get(cardOne)) && i != cardOne)
			{
				return i;
			}
			
		}
		return -1;
	}

	
}
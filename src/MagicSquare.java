/*
 * Teena Xiong
 * 11/18/2016
 * 
 * Assignment 3
 * 
 * This program will first display to the user what a Magic Square is. Then it will prompt the user to 
 * enter start for the program to start finding a Magic Square. 
 * The program will go through a series of while loop, which will contain many methods. It will try
 * to find an 3x3 array that is filled with unique numbers from 1-9, in which the sum of the rows, columns,
 * and diagonal are equal. If it is not equal, the while loop continues until it is satisfy. 
 */
import java.util.Scanner; 
public class MagicSquare {
	public static void main (String [] args)
	{
		Scanner input = new Scanner (System.in); 
		
		//prints out the the screen what a magic square is. 
		System.out.println("A Magic Square is a matrix in which all rows, " +
				"\ncolumns, and diagonals, when summing its integer elements, are equal. "
				+ "\nEnter start to begin the search!");
		
		String start = input.next(); //allows user to enter string. 
		start.toLowerCase(); //what user entered is changed to lowercase. 
		
		//this while loop will run until user enters the word start. 
		while(!(start.equals("start")))
		{
			System.out.print("Enter start to being the search."); 
			start = input.next(); 
		}
		
		//creating and initializing the array name magicArray. 
		int [][] magicArray = new int [3][3]; 
		boolean unique = false; //setting variable to false. 
		boolean magic = false; //setting variable to false. 
		
		//this while loop is ran as long as variable magic is not true (it is false). 
		while (!magic)
		{
			//this inner while loop will keep running as long as unique is not true(meaning it is false). 
			while(!unique)
			{ 	int num = 9;  //creating and initializing num to 9
				fillMatrix(magicArray); //calling the fillMatric array with array passed as argument
				unique = isUnique(magicArray, num); //calling isUnique method and return value is stored here
			}
			
			System.out.println(); 
			
			displayMagicSquare(magicArray);//prints out the array here.  
			System.out.println("Sum of Rows: " + sumRows(magicArray)); //calls sumRows method. 
			System.out.println("Sum of Columns: " + sumColumns(magicArray)); //calls sumColumns method
			System.out.println("Sum of Diagonal: " + sumDiagonals(magicArray)); //calls sumDiagonals method
			magic = isMagicSquare(magicArray); //call isMagicSquare method and stored in variable magic
			
			//this if statement is ran if magic is not true(it is false). 
			if(!magic)
			{
				unique = false; //changes unique to false, so that we can refill the new array with new numbers
			}
			else System.out.println("Matrix is a Magic Sqaure"); //the array is a magic square
		}//end of while loop
		
	}//end of main method. 
	
	
	
	//fill the array method. 
	public static void fillMatrix(int [][] array)
	{
		for(int row=0; row<array.length; row++)
		{
			for(int column = 0; column<array[row].length; column++)
			{
				array[row][column] = (int)(Math.random() * 9 + 1); //filling it with random number 1-9
			}
		}
		
	}
	
	
	//testing if each number occurs only 1 time. returning a boolean variable unique.
	//returns false if more than 1 of the same kind of int exist in the array.
	//returns true if only the number occurs just 1 time. 
public static boolean isUnique(int [][] array, int num)
{
	int counter = 0; //will keep tracks of how many times a int appears in the array
	boolean unique = true;  
		
	while(num>0)
	{
		for(int row=0; row<array.length; row++)
		{
			for(int column = 0; column<array[row].length; column++)
			{
				if(num == array[row][column])//if num exist in the array, this is ran. 
				{
					counter++; //increment if there is more than 1 of num in the array
				}
				
				//this is ran if counter is 2 or more. we want to get out of the method
				//since this array is not unique because it contains more than 1 of the same number. 
				if(counter >= 2)
				{
					unique = false; 
					column=array[row].length; //getting out of the inner for loop
					row=array.length-1; //getting out of the outer for loop. 
					num = -1; //getting out of the while loop
					
				}
			}//end of inner for loop
		}//end of outer for loop
		num--; //subtracting the num variable
		counter = 0; //resetting counter to 0 each time
		}
		return unique; 
	}//ends while loop
	

//display the array to the screen. 
public static void displayMagicSquare(int [][] array)
	{
		for(int row = 0; row < array.length; row++)
		{
			for(int column = 0; column < array[row].length; column++)
			{
				System.out.print(array[row][column] + "  ");
			}
			System.out.println(); 
		}
	}


//calculate the sum of each row. takes in an array, and returns a int
public static int sumRows(int [][]array) 
	{
		int sumOfAllElement =0; //setting variable to 0. 
		int average = 0; //setting variable to 0. 
		
		int [] rowCount = new int[3]; //making a new array to store the sum of each row. 
		
		//for loop will calculate the sumOfAllElement, and sum of each row. 
		for(int row=0; row<array.length; row++)
		{
			for(int column = 0; column<array[row].length; column++)
			{
				sumOfAllElement += array[row][column]; //calculates all the elements together. 
				rowCount[row] += array[row][column]; //stores the sum of only 1 row at a time into the array
			}
			
		}//ends for loop
		
		average = sumOfAllElement / array.length; //calculate the average of all element
		int returnValue = average; //stores the average in returnValue. 
		
		//this for loop is ran on the rowCount array. 
		for(int row=0; row<rowCount.length; row++)
		{
			if(average != rowCount[row]) //if each row does not equal to average, this is ran. 
				returnValue = -1; 
		}
		
		return returnValue; 
		
	}//end of sumRow
	

//calculates the sum of each columns. takes an array and return an int
public static int sumColumns(int [][]array)
	{
		//initialize each variable to 0 
		int row = 0; 
		int sumOfElements = 0; 
		int average = 0; 
		int [] columnCount = new int [3]; //new array to store the sum of each column. 
		
		//for loop to calculate the sum of each column. 
		for(int column = 0; column<array[0].length; column++)
		{
			for(row = 0; row<array.length; row++)
			{
				sumOfElements += array[row][column]; //each element is added to sumOfElments
				columnCount[column] += array[row][column];//stored the sum of the column in array columnCount
			}
		}
		
		average = sumOfElements / array[0].length; //average of each column. 
		int returnValue = average; 
		
		//checking to see if the sum of each column, stored in columnCount array, is equal to the average
		for(int index = 0; index<columnCount.length; index++)
		{
			if(average != columnCount[index])
				returnValue = -1; 
		}
		
		return returnValue; 
	}
	
//calculates the sum of the diagonal lines. 
public static int sumDiagonals(int [][]array)
	{
		//initialize each variables. 
		int sumOfElements01 = 0;
		int sumOfElements02 =0; 
		int returnValue = 15; 
		
		//calculates the sum of the first diagonal line. 
		for(int row = 0; row<array.length; row++)
		{
			for(int column =0; column<array[row].length; column++)
			{
				if(row == column)
				{
					sumOfElements01 += array[row][column]; //stores the sum here. 
				}
			}
		}
		
		//calculates the sum of the second diagonal line. 
		sumOfElements02 += array[0][2]; 
		sumOfElements02 += array[array.length - 2][1];
		sumOfElements02 += array[array.length - 1][0];
		
		//if either both of the variable is not equal to 15, this is ran. 
		if(sumOfElements01!=15 || sumOfElements02!=15)
		{
			returnValue = -1; //returns a -1. 
		}
		return returnValue; 
	}
	
//determins if the square is a magic or not. 
public static boolean isMagicSquare(int [][]array)
	{
		boolean magicSqaure = false; //setting the boolean to false. 
		
		//if all the methods returns are equal, this is ran. 
		if((sumRows(array) == sumColumns(array) && sumColumns(array)==sumDiagonals(array)))
		{
			//if all methods return are not -1 but are all 15, this is ran. 
			if(sumRows(array) != -1 && sumColumns(array)!= -1 && sumDiagonals(array)!=-1)
			{
				magicSqaure = true; //sets it to true, because it is a magic sqaure. 
			}
		}
		return magicSqaure; //returns the boolean value. 

	}
}

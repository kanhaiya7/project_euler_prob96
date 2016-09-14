package Sudoku_ass;

import java.util.Scanner;
import java.io.File;

public class SudokuProblem96
{
	static int ttl=0;
	private static Scanner fileScanner;
  public static void main(String[] args) throws Exception
  {	
    fileScanner = new Scanner(new File("C:\\Users\\Kanaiya\\Desktop\\p096_sudoku.txt"));
    int sudoku_cnt=1;
    
    while (fileScanner.hasNextLine()){
    int[][] sudoku = new int[9][9]; 
    //Two dimensional array for storing the array from the given file one by one
    
    fileScanner.nextLine();
	String line = fileScanner.nextLine();
	for (int i = 0; i < 9; i++)
	{
	  for (int j = 0; j < 9; j++)
	  {
        sudoku[i][j] = Character.getNumericValue(line.charAt(j));
        //storing the lines from buffered line into the array of sudoku

	    if (j == 8 && i < 8)
	    	//checking if the size of the array of 9*9 is reached
		{
		  line = fileScanner.nextLine();
		}
	  }
    } 

	
	System.out.println("Sudoku: " + sudoku_cnt + "\n");
	sudoku_cnt++;
	// counter to print the sequential number of sudoku
	
    solve(sudoku, 0, 0);
    //calling method to solve the sudoku
    
    }
    System.out.println("Total of first three numbers from all sudoku: " + ttl);

  } //main


  private static void solve(int[][] sudoku, int cellX, int cellY)
  {
    if(cellY > 8)
    {
      printSudoku(sudoku);
      System.out.println();
    }
    else
    {
      int nxtX = cellX;
      int nxtY = cellY;
      if(cellX == 8)
      {
    	  // If row ends going to start of next row
        nxtX = 0;
        nxtY++;
      }
      else
      {
        nxtX++;
      }

      //moving from the place where given digit is non zero 
      if(sudoku[cellY][cellX] != 0)
      {
        solve(sudoku, nxtX, nxtY);
      }
      else
      {
        //check for all numbers from 1 to 9 if the number is valid
        for(int check = 1; check < 10; check++)
        {
          if(verifySquare(sudoku, cellX, cellY, check)
             && verifyRow(sudoku, cellY, check)
             && verifyCol(sudoku, cellX, check))
        	  //verify square, row and column for duplication
          {
            sudoku[cellY][cellX] = check;
            solve(sudoku, nxtX, nxtY);
          }
        }
        //wrong sudoku or incorrect placing situation
        sudoku[cellY][cellX] = 0;
      }
    }
  }

  ////Boolean function to check the value in the square if already present.
  
  private static boolean verifySquare(int[][] sudoku, int reqX, int reqY, int toCheck)
  {
    int rowY;
    int colX;

    //check which column the "square" belongs to.
    if(reqX < 3)
    {
      colX = 0;
    }
    else if (reqX < 6)
    {
      colX = 3;
    }
    else
    {
      colX = 6;
    }

    //check which row the square belongs to
    if(reqY < 3)
    {
      rowY = 0;
    }
    else if (reqY < 6)
    {
      rowY = 3;
    }
    else
    {
      rowY = 6;
    }


    //checking the square for the value, returning false if present
    for(int i = rowY; i < rowY + 3; i++)
    {
      for(int j = colX; j < colX + 3; j++)
      {
        if(sudoku[i][j] == toCheck)
          {
            return false;
          }
      }
    }

    return true; //returning boolean true for not present in the given square.

  }

  //Boolean function to check the value in the row if already present.
  private static boolean verifyRow(int[][] sudoku, int rowY, int toCheck)
  {
    //checking all the digit in the row
    for(int i = 0; i < 9; i++)
    {
      //is same number present, return false
      if (toCheck == sudoku[rowY][i])
      {
        return false;
      }
    }
    return true; //True if not found.
  }

  //Boolean function to check the value in the column if already present.
  private static boolean verifyCol(int[][] sudoku, int colX, int toCheck)
  {
	//checking all the digit in the column.
    for(int i = 0; i < 9; i++)
    {
      //is same number present, return false
      if (toCheck == sudoku[i][colX])
      {
        return false;
      }
    }
    return true; //true if not found.
  }

  //outputs the sudoku to the java console.
  private static void printSudoku(int sudoku[][])
  {
	 ttl = ttl + ((sudoku[0][0]*100)+(sudoku[0][1]*10)+(sudoku[0][2]*1));
	  

    for(int i = 0; i < 9; i++)
	{
	  for(int j = 0; j < 9; j++)
	  {
	    System.out.print(sudoku[i][j]);

        if(j == 8)
		{
		  System.out.println();
		}
	  }
	} 
  }
} 

	/*Tatyana Marushchak
CS& 141
Instructor: Y. Richardson
Assignment 04 
Write 2 of the following 4 programs:
Page 183 #4
Page 184 #5 and #8, page 186 #5
DUE THURSDAY, FEBRUARY 9*/

/* Page 184 #5: This program runs a method printGrid that accepts two integers representing a number of row and columns
and prints a grid of integers from 1 to (row *columns) in column major order.*/
	
	
public class MarushchakAssignmentFourExerciseFiveChapterThree{
public static void main(String[] args){
	printGrid(4, 6);
}
	 public static void printGrid(int rows, int columns){
		//indicates how many lines to print
      for (int line = 1; line <= rows; line++) {
			//indicates what to print on each line
         for (int i = line; i <= columns * rows; i=i+rows)
            System.out.print(i + " ");
            System.out.println();
        	}
     }
}
/*Tatyana Marushchak
CS& 141
Instructor: Y. Richardson
Assignment 04 
Write 2 of the following 4 programs:
Page 183 #4
Page 184 #5 and #8, page 186 #5
DUE THURSDAY, FEBRUARY 9*/

/* Page 183 #4: This program runs a method printSquare that accepts a minimum and maximum integer and prints square of lines
 of increasing numbers. The first line starts with the minimum, and each line that follows starts with the next-higher number.
 The sequence of numbers on a line wraps back to the minimum after it hits the maximum.*/
	
	
	public class MarushchakAssignmentFourExerciseFourChapterThree{
		public static void main(String[] args){
			printSquare(3, 7);
		}
		public static void printSquare(int min, int max) {
			//tells how many lines to print
			for(int i = 1; i <= max-min+1; i++) {
				//tells 1st line to start with the min and go to max. Each next line starts with the next-higher # and goes to max
				for(int j = 1; j <= max-min+2-i; j++) {
					System.out.print(min+i+j-2);
				}
				//tell the sequence of numbers on a line to wrap back to minimum after it hit the maximum
				for(int j=1; j<i; j++) {
					System.out.print(min+j-1);
				}
				System.out.println();
			}
		}
  }
	/*Tatyana Marushchak
CS& 141
Instructor: Y. Richardson
Assignment 03: Figure on Stairs
Chapter 2, page 128, Exercise 4
Due Date 01/31/2012*/
	
public class MarushchakTatyanaAssignmentThreeExerciseFourFigureOnStairs{
	public static final int SUB_HEIGHT = 5;
	public static void main(String[] args){
   	drawMan();
		System.out.println();
	}

	public static void drawMan(){
		for (int line = SUB_HEIGHT; line >= 1; line--){
			//draw space before head, head, space after head and star
			for (int i = 1; i <=line-1; i++){
				System.out.print("     ");
			}
			System.out.print("  o  ******");
			for (int i = 1; i <= SUB_HEIGHT-line; i++){	 
				System.out.print("     ");					
         }
		   System.out.println("*");
			//draw space before body, body, space after body and star
	      for (int i = 1; i <=line-1; i++){  	    
				System.out.print("     ");
	      }
			System.out.print(" /|\\ *");
			for (int i = 1; i <= SUB_HEIGHT-line+1; i++){	 
				System.out.print("     ");					
         }
			System.out.println("*");
			//draw space before legs, legs, space after legs and star
         for (int i = 1; i <=line-1; i++){      
				System.out.print("     ");
         }
			System.out.print(" / \\ *");
			for (int i = 1; i <= SUB_HEIGHT-line+1; i++){	 
				System.out.print("     ");					
         }
			System.out.println("*");
		}
		//draw the rest of the stars
		System.out.print("**");
			for (int i = 1; i <= SUB_HEIGHT+1; i++){	
				System.out.print("*****");
			}
	}
}


									


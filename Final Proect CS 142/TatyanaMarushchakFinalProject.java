/*Tatyana Marushchak
CS& 141
Instructor: Y. Richardson
Final Project 
DUE FRIDAY, MARCH 16
*/
/* This program asks the user to type the name of the existing input file containing named sequences of nucleotides. 
The input file is analyzed and information about each nucleotide sequence is displayed. For each nucleotide sequence, 
the program counts the occurrences of each of the four nucleotides (A, C, G, and T). The program also computes the mass 
percentage occupied by each nucleotide, rounded to one digit past the decimal point. Next the program reports the codons
(trios of nucleotides) present in each sequence and predicts whether or not the sequence is a protein-coding gene.
 For us, a protein-coding gene is a string that matches all of the following constraints*:
 begins with a valid start codon (ATG)
 ends with a valid stop codon (one of the following: TAA, TAG, or TGA)
 contains at least 5 total codons (including its initial start codon and final stop codon)
 Cytosine (C) and Guanine (G) combined account for at least 30% of its total mass
*/

   import java.io.*;
   import java.util.Arrays;
   import java.util.Scanner;

   public class TatyanaMarushchakFinalProject {
      static final int MINIMUM_CODONS = 5; // The minimum number of codons to be considered a protein.
      static final int PERCENTAGE_OF_G_AND_C = 30; // The percentage of mass from C and G.
      static final int UNIQUE_NUCLEOTIDES = 4; // The number of unique nucleotides (A, C, G, and T)
      static final int NUCLEOTIDES_PER_CODON = 3; // The number of nucleotides per codon.
      static boolean checkValidity = true; // To check if it's a valid sequences of nucleotides.
      static double [] masses = {135.128, 111.103, 151.128, 125.107, 100.000};//masses of A, C, G, T and Junk (-)
      static double totalMass = 0;// initialize and declare mass of the nucleotide sequence
      public static void main(String[] args)throws FileNotFoundException {
         introduction();//gives introduction to the program
         Scanner console = new Scanner(System.in); // scanner for user input
         Scanner input = getInput(console); // scanner for the input file 
         System.out.print  ("Output file name: "); // user is prompted to enter output file name
         String outputFileName = console.next();//set file name
         PrintStream output = new PrintStream(new File(outputFileName));
         while(input.hasNextLine()){//indicates what to do with each line in the input file
            checkValidity = true;
            String Line1 = input.nextLine();//initialize and declares name of the nucleotide sequence
            String Line2 = input.nextLine().toUpperCase();//all nucleotides are capitalized
            outputToConsole(Line1, Line2, counts(Line2), masses(Line2), codonsList(Line2));
            outputToFile(Line1, Line2, counts(Line2), masses(Line2), codonsList(Line2), outputFileName , output);
         }//end while loop
      }//end main method
      
      //gives introduction to the program    
      public static void introduction() {
         System.out.println("This program reports information about DNA");
         System.out.println("nucleotide sequences that may encode proteins.");
         System.out.println();
      }//close introduction method
   
    
      //counts each nucleotide in the sequence
      public static int [] counts(String line){
         int [] nucleotides = new int[UNIQUE_NUCLEOTIDES];//declares array for nucleotides count
         int Junk = 0;//initialize and declare Junk(-) count in the sequence
         for(int i = 0; i < line.length(); i++){//counts each nucleotide in the sequence, Junk is ignored
            if (line.charAt(i)=='A'){
               nucleotides[0]++;
            }
            else if (line.charAt(i)=='C'){
               nucleotides[1]++;
            }
            else if (line.charAt(i)=='G'){
               nucleotides[2]++;
            }
            else if (line.charAt(i)=='T'){
               nucleotides[3]++;
            }
            else if (line.charAt(i)=='-'){
               Junk++;
            }
           else checkValidity = false; // indicates to ignore characters that are not A, C, G, T or Junk(-)
         }//end for loop
         //total Mass of the nucleotide sequence is calculated   
         totalMass = Math.round((nucleotides[0]*masses[0] + nucleotides[1]*masses[1]+ nucleotides[2]*masses[2] + nucleotides[3]*masses[3] +Junk*masses[4])*10.0)/10.0;
         return nucleotides;
      }//end count method
       
      //calculate mass percentage of each nucleotide 
      public static double [] masses(String line){
         int[] count = counts(line);//put counts of nucleotide into an array
         double [] nucleotides = new double [UNIQUE_NUCLEOTIDES];//puts mass percentage into an array
         for (int i= 0; i<nucleotides.length; i++){
            nucleotides[i] = Math.round((count[i]*masses[i]/totalMass)*1000.0)/10.0;
         }//end for loop
         return nucleotides;
      }//end of masses method
      
      // codons of the sequence are displayed, Junk(-) is removed from the sequence
      public static String [] codonsList(String line){
         String deleteJunk = line.replaceAll("-","");// Junk(-) is removed. Sequence that contains only A,G,C and T is formed
         String [] nucleotides = new String[deleteJunk.length()/NUCLEOTIDES_PER_CODON];//array to display codons
         for (int i = 0, j = 0; i+NUCLEOTIDES_PER_CODON <= deleteJunk.length() && j<=deleteJunk.length()/NUCLEOTIDES_PER_CODON; i+=NUCLEOTIDES_PER_CODON, j++){
            nucleotides[j] = deleteJunk.substring(i, i+NUCLEOTIDES_PER_CODON);
         }//end forloop
         return nucleotides;  
      }//end codonsList method
      

      //determine if the nucleotide sequence is the protein or not  
      public static boolean check(String Nucleotides, int []Counts, String [] condonsList){
         if (checkValidity){//checks if the sequence contains at least 5 codons
            checkValidity = ((Nucleotides.replaceAll("-","").length()/NUCLEOTIDES_PER_CODON)>=MINIMUM_CODONS);
         }
         if (checkValidity){//checks if C and G accounts for at least 30%
            checkValidity = ((Counts[1]*masses[1] + Counts[2]*masses[2]) / totalMass)>=(PERCENTAGE_OF_G_AND_C/100.0);
         }
         if (checkValidity){//checks if sequence begins with the start codon ATG
            checkValidity = (condonsList[0].equals("ATG"));
         }
         if (checkValidity){//checks if the sequence ends with a valid stop codon (TAA, TAG or TGA)
            checkValidity = (condonsList[condonsList.length-1].equals("TAA")||condonsList[condonsList.length-1].equals("TAG")||condonsList[condonsList.length-1].equals("TGA"));
         }   
         return checkValidity;
      }//end check method
		
		//console output of the processed file
      public static void outputToConsole(String RegionName, String Nucleotides, int []Counts, double [] totalMass2, String [] codonsList){
         System.out.println("Region Name: " + RegionName);
         System.out.println("Nucleotides: " + Nucleotides);
         System.out.println("Nuc. Counts: " + Arrays.toString(Counts));
         System.out.println("Total Mass%: " + Arrays.toString(totalMass2)+ " of " + totalMass);
         System.out.println("Codons List: " + Arrays.toString(codonsList));
         System.out.println("Is Protein?: " + ((check(Nucleotides, Counts, codonsList))?"YES\n":"NO\n"));
      }
		
		//processed information goes to the output File    
      public static void outputToFile(String RegionName, String Nucleotides, int []Counts, double [] totalMass2, String [] condonsList, String outputFileName, PrintStream output) {
        output.println("Region Name: " + RegionName);
        output.println("Nucleotides: " + Nucleotides);
        output.println("Nuc. Counts: " + Arrays.toString(Counts));
        output.println("Total Mass%: " + Arrays.toString(totalMass2)+" of "+ totalMass);
        output.println("Codons List: " + Arrays.toString(condonsList));
        output.println("Is Protein?: " + ((check(Nucleotides, Counts, condonsList))?"YES":"NO"));
        output.println();
      }//end outputFile method
       
            
      //method that makes sure that the input file exists
      public static Scanner getInput(Scanner console) throws FileNotFoundException {
         System.out.print("Input File name? ");
        File f = new File(console.nextLine());
        while (!f.canRead()) {
            System.out.println("File not found. Try again");
            System.out.print("Input File name? ");
            f = new File(console.nextLine());
         } // end while
      // at this point the file f has been instantiated
         return new Scanner(f);
      }  // end getInput 
   }//end class



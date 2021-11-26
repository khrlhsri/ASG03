import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class deletingfile {

	public void getdeletefile() {
		
		Scanner input = new Scanner(System.in);
		String filename;
		
		System.out.println("\n> Delete file");
		System.out.print("\nEnter File name + location to delete file: "); filename = input.nextLine();
//		C:\Users\67372\Documents\PB Sem 3\OOP\ASG03\Clothing2.txt
		
		boolean loop = false;
		
		while(true) {
			
		File deletefile = new File(filename);
		
		
		if (deletefile.exists()) {
			System.out.print("\nAre you sure you want to delete this file? (y/n): "); String yesno = input.next();
			
			switch (yesno){
				case "y": 
					//delete file
					if (deletefile.delete()) {
						System.out.println("\n=======================================================================================");
						System.out.println("\tSuccesfully delete file: " + deletefile.getName());
						System.out.println("=======================================================================================");
					} else {
						System.out.println("\n\tFailed to delete file: " + deletefile.getName());
					}
					break;
				case "n": break;
			}
			break;
		}	
		else if (!deletefile.exists()){
			System.out.println("\n=======================================================================================");
			System.out.println("\tFailed to delete file \"" + deletefile.getName() + "\". File is not exist");
			System.out.println("=======================================================================================");
			System.out.print("\nRe-enter File name + location to delete file: "); filename = input.nextLine();
		}
		}
	}		
}


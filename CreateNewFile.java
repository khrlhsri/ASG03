import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class CreateNewFile {
	public void creatingfile() {
		
		Scanner input = new Scanner(System.in);
		String newfilename, location, usernewfile;
		DateFormat sdf = new SimpleDateFormat("dd MMMM yyyy hh:mm a");
		Pattern filepattern = Pattern.compile("^<Clothing>.*</Clothing>$");
		StringBuilder Content = new StringBuilder();
		boolean loop = false;
		
		System.out.println("\n==================");
		System.out.println("> Create New File");
		System.out.println("==================");
		System.out.print("\nEnter file name: "); newfilename = input.nextLine();
		System.out.print("Where do you want to save this file?: "); location = input.nextLine(); 
		//C:\Users\67372\Documents\PB Sem 3\OOP\ASG03\
		
					
		usernewfile = location + newfilename + ".txt";
		File newfile = new File(usernewfile);
				
		if (!newfile.exists()) {
			System.out.println("\n=======================================================================================");
			System.out.println("\tFile created successfully.");
			System.out.println("\n\tFile created: " + newfile.getName());
			System.out.println("\tFile location: " + newfile.getAbsolutePath());
							
			PrintWriter writefile;
			try {
				writefile = new PrintWriter(new FileWriter(newfile));
				writefile.println("<Clothing></Clothing>");
				Content = new StringBuilder("<Clothing></Clothing>");
				writefile.close();
							
				Path path = newfile.toPath();
				BasicFileAttributes file_att = null;
				try {
					file_att = Files.readAttributes(path, BasicFileAttributes.class);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					file_att.creationTime();
					System.out.println("\tDate Creation: " + sdf.format(file_att.creationTime().toMillis()));
				}
				System.out.println("=======================================================================================");
				loop = true;
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {			
			Path path = newfile.toPath();
			BasicFileAttributes file_att = null;
			try {
				file_att = Files.readAttributes(path, BasicFileAttributes.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				file_att.creationTime();
				System.out.println("\n=======================================================================================");
				System.out.println("\tFile Already exist.");
				System.out.println("\n\tFile created: " + newfile.getName());
				System.out.println("\tFile location: " + newfile.getAbsolutePath());
				System.out.println("\tDate Creation: " + sdf.format(file_att.creationTime().toMillis()));
				System.out.println("=======================================================================================");
			}
		}
	}
}

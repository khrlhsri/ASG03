import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class openfileoption {
	
	OpenExistingFile filename;
	StringBuilder Content = new StringBuilder("<Clothing></Clothing>");
	Scanner input = new Scanner(System.in);
	Pattern clothingDataPattern = Pattern.compile("<Clothes><ID>(.+?)</ID><Name>(.+?)</Name><Category>(.+?)</Category><Colour>(.+?)</Colour><Quantity>(.+?)</Quantity><Price>(.+?)</Price></Clothes>");
	
	Pattern contentfile = Pattern.compile("^<Clothing>.*<Clothing>$");
	
	public static String ClothesID,ClothesName,ClothesCategory,ClothesColour,ClothesQty,ClothesPrice;
	
	public void getdisplaydata(){
		
		File openfile = new File(filename.filename);
		StringBuilder fileContent1 = new StringBuilder();
		
		System.out.println();
		System.out.println("> Display data\n");
		Scanner displaydata = null;
		if(openfile.length() != 0) {
			try {
				displaydata = new Scanner (openfile);
			
				while(displaydata.hasNextLine()) {
				fileContent1.append("\t" + displaydata.nextLine() + "\n");
				}
				System.out.println("=======================================================================================");
				System.out.println("\n\t" + openfile.getName() + "\n");
				System.out.println(fileContent1);
				System.out.println("=======================================================================================");
			} catch (FileNotFoundException e) {
				System.out.println("\n\tNo data");;
			} finally {
				if (displaydata != null) {
					displaydata.close();
				}
			}
		} else {
			System.out.println("\tFile is empty");
		}
	}
	
	public void getaddnewdata(){
		
		File file = new File(filename.filename);
		
		Scanner scanfilecontent1 = null;
		Scanner scanfilecontent = null;
		
		StringBuilder filecontent = new StringBuilder();
		StringBuilder oldfile = new StringBuilder();
		
		String oldContent = "";
		
		try {
			scanfilecontent1 = new Scanner(file);
			while (scanfilecontent1.hasNextLine()){
				filecontent.append(scanfilecontent1.nextLine());
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			System.out.println("\n\tFailed to read data");
			e2.printStackTrace();
		} finally {
			scanfilecontent1.close();
		}
		
		try {
			scanfilecontent = new Scanner (file);
			while (scanfilecontent.hasNextLine()) {
				oldfile.append(scanfilecontent.nextLine());
			}
			
			Pattern oldfilepattern = Pattern.compile("^<Clothing>.*</Clothing>$");
			Matcher datamatcher2 = oldfilepattern.matcher(oldfile);
			
			if (datamatcher2.matches()) {
				Pattern olddatapattern = Pattern.compile("<Clothes>" + 
															"<ID>(.*?)</ID>" +
															"<Name>(.*?)</Name>" +
															"<Category>(.*?)</Category>" +
															"<Colour>(.*?)</Colour>" +
															"<Quantity>(.*?)</Quantity>" +
															"<Price>(.*?)</Price>" +
														 "</Clothes>"
															);
				Matcher datamatcher3 = olddatapattern.matcher(oldfile);
				int count = 0;
				
				while (datamatcher3.find()) {
					count++;
					System.out.println("data maatch");
					System.out.println("Group\n"+ datamatcher3.group().toString());
					oldContent = datamatcher3.group();
				}
				
				System.out.println("\n\t> Add new data");
				System.out.print("\nEnter Clothes ID: "); ClothesID = input.next();
				System.out.print("Enter Clothes Name: "); ClothesName = input.next();
				System.out.print("Enter Clothes Category: "); ClothesCategory = input.next();
				System.out.print("Enter Clothes Colour: "); ClothesColour = input.next();
				System.out.print("Enter Clothes Quantity: "); ClothesQty = input.next();
				System.out.print("Enter Clothes Price: "); ClothesPrice = input.next();
				
				System.out.println("\n======================================================");
				System.out.println("\tData has been inserted into " + file.getName() + ".");
				System.out.println("======================================================");
				
				Matcher dataMatcher = clothingDataPattern.matcher(Content);
				
				int dataStart = 0;
				int dataEnd = 0;
				while(dataMatcher.find()) {
					if(dataMatcher.group(1).equalsIgnoreCase(ClothesID)) {
						if(dataMatcher.group(2).equalsIgnoreCase(ClothesName)) {
							if(dataMatcher.group(3).equalsIgnoreCase(ClothesCategory)) {
								if(dataMatcher.group(4).equalsIgnoreCase(ClothesColour)) {
									if(dataMatcher.group(5).equalsIgnoreCase(ClothesQty)) {
										if(dataMatcher.group(6).equalsIgnoreCase(ClothesPrice)) {
											dataStart = dataMatcher.start();
											dataEnd = dataMatcher.end();
											break;
										}
									}
								}
							}
						}
					}
				}
				StringBuilder oldclothes = new StringBuilder();
				oldclothes.append(oldContent);
				
				Content.insert(Content.lastIndexOf("</Clothing>"), oldclothes);
				
				PrintWriter writeoldfile = null;
				try {
					writeoldfile = new PrintWriter(file);
					writeoldfile.println(Content);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("\tFailed to add data.");
				} finally {
					writeoldfile.close();
				}
				
				StringBuilder newClothes = new StringBuilder();	
				newClothes.append("\n\t<Clothes>");
					newClothes.append("\n\t<ID>"); newClothes.append(ClothesID); newClothes.append("</ID>");
					newClothes.append("\n\t<Name>"); newClothes.append(ClothesName); newClothes.append("</Name>");
					newClothes.append("\n\t<Category>"); newClothes.append(ClothesCategory); newClothes.append("</Category>");
					newClothes.append("\n\t<Colour>"); newClothes.append(ClothesColour); newClothes.append("</Colour>");
					newClothes.append("\n\t<Quantity>"); newClothes.append(ClothesQty); newClothes.append("</Quantity>");
					newClothes.append("\n\t<Price>"); newClothes.append(ClothesPrice); newClothes.append("</Price>");
				newClothes.append("\n\t</Clothes>\n");
				
				Content.insert(Content.lastIndexOf("</Clothing>"), newClothes);
				
				FileWriter writefile = null;
				try {
					writefile = new FileWriter(file);
					writefile.write(Content.toString());
					writefile.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("\tFailed to add data.");
				}
			} else {
				System.out.println("\nFormatting is invalid");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("\n\tFailed to read file");
		} finally {
			scanfilecontent.close();
		}
	}
}

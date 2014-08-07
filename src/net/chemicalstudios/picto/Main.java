package net.chemicalstudios.picto;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {
	private static BufferedImage input = null;
	private static Scanner keyboard = new Scanner(System.in);
	private static int greenI = 1, blueI = 2, redI = 3;
	private static int greenRGB = new Color(0, 0, 255).getRGB(), blueRGB = new Color(255, 255, 255).getRGB(), redRGB = new Color(255, 0, 0).getRGB();
	
	private static String fileName = null;
	private static String fileNameWithoutExtension = null;
	private static boolean patternMode = false;
	private static Random rng = new Random();
	
	private static int amount = 0;
	
	public static void main(String[] args) {
		
		
		System.out.println("@FileName - example.txt\n"x
				+ "@patternMode - true | false\n"
				+ "@amount - #\n"
				+ "Input format: FileName,patternMode,amount");
		String fileArgs[] = keyboard.nextLine().split(",");
		
		fileName = fileArgs[0];
		fileNameWithoutExtension = (String) fileName.subSequence(0, fileName.indexOf("."));
		patternMode = fileArgs[1].equals("true") ? true : false;
		amount = (int) Integer.parseInt(fileArgs[2]);
		
		
		if (amount == 0) {
			System.out.println("Done.");
		} else {
			for (int i = 0; i < amount; i++) {
				if (patternMode) {
					patternColors();
				}
				toPic();
			}
		}
		keyboard.close();
	}
	
	private static void toPic() {
		try {
			int length = 0, height = 0;
			// Read text file inputted by user
			keyboard = new Scanner(new File(fileName));
			
			// first two lines are dimensions
			length = keyboard.nextInt();
			height = keyboard.nextInt();
			
			// Create the new image and convert the text into each pixel
			input = new BufferedImage(length, height, BufferedImage.TYPE_INT_RGB);
			
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < length; x++) {
					
					int inText = (keyboard.hasNext()) ? keyboard.nextInt() : 0;
					
					if (inText == greenI) {
						input.setRGB(x, y, greenRGB);
					} else if (inText == blueI) {
						input.setRGB(x, y, blueRGB);
					} else if (inText == redI) {
						input.setRGB(x, y, redRGB);
					} else {
						input.setRGB(x, y, 0);
					}
				}
			}
			
			File outputFile = new File(fileNameWithoutExtension + ".png");
			int fileNum = 0;
			while (outputFile.exists()) {
				fileNum++;
				fileNameWithoutExtension = (String) fileName.subSequence(0, fileName.indexOf(".")) + "_" + fileNum;
				outputFile = new File(fileNameWithoutExtension + ".png");
			}
			
			// Save image to disk
			ImageIO.write(input, "png", outputFile);
			System.out.println("Created file: " + outputFile.getName() + " @ " + outputFile.getAbsolutePath());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void patternColors() {
		System.out.println(redRGB + ", " + greenRGB + ", " + blueRGB);
		redRGB = rng.nextInt() * -16776961;
		greenRGB = rng.nextInt() * -16776961;
		blueRGB = rng.nextInt() * -16776961;
	}
}

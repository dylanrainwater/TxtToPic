package net.chemicalstudios.picto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {
	
	private static BufferedImage input = null;
	private static URL inputDir = null;
	private static boolean ioMode = false; // Mode of user interaction -- true = user puts in text, false = user inputs in image
	private static Scanner keyboard = new Scanner(System.in);
	private static String grassO = "#", waterO = "~", flowerO = "+";
	private static int grassI = 1, waterI = 2, flowerI = 3;
	private static int grassRGB = -16711915, waterRGB = -13264129, flowerRGB = -10240;
	
	public static void main(String[] args) {
		System.out.println("IOMode?");
		ioMode = keyboard.nextBoolean();
		
		if (ioMode) {
			try {
				int length = 0, height = 0;
				keyboard = new Scanner(new File("map2.txt"));
				
				length = keyboard.nextInt();
				height = keyboard.nextInt();
				System.out.println(length + "x" + height);
				input = new BufferedImage(length, height, BufferedImage.TYPE_INT_RGB);
				
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < length; x++) {
						
						int inText = (keyboard.hasNext()) ? keyboard.nextInt() : 0;
						
						System.out.println(x + ", " + y + ": " + inText);
						if (inText == grassI) {
							input.setRGB(x, y, grassRGB);
						} else if (inText == waterI) {
							input.setRGB(x, y, waterRGB);
						} else if (inText == flowerI) {
							input.setRGB(x, y, flowerRGB);
						} else {
							input.setRGB(x, y, 0);
						}
					}
				}
				
				File outputFile = new File("map2.png");
				ImageIO.write(input, "png", outputFile);
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			try {
				input = ImageIO.read(new File("map1.png"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (input != null) {
			for (int y = 0; y < input.getHeight(); y++) {
				System.out.println();
				for (int x = 0; x < input.getWidth(); x++) {
					if (input.getRGB(x, y) == grassRGB) {
						System.out.print(grassO);
					} else if (input.getRGB(x, y) == waterRGB) {
						System.out.print(waterO);
					} else if (input.getRGB(x, y) == flowerRGB) {
						System.out.print(flowerO);
					} else {
						System.out.print(0);
					}
				}
			}
			
		} else {
			System.out.println("Image was null.");
		}
		keyboard.close();
	}
}

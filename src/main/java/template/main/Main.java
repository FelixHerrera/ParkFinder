/*
 * Filename: Main.java
 * Description: This file contains our main which will run the application
 */
package template.main;

public class Main {
	public static void main(String[] args) {
		System.setProperty("environment", "dev");
		Application.run(args);
	}
}

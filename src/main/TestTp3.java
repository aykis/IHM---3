package main;

import java.awt.Dimension;

import model.ImageLibrary;

public class TestTp3 {
	public static void main(String args[]) {

		/*
		 * On instancie le modele de l'application de facon independante dans le main.
		 */
		ImageLibrary imglib = new ImageLibrary();

		/*
		 * On instancie l'interface en lui passant en parametre les donnees du modele.
		 */
		IhmTp3 f = new IhmTp3(imglib);
		
		f.setLocation(80, 50);
		f.setPreferredSize(new Dimension(800, 700));
		f.pack();
		f.setVisible(true);
	}
}

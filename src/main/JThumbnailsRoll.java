package main;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ImageLibrary;

public class JThumbnailsRoll extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JThumbnail> thumbnails;
	
	public JThumbnailsRoll(ImageLibrary model) {
		super(new FlowLayout(FlowLayout.LEFT, 10, 10));
        super.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        
        thumbnails = new ArrayList<JThumbnail>();
		for(int i=0; i<model.getSize(); i++) {
			thumbnails.add(new JThumbnail(model.getImage(i)));
			this.add(thumbnails.get(thumbnails.size()-1));
		}
	}
	
}

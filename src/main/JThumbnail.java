package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.IconView;

public class JThumbnail extends JPanel{

	private JLabel image;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JThumbnail(ImageIcon image) {
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        //super.setPreferredSize(new Dimension(40, 30));
        super.setBorder(BorderFactory.createBevelBorder(1));
        
        this.image = new JLabel(image);
        this.add(this.image, BorderLayout.CENTER);
	}

	public void add(JLabel image, BorderLayout layout) {
		this.image = image;
        super.add(this.image, layout);
	}
	
	
	

}

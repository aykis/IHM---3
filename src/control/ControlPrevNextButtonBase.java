package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.ImageLibrary;

public class ControlPrevNextButtonBase implements ActionListener {
    private ImageLibrary model;
    private JLabel labelImage;
    private JLabel labelImageName;
    private JButton btnPrevious;
    private JButton btnNext;

    public ControlPrevNextButtonBase(ImageLibrary model) {
        this.model = model;
    }

    public void connectToImageCentre(JLabel imageCentre) {
        this.labelImage = imageCentre;
    }

    public void connectToImageName(JLabel labelImageName) {
        this.labelImageName = labelImageName;
    }

    public void connectToButtonPrevious(JButton btnPrevious) {
        this.btnPrevious = btnPrevious;
    }
    
    public void connectToButtonNext(JButton btnNext) {
        this.btnNext = btnNext;
    }

    
	@Override
	 public void actionPerformed(ActionEvent e) {
       int idx = model.getCurrentIndex();
       if (e.getSource() == btnPrevious) {
           idx= idx-1;
       }
       else if (e.getSource() == btnNext) {
           idx= idx+1;
       }
       model.setCurrentIndex(idx);
       
       int nouvelindex = model.getCurrentIndex();
       labelImage.setIcon(model.getImage(idx));
       labelImageName.setText("<html><h4>" + model.getImageName(nouvelindex) + "</h4></html>");
       btnPrevious.setEnabled(nouvelindex > 0);
       btnNext.setEnabled(nouvelindex < model.getSize() - 1);
      
   }

	
	

}


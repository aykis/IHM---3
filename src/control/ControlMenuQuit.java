package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenuQuit implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}

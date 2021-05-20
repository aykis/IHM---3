package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.ControlMenuQuit;
import control.ControlPrevNextButtonBase;
import model.ImageLibrary;

/**
 * squelette TP3 IHM FISE A1 : bibliotheque d images
 * 
 * @author Cedric Dumas
 * @version 2.0
 * @date 17 avril 2007
 * @revision 29 mai 2020
 */


public class IhmTp3 extends JFrame {

    private ImageLibrary model;
   
    /*
     * on déclare en données membres les composants importants de l'interface 
     * qui vont servir à plusieurs endroits
     */

    private JPanel imagePanel; // panel du centre
    private JLabel centerImage; // image du centre
    private JLabel imageName; // titre de l'image
    private JButton previousButton; // bouton pour passer à l'image précédente
    private JButton nextButton; // bouton pour passer à l'image suivante

    /*
     * on déclare les controleurs (Listener)
     */
    private ControlPrevNextButtonBase btnPrevNextControl;
    private ControlMenuQuit quitMenuControl;
    
    public IhmTp3(ImageLibrary imglib) {

        // on conserve la reference du modele de bibliotheque d Images
        this.model = imglib;

        // parametrage de la fenetre principale : la JFrame est organisee en BorderLayout
        this.setTitle("IHM TP3");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         *  on structure le code en appelant des méthodes qui permettent de clarifier la
         *  construction de l'interface dont le code devient vite assez imposant sinon (pres de 300 lignes)
         */
        this.buildPanelNorth();
        this.buildPanelCenter();
        this.buildPanelEast();
        this.buildPanelWest();
        this.buildPanelSouth();
        this.buildMenu();

        /* 
         *  les Controleurs modifiant différents widgets de l'interface (par exemple le bouton precedent modifie l'image courante imageCentre)
         *  on doit indiquer à chaque controleur l'instance des objets qu'il modifie
         *  on le fait ici à la fin du constructeur quand tous les composantsde l'interface sont instanciés afin d'éviter les pointeurs null ! (quand des bjets passés en paramètre à un constructeur bien que n'étant pas encore initialisé)
         *  au TP2, nous avons passer ces différentes instances au constructeur du controleur directement, cependant avec les composants qui se multiplient, le probleme de l'ordre de création se pose !
         *  pour simplifier, on ajoute ici (à la fin du constructeur) à chaque controleur les composants dont il a besoin, afin d'être sur que tout est déjà initialisé !
         */
       
        // le bouton image précédente modifie l'image centrale, le nom de l'image et active eventuellement l'autre bouton 
        // idem pour le bouton image suivante
        // comme dans la question 6 du TP 2, on utilise un même controleur pour les deux boutons, dont le comportement est similaire, pour éviter de dupliquer du code        
        this.btnPrevNextControl.connectToImageCentre(centerImage);
        this.btnPrevNextControl.connectToImageName(imageName);
        this.btnPrevNextControl.connectToButtonNext(nextButton);
        this.btnPrevNextControl.connectToButtonPrevious(previousButton);
        
        // question 4 : faire de même pour les autres controleurs (quand nécessaire)

    }

    private void buildPanelSouth() {
        // question 1 :  Bandeau des images dans la partie basse
        
    }
    
    private void buildPanelEast() {
        // question 2 : JSlider qui permet de redimensionner chaque image

    }

    private void buildPanelWest() {
        // question 3 : Données de la JList
        

    }

    private void buildMenu() {
    	JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem quitItem = new JMenuItem("Quitter");		
		menu.add(quitItem);
		// Controleur pour quitter l'application
		quitMenuControl = new ControlMenuQuit();
		quitItem.addActionListener(quitMenuControl);
		
		// question 5 : ajouter une image
		
		// quesiton 6 : changer le nom de l'image courante
		
		
    }

    private void buildPanelCenter() {
        // JPanel central contenant le JLabel (et donc l image)
        this.imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.imagePanel.setPreferredSize(new Dimension(400, 300));
        this.imagePanel.setBorder(BorderFactory.createBevelBorder(1));
        this.add(this.imagePanel, BorderLayout.CENTER);
        
        // on y ajoute le JLabel qui contient les images
        this.centerImage = new JLabel(this.model.getCurrentImage());
        this.imagePanel.add(this.centerImage);

    }
    void buildPanelNorth() {
        // Panel du haut avec boutons suivant/précédent et titre
        JPanel panelUp = new JPanel();
        panelUp.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        this.add(panelUp, BorderLayout.NORTH);

        // bouton image précédente
        this.previousButton = new JButton("< Image Précédente");
        this.previousButton.setEnabled(false);       
        this.btnPrevNextControl = new ControlPrevNextButtonBase(this.model);
        this.previousButton.addActionListener(this.btnPrevNextControl);
        panelUp.add(this.previousButton);

        // titre
        this.imageName = new JLabel("<html><h4>" + this.model.getCurrentImageName() + "</h4></html>", SwingConstants.CENTER);
        panelUp.setAlignmentX(0.7f);
        panelUp.add(this.imageName);
        
        // bouton image suivante
        this.nextButton = new JButton("Image suivante >");
        this.nextButton.addActionListener(this.btnPrevNextControl);
        panelUp.add(this.nextButton);
    }

}
package model;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Observable;

import javax.swing.ImageIcon;


/**
 * TP IHM : bibliotheque d images
 * 
 * @author Cedric Dumas
 * @version 2.0
 * @date 17 avril 2007
 * @revision 29 mai 2020
 */


public class ImageLibrary extends Observable { 	// la partie "extends Observable" ne sert PAS au TP3, ignorez la, les méthodes restent les mêmes.

	/**
	 * Renvoie la taille de la bibliotheque.
	 */
	public int getSize() {
		return current_size;
	}

	/**
	 * Ajoute une image a la bibliotheque. 
	 * @param nom donne � l image
	 * @param chemin complet du fichier
	 * @return 
	 */
	public Image addImage(String imagename, String fullpathname) {
		if (current_size < max_size) {
			library[current_size] = new ImageItem(imagename, fullpathname);
			current_size++;
		}
		this.setChanged();
		this.notifyObservers(MESSAGE_NOUVELLE_IMAGE);
		return library[current_size-1].getImage();
	}

	/**
	 * Renvoi l'image (donc pleine taille) de la bibliotheque.
	 * @param i  indice de l image a retourner
	 */
	public ImageIcon getImage(int i) {
		if ((i < current_size) && (i >= 0))
			return this.library[i];
		else
			return null;
	}

	/**
	 * Renvoi l'icone de l'image (petite taille) de la bibliotheque.
	 * @param i  indice de l image a retourner
	 */
	public ImageIcon getImageIcon(int i) {
		if ((i < current_size) && (i >= 0))
			return this.library[i].getIcon();
		else
			return null;
	}
	
	/**
	 * Renvoi le nom de l'image de la bibliotheque.
	 * @param i  indice de l image a retourner
	 */
	public String getImageName(int i) {
		if ((i < current_size) && (i >= 0))
			return this.library[i].getName();
		else
			return null;		
	}
	
	/**
	 * Permet de recuperer l'indice de l'image courante de la bibliotheque
	 */
	public int getCurrentIndex() {
		return this.currentindex;
	}

	/**
	 * Avance d un indice dans la bibliotheque
	 * @return le nouvel indice de l'image courante
	 */
	public int getNextImage() {
		setCurrentIndex(currentindex + 1);
		return this.currentindex;
	}

	/**
	 * Recule d un indice dans la bibliotheque
	 * @return le nouvel indice de l'image courante
	 */
	public int getPrevImage() {
		setCurrentIndex(currentindex - 1);
		return this.currentindex;
	}

	/**
	 * opérations sur l'image courante
	 * (getters et setters)
	 */
	public ImageIcon getCurrentImage() {
		return this.library[currentindex];
	}

	public String getCurrentImageName() {
		return this.library[currentindex].getName();
	}

	public Dimension getCurrentImageSize() {
		return this.library[currentindex].getImageSize();
	}

	public int getCurrentImageScaleFactor() {
		return this.library[currentindex].getScaleFactor();
	}
	
	public void setCurrentIndex(int cindex) {
		if ((cindex < current_size) && (cindex >= 0)) {
			this.currentindex = cindex;
			this.setChanged();
			this.notifyObservers(MESSAGE_IMAGE_COURANTE);
		}
	}
	
	public void setCurrentScaleFactor(float factor) {
		this.library[currentindex].scaleImage(factor);
		this.setChanged();
		this.notifyObservers(MESSAGE_CHANGEMENT_TAILLE);
	}
	
	public void setCurrentImageName(String newName) {
		this.library[currentindex].setName(newName);
		this.setChanged();
		this.notifyObservers(MESSAGE_NOUVELLE_NOM);
	}

	/**
	 * Ajoute une image a la bibliotheque a partir du repertoire ./images
	 * 
	 * @param nom
	 *            du fichier seul de l image placee dans le repertoire ./images
	 *            de l application
	 */
	private void addImage(String filename) {
		this.addImage(filename, "images/" + filename);
	}

	/** Constructeur
	 * 
	 */
	public ImageLibrary() {
		library = new ImageItem[max_size];
		current_size = 0;
		for (int i = 0; i < images.length; i++) {
			this.addImage(images[i]);
		}
		currentindex = 0;
	}

	/***************************************************************************
	 * 
	 *  paramètre et méthodes privées de la classe ImageLibrary
	 */
	final int max_size = 50;

	private final String images[] = { "un.png", "geste.png", "pour.png",
			"la.png", "pla.png", "ne.png", "te.png" };

	private ImageItem library[]; // bibliotheque d image
	private int current_size; // taille courante de la bibliotheque
	private int currentindex; // index de l image courante

	// permet de créer des messages d'information pour le passage d'un paramètres aux observers (optionnel)
	private static int message_counter = 0;
	public final static Integer MESSAGE_IMAGE_COURANTE = message_counter++;
	public final static Integer MESSAGE_CHANGEMENT_TAILLE = message_counter++;
	public final static Integer MESSAGE_NOUVELLE_IMAGE = message_counter++;
	public final static Integer MESSAGE_NOUVELLE_NOM = message_counter++;
	
	/** la classe ImageItem permet de manipuler des images individuelles avec plus de fonctionalités pour ImageLibray 
	 * Seule ImageLibrary a accés à cette classe.
	 * Vous n'en avez donc pas besoin et vous pouvez l'ignorer. 
	 *
	 */
	private class ImageItem extends ImageIcon {

		/**
		 * Constructeur.
		 * 
		 * @param imagename
		 *            nom que l on veut donner a l image (independant de filename)
		 * @param filename
		 *            chemin complet du fichier image
		 */
		ImageItem(String imagename, String filename) {
			super(filename);
			original_width = this.getIconWidth();
			original_height = this.getIconHeight();
			original_image = this.getImage();
			current_scalefactor = 1.0f;
			name = new String(imagename);

		}

		/**
		 * Renvoi la taille de l image.
		 * 
		 */
		public Dimension getImageSize() {
			return new Dimension(this.getIconWidth(), this.getIconHeight());
		}

		/**
		 * Permet de recuperer le facteur d echelle de l image
		 */
		public int getScaleFactor() {
			return ((int) (current_scalefactor * static_scale_factor));
		}

		/**
		 * Permet de changer le facteur d echelle de l image
		 */
		public void setScaleFactor(float scalefactor) {
			this.current_scalefactor = Math.min(Math.max(scalefactor
					/ static_scale_factor, static_min_scale), static_max_scale);
		}

		/**
		 * Permet de recuperer le descripteur de l image.
		 */
		public ImageIcon getIcon() {
			float ratio = Math.max(original_width, original_height);

			int icon_x = (int) (ICON_SIZE * (float) original_width / ratio);
			int icon_y = (int) (ICON_SIZE * (float) original_height / ratio);

			/* On cree une nouvelle image aux bonnes dimensions. */
			Image source = original_image;
			BufferedImage buf = new BufferedImage(icon_x, icon_y,
					BufferedImage.TYPE_INT_ARGB);

			/* On dessine sur le Graphics de l image bufferisee. */
			Graphics2D g = buf.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(source, 0, 0, icon_x, icon_y, null);
			g.dispose();

			return (new ImageIcon(buf));

		}

		/**
		 * Permet de recuperer le nom de l image.
		 */
		public String getName() {
//	            return this.name.substring(0, Math.max(1, name.length() - 4));
	            return this.name;
		}

		public void setName(String newName) {
			this.name = newName;
		}
		/**
		 * Redimensionne l image.
		 * 
		 * @param factor
		 *            facteur d echelle du redimensionnement.
		 */
		public void scaleImage(float factor) // Image source, int width, int
												// height)
		{
			setScaleFactor(factor);

			/* On cree une nouvelle image aux bonnes dimensions. */
			Image source = original_image;
			int width = (int) (original_width * current_scalefactor);
			int height = (int) (original_height * current_scalefactor);
			BufferedImage buf = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);

			/* On dessine sur le Graphics de l image bufferisee. */
			Graphics2D g = buf.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(source, 0, 0, width, height, null);
			g.dispose();

			this.setImage(buf);

		}

		private final float static_scale_factor = 100.0f;
		private final float static_min_scale = 0.1f;
		private final float static_max_scale = 5.0f;
		private final float ICON_SIZE = 80.0f;
		private int original_width;
		private int original_height;
		private Image original_image;
		private float current_scalefactor;
		private String name;
		
		

	}
	
}

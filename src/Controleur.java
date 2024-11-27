import java.util.*;

public class Controleur 
{
	private FrameJoueur  		frJoueur;
	private FramePlateau 		frPlateau;
	private Jeu					jeuC;
	

	public Controleur 
	{
		this.frJoueur = new FramePlateau(this);

		this.jeuC = new Jeu();
		
		for (Joueur j : this.jeuC.getJoueurs())
			new FramePlateau(this, j);
		
	}

	public void majIHM ()
	{
		this.frJoueur.getPanelJoueur().repaint();
		this.frPlateau.getPanelPlateau().repaint();
	}

	public static void main (String[] a)
	{
		new Controleur();
	}
}
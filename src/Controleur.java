import java.util.*;

public class Controleur 
{
	private FrameJoueur  		frJoueur;
	private FramePlateau 		frPlateau;
	private Jeu					jeuC;
	private 
	

	public Controleur 
	{
		this.frJoueur = new FramePlateau(this);

		this.jeuC = new Jeu(this);
		
		for (Joueur j : this.jeuC.getJoueurs())
			new FramePlateau(this, j);
		
	}

	public void jouer(Joueur j, String action)
	{
		jeuC.action();
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
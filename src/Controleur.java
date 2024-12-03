import java.util.*;

public class Controleur 
{
	private ArrayList<FrameJoueur>  	lstFrJoueur;
	private FramePlateau 				frPlateau;
	private Jeu							jeuC;

	public Controleur ()
	{
		this.frPlateau = new FramePlateau(this);

		this.jeuC = new Jeu(this);
		
		for (Joueur j : this.jeuC.getJoueurs())
			this.lstFrJoueur.add(new FrameJoueur(this, j));
		
	}

	public void jouer(Joueur j, String action)
	{
		jeuC.action(j,action);
	}

	public void majIHM ()
	{
		for (FrameJoueur fr : this.lstFrJoueur)
			fr.getPanelJoueur().repaint();
		this.frPlateau.getPanelPlateau().repaint();
	}

	public Jeu getJeu() { return this.jeuC;}

	public static void main (String[] a)
	{
		new Controleur();
	}
}
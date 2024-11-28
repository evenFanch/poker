import java.util.*;

public class Jeu 
{
	private int tour  ;
	/*
	 * Tour 1 : pas de cartes
	 * Tour 2 : 3 cartes 
	 * Tour 3 : 4 cartes 
	 * Tour 5 : 5 cartes
	 */

	private int manche;
	private Controleur ctrl;

	private ArrayList<Joueur>	lstJoueur;
	private ArrayList<Joueur>	lstJoueurEnJeu;
	private Joueur 				tourDeJouer;

	public Jeu (Controleur ctrl)
	{
		this. ctrl=ctrl;
		
		this.lstJoueur 		= new ArrayList<Joueur>();
		this.lstJoueurEnJeu = new ArrayList<Joueur>();

		this.lstJoueur.add(new Joueur("Jean"));
		this.lstJoueur.add(new Joueur("Jean2"));
		this.lstJoueur.add(new Joueur("Jean3"));
		this.lstJoueur.add(new Joueur("Jean4"));

		for (Joueur j : lstJoueur)
			this.lstJoueurEnJeu.add(j);

		this.tourDeJouer = this.lstJoueurEnJeu.get(0);

		this.tour=1;
		this.manche =1;

	}

	public boolean jouerTour ()
	{
		int miseMax;

		for (Joueur j : this.lstJoueurEnJeu)
			if (!j.getAJouer())
				return false;

		if (this.tour<5)
			this.tour++;
		else 
		{
			this.tour=1				;
			this.changerManche()	;
		}
		return true;

	}


	public void changerManche ()
	{
		this.lstJoueurEnJeu = new ArrayList<Joueur>();

		for (Joueur j : this.lstJoueur)
		{
			if (j.getCredits<=0)
				this.lstJoueur.remove(this.lstJoueur.indexOf(j));
			else 
				this.lstJoueurEnJeu.add(j);
		}

	}

	public void changerJoueur ()
	{
		int index = this.lstJoueurEnJeu.indexOf(this.tourDeJouer);

		if (index < this.lstJoueurEnJeu.size()-1)
			this.tourDeJouer=this.lstJoueurEnJeu.get(index);
		else 
			this.tourDeJouer= this.lstJoueurEnJeu.get(0);
	}

	public ArrayList<Joueur> getJoueurs () { return this. lstJoueur;}


}
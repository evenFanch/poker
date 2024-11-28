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
	private int miseMax;
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
		this.miseMax=0;

	}

	public boolean jouerTour ()
	{

		for (Joueur j : this.lstJoueurEnJeu)
			if (!j.getAJouer())
				return false;

		Paquet.distribuerPlateau(this.tour);
		this.miseMax=0;
		if (this.tour<5)
			this.tour++;
		else 
		{
			this.tour=1				;
			this.verifGagnant()		;
			this.changerManche()	;
		}
		return true;

	}

	public void action (Joueur j, String action)
	{

		if (tourDeJouer != j)
			return ;
		
		if (action.equals("Coucher"))
			this.lstJoueurEnJeu.remove(this.lstJoueur.indexOf(j))
		
		if (action.equals("Suivre"))
		{
			j.setMise(this.miseMax);
		}

		if (action.startsWith("Relancer"))
		{
			int index = action.indexOf(":");

			if (index == -1)
				j.setMise(this.miseMax*2);
			else 
				j.setMise(Integer.parseInt(action.substring(index+2)));
		}

		if (j.getMise()<this.miseMax)
			return ;
		else if (j.getMise() > this.miseMax)
			for (Joueur j : this.lstJoueurEnJeu)
				j.setAJouer(false);
		
		this.miseMax = j.getMise();
		j.setCredits(j.getCredits()-this.miseMax);
		
		j.setAJouer(true);
		this.changerJoueur();
		this.jouerTour()
	}

	public void verifGagnant()
	{

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

	public void distribuerCartes ()
	{
		for (Joueur j : this.lstJoueur)
			j.setCartes(Paquet.piocherCarte(), Paquet.piocherCarte())
	}

	public ArrayList<Joueur> getJoueurs () { return this. lstJoueur;}


}
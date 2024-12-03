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
		{
			int index=0 ; 

			for (int i=0; i< this.lstJoueurEnJeu.size(); i++)
				if (this.tourDeJouer.equals(this.lstJoueurEnJeu.get(i)))
					index = i;

			this.lstJoueurEnJeu.remove(index);
		}
			
		
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
		else 
			for (Joueur ji : this.lstJoueurEnJeu)
				ji.setAJouer(false);
		
		this.miseMax = j.getMise();
		j.setCredits(j.getCredits()-this.miseMax);
		
		j.setAJouer(true);
		this.changerJoueur();
		this.jouerTour();
	}

	public void verifGagnant()
	{
		return;
	}

	public void changerManche ()
	{
		Paquet.initPaquet();
		this.lstJoueurEnJeu = new ArrayList<Joueur>();

		for (Joueur j : this.lstJoueur)
		{
			if (j.getCredits()<=0)
				this.lstJoueur.remove(this.lstJoueur.indexOf(j));
			else 
				this.lstJoueurEnJeu.add(j);
		}

	}

	public void changerJoueur ()
	{

		int index=-1 ; 

		for (int i=0; i< this.lstJoueurEnJeu.size(); i++)
			if (this.tourDeJouer.equals(this.lstJoueurEnJeu.get(i)))
				index = i;
		//int index = this.lstJoueurEnJeu.indexOf(this.tourDeJouer);

		System.out.println(this.tourDeJouer);
		System.out.println(index);

		if (index < this.lstJoueurEnJeu.size()-1)
			this.tourDeJouer=this.lstJoueurEnJeu.get(index+1);
		else 
			this.tourDeJouer= this.lstJoueurEnJeu.get(0);

		this.ctrl.majIHM();
	}

	public void distribuerCartes ()
	{
		for (Joueur j : this.lstJoueur)
			j.setCartes(Paquet.piocherCarte(), Paquet.piocherCarte());
	}

	public ArrayList<Joueur> getJoueurs () { return this. lstJoueur;}
	public int getTour    () {return this.tour  ;}
	public int getManche  () {return this.manche;}
	public int getMiseMax () {return this.manche;}
	public Joueur getTourDeJouer() {return this.tourDeJouer;}

	public int getPot()
	{
		int pot =0;

		for (Joueur j : this.lstJoueur)
		{
			j.setCartes(Paquet.piocherCarte(), Paquet.piocherCarte());
			pot+=j.getMise();
		}
		return pot;
	}


}
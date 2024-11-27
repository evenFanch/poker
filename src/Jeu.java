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

	private ArrayList<Joueur>	lstJoueur;
	private ArrayList<Joueur>	lstJoueurEnJeu;

	public Jeu ()
	{
		this.lstJoueur.add(new Joueur("Jean"));
		this.lstJoueur.add(new Joueur("Jean2"));
		this.lstJoueur.add(new Joueur("Jean3"));
		this.lstJoueur.add(new Joueur("Jean4"));

		for (Joueur j : lstJoueur)
		{
			lstJoueurEnJeu.add(j);
		}

		this.tour=1;
		this.manche =1;

		while (lstJoueur.size() >1)
		{
			while (this.tour<=5)
			{
				this.jouerTour();
				this.tour++;
			}
			this.changerMache();
			this.tour =1;
			this.manche++;
		}
	}

	public void jouerTour ()
	{
		int miseMax;

		
	}

	public ArrayList<Joueur> getJoueurs () { return this. lstJoueur;}


}
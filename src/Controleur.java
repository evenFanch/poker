import java.util.ArrayList;
import java.util.Random;

public class Controleur
{
	private ArrayList<Carte>  		paquet			;
	private ArrayList<Carte>  		plateau			;

	private ArrayList<Joueur> 		lstJoueurs		;

	private FramePlateau 			framePlateau	;
	private ArrayList<FrameJoueur> 	lstFrameJoueur	;

	private Joueur					tour			;
	private Jeu						jeu 			;

	public Controleur ()
	{
		this.paquet		= new ArrayList<Carte> ();
		this.lstJoueurs = new ArrayList<Joueur>();
		this.plateau 	= new ArrayList<Carte> ();

		this.framePlateau	= new FramePlateau		(this);
		this.lstFrameJoueur	= new ArrayList<FrameJoueur>();

		this.lstJoueurs.add(new Joueur("Jean"));
		this.lstJoueurs.add(new Joueur("Michel"));
		this.lstJoueurs.add(new Joueur("Jack"));
		this.lstJoueurs.add(new Joueur("Pierre"));
		//this.lstJoueurs.add(new Joueur("Mathis"));
		this.tour = this.lstJoueurs.get(0);

		this.jeu = new Jeu(this);
		


		this.distribution();

		for (int i=0; i<this.lstJoueurs.size(); i++)
		{
			this.lstFrameJoueur.add(new FrameJoueur(this, this.lstJoueurs.get(i)));
		}

		/*for (int i=0; i<this.lstJoueurs.size(); i++)
		{
			System.out.println(this.lstJoueurs.get(i));
		}*/


		/*this.deposer();
		this.deposer();
		this.deposer();*/
		this.framePlateau.getPanelPlateau().repaint();
			
		/*for (int i=0; i<52 ; i++)
			System.out.println(this.paquet.get(i));*/ //Affiche le paquet complet

		/*for (int i=0; i<10; i++)
		{
			System.out.println(prendreCarte());
		}*/
		
	}

	public void melangerPaquet ()
	{
		this.plateau 	= new ArrayList<Carte> ();
		this.paquet		= new ArrayList<Carte> ();
		
		for (int i=0; i<this.lstJoueurs.size(); i++)
		{
			this.lstJoueurs.get(i).setCartes(null,null);
		}

		String[] str = {"Pique", "Coeur", "Carreau", "Trefle"};

		for (int i=1; i<14 ; i++)
			for (int j=0; j<4; j++)
				this.paquet.add(new Carte(i,str[j]));
	}

	public Carte prendreCarte ()
	{
		Random r = new Random();

		return this.paquet.remove(r.nextInt(this.paquet.size()));
	}

	public void deposer()
	{
		if (this.plateau.size()==0)
			for (int i=0; i<3; i++)
				this.plateau.add(this.prendreCarte());
		
		else if (this.plateau.size()==3 || this.plateau.size()==4)
			this.plateau.add(this.prendreCarte());

		/*for (int i=0; i<this.plateau.size(); i++)
		{
			System.out.println("Carte Plateau : "+this.plateau.get(i));
		}*/
	}

	public void distribution ()
	{	
		for (int i=0; i<this.lstJoueurs.size(); i++)
		{
			this.lstJoueurs.get(i).setCartes(this.prendreCarte(),this.prendreCarte());
			//System.out.println(this.lstJoueurs.get(i));
		}
	}

	public ArrayList<Carte> getCartePlateau(){return this.plateau;}
	public Joueur 			getTour 	   (){return this.tour	 ;}

	public Joueur gagnant()
	{
		Joueur gagnant=null;

		for (int i=0; i<this.lstJoueurs.size(); i++)
		{
			if (this.lstJoueurs.get(i).estEnVie())
			{
				if (gagnant!=null)
					return null;
				gagnant=this.lstJoueurs.get(i);
			}
		}
		return gagnant;
	}

	

	public void majIhm ()
	{
		this.framePlateau.getPanelPlateau().repaint();

		for (int i=0; i<this.lstFrameJoueur.size(); i++)
		{
			this.lstFrameJoueur.get(i).repaint();
		}
	}

	public void setTour (Joueur j) {this.tour=j;}

		

	public Jeu getJeu() { return this.jeu;}

	public ArrayList<Joueur> getLstJoueur () {return this.lstJoueurs;}

	public static void main (String[] a)
	{
		new Controleur();
	}
}
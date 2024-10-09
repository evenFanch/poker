import java.util.ArrayList;
import java.util.Random;

public class Jeu 
{
	private Controleur 	ctrl	;
	private boolean 	estFin	; 

	private ArrayList<Joueur> 	lstCoucher	;
	private int 		mise 	;
	private int 		pot 	;

	public Jeu(Controleur ctrl)
	{
		this.ctrl=ctrl;
		this.estFin=false;
		this.mise = 0;
		this.pot  = 0;

		this.lstCoucher= new ArrayList<Joueur>();

		for (Joueur jo : this.ctrl.getLstJoueur())
			this.lstCoucher.add(jo);
	
		this.ctrl.melangerPaquet();
		this.ctrl.distribution();

	}

	public boolean jouer(Joueur j,String action)
	{

		if (!j.equals(this.ctrl.getTour()))
			return false ;

		if (action.startsWith("Relancer : "))
			if (Integer.valueOf(action.substring(action.indexOf(":")+2))<this.mise)
				return false;

		System.out.println("Action : "+action);
		System.out.println(this.mise);
		
		this.nouveauTour();

		if (action.equals("Coucher"))
		{
			
			for (int i=0; i<this.lstCoucher.size(); i++ )
			{
				if (this.lstCoucher.get(i).equals(j))
					this.lstCoucher.remove(i);
			}
			return false;
		}

		if (action.equals("Suivre"))
		{
			j.setMise(this.mise);
		}

		if (action.equals("RelancerDouble"))
		{
			this.mise=this.mise*2;
			j.setMise(this.mise);
		}

		if (action.startsWith("Relancer : "))
		{
			j.setMise(Integer.valueOf(action.substring(action.indexOf(":")+2)));
			this.mise = j.getMise();
			System.out.println("Mise finale : "+this.mise);
		}

		this.pot+=this.mise;

		for (Joueur jo : lstCoucher )
		{
			if (jo.getMise() != this.mise)
			{
				return false;
			}
		}

		System.out.println("Mise finale : "+this.mise);

		if (this.ctrl.getCartePlateau().size()!=5)
		{
			this.ctrl.deposer();
			this.mise=0;

			for (int i=0; i<this.lstCoucher.size(); i++)
				this.lstCoucher.get(i).setMise(-1);
		}
			
		else 
			this.gagnant ();
		/*else 
			this.nouvelleManche();*/

		this.ctrl.majIhm();

		return true;

	}

	public void nouvelleManche()
	{
		this.gagnant ();
		this.mise=0;
		this.pot=0;
		for (int i=0; i<this.lstCoucher.size(); i++)
			this.lstCoucher.get(i).setMise(-1);

		this.ctrl.melangerPaquet();
		this.ctrl.distribution();
		this.ctrl.majIhm();

	}

	public void gagnant()
	{
		Carte[] plateauMain 					;
		int 	cpt 							;
		boolean egalite							;
		Joueur	gagnant=this.lstCoucher.get(0)	;

		for (Joueur ji : this.lstCoucher)
		{
			plateauMain = new Carte[7];
			cpt = 0;
			egalite=true;

			plateauMain=this.initierMain(ji);
			this.finireMain (plateauMain, ji);
			plateauMain=this.initierMain(ji);

			
			cpt=0;
			
			if (this.estPaire(plateauMain, ji))
				System.out.println("PAIRE : "+ji.getNom());
			
			plateauMain=this.initierMain(ji);


			if (this.estDoublePaire(plateauMain, ji))
				System.out.println("DOUBLE PAIRE : "+ji.getNom());
			System.out.println (ji.getNom()+" : "+ji.afficherMain());

		}

		gagnant.setCredits(gagnant.getCredits()+this.pot);
	}

	public Carte[] initierMain(Joueur j)
	{
		Carte[] plateauMain = new Carte[7];
		int cpt=0;

		for (Carte c : this.ctrl.getCartePlateau())
				plateauMain[cpt++]=c;
			
		for (Carte c : j.getCartes())
			plateauMain[cpt++]=c;
		return plateauMain;

	}

	public boolean estPaire(Carte[] tab, Joueur jm)
	{
		Carte temp;

		for (int i=0; i<tab.length; i++)
		{
			temp = tab[i];

			for (int j=0; j<tab.length; j++)
				if (tab[j].getValeur()==temp.getValeur() && !(tab[j].getCouleur().equals(temp.getCouleur())))
				{
					jm.setValeurMain(1,0			);
					jm.setValeurMain(tab[j].getValeur(),1);
					jm.setValeurMain(tab[j].getValeur(),2);
					tab[j]=null;
					tab[i]=null;
					this.finireMain 	(tab,jm);
					return true ;
				}
		}		
		return false ;
	}

	public void finireMain(Carte[] tab, Joueur jm)
	{
		int   temp  ;
		int[] tabTri = new int[tab.length];

		for (int i=0; i<tabTri.length;i++)
		{
			temp=0;
			for (int j=0; j<tab.length;j++)
			{
				if (tab[j]!=null)
					if (temp<tab[j].getValeur())
					{
						temp=tab[j].getValeur();
						tab[j]=null;
					}
					

			}
			tabTri[i]=temp;
		}

		int cpt=0;
		for (int i=0;i<jm.getValeurMain().length;i++)
		{
			if (jm.getValeurMain()[i]==0)
			{
				jm.setValeurMain(tabTri[cpt++], i);
			}
		}

		
	}

	public boolean estDoublePaire(Carte[] tab, Joueur jm)
	{
		Carte temp1=tab[0], temp2;
		boolean premierePaire=false;
		int cpt=0;

		while(premierePaire=false)
		{
			temp1=tab[cpt];
			for (int k=cpt; k<tab.length; k++)
			{
				if (tab[k].getValeur()==temp1.getValeur() && !(tab[k].getCouleur().equals(temp1.getCouleur())))
				{
					tab[k]=null;
					tab[cpt]=null;
					premierePaire=true;
				}
				
			}
			cpt++;
			if (cpt>=tab.length)
				return false ; 
		}

		for (int i=0; i<tab.length; i++)
		{
			
			if (!(tab[i]==null) && tab[i].getValeur()!=temp1.getValeur())
			{
				temp2=tab[i];
				
				for (int k=i; k<tab.length; k++)
				{
					if (tab[k].getValeur()==temp2.getValeur() && !(tab[k].getCouleur().equals(temp2.getCouleur())))
					{
						jm.setValeurMain(2,0			);
						if (temp1.getValeur()>temp2.getValeur())
						{
							jm.setValeurMain(temp1.getValeur(), 1);
							jm.setValeurMain(temp1.getValeur(),2);
						}
						else
						{
							jm.setValeurMain(temp2.getValeur(), 3);
							jm.setValeurMain(temp2.getValeur(),4);
						}
							
						tab[k]=null;
						tab[i]=null;
						this.finireMain 	(tab,jm);

						return true;
					}
					
				}
			}
		}
		return false;
	}

	public void nouveauTour()
	{

		for (int i=0; i<this.lstCoucher.size(); i++)
		{
			if (this.lstCoucher.get(i)==this.ctrl.getTour())
			{
				if (i>=this.lstCoucher.size()-1)
					this.ctrl.setTour(this.lstCoucher.get(0));
				else
					this.ctrl.setTour(this.lstCoucher.get(i+1));	
				i=this.lstCoucher.size();
			}

		}
		this.ctrl.majIhm();
	}

	public int getPot  () {return this.pot ;}
	public int getMise () {return this.mise;}

}
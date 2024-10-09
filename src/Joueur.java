public class Joueur 
{
	private String  nom  	  	;
	private Carte[] main 		;
	private int 	credits	  	;
	private int []	valeurMain	;

	private boolean enVie		;
	private int 	mise 		;

	public Joueur (String nom)
	{
		this.nom	 	= nom			;
		this.main 	 	= new Carte[2]	;
		this.credits 	= 10000			;
		this.enVie 	 	= true			;
		this.mise	 	= -1			;
		this.valeurMain = new int[6]	;

		for (int i=0;i<this.valeurMain.length;i++)
			this.valeurMain[i]=0;
	}

	public void setCartes(Carte  a, Carte b)
	{
		this.main[0]=a;
		this.main[1]=b;
	}

	public Carte[] 	getCartes  	 () {return this.main		;}
	public String 	getNom 		 () {return this.nom		;}
	public int 		getCredits	 () {return this.credits	;}
	public boolean	estEnVie	 () {return this.enVie		;}
	public int[] 	getValeurMain() {return this.valeurMain ;}
	public int 		getMise		() 	{return this.mise		;}

	public void 	setValeurMain(int valeur, int indice) {this.valeurMain[indice]=valeur ;}
	public void 	setMise		 (int mise)   {this.mise=mise	;this.credits-=this.mise;}
	public void		setCredits   (int credits)
	{
		this.credits=credits;

		if (this.credits<=0)
			this.enVie=false;
	}

	public String afficherMain()
	{
		String str = "";
		for (int i : this.valeurMain)
			str+=i+" | ";
		return str;
	}

	public String toString()
	{
		String str="";

		str+="			Nom : "+this.nom;
		str+="\n  Carte 1 : "+this.main[0];
		str+="\n  Carte 2 : "+this.main[1];
		str+="\n ";

		return str;
	}

	
}
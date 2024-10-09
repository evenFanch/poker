public class Carte
{
	private int 	valeur	;
	private String 	couleur	;

	public Carte (int valeur, String couleur)
	{
		this.valeur = valeur;
		this.couleur= couleur;
	}

	public int 		getValeur  () 	{return this.valeur ;}
	public String 	getCouleur ()	{return this.couleur;}

	public String 	toString () 
	{
		return this.valeur +" de "+this.couleur;
	}

}
import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

public class PanelJoueur extends JPanel 
{
	private Controleur 	ctrl	;
	private Joueur j;

	private Graphics2D g2	;

	public PanelJoueur (Controleur ctrl, Joueur j)
	{
		this.ctrl= ctrl ;
		this.j = j;

		this.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		String lien="";


		this.g2 = (Graphics2D) g;

		this.g2.setBackground (Color.WHITE);
		this.g2.clearRect (0,0,700,300);

		lien = ""+this.j.getCartes()[0].getValeur()+this.j.getCartes()[0].getCouleur();
		this.g2.drawImage( getToolkit().getImage("images/"+lien+".gif"), 50,50,this);

		lien = ""+this.j.getCartes()[1].getValeur()+this.j.getCartes()[1].getCouleur();
		this.g2.drawImage( getToolkit().getImage("images/"+lien+".gif"), 150,50,this);


		this.g2.setColor (Color.BLACK);
		this.g2.drawString( "credits="+this.j.getCredits(),275, 100);

		if (this.j==this.ctrl.getTour())
		{
			this.g2.setColor (Color.GREEN);
			this.g2.drawString( "C'est à votre tour",275, 200);
		}
			
		else
		{
			this.g2.setColor (Color.RED);
			this.g2.drawString( "Ce n'est pas a votre tour ",275, 200);
		}
			
	}


}
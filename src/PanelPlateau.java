import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

public class PanelPlateau extends JPanel 
{
	private Controleur 	ctrl	;
	

	private Graphics2D g2	;

	public PanelPlateau (Controleur ctrl)
	{

		this.ctrl= ctrl ;

		this.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		int x,y;
		String lien="";

		x=y=50;

		this.g2 = (Graphics2D) g;

		this.g2.setBackground (Color.WHITE);
		this.g2.clearRect (0,0,700,300);
		
		for (int i=0; i<this.ctrl.getCartePlateau().size(); i++)
		{
			lien=this.ctrl.getCartePlateau().get(i).getValeur()+""+this.ctrl.getCartePlateau().get(i).getCouleur();
			this.g2.drawImage( getToolkit().getImage("images/"+lien+".gif"), x,y,this);
			x+=100;
		}

		this.g2.setColor (Color.BLACK);
		this.g2.drawString( "Pot actuel : "+this.ctrl.getJeu().getPot(),50, 200);
		this.g2.drawString( "Mise : "+this.ctrl.getJeu().getMise(),50, 225);
	}


}
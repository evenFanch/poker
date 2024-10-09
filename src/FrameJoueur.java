import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FrameJoueur extends JFrame
{
	private static int 		nbFrame	;

	private Controleur 		ctrl 	;
	private PanelJoueur 	panel	;
	private PanelBoutons	panelBtn;

	public FrameJoueur (Controleur ctrl, Joueur j)
	{
		

		this.ctrl=ctrl;

		this.setTitle   ("Mains de "+j.getNom() );
		this.setSize    ( 450,300  );
		this.setLocation( 10+20*FrameJoueur.nbFrame, 450  );
		FrameJoueur.nbFrame++;

		this.panel 	  = new PanelJoueur(ctrl, j);
		this.panelBtn = new PanelBoutons(ctrl ,j );

		this.add (this.panel, BorderLayout.CENTER);
		this.add (this.panelBtn, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public PanelJoueur getPanelJoueur() {return this.panel;}
}
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PanelBoutons extends JPanel implements ActionListener   
{
	private Controleur 	ctrl		;
	private Joueur		j 			;

	private JButton 	btnCoucher	;
	private JButton 	btnRelancer	;
	private JButton 	btnSuivre	;
	private TextField	relancerTxt	;

	public PanelBoutons (Controleur ctrl, Joueur j)
	{
		this.ctrl= ctrl ;
		this.j=j;

		this.btnCoucher  = new JButton("Se Coucher" );
		this.btnRelancer = new JButton(" Relancer	");
		this.btnSuivre   = new JButton("  Suivre	");
		this.relancerTxt = new TextField (10);

		this.btnCoucher	.addActionListener(this);
		this.btnRelancer.addActionListener(this);
		this.btnSuivre	.addActionListener(this);
		this.relancerTxt.addActionListener(this);

		this.add (this.btnCoucher	);
		this.add (this.btnSuivre	);
		this.add (this.btnRelancer	);
		this.add (this.relancerTxt	);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(this.btnCoucher))
		{
			this.ctrl.jouer(this.j,"Coucher");
		}

		if (e.getSource().equals(this.btnRelancer))
		{
			if (this.relancerTxt.getText().equals(""))
				this.ctrl.jouer(this.j,"RelancerDouble");
			else 
				this.ctrl.jouer(this.j,"Relancer : "+this.relancerTxt.getText());

		}

		if (e.getSource().equals(this.btnSuivre))
		{
			this.ctrl.jouer(this.j,"Suivre");
		}

	}

}
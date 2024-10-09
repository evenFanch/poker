import javax.swing.*;
import java.awt.event.*;

public class FramePlateau extends JFrame
{
	private Controleur 		ctrl ;
	private PanelPlateau 	panel;

	public FramePlateau (Controleur ctrl)
	{
		this.ctrl=ctrl;

		this.setTitle   ("Plateau" );
		this.setSize    ( 700,300  );
		this.setLocation( 150, 50  );

		this.panel = new PanelPlateau(ctrl);

		this.add (this.panel);

		this.setVisible(true);
	}

	public PanelPlateau getPanelPlateau() {return this.panel;}
}
package ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import control.* ;

/**
 * Cette classe intervient lors de la suppression d'un contact.
 */
public class WtDialogSupprimer extends JDialog implements ActionListener {

	/** Attribut graphique. */
	private JLabel labelSupprimer;
	/** Attribut graphique. */
	private JTextField aSupprimer;
	/** Attribut graphique. */
	private JButton confirmer, annuler;
	/** Copie locale de Wintel. */
	private Wintel parent;

	/**
	 * Le constructeur gère les autres méthodes de la classe.
	 */
	public WtDialogSupprimer(Wintel parent) {
		super(parent, "Supprimer un contact", true); // appel constructeur JDialog
		this.parent = parent;
		this.creerInterface(); // mise en place du décor
		this.attacherReactions(); // écouteurs sur les boutons et JComboBox
		this.setSize(300, 300);
		this.setVisible(false); // invisible à la création
	}

	/**
	 * Dispose les attributs graphiques.
	 */
	public void creerInterface() {
		// Boutton en bas
		confirmer = new JButton("Confirmer");
		annuler = new JButton("Annuler");
		JPanel panelBoutton = new JPanel(new GridLayout(1, 2));
		panelBoutton.add(confirmer);
		panelBoutton.add(annuler);

		// Panel principal
		labelSupprimer = new JLabel("Supprimer le contact ?");
		labelSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
		labelSupprimer.setForeground(Color.blue);
		labelSupprimer.setFont(new Font(null, Font.PLAIN, 18));

		aSupprimer = new JTextField();
		aSupprimer.setForeground(Color.blue);
		aSupprimer.setEditable( false ) ;
		aSupprimer.setBackground( Color.white ) ;
		aSupprimer.setFont(new Font(null, Font.PLAIN, 18));

		BorderLayout gestionnairePlacement = new BorderLayout(20, 20);
		JPanel panelCentre = new JPanel(new GridLayout(3, 1, 0, 20));
		JPanel panelGauche = new JPanel();
		JPanel panelDroit = new JPanel();
		JPanel panelBas = new JPanel();
		this.setLayout(gestionnairePlacement);
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelDroit, BorderLayout.EAST);
		this.add(panelBas, BorderLayout.SOUTH);

		panelCentre.add(labelSupprimer);
		panelCentre.add(aSupprimer);
		panelCentre.add(panelBoutton);
	}

	/**
	 * Lie les boutons à des actions par le biais de classes extérieures.
	 */
	public void attacherReactions() {
		// La "fermeture" de la fenêtre est traitée en local.
		annuler.addActionListener( this ) ;
		confirmer.addActionListener( parent.getEcouteurs() ) ;
	}

	/**
	 * Remplit le champ de texte avec le nom du contact sélectionné
	 * dans le liste de l'objet Wintel.
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource() ;
		if ( src == this.annuler ) { this.setVisible( false ) ; }
		else {
			JList liste = parent.getListeGche();
			if(liste.getSelectedValue() != null) {
				String valeur = (String) liste.getSelectedValue();
				aSupprimer.setText(valeur);
				this.setVisible(true);
			}
			else if ( liste.getSelectedValue() == null ) {
				JOptionPane.showMessageDialog( parent , "Veuillez sélectionner un contact." , "Erreur" , JOptionPane.WARNING_MESSAGE ) ;
			}
		}
	}

	/**
	 * Acesseur utilisé par la classe Ecouteurs.
	 */
	public JButton getJButtonConfirmer() {
		return ( this.confirmer ) ;
	}
}

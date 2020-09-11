package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Serveur.Logs;
import classeMetier.Joueur;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;

public class NouvellePartiePanel extends JPanel {
	private JButton validerCreationPartieBt;
	private JComboBox listeNbJoueurComboBox;
	private JPanel imgsPersonnagesPanel;
	private JPanel img1Panel;
	private JPanel img2Panel;
	private JPanel img3Panel;
	private JPanel img4Panel;
	private JPanel img5Panel;
	private JPanel img6Panel;
	private ImagePanel img1;
	private ImagePanel img2;
	private ImagePanel img3;
	private ImagePanel img4;
	private ImagePanel img5;
	private ImagePanel img6;
	private ImagePanel imgPanel4;
	private ImagePanel imgPanel5;
	private ImagePanel imgPanel6;
	private JPanel btcreerPartiePanel;
	private JPanel listeJoueursPanel;
	private JLabel attenteLbl;
	private JLabel joueursLbl;
	private JLabel j1Lbl;
	private JLabel j2Lbl;
	private JLabel j3Lbl;
	private JLabel j4Lbl;
	private JLabel j5Lbl;
	private JLabel j6Lbl;
	private Joueur joueur;
	private Socket soc;
	private Frame fr;
	private JLabel titreLbl;
	private JPanel panelNbJoueurPersonnage;
	private JPanel nbJoueurPanel;
	private JLabel titreNbJoueurLbl;
	private JPanel personnagesPanel;
	private JLabel personnageLbl;

	public NouvellePartiePanel(final Joueur joueur, final Socket soc, final Frame fr) {
		this.joueur = joueur;
		this.soc = soc;
		this.fr = fr;

		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 1, 0, 0));

		titreLbl = new JLabel("Création d'une nouvelle partie");
		titreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titreLbl.setFont(new Font(getName(), Font.PLAIN, 20));
		add(titreLbl);

		panelNbJoueurPersonnage = new JPanel();
		panelNbJoueurPersonnage.setBackground(Color.WHITE);
		this.add(panelNbJoueurPersonnage);
		panelNbJoueurPersonnage.repaint();

		nbJoueurPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) nbJoueurPanel.getLayout();
		panelNbJoueurPersonnage.setLayout(new GridLayout(0, 2, 0, 0));
		nbJoueurPanel.setBackground(Color.WHITE);
		panelNbJoueurPersonnage.add(nbJoueurPanel);
		nbJoueurPanel.repaint();
		nbJoueurPanel.setLayout(new GridLayout(0, 1, 0, 0));
		//this.add(nbJoueurPanel);

		titreNbJoueurLbl = new JLabel("Nombre de joueur");
		titreNbJoueurLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nbJoueurPanel.add(titreNbJoueurLbl);

		listeNbJoueurComboBox = new JComboBox();
		listeNbJoueurComboBox.setBackground(Color.WHITE);
		listeNbJoueurComboBox.setPreferredSize(new Dimension(50, 20));
		listeNbJoueurComboBox.addItem("3");
		listeNbJoueurComboBox.addItem("4");
		listeNbJoueurComboBox.addItem("5");
		listeNbJoueurComboBox.addItem("6");
		nbJoueurPanel.add(listeNbJoueurComboBox);

		personnagesPanel = new JPanel();
		personnagesPanel.setBackground(Color.WHITE);
		panelNbJoueurPersonnage.add(personnagesPanel);
		personnagesPanel.repaint();
		personnagesPanel.setLayout(new GridLayout(0, 1, 0, 0));
		//this.add(personnagesPanel);

		personnageLbl = new JLabel("Personnages de la partie");
		personnageLbl.setBackground(new Color(240, 240, 240));
		personnageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		personnagesPanel.add(personnageLbl);

		imgsPersonnagesPanel = new JPanel();
		imgsPersonnagesPanel.setBackground(Color.WHITE);
		personnagesPanel.add(imgsPersonnagesPanel);
		imgsPersonnagesPanel.repaint();
		imgsPersonnagesPanel.setLayout(new GridLayout(0, 6, 0, 0));

		img1Panel = new JPanel();
		imgsPersonnagesPanel.add(img1Panel);
		img1Panel.setLayout(new GridLayout(0, 1, 0, 0));

		img1 = new ImagePanel(getImageFromName("loupMINI.jpg"));
		img1.setBackground(Color.WHITE);
		img1Panel.add(img1);
		img1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		img2Panel = new JPanel();
		imgsPersonnagesPanel.add(img2Panel);
		img2Panel.setLayout(new GridLayout(0, 1, 0, 0));
		img2 = new ImagePanel(getImageFromName("mere cochonMINI.jpg"));
		img2.setBackground(Color.WHITE);
		img2Panel.add(img2);

		img3Panel = new JPanel();
		imgsPersonnagesPanel.add(img3Panel);
		img3Panel.setLayout(new GridLayout(0, 1, 0, 0));
		img3 = new ImagePanel(getImageFromName("petit cochonMINI.jpg"));
		img3.setBackground(Color.WHITE);
		img3Panel.add(img3);

		img4Panel = new JPanel();
		img4Panel.setBackground(Color.WHITE);
		imgsPersonnagesPanel.add(img4Panel);
		img4Panel.setLayout(new GridLayout(1, 0, 0, 0));

		img5Panel = new JPanel();
		img5Panel.setBackground(Color.WHITE);
		imgsPersonnagesPanel.add(img5Panel);
		img5Panel.setLayout(new GridLayout(1, 0, 0, 0));

		img6Panel = new JPanel();
		img6Panel.setBackground(Color.WHITE);
		imgsPersonnagesPanel.add(img6Panel);
		img6Panel.setLayout(new GridLayout(1, 0, 0, 0));

		btcreerPartiePanel = new JPanel();
		btcreerPartiePanel.setBackground(Color.WHITE);
		this.add(btcreerPartiePanel);

		listeJoueursPanel = new JPanel();
		listeJoueursPanel.setBackground(Color.WHITE);
		this.add(listeJoueursPanel);
		listeJoueursPanel.repaint();
		listeJoueursPanel.setLayout(new GridLayout(0, 1, 0, 0));

		attenteLbl = new JLabel("");
		listeJoueursPanel.add(attenteLbl);
		attenteLbl.setHorizontalAlignment(SwingConstants.CENTER);

		joueursLbl = new JLabel("");
		listeJoueursPanel.add(joueursLbl);
		joueursLbl.setHorizontalAlignment(SwingConstants.CENTER);

		validerCreationPartieBt = new JButton("Cr�er une partie");
		
		btcreerPartiePanel.add(validerCreationPartieBt);

		j1Lbl = new JLabel("");
		listeJoueursPanel.add(j1Lbl);

		j2Lbl = new JLabel("");
		listeJoueursPanel.add(j2Lbl);

		j3Lbl = new JLabel("");
		listeJoueursPanel.add(j3Lbl);

		j4Lbl = new JLabel("");
		listeJoueursPanel.add(j4Lbl);

		j5Lbl = new JLabel("");
		listeJoueursPanel.add(j5Lbl);

		j6Lbl = new JLabel("");
		listeJoueursPanel.add(j6Lbl);
		
		validerCreationPartieBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer nbJoueur = listeNbJoueurComboBox.getSelectedIndex() + 3;
				Logs.insererLogINFO("test3");
				try {
					OutputStream os = soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.flush();
					oos.writeObject(nbJoueur);
					oos.flush();
					
					InputStream is = soc.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);
					
					Object o = ois.readObject();
					
					ArrayList<Joueur> joueurs = (ArrayList<Joueur>) o;
					System.out.println(joueurs.toString());
									
					fr.setPartiePanel(new PartiePanel(soc, joueur, joueurs));
					fr.getContentPane().add(fr.getPartiePanel());
					
					fr.setContentPane(fr.getPartiePanel());
					
					revalidate();
					fr.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/*
				 * validerCreationPartieBt.setEnabled(false);
				 * 
				 * attenteLbl.setText("En attente de joueurs");
				 * joueursLbl.setText("Liste des joueurs :");
				 * 
				 * listeJoueursPanel.repaint();
				 */
			}
		});
		
		

		// gestion évènements
		listeNbJoueurComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int nbJoueur = listeNbJoueurComboBox.getSelectedIndex() + 3; // +
																				// 3
																				// car
																				// on
																				// récupère
																				// l'index
				removeAllImage();

				if (nbJoueur == 3) {
					img2 = new ImagePanel(getImageFromName("mere cochonMINI.jpg"));
					img2.setBounds(100, 0, 100, 100);
					img3 = new ImagePanel(getImageFromName("petit cochonMINI.jpg"));
					img3.setBounds(200, 0, 100, 100);

					img1Panel.add(img1);
					img2Panel.add(img2);
					img3Panel.add(img3);

				} else if (nbJoueur >= 4) {
					img2 = new ImagePanel(getImageFromName("chaperon rougeMINI.jpg"));
					img2.setBounds(100, 0, 100, 100);
					img3 = new ImagePanel(getImageFromName("mere cochonMINI.jpg"));
					img3.setBounds(200, 0, 100, 100);
					img4 = new ImagePanel(getImageFromName("petit cochonMINI.jpg"));
					img4.setBounds(200, 0, 100, 100);

					img1Panel.add(img1);
					img2Panel.add(img2);
					img3Panel.add(img3);
					img4Panel.add(img4);

					if (nbJoueur >= 5) {
						img5 = new ImagePanel(getImageFromName("petit cochonMINI.jpg"));
						img5.setBounds(200, 0, 100, 100);
						img5Panel.add(img5);

						if (nbJoueur == 6) {
							img6 = new ImagePanel(getImageFromName("petit cochonMINI.jpg"));
							img6.setBounds(200, 0, 100, 100);
							img6Panel.add(img6);
						}
					}
				}
				revalidateAndRepaintAll();
			}
		});

	}	

	public JComboBox getListeNbJoueurComboBox() {
		return listeNbJoueurComboBox;
	}

	public void setListeNbJoueurComboBox(JComboBox listeNbJoueurComboBox) {
		this.listeNbJoueurComboBox = listeNbJoueurComboBox;
	}

	public void removeAllImage() {
		img1Panel.removeAll();
		img2Panel.removeAll();
		img3Panel.removeAll();
		img4Panel.removeAll();
		img5Panel.removeAll();
		img6Panel.removeAll();
	}

	public void revalidateAndRepaintAll() {
		img1Panel.revalidate();
		img2Panel.revalidate();
		img3Panel.revalidate();
		img4Panel.revalidate();
		img5Panel.revalidate();
		img6Panel.revalidate();
		img1Panel.repaint();
		img2Panel.repaint();
		img3Panel.repaint();
		img4Panel.repaint();
		img5Panel.repaint();
		img6Panel.repaint();
	}

	public void setTextJ1Lbl(String txtj1) {
		this.j1Lbl.setText(txtj1);
	}

	public void setTextJ2Lbl(String txtj2) {
		this.j2Lbl.setText(txtj2);
	}

	public void setTextJ3Lbl(String txtj3) {
		this.j3Lbl.setText(txtj3);
	}

	public void setTextJ4Lbl(String txtj4) {
		this.j4Lbl.setText(txtj4);
	}

	public void setTextJ5Lbl(String txtj5) {
		this.j5Lbl.setText(txtj5);
	}

	public void setTextJ6Lbl(String txtj6) {
		this.j6Lbl.setText(txtj6);
	}

	public JButton getValiderCreationPartieBt() {
		return validerCreationPartieBt;
	}

	public Image getImageFromName(String nomFichier) {
		InputStream i = this.getClass().getClassLoader().getResourceAsStream(nomFichier);
		Image logo = null;
		try {
			logo = ImageIO.read(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logo;
	}
}

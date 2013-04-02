/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.RegistrazioneSpesa;

import model.Spesa;

public class InserisciSpesaPanel extends JPanel implements ActionListener{

	private JLabel valore, tipologia;
	private JTextField valoreInserimento;

	private JComboBox<String> listaTipologia;
	private JButton registraSpesa;
	
	private String tipologiaScelta = null;
	private float prezzo = 0;
	
	public InserisciSpesaPanel() {
		super();
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		valore = new JLabel("Valore:");
		valore.setAlignmentX(CENTER_ALIGNMENT);
		valore.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		valoreInserimento = new JTextField();
		valoreInserimento.setAlignmentX(CENTER_ALIGNMENT);
		valoreInserimento.setHorizontalAlignment(JTextField.CENTER);
		valoreInserimento.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		tipologia = new JLabel("Tipologia:");
		tipologia.setAlignmentX(CENTER_ALIGNMENT);
		tipologia.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		listaTipologia = new JComboBox<String>();
		listaTipologia.addItem("Seleziona...");
		listaTipologia.addItem("Alimentari");
		listaTipologia.addItem("Ricarica cellulare");
		listaTipologia.addItem("SKY");
		listaTipologia.addActionListener(this);
		listaTipologia.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		registraSpesa = new JButton("Salva");
		registraSpesa.addActionListener(this);
		registraSpesa.setAlignmentX(CENTER_ALIGNMENT);
		registraSpesa.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(valore); this.add(valoreInserimento);
		this.add(tipologia); this.add(listaTipologia);
		this.add(registraSpesa);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object evento = arg0.getSource();
		if (evento == listaTipologia) {
			tipologiaScelta = listaTipologia.getItemAt(listaTipologia.getSelectedIndex());
		}
		if (evento == registraSpesa) {
			
			//Controllo che il numero inserito sia corretto. Va ampliato:
			//devo essere sicuro che ci siano al massimo 2 decimali e che
			//il formato sia riconosciuto correttamente a seconda del paese
			try {
			prezzo = Float.parseFloat(getValoreInserimento());
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Inserire solo numeri.",
						"Prezzo non valido", JOptionPane.ERROR_MESSAGE);
			}
			//Invia la spesa al database
			if (tipologiaScelta != null && prezzo != 0) {
				Spesa spesa = new Spesa(prezzo, tipologiaScelta);
				RegistrazioneSpesa rSpesa = new RegistrazioneSpesa(spesa);
				boolean registrazioneEffettuata = rSpesa.effettuaRegistrazione();
				if (registrazioneEffettuata) {
					//Implementa avviso in South
				}
				else {
					//Implementa avviso in South
				}
			}
		}
	}
	
	public String getValoreInserimento() {
		return valoreInserimento.getText();
	}
}

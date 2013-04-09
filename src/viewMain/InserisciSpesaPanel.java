/**
 *
 *@author Andrea Candini <formulauff@gmail.com>
 */

package viewMain;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Spesa;
import controller.RegistrazioneSpesa;

public class InserisciSpesaPanel extends JPanel implements ActionListener{

	private JLabel valore, tipologia;
	private JTextField valoreInserimento;

	private JComboBox<String> listaTipologia;
	private JButton registraSpesa;

	private String tipologiaScelta = null;
	private BigDecimal prezzo = null;
	private String[] listaTipologie = null;
	
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

		//Ricevo le tipologie dinamicamente, così da avere la lista
		//sempre aggiornata in caso di login da macchine differenti
		listaTipologie = aggiornaListaTipologia();
		listaTipologia = new JComboBox<String>();
		for(int i = 0; i < listaTipologie.length; i++) {
			listaTipologia.addItem(listaTipologie[i]);
		}
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
				String valoreDaConvertire = convertiSpesa(getValoreInserimento());
				prezzo = new BigDecimal(valoreDaConvertire);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Inserire solo numeri.",
						"Prezzo non valido", JOptionPane.ERROR_MESSAGE);
			}
			//Invia la spesa al database
			if (tipologiaScelta != null && !prezzo.equals(new BigDecimal(0))) {
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

	//Il metodo parseFloat non riconosce la cultura locale, come tale
	//è necessario fornirgli la stringa nel formato che si aspetta
	private String convertiSpesa(String valoreDaConvertire) {

		int lunghezza = valoreDaConvertire.length();
		String risultato = "";

		for(int i = 0; i < lunghezza; i++) {
			String c = "" + valoreDaConvertire.charAt(i);

			//Copio tutti i valori fino ai decimali se non sono '.' o ','
			if (!c.equals(",") && !c.equals(".") && i < lunghezza - 3)
				risultato += c;

			//Gestisco la virgola o il punto dei decimali
			if (c.equals(",") && i == lunghezza - 3)
				risultato += ".";
			else if (c.equals(".") && i == lunghezza - 3)
				risultato += c;
			else if (i == lunghezza - 3)
				risultato += c;

			if (c.equals(",") && i == lunghezza - 2)
				risultato += ".";
			else if (c.equals(".") && i == lunghezza - 2)
				risultato += c;
			else if (i == lunghezza - 2)
				risultato += c;

			if (i == lunghezza - 1)
				risultato += c;
		}

		return risultato;
	}

	public String[] aggiornaListaTipologia() {
		RegistrazioneSpesa reg = new RegistrazioneSpesa();
		return reg.aggiornaListaTipologia();
	}
	
	public String getValoreInserimento() {
		return valoreInserimento.getText();
	}
	
	public void setValoreInserimento() {
		valoreInserimento.setText("20");
	}

	//Metodo invocato per aggiornare il Panel con la
	//nuova tipologia inserita
	public void aggiornaInserisciSpesa(JFrame frame,
			InserisciSpesaPanel inserisciPanel) {
		frame.getContentPane().remove(inserisciPanel);
		frame.add(new InserisciSpesaPanel(), BorderLayout.EAST);
		frame.getContentPane().invalidate();
		frame.getContentPane().validate();
	}

}

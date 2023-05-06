package gui;

import java.awt.BorderLayout;
import org.json.JSONObject;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class VentanaOperaciones extends JFrame {

	private JPanel panelPrincipal;
	private JTextField txtCantidad;
	private JTextField txtMostrar;

	
	public VentanaOperaciones() {
		setTitle("Mi Conversor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 324);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(173, 216, 230));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		//Agregar los componentes a un JPanel o JFrame
		
		JLabel lbltitle = new JLabel("Conversor de Monedas");
		lbltitle.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setBounds(107, 25, 250, 15);
		panelPrincipal.add(lbltitle);		
		
		
		JComboBox cbxmoneda1 = new JComboBox<String>();
		cbxmoneda1.setBounds(98, 109, 112, 22);
		cbxmoneda1.addItem("BOB");
		cbxmoneda1.addItem("ARS");
		cbxmoneda1.addItem("CLP");
		cbxmoneda1.addItem("USD");
		cbxmoneda1.addItem("EUR");
		cbxmoneda1.addItem("CAD");
		cbxmoneda1.addItem("CRC");
		cbxmoneda1.addItem("JPY");
		cbxmoneda1.addItem("KRW");
		cbxmoneda1.addItem("MXN");
		cbxmoneda1.addItem("PEN");
		cbxmoneda1.addItem("PYG");
		
		panelPrincipal.add(cbxmoneda1);
		
		JComboBox cbxmoneda2 = new JComboBox<String>();
		cbxmoneda2.setBounds(270, 109, 104, 22);
		cbxmoneda2.addItem("USD");
		cbxmoneda2.addItem("BOB");
		cbxmoneda2.addItem("ARS");
		cbxmoneda2.addItem("CLP");
		cbxmoneda2.addItem("EUR");
		cbxmoneda2.addItem("CAD");
		cbxmoneda2.addItem("CRC");
		cbxmoneda2.addItem("JPY");
		cbxmoneda2.addItem("KRW");
		cbxmoneda2.addItem("MXN");
		cbxmoneda2.addItem("PEN");
		cbxmoneda2.addItem("PYG");
		panelPrincipal.add(cbxmoneda2);		
		
		
		JButton btnConvertir = new JButton("Convertir");
		btnConvertir.setBackground(new Color(100, 149, 237));
		btnConvertir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConvertir.setBounds(198, 168, 104, 23);
		btnConvertir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
		            String amountStr = txtCantidad.getText();
		            double amount = Double.parseDouble(amountStr);
		            
		            String fromCurrency = cbxmoneda1.getSelectedItem().toString();
		            String toCurrency = cbxmoneda2.getSelectedItem().toString();
		            
		            URL url = new URL("https://api.exchangerate-api.com/v4/latest/" + fromCurrency);
		            HttpURLConnection con = (HttpURLConnection) url.openConnection();
		            con.setRequestMethod("GET");
		            
		            int status = con.getResponseCode();
		            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		            String inputLine;
		            StringBuffer content = new StringBuffer();
		            
		            while ((inputLine = in.readLine()) != null) {
		                content.append(inputLine);
		            }
		            in.close();
		            con.disconnect();
		            
		            JSONObject json = new JSONObject(content.toString());
		            double fromRate = json.getJSONObject("rates").getDouble(toCurrency);
		            double toRate = json.getJSONObject("rates").getDouble(fromCurrency);
		            double convertedAmount = amount * fromRate;
		            
		            String result = String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency);
		            txtMostrar.setText(result);
		            
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
				
			}
		});
		
		panelPrincipal.add(btnConvertir);	
		
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidad.setBounds(154, 71, 72, 15);
		panelPrincipal.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCantidad.setBounds(225, 68, 104, 22);
		panelPrincipal.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtMostrar = new JTextField();
		txtMostrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMostrar.setBounds(135, 202, 222, 33);
		txtMostrar.setBorder(null);
		panelPrincipal.add(txtMostrar);
		txtMostrar.setColumns(10);
		
		JLabel lbl_de = new JLabel("De:");
		lbl_de.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_de.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_de.setBounds(50, 113, 46, 14);
		panelPrincipal.add(lbl_de);
		
		JLabel lbl_para = new JLabel("A:");
		lbl_para.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_para.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_para.setBounds(235, 113, 31, 14);
		panelPrincipal.add(lbl_para);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCantidad.setText("");
				txtCantidad.requestFocus();
				txtMostrar.setText("");
				
			}
		});
		btnLimpiar.setHorizontalAlignment(SwingConstants.LEFT);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnLimpiar.setBackground(new Color(100, 149, 237));
		btnLimpiar.setBounds(339, 71, 72, 17);
		panelPrincipal.add(btnLimpiar);
		
		
	}
}

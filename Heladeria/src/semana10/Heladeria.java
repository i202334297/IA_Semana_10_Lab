package semana10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;

public class Heladeria extends JFrame implements ActionListener {

	// Declaraci�n de variables
	private static final long serialVersionUID = 9206324162700448001L;
	private JPanel contentPane;
	private JLabel lblHelado;
	private JLabel lblCantidad;
	private JComboBox<String> cboHelado;
	private JTextField txtCantidad;
	private JButton btnProcesar;
	private JButton btnBorrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	// Lanza la aplicaci�n
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Heladeria frame = new Heladeria();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea la GUI
	public Heladeria() {
		setTitle("Heladeria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblHelado = new JLabel("Helado");
		lblHelado.setBounds(10, 11, 80, 14);
		contentPane.add(lblHelado);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 36, 80, 14);
		contentPane.add(lblCantidad);

		cboHelado = new JComboBox<String>();
		cboHelado.setModel(new DefaultComboBoxModel<String>(new String[] {"Sol", "Fresa", "Mar", "Rico"}));
		cboHelado.setBounds(100, 8, 100, 20);
		contentPane.add(cboHelado);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(100, 33, 100, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);

		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(this);
		btnProcesar.setBounds(335, 7, 89, 23);
		contentPane.add(btnProcesar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(this);
		btnBorrar.setBounds(335, 32, 89, 23);
		contentPane.add(btnBorrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 61, 414, 190);
		contentPane.add(scpScroll);

		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);
	}

	// Direcciona eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBorrar) {
			actionPerformedBtnBorrar(arg0);
		}
		if (arg0.getSource() == btnProcesar) {
			actionPerformedBtnProcesar(arg0);
		}
	}

	// Procesa la pulsaci�n del bot�n Borrar
	protected void actionPerformedBtnBorrar(ActionEvent arg0) {
		txtCantidad.setText("");
		txtS.setText("");
		cboHelado.setSelectedIndex(0);
		txtCantidad.requestFocus();
	}

	// Procesa la pulsaci�n del bot�n Procesar
	protected void actionPerformedBtnProcesar(ActionEvent arg0) {
		int     cant = obtenerCantidad();
		String marca = obtenerMarca();
		double   pre = obtenerPrecio(marca);
		double	impC = CalcularImpCompra(pre,cant);
		double  impD = CalcularImpDescuento(impC,cant); 
		double  impP = CalcularImpPagar(impC,impD);
		int     obs  = CalcularCaramelos(marca,cant);
		
		txtS.setText("REPORTE HELADERIA 2023 \n\n");
		imprimir("Importe compra    : " + decimalFormat(impC));
		imprimir("Importe descuento : " + decimalFormat(impD));
		imprimir("Importe pagar     : " + decimalFormat(impP));
		imprimir("Cantidad Caramelo : " + obs);
	}
	int obtenerCantidad(){
		return Integer.parseInt(txtCantidad.getText());
	}
	String obtenerMarca(){
		return cboHelado.getSelectedItem().toString();
	}
	double obtenerPrecio(String marca){
		if     (marca=="Sol")  {return 2.5;}
		else if(marca=="Fresa"){return 1.3;}
		else if(marca=="Mar")  {return 2.0;}
		else                   {return 1.7;}
	}
	
	double CalcularImpCompra(double pre,int cant){
		return pre*cant;
	}
	double CalcularImpDescuento (double impC,int cant){
		if (cant <10){return impC*0.05;}
		else if (cant >= 10 && cant < 20){return impC*0.07;}
		else if (cant >= 30 && cant < 30){return impC*0.09;}
		else{return impC*0.11;}
	}
	
	double CalcularImpPagar (double impC,double impD){
		return impC-impD;
	}
	
	
	
	int CalcularCaramelos(String marca, int cant){
		
		if (marca=="Sol"){return 1*cant;}
		else if (marca=="Mar"){return 2 * cant;}
		else {return 6;}
		
		
	}
	public String decimalFormat(double p) {
		return String.format("%.2f",p);
	}
	public void imprimir(String s) {
		txtS.append(s + "\n");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
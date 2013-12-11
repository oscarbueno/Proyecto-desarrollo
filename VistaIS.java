package Inicio_sesion;

import javax.swing.*;

public class VistaIS extends JFrame{
	private JButton iniciar;
	private JButton cancelar;
	private JLabel nombreUsuario;
	private JLabel contrasena;
	private JLabel logo;
	private JTextField usuario;
	private JTextField contraUsuario;
	
	VistaIS(){
		setSize(500, 500);
		
		iniciar=new JButton("Iniciar");
		cancelar=new JButton("cancelar");
		nombreUsuario=new JLabel("Nick");
		contrasena=new JLabel("contraseña");
		usuario=new JTextField();
		contraUsuario=new JTextField();
		setLayout(null);
		nombreUsuario.setBounds(50, 50,100 ,30 );
		usuario.setBounds(100, 50, 100, 30);
		
		contraUsuario.setBounds(100,100 , 100, 30);
		contrasena.setBounds(50, 100, 100, 30);
		
		add(iniciar);
		add(cancelar);
		add(nombreUsuario);
		add(contrasena);
		add(usuario);
		add(contraUsuario);
		setVisible(true);
	}
	
	public JButton getIniciar() {
		return iniciar;
	}
	public void setIniciar(JButton iniciar) {
		this.iniciar = iniciar;
	}
	public JButton getCancelar() {
		return cancelar;
	}
	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
	}
	public JTextField getUsuario() {
		return usuario;
	}
	public void setUsuario(JTextField usuario) {
		this.usuario = usuario;
	}
	public JTextField getContraUsuario() {
		return contraUsuario;
	}
	public void setContraUsuario(JTextField contraUsuario) {
		this.contraUsuario = contraUsuario;
	}
	
	
	
	

}

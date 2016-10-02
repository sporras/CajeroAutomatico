
package cajeroautomatico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


public class FuncionesCajero extends JFrame  {
    JTextField pantalla;
    JPanel panelOperaciones, panelEventos;
    JLabel indicaciones=new JLabel ("Ingresa La cantidad y presiona operacion finalizada");
    double saldo=0;
    int opFinalizada=0;
    int pregunta;
    String nombre="";
    
    public FuncionesCajero()
    {
        setSize(650, 500);
	setTitle("Cajero Automatico ");
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
	setLayout(null);
        
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());
        
        pantalla = new JTextField("", 20);
	pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
	pantalla.setFont(new Font("Arial", Font.BOLD, 25));
	pantalla.setHorizontalAlignment(JTextField.RIGHT);
	pantalla.setEditable(false);
	pantalla.setBackground(Color.WHITE);
	panel.add("North", pantalla);
    
        panelOperaciones = new JPanel();
        panelOperaciones.setLayout(new GridLayout(4,3));
        panelOperaciones.setBorder(new EmptyBorder(4,4,4,4));
        
        BotonAccion("Crear cuenta");
	BotonAccion("Depositar");
        BotonAccion("Retirar");
        BotonAccion("Ver saldo");
        BotonAccion("Donativo");
        BotonAccion("Operacion Finalizada");
        BotonAccion("Salir");
        
        panel.add("Center", panelOperaciones);
        
        panelEventos = new JPanel();
        panelEventos.setLayout(new GridLayout(5,2)); 
        panelEventos.setBorder(new EmptyBorder(4, 4, 4, 4)); 
        
        panelEventos.add(indicaciones);       
        indicaciones.setVisible(false);
        panel.add("East", panelEventos);

	
                
    }
    
    private void BotonAccion(String operacion) {
       JButton btn = new JButton(operacion);
		btn.setForeground(Color.RED);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}

           
		});

		panelOperaciones.add(btn);
    }
    
    private void operacionPulsado(String tecla) 
    {
    switch(tecla)
    {
        case "Ver saldo":
            if(nombre.equals(""))
                        {
                            JOptionPane.showMessageDialog(null,"Registrate con tu nombre");
                        }
                else
                {
                    pantalla.setText(""+saldo);
                }
        break;
            case "Depositar":
                if(nombre.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Registrate con tu nombre");
                }
                else
                {          
                    opFinalizada=2;
                    indicaciones.setVisible(true);
                    pantalla.setEditable(true);
                    pantalla.setText("");
                    pantalla.requestFocus();     
                    }
                break;
            case "Retirar":
                if(nombre.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Registrate con tu nombre");
                }
                else
                {
                    opFinalizada=3;
                    indicaciones.setVisible(true);
                    pantalla.setEditable(true);
                    pantalla.setText("");
                    pantalla.requestFocus();
                }
                break;
            case "Crear cuenta":
                if(nombre.equals(""))
                {
                    indicaciones.setText("Escribe tu nombre y presiona operacion finalizada");
                    indicaciones.setVisible(true);
                    pantalla.setEditable(true);
                    pantalla.setText("");
                    pantalla.requestFocus();
                    opFinalizada=1;
                    saldo=0;
                }
                else
                {
                    pregunta= JOptionPane.showConfirmDialog(null,"¿Seguro que desea salir?");
                    
                    if(pregunta==JOptionPane.YES_OPTION)
                    {
                        saldo=0;
                        setTitle("");
                        nombre="";
                        JOptionPane.showMessageDialog(null, "Vuelve pronto");
                    }
                }  break;
            case "Donativo":
                if(nombre.equals(""))
                {
                     JOptionPane.showMessageDialog(null,"Registrate con tu nombre");
                }
                else{
                int pregunta= JOptionPane.showConfirmDialog(null,"¿Desea donar 10 pesos?");
                if(pregunta == JOptionPane.YES_OPTION)
                   {    
                     saldo=saldo-10;
                      pantalla.setText(""+saldo);
                      }
                 
                }break;
                case "Operacion Finalizada":
                if(opFinalizada == 0)
                {
                    JOptionPane.showMessageDialog(null,"No has ejecutado ninguna instrucción");
                }
                else
                    if(opFinalizada == 1)
                    {
                        nombre=pantalla.getText();
                        JOptionPane.showMessageDialog(null,"Bienvenido" +"  "+nombre);
                        setTitle("Cuenta de"+"  "+nombre);
                    }
                    else
                        if(opFinalizada==2)
                        {
                            saldo=saldo+Double.parseDouble(pantalla.getText());
                            pantalla.setText(""+saldo);
                            JOptionPane.showMessageDialog(null,"Operacion finalizada");
                        }
                        else
                            if(opFinalizada==3)
                            { if (saldo-Double.parseDouble(pantalla.getText())<0)
                            {
                                JOptionPane.showMessageDialog(null,"La cantidad de retiro es mayor al saldo solo cuentas con:"+saldo+" "+"pesos");
                            }
                            else
                            {
                                if(pantalla.getText().contains("."))
                                {
                                    JOptionPane.showMessageDialog(null,"Sólo valores enteros");
                                }
                                else
                                {
                                    saldo=saldo-Double.parseDouble(pantalla.getText());
                                    pantalla.setText(""+saldo);
                                    JOptionPane.showMessageDialog(null,"Operacion finalizada");
                                }
                            }
                            }
                    indicaciones.setVisible(false);
                    indicaciones.setText("Ingresa La cantidad  y presiona operacion finalizada");                  
                    pantalla.setEditable(false);
                    pantalla.setText("");
                break;
            case "Salir":
                if(nombre.equals(""))
                            {
                                JOptionPane.showMessageDialog(null, "No has iniciado sesión");
                            }else
                            {
                             
                                int pregunta = JOptionPane.showConfirmDialog(null,"¿Seguro que desea salir?");
                                
                                if(pregunta==JOptionPane.YES_OPTION)
                                {
                                    pantalla.setText("");
                                    saldo=0;
                                    setTitle("");
                                    JOptionPane.showMessageDialog(null, "Adios");
                                    nombre="";
                                }
                            }
                break;   
    }
    }
}

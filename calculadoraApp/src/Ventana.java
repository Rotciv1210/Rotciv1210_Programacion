import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame implements ActionListener{

    private JTextField pantalla;
    private double resultado = 0;
    private String guardarOperadorAnterior = "=";
    private boolean nuevoNumero = true;



    public Ventana(){

        setTitle("Calculadora Simple");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        pantalla= new JTextField("0");
        pantalla.setBounds(20,20,340,40);
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        panel.setLayout(null);
        panel.add(pantalla);

        String[] botones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "C", "+",
                "="
        };

        int x = 20;
        int y = 80;
        int ancho = 80;
        int alto = 50;
        int contador = 0;

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.setBounds(x,y,ancho,alto);
            boton.addActionListener(this);
            panel.add(boton);

            x += ancho +10;
            contador++;
            if (contador % 4 == 0){

                x = 20;
                y += alto +10;
            }
        }

        add(panel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("[0-9\\.]")) {
            if (nuevoNumero) {
                pantalla.setText(comando);
                nuevoNumero = false;
            } else {
                pantalla.setText(pantalla.getText() + comando);
            }
        } else if (comando.matches("[+\\-*/]")) {
            calcular(Double.parseDouble(pantalla.getText()));
            guardarOperadorAnterior = comando;
            nuevoNumero = true;
        } else if (comando.equals("=")) {
            calcular(Double.parseDouble(pantalla.getText()));
            pantalla.setText(String.valueOf(resultado));
            guardarOperadorAnterior = "=";
            nuevoNumero = true;
        } else if (comando.equals("C")) {
            pantalla.setText("0");
            resultado = 0;
            guardarOperadorAnterior = "=";
            nuevoNumero = true;
        }
    }


    private void calcular(double valor){

        switch (guardarOperadorAnterior){

            case "+": resultado += valor;
                break;
            case "-": resultado -= valor;
                break;
            case "/": resultado /= valor;
                break;
            case "*": resultado *= valor;
                break;
            case "=": resultado = valor;
                break;
        }
    }
}

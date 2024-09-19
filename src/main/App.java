package main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App implements MouseListener {
    int anchoPantalla = 600;
    int altoPantalla = 500;

    String rutaArchivoOrigen;
    String rutaArchivoFinal;

    int btnAnchoOpcion = 200;
    int btnAltoOpcion = 100;

    Font fuentePredeterminada = new Font("Arial", Font.PLAIN, 18);

    JFrame frame;
    Dimension miPantalla;
    Toolkit tools;
    JPanel contenedor;
    JPanel contenedorOpciones;

    JTextField input;

    JPanel contenedorRutaOrigen;
    JPanel contenedorRutaFinal;

    JButton btnABinario;
    JButton btnAFichero;

    public App() {
        tools = Toolkit.getDefaultToolkit();
        miPantalla = tools.getScreenSize();

        frame = new JFrame("Binario o fichero");
        frame.setSize(new Dimension(miPantalla.width/2, miPantalla.height/2));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.GRAY);

        anidirContenedor();

        frame.setVisible(true);
    }

    void anidirContenedor(){
        contenedor = new JPanel();
        contenedor.setPreferredSize(new Dimension(frame.getSize().width, frame.getSize().height));
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));

        contenedorRutaOrigen = anidirRuta("Introduce la ruta del archivo origen");
        contenedorRutaFinal = anidirRuta("Introduce la ruta de la carpeta final");

        contenedor.add(contenedorRutaOrigen);
        contenedor.add(contenedorRutaFinal);

        anidirOpciones();

        frame.add(contenedor);
        frame.pack();
    }

    JPanel anidirRuta(String rutaPlaceholderString){
        JPanel contenedorRuta = new JPanel();
        contenedorRuta.setSize(new Dimension(contenedor.getSize().width, contenedor.getSize().height/3));
        contenedorRuta.setBackground(Color.RED);
        //contenedorRuta.setLayout(new BoxLayout(contenedorRuta, BoxLayout.X_AXIS));
        contenedorRuta.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        // Instanciar input
        input = new JTextField();
        input.setSize(new Dimension(contenedorRuta.getSize().width/2, 50));
        input.setText(rutaPlaceholderString);
        input.setFont( fuentePredeterminada );
        contenedorRuta.add(input);

        // Instanciar texto de opcion
        JLabel opcion = new JLabel(" o ");
        opcion.setFont(fuentePredeterminada);
        contenedorRuta.add(opcion);

        // Instanciar boton de examinar
        JButton btnExaminar = new JButton();
        btnExaminar.setText(rutaPlaceholderString);
        btnExaminar.setSize(new Dimension(300, 100));
        btnExaminar.setBorder(null);
        // Escuchador para este boton
        btnExaminar.addMouseListener(this);
        contenedorRuta.add(btnExaminar);

        //contenedor.add(contenedorRuta);
        return contenedorRuta;
    }

    void anidirOpciones(){
        contenedorOpciones = new JPanel();
        contenedorOpciones.setSize(new Dimension( contenedor.getSize().width, contenedor.getSize().height/2 ));
        contenedorOpciones.setLayout(new BoxLayout(contenedorOpciones, BoxLayout.Y_AXIS));
        contenedorOpciones.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        contenedorOpciones.setBackground(Color.BLUE);

        JPanel contenedorBtnsConversion = new JPanel();
        contenedorBtnsConversion.setBackground(null);

        // Boton de conversion a binario
        btnABinario = new JButton("Fichero -> binario");
        btnABinario.setSize(new Dimension(btnAnchoOpcion, btnAltoOpcion));
        btnABinario.setFont(fuentePredeterminada);
        // Evento de mouselistener para este boton
        btnABinario.addMouseListener(this);
        contenedorBtnsConversion.add(btnABinario);

        // Boton de conversion a fichero
        btnAFichero = new JButton("Binario -> fichero");
        btnAFichero.setSize(new Dimension(btnAnchoOpcion, btnAltoOpcion));
        btnAFichero.setFont(fuentePredeterminada);
        // Evento de mouselistener para este boton
        btnAFichero.addMouseListener(this);
        contenedorBtnsConversion.add(btnAFichero);

        contenedorOpciones.add(contenedorBtnsConversion);
        contenedor.add(contenedorOpciones);

    }

    void conversionTerminado(){
        JPanel contenedorTextTerminado = new JPanel();
        contenedorTextTerminado.setSize(new Dimension(contenedorOpciones.getSize().width, 200));
        contenedorTextTerminado.setBackground(null);
        
        JLabel textoTerminado = new JLabel("Archivo convertido!");
        textoTerminado.setFont(fuentePredeterminada);
        textoTerminado.setForeground(Color.white);
        
        contenedorTextTerminado.add(textoTerminado);
        contenedorOpciones.add(contenedorTextTerminado);

        // Temporizador
        Timer conversionTerminadoTimer = new Timer();

        TimerTask conversionDelay = new TimerTask() {
            @Override
            public void run(){
                contenedorOpciones.remove(contenedorTextTerminado);
            }
        };
        
        conversionTerminadoTimer.schedule(conversionDelay, 3000);

        conversionTerminadoTimer.cancel();
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object boton = e.getSource();

        String campoDeTextoOrigen = ((JTextField) contenedorRutaOrigen.getComponent(0)).getText();
        String campoDeTextoFinal = ((JTextField) contenedorRutaFinal.getComponent(0)).getText();

        if( boton == contenedorRutaOrigen.getComponent(2) ) {
            System.out.println( "Boton de examinar origen" );
            elegirFichero(1);
        }
        if( boton == contenedorRutaFinal.getComponent(2) ) {
            System.out.println( "Boton de examinar final" );
            elegirFichero(2);
        }
        if( boton == btnABinario ) {
            System.out.println( "Boton conversion binario" );
            fichero_a_binario conversionb = new fichero_a_binario(new File(campoDeTextoOrigen), new File(campoDeTextoFinal));
             conversionTerminado();
        }
        if( boton == btnAFichero ) {
            System.out.println( "Boton conversion fichero" );
            binario_a_fichero conversionf = new binario_a_fichero(new File(campoDeTextoOrigen), new File(campoDeTextoFinal));
            conversionTerminado();
        }
    }

    void elegirFichero(int opcion){
        JFileChooser elegirFichero = new JFileChooser();
        elegirFichero.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        // Filtros de extensiones
        FileNameExtensionFilter filtroJPG = new FileNameExtensionFilter("Imagenes JPG", "jpg", "jpeg");
        FileNameExtensionFilter filtroPNG = new FileNameExtensionFilter("Imagenes PNG", "png");
        FileNameExtensionFilter filtroTXT = new FileNameExtensionFilter("ficheros de texto", "png");
        // Filtro para seleccionar carpetas
        FileNameExtensionFilter filtroCarpetas = new FileNameExtensionFilter("Carpetas", " ");

        elegirFichero.setFileFilter(filtroJPG);
        elegirFichero.setFileFilter(filtroPNG);
        elegirFichero.setFileFilter(filtroTXT);
        elegirFichero.setFileFilter(filtroCarpetas);

        int valorRetorno = elegirFichero.showOpenDialog(frame);

        if( valorRetorno == elegirFichero.APPROVE_OPTION ) {
            File archivoAConvertir = elegirFichero.getSelectedFile();
            
            if (opcion == 1) {
                rutaArchivoOrigen = archivoAConvertir.getAbsolutePath();
                JTextField btnArchivoOrigen = (JTextField) contenedorRutaOrigen.getComponent(0);
                btnArchivoOrigen.setText(rutaArchivoOrigen);
                System.out.println("Archivo origen: "+rutaArchivoOrigen);

            }else{
                rutaArchivoFinal = archivoAConvertir.getAbsolutePath();
                JTextField btnArchivoFinal = (JTextField) contenedorRutaFinal.getComponent(0);
                btnArchivoFinal.setText(rutaArchivoFinal);
                System.out.println("Carpeta final: "+rutaArchivoFinal);

            }
        }else{
            System.out.println("Archivo incorrecto");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}

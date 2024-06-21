import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbolGUI {
    private JTable tbMatrizAdyacencia;
    private JTextArea textArea;
    private JTextField txtRaiz;
    private JButton btnAgregarNodo;
    private JButton btnDibujarArbol;
    private JButton btnRecorridoAnchura;
    private JButton btnRecorridoProfundidad;
    private JButton btnPreorden;
    private JButton btnInorden;
    private JLabel lblNodo;
    private JLabel lblRaiz;
    private JLabel lblHoja;
    private JComboBox cbIzqDer;
    private JPanel panelArbol;
    private JPanel panelGeneral;
    private JPanel panelDatos;
    private JLabel lblRecorridos;
    private JButton btnPostorden;
    private JButton btnMatrizAdyacencia;

    private Arbol arbol=new Arbol();
    private ArbolGrafico arbolGrafico=new ArbolGrafico(arbol);
    private DefaultTableModel modeloTabla=new DefaultTableModel();

    public ArbolGUI() {

        btnAgregarNodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String etiqueta = arbol.getEtiquetaNodoSiguiente();
                    Nodo nuevoNodo = new Nodo(0, 0, etiqueta);
                    String etiquetaPadre = txtRaiz.getText().trim();
                    boolean esIzquierda = Boolean.parseBoolean(cbIzqDer.getSelectedItem().toString());

                    Nodo nodoPadre= null;
                    for (Nodo nodo : arbol.getNodos()) {
                        if (nodo.etiqueta.equals(etiquetaPadre)) {
                            nodoPadre = nodo;
                            break;
                        }
                    }
                    arbol.anadirNodo(nuevoNodo, nodoPadre, esIzquierda);
                    imprimirArbol();
                    arbolGrafico.repaint();
                    arbolGrafico.paintComponent(panelArbol.getGraphics());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar nodo: " + ex.getMessage());
                }
            }
        });
        btnDibujarArbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arbolGrafico.setVisible(true);
                arbolGrafico.repaint();
                arbolGrafico.paintComponent(panelArbol.getGraphics());
            }
        });
        btnRecorridoAnchura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.bfs();
                textArea.append("Recorrido en Anchura (BFS): " + resultado + "\n");
            }
        });
        btnRecorridoProfundidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.dfs();
                textArea.append("Recorrido en Profundidad (DFS): " + resultado + "\n");
            }
        });
        btnPreorden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.preorden();
                textArea.append("Preorden: " + resultado + "\n");
            }
        });
        btnInorden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.inorden();
                textArea.append("Inorden: " + resultado + "\n");
            }
        });
        btnPostorden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.postorden();
                textArea.append("Postorden: " + resultado + "\n");
            }
        });
        btnMatrizAdyacencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    mostrarMatrizAdyacencia();
            }
        });
    }

    private void imprimirArbol() {
        textArea.setText("");
        textArea.append("Nodos:\n");
        for (Nodo nodo : arbol.getNodos()) {
            textArea.append(nodo.etiqueta + ": " + nodo.toString() + "\n");
        }
    }

    private void mostrarMatrizAdyacencia() {


       Object[][] matriz = arbol.getMatrizAdyacencia();
        String[] nombreColumnas = new String[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            nombreColumnas[i] = String.valueOf((char) ('A' + i));
        }

        modeloTabla.setDataVector(matriz , nombreColumnas);
       tbMatrizAdyacencia.setModel(modeloTabla);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ArbolGUI");
        frame.setContentPane(new ArbolGUI().panelGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

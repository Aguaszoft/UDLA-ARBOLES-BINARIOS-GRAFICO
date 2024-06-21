import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ArbolGrafico extends JPanel {
    private Arbol arbol;

    public ArbolGrafico (Arbol arbol) {
        this.arbol = arbol;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));

        ArrayList<Nodo> nodos = arbol.getNodos();
        dibujarArbol(g2d, arbol.getRaiz(), getWidth() / 2, 30, getWidth() / 4, 50);

        for (Nodo nodo : nodos) {
            g2d.fillOval(nodo.x - 5, nodo.y - 5, 10, 10);
            g2d.drawString(nodo.etiqueta, nodo.x + 10, nodo.y);
        }
    }

    private void dibujarArbol(Graphics2D g2d, Nodo nodo, int x, int y, int dimensionX, int dimensionY) {
        if (nodo != null) {
            nodo.x = x;
            nodo.y = y;
            if (nodo.izquierda != null) {
                g2d.drawLine(x, y, x - dimensionX, y + dimensionY);
                dibujarArbol(g2d, nodo.derecha, x - dimensionX, y + dimensionY, dimensionX / 2, dimensionY);
            }
            if (nodo.derecha != null) {
                g2d.drawLine(x, y, x + dimensionX, y + dimensionY);
                dibujarArbol(g2d, nodo.derecha, x + dimensionX, y + dimensionY, dimensionX / 2, dimensionY);
            }
        }
    }
}
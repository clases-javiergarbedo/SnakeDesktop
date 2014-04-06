package snake;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Snake {

    private char[][] tablero;
    private int tamFila;
    private int tamCol;
    private ArrayList<Point> listaPosSnake;
    private byte sentidoMov;
    private Random random = new Random();

    public static final char VACIO = '.';
    public static final char CABEZA = '@';
    public static final char CUERPO = '=';
    public static final char MURO = '#';
    public static final char FRUTA = '$';

    private final int MARGEN = 2;
    //El tamaño inicial del cuerpo no puede ser mayor que el margen
    private final int TAM_INI_CUERPO = 2;

    public static final byte DERECHA = 0;
    public static final byte IZQUIERDA = 1;
    public static final byte ARRIBA = 2;
    public static final byte ABAJO = 3;

    /*
     Información contenida en el tablero:
     '.': Vacío
     '@': Cabeza
     '=': Cuerpo
     '#': Muro
     '$': Fruta
     */
    public Snake(int tamFila, int tamCol) {
        this.tamFila = tamFila;
        this.tamCol = tamCol;
        listaPosSnake = new ArrayList();
        sentidoMov = DERECHA;
        tablero = new char[tamFila][tamCol];
        //Inicializamos el tablero con todo vacío
        for (int f = 0; f < tamFila; f++) {
            for (int c = 0; c < tamCol; c++) {
                tablero[f][c] = VACIO;
            }
        }

        //Colocar la cabeza en posición aleatoria
        int cabezaFila = random.nextInt(tamFila - 2 * MARGEN) + MARGEN;
        int cabezaCol = random.nextInt(tamCol - 2 * MARGEN) + MARGEN;
        tablero[cabezaFila][cabezaCol] = CABEZA;
        //Guardar la cabeza en la lista de posiciones
        listaPosSnake.add(new Point(cabezaCol, cabezaFila));
        //Colocar el cuerpo
        for (int i = 1; i <= TAM_INI_CUERPO; i++) {
            tablero[cabezaFila][cabezaCol - i] = CUERPO;
            listaPosSnake.add(new Point(cabezaCol - i, cabezaFila));
        }
        //Colocar fruta
        generarFruta();
    }

    /**
     * Creación del juego a partir de un archivo de nivel almacenado como recurso
     * @param levelResourceName 
     */
    public Snake(String levelResourceName) {
        InputStreamReader levelReader = null;
        BufferedReader br = null;
        //Obtener tamaños del nivel
        try {
            levelReader = new InputStreamReader(getClass().getResourceAsStream(levelResourceName));
            br = new BufferedReader(levelReader);
            String levelLine = br.readLine();
            this.tamCol = levelLine.length();
            while (levelLine != null) {
                this.tamFila++;
                levelLine = br.readLine();
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }

        listaPosSnake = new ArrayList();
        sentidoMov = DERECHA;
        tablero = new char[tamFila][tamCol];
        //Guadar en la matriz el contenido del fichero de nivel
        int f = 0, c = 0;
        try {
            //Hay que volver a abrir el stream, ya que al terminar de leerlo antes, se abrá cerrado           
            levelReader = new InputStreamReader(getClass().getResourceAsStream(levelResourceName));
            br = new BufferedReader(levelReader);
            String levelLine = br.readLine();
            while (levelLine != null) {
                c = 0;
                for (int i = 0; i < levelLine.length(); i++) {
                    tablero[f][c] = levelLine.charAt(i);
                    //Guardar en lista si es una parte de la serpiente
                    if (tablero[f][c] == CABEZA) {
                        //Si es la cabeza, se guarda en la primera posición de la lista
                        listaPosSnake.add(0, new Point(c, f));
                    } else if (tablero[f][c] == CUERPO) {
                        listaPosSnake.add(new Point(c, f));
                    }
                    c++;
                }
                levelLine = br.readLine();
                f++;
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Colocar fruta
        generarFruta();
    }

    public char[][] getTablero() {
        return tablero;
    }

    public int getTamFila() {
        return tamFila;
    }

    public int getTamCol() {
        return tamCol;
    }

    public void setSentidoMov(byte sentidoMov) {
        this.sentidoMov = sentidoMov;
    }

    private void generarFruta() {
        int fila = random.nextInt(tamFila);
        int col = random.nextInt(tamCol);
        tablero[fila][col] = FRUTA;
    }

    @Override
    public String toString() {
        String retorno = "";
        for (int f = 0; f < tamFila; f++) {
            for (int c = 0; c < tamCol; c++) {
                retorno += tablero[f][c];
            }
            retorno += '\n';
        }
        return retorno;
    }

    public boolean mover() {
        //Guardar posición actual de la cabeza
        Point antiguaCabeza = listaPosSnake.get(0);
        Point nuevaCabeza = null;

        //Borrar la cola antigua
        Point antiguaCola = listaPosSnake.get(listaPosSnake.size() - 1);
        tablero[antiguaCola.y][antiguaCola.x] = VACIO;
        listaPosSnake.remove(listaPosSnake.size() - 1);

        //Mover el punto de la cabeza
        tablero[antiguaCabeza.y][antiguaCabeza.x] = CUERPO;
        switch (sentidoMov) {
            case DERECHA:
                nuevaCabeza = new Point(antiguaCabeza.x + 1, antiguaCabeza.y);
                break;
            case IZQUIERDA:
                nuevaCabeza = new Point(antiguaCabeza.x - 1, antiguaCabeza.y);
                break;
            case ARRIBA:
                nuevaCabeza = new Point(antiguaCabeza.x, antiguaCabeza.y - 1);
                break;
            case ABAJO:
                nuevaCabeza = new Point(antiguaCabeza.x, antiguaCabeza.y + 1);
                break;
        }
        tablero[nuevaCabeza.y][nuevaCabeza.x] = CABEZA;
        listaPosSnake.add(0, nuevaCabeza);

        return true;
    }

}

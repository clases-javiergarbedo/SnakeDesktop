package snake;

import java.util.Random;

public class Snake {
    
    private char[][] tablero;
    private int tamFila;
    private int tamCol;

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
        tablero = new char[tamFila][tamCol];
        //Inicializamos el tablero con todo vacío
        for(int f=0; f<tamFila; f++) {
            for(int c=0; c<tamCol; c++) {
                tablero[f][c] = VACIO;
            }
        }
        //Colocar la cabeza en posición aleatoria
        Random random = new Random();
        int cabezaFila = random.nextInt(tamFila-2*MARGEN)+MARGEN;
        int cabezaCol = random.nextInt(tamCol-2*MARGEN)+MARGEN;
        tablero[cabezaFila][cabezaCol] = CABEZA;
        //Colocar el cuerpo
        for(int i=1; i<=TAM_INI_CUERPO; i++) {
            tablero[cabezaFila][cabezaCol-i] = CUERPO;
        }
    }
    
    @Override
    public String toString() {
        String retorno = "";
        for(int f=0; f<tamFila; f++) {
            for(int c=0; c<tamCol; c++) {
                retorno += tablero[f][c];
            }
            retorno += '\n';
        }        
        return retorno;
    }
    
    public boolean mover(byte direccion) {
        
    }
    
}

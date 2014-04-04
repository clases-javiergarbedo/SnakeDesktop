package snake;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class SnakeLoop implements Runnable {

    private Snake snake;
    private boolean running = false;
    private long pauseSpeed;
    private SnakePanel snakePanel;

    public SnakeLoop(Snake snake, long pauseSpeed, SnakePanel snakePanel) {
        this.snake = snake;
        this.pauseSpeed = pauseSpeed;
        this.snakePanel = snakePanel;
    }

    /**
     * Permite cambiar el tiempo de pausa que se utiliza en el bucle
     * @param pauseSpeed 
     */
    public void setPauseSpeed(long pauseSpeed) {
        this.pauseSpeed = pauseSpeed;
    }
    
    /**
     * Ejecuta el método run como un hilo de ejecución paralelo
     */
    public void start() {
        running = true;
        //Crear un nuevo hilo de ejecución para esta clase
        Thread thread = new Thread(this);   
        //Llamada automática al método run
        thread.start();
    }
    
    /**
     * Permite finalizar la ejecución de este proceso
     */
    public void stop() {
        running = false;
    }
    
    /**
     * Este método es llamado de manera automática por el método start
     */
    @Override
    public void run() {
        while(running) {
            //Mover la serpiente
            snake.mover();
            //Pintar el juego
            snakePanel.repaint();
            //Pausar el juego durante el tiempo establecido
            try {
                Thread.sleep(pauseSpeed);
            } catch (InterruptedException ex) {
                Logger.getLogger(SnakeLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

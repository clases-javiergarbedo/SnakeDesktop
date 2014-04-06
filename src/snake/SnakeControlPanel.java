/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author Javier García Escobedo <javiergarbedo.es>
 */
public class SnakeControlPanel extends javax.swing.JPanel {

    private Snake snake;
    private SnakeLoop snakeLoop;
    private int pauseSpeed = 250;
    private SnakePanel snakePanel;

    public void setSnakePanel(SnakePanel snakePanel) {
        this.snakePanel = snakePanel;
        //Preseleccionar el primer nivel
        jComboBox1.setSelectedIndex(0);
    }    
    
    /**
     * Creates new form SnakeControlPanel
     */
    public SnakeControlPanel() {
        initComponents();
        //Cambiar aspecto de la barra de botones
        jToolBar1.setFloatable(false);
        jToolBar1.setBorderPainted(false);        
    }        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        jToolBar1.setRollover(true);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nivel 1", "Nivel 2", "Nivel 3", "Nivel 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/snake/img/start.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Pasar el control (foco) al panel del juego para que pueda recibir
        //  las pulsaciones de teclas
        jComboBox1.setEnabled(false);
        snakePanel.requestFocus();
        
        snakeLoop = new SnakeLoop(snake, pauseSpeed, snakePanel);
        snakeLoop.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String levelFileName = "/snake/levels/";
        switch(jComboBox1.getSelectedIndex()) {
            case 0:
                levelFileName += "001.dat";
                break;
            case 1:
                levelFileName += "002.dat";
                break;
            case 2:
                levelFileName += "003.dat";
                break;
            case 3:
                levelFileName += "004.dat";
                break;
        }
        snake = new Snake(levelFileName);
        snakePanel.setSnake(snake);
        snakePanel.repaint();
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
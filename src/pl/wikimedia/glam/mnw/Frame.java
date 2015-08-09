/*
 * The MIT License
 *
 * Copyright 2015 Pawel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pl.wikimedia.glam.mnw;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import org.wikipedia.Wiki;

public class Frame extends javax.swing.JFrame {

  Log log;
  GLAM glam;
  Wiki wiki = new Wiki("test.wikipedia.org"); 

  public Frame() {
    initComponents();
    setLocationRelativeTo(null);
    
    wiki.setUserAgent("WMPL GLAM Upload Tool/1.0 (https://github.com/wikimedia-pl/glam-mnw)");
    wiki.setMarkBot(true);
    
    log = new Log(logField);
    glam = new GLAM(wiki);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    numberPanel = new javax.swing.JPanel();
    numberLabel = new javax.swing.JLabel();
    numberField = new javax.swing.JTextField();
    loadButton = new javax.swing.JButton();
    logPanel = new javax.swing.JPanel();
    logScrollPane = new javax.swing.JScrollPane();
    logField = new javax.swing.JTextPane();
    uploadButton = new javax.swing.JButton();
    wikiTextPanel = new javax.swing.JPanel();
    wikiTextScrollPane = new javax.swing.JScrollPane();
    wikiTextField = new javax.swing.JTextArea();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    numberPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

    numberLabel.setText("Number");

    numberField.setText("4974");

    loadButton.setText("Load");
    loadButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        loadButtonActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout numberPanelLayout = new javax.swing.GroupLayout(numberPanel);
    numberPanel.setLayout(numberPanelLayout);
    numberPanelLayout.setHorizontalGroup(
      numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(numberPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(numberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(loadButton)
        .addContainerGap())
    );
    numberPanelLayout.setVerticalGroup(
      numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(numberPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(numberField)
          .addComponent(numberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    logPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

    logScrollPane.setViewportView(logField);

    javax.swing.GroupLayout logPanelLayout = new javax.swing.GroupLayout(logPanel);
    logPanel.setLayout(logPanelLayout);
    logPanelLayout.setHorizontalGroup(
      logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(logPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(logScrollPane)
        .addContainerGap())
    );
    logPanelLayout.setVerticalGroup(
      logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(logPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        .addContainerGap())
    );

    uploadButton.setText("Upload");

    wikiTextPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

    wikiTextField.setColumns(20);
    wikiTextField.setFont(new java.awt.Font("Consolas", 0, 10)); // NOI18N
    wikiTextField.setRows(5);
    wikiTextScrollPane.setViewportView(wikiTextField);

    javax.swing.GroupLayout wikiTextPanelLayout = new javax.swing.GroupLayout(wikiTextPanel);
    wikiTextPanel.setLayout(wikiTextPanelLayout);
    wikiTextPanelLayout.setHorizontalGroup(
      wikiTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(wikiTextPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(wikiTextScrollPane)
        .addContainerGap())
    );
    wikiTextPanelLayout.setVerticalGroup(
      wikiTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(wikiTextPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(wikiTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(wikiTextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(logPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(numberPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(uploadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(numberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(wikiTextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(logPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(uploadButton)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
    int number = Integer.parseInt(numberField.getText());
    System.out.print(number);
    
    log.log("Getting data for #" + number);
    String wikiText = glam.getPhotoWikiText(number);
    wikiTextField.setText(wikiText);
  }//GEN-LAST:event_loadButtonActionPerformed

  public static void main(String args[]) {
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code ">
    try {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        Logger.getLogger(GLAM.class.getName()).log(Level.SEVERE, null, ex);
      }
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(GLAM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Frame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton loadButton;
  private javax.swing.JTextPane logField;
  private javax.swing.JPanel logPanel;
  private javax.swing.JScrollPane logScrollPane;
  private javax.swing.JTextField numberField;
  private javax.swing.JLabel numberLabel;
  private javax.swing.JPanel numberPanel;
  private javax.swing.JButton uploadButton;
  private javax.swing.JTextArea wikiTextField;
  private javax.swing.JPanel wikiTextPanel;
  private javax.swing.JScrollPane wikiTextScrollPane;
  // End of variables declaration//GEN-END:variables
}

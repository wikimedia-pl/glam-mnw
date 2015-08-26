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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.swing.UIManager;
import org.wikipedia.Wiki;

public class Frame extends javax.swing.JFrame {

  Log log;
  GLAM glam;
  Wiki wiki = new Wiki("commons.wikimedia.org");

  ArrayList<File> files;

  public Frame() {
    initComponents();
    setLocationRelativeTo(null);

    wiki.setUserAgent("WMPL GLAM Upload Tool/1.0 (https://github.com/wikimedia-pl/glam-mnw)");

    log = new Log(logField);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jCheckBox1 = new javax.swing.JCheckBox();
    numberPanel = new javax.swing.JPanel();
    numberLabel = new javax.swing.JLabel();
    numberField = new javax.swing.JTextField();
    loadButton = new javax.swing.JButton();
    logPanel = new javax.swing.JPanel();
    logScrollPane = new javax.swing.JScrollPane();
    logField = new javax.swing.JTextPane();
    jLabel1 = new javax.swing.JLabel();
    wikiTextPanel = new javax.swing.JPanel();
    wikiTextScrollPane = new javax.swing.JScrollPane();
    wikiTextField = new javax.swing.JTextArea();
    fileNameField = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    uploadPanel = new javax.swing.JPanel();
    uploadButton = new javax.swing.JButton();
    passwordLabel = new javax.swing.JLabel();
    passwordField = new javax.swing.JPasswordField();

    jCheckBox1.setText("jCheckBox1");

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
        .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(numberField, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
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

    jLabel1.setText("Operation Log");

    javax.swing.GroupLayout logPanelLayout = new javax.swing.GroupLayout(logPanel);
    logPanel.setLayout(logPanelLayout);
    logPanelLayout.setHorizontalGroup(
      logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(logPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
          .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    logPanelLayout.setVerticalGroup(
      logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logPanelLayout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(logScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    wikiTextPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

    wikiTextField.setColumns(20);
    wikiTextField.setFont(new java.awt.Font("Consolas", 0, 10)); // NOI18N
    wikiTextField.setRows(5);
    wikiTextScrollPane.setViewportView(wikiTextField);

    jLabel2.setText("File Name");

    jLabel3.setText("Wiki Text");

    javax.swing.GroupLayout wikiTextPanelLayout = new javax.swing.GroupLayout(wikiTextPanel);
    wikiTextPanel.setLayout(wikiTextPanelLayout);
    wikiTextPanelLayout.setHorizontalGroup(
      wikiTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(wikiTextPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(wikiTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(wikiTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
          .addGroup(wikiTextPanelLayout.createSequentialGroup()
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(fileNameField)))
        .addContainerGap())
    );
    wikiTextPanelLayout.setVerticalGroup(
      wikiTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wikiTextPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(wikiTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(fileNameField)
          .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(wikiTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        .addContainerGap())
    );

    uploadPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

    uploadButton.setText("Upload");
    uploadButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        uploadButtonActionPerformed(evt);
      }
    });

    passwordLabel.setText("Bot Password");

    javax.swing.GroupLayout uploadPanelLayout = new javax.swing.GroupLayout(uploadPanel);
    uploadPanel.setLayout(uploadPanelLayout);
    uploadPanelLayout.setHorizontalGroup(
      uploadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(uploadPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(uploadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(uploadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    uploadPanelLayout.setVerticalGroup(
      uploadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(uploadPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(uploadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(uploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(uploadPanelLayout.createSequentialGroup()
            .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(wikiTextPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(logPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(numberPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(uploadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        .addComponent(uploadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
    final int number = Integer.parseInt(numberField.getText());
    glam = new GLAM(wiki, log, number);

    Runnable run = new Runnable() {
      @Override
      public void run() {
        uploadButton.setEnabled(false);
        
        log.log("Getting data for #" + number + "\n");
        wikiTextField.setText(glam.getPhotoWikiText());
        fileNameField.setText(glam.getFileName());

        log.log("Getting files...\n");
        files = glam.getFiles();
        log.log(files.size() + " files downloaded.\n");
        log.log("Done\n\n");
        
        uploadButton.setEnabled(true);
      }
    };
    Thread t = new Thread(run);
    t.start();
    
   
  }//GEN-LAST:event_loadButtonActionPerformed

  private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
    Runnable run = new Runnable() {
      @Override
      public void run() {
        uploadButton.setEnabled(false);
        log.log("Log in...\n");
        try {
          wiki.login("National Museum Warsaw", passwordField.getPassword());

          for (File file : files) {
            if(!file.exists()) {
              continue;
            }

            String properFileName = fileNameField.getText().replace("__NUMER__", "0" + Integer.toString(files.indexOf(file) + 1));
            log.log("Uploading " + properFileName + "... ");
            wiki.upload(file, properFileName, wikiTextField.getText(), "");
            log.log("Uploaded!\n");
          }

          for (File file : files) {
            file.delete();
          }
          log.log("DONE\n");
          uploadButton.setEnabled(true);
        } catch (IOException | FailedLoginException ex) {
          log.red("Something failed!\n");
        } catch (LoginException ex) {
          log.red("Login failed!\n");
        }
      }
    };
    Thread t = new Thread(run);
    t.start();
  }//GEN-LAST:event_uploadButtonActionPerformed

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
  private javax.swing.JTextField fileNameField;
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JButton loadButton;
  private javax.swing.JTextPane logField;
  private javax.swing.JPanel logPanel;
  private javax.swing.JScrollPane logScrollPane;
  private javax.swing.JTextField numberField;
  private javax.swing.JLabel numberLabel;
  private javax.swing.JPanel numberPanel;
  private javax.swing.JPasswordField passwordField;
  private javax.swing.JLabel passwordLabel;
  private javax.swing.JButton uploadButton;
  private javax.swing.JPanel uploadPanel;
  private javax.swing.JTextArea wikiTextField;
  private javax.swing.JPanel wikiTextPanel;
  private javax.swing.JScrollPane wikiTextScrollPane;
  // End of variables declaration//GEN-END:variables
}

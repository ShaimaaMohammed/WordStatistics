package WordStatistics;

import javax.swing.table.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;

public class WordStatistics extends javax.swing.JFrame {

    private file functions;
    private Run[] Threads;
    private Thread[] T;
    private File f;
    public DefaultTableModel t = new DefaultTableModel();

    private ArrayList<File> folders;
    private int numberOfFiles=0;
    private int i=0;
    
    public WordStatistics() {
        initComponents();
        table.setModel(t);
        t.addColumn("File");
        t.addColumn("Total Words");
        t.addColumn("is");
        t.addColumn("are");
        t.addColumn("you");
        t.addColumn("max");
        t.addColumn("min");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        StartProcessing = new javax.swing.JButton();
        selectDirectoryLabel = new javax.swing.JLabel();
        directoryNameLable = new javax.swing.JLabel();
        directoryName = new javax.swing.JTextField();
        Browse = new javax.swing.JButton();
        selectSubDirectories = new javax.swing.JCheckBox();
        longestWordTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        longestWord = new javax.swing.JTextField();
        shortestWord = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Word Statistics");
        getContentPane().setLayout(null);

        table.setBackground(new java.awt.Color(204, 204, 255));
        table.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setCellSelectionEnabled(true);
        table.setEnabled(false);
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 150, 610, 130);

        StartProcessing.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        StartProcessing.setText("Start Processing");
        StartProcessing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartProcessingActionPerformed(evt);
            }
        });
        getContentPane().add(StartProcessing);
        StartProcessing.setBounds(10, 100, 290, 40);

        selectDirectoryLabel.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        selectDirectoryLabel.setForeground(new java.awt.Color(0, 0, 204));
        selectDirectoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectDirectoryLabel.setText("Please Select a Directory");
        getContentPane().add(selectDirectoryLabel);
        selectDirectoryLabel.setBounds(0, 0, 300, 40);

        directoryNameLable.setBackground(new java.awt.Color(255, 153, 0));
        directoryNameLable.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        directoryNameLable.setText("Directory Name : ");
        getContentPane().add(directoryNameLable);
        directoryNameLable.setBounds(10, 40, 140, 40);

        directoryName.setEditable(false);
        directoryName.setBackground(new java.awt.Color(255, 255, 255));
        directoryName.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        directoryName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(directoryName);
        directoryName.setBounds(160, 50, 140, 30);

        Browse.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        Browse.setText("Browse");
        Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseActionPerformed(evt);
            }
        });
        getContentPane().add(Browse);
        Browse.setBounds(340, 10, 130, 40);

        selectSubDirectories.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        selectSubDirectories.setText("Include SubDirectories");
        getContentPane().add(selectSubDirectories);
        selectSubDirectories.setBounds(340, 100, 260, 40);

        longestWordTitle.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        longestWordTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        longestWordTitle.setText("Longest Word : ");
        getContentPane().add(longestWordTitle);
        longestWordTitle.setBounds(0, 290, 130, 30);

        jLabel2.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Shortest Word : ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 340, 130, 19);

        longestWord.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        longestWord.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(longestWord);
        longestWord.setBounds(160, 290, 140, 30);

        shortestWord.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        shortestWord.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(shortestWord);
        shortestWord.setBounds(160, 330, 140, 30);

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/WordStatistics/ZbackGround.jpg"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 610, 380);

        pack();
    }// </editor-fold>//GEN-END:initComponents

public boolean ISselectSubDirectories(){
    return selectSubDirectories.isSelected();
} 

public void longestWord(String longest){
    longestWord.setText(longest);
}

public void ShortestWords(String shortest){
    shortestWord.setText(shortest);
} 
    
    private void StartProcessingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartProcessingActionPerformed
        t.setRowCount(0);
        numberOfFiles=0;
        
        if(i>0) for(int i=0; i<numberOfFiles; i++) T[i].stop();
        
        folders = new ArrayList<File>();
        functions = new file(f.getAbsolutePath(), this);
        functions.getFiles();
        
        folders.add(f);
        if(ISselectSubDirectories()){   for (File file : f.listFiles())
                                                if (file.isDirectory()) folders.add(file);
                        }
        
        for(int i=0; i<folders.size(); i++){
                for(File f: folders.get(i).listFiles())
                    if(f.isFile())  numberOfFiles++;
        }
        
        Threads = new Run [numberOfFiles];
        T = new Thread[numberOfFiles];

        int j=0, k=0;
        for(int i=0; i<folders.size(); i++){ j=0;
                                             for(File file : folders.get(i).listFiles()) {
                                                 if (file.isFile()) {   Threads[k] = new Run(i, j, k,functions);
                                                                        j++;
                                                                        k++;
                                                                    }
                                                                            }
                                           }
        for(int i=0; i<numberOfFiles; i++) T[i] = new Thread(Threads[i]);
        for(int i=0; i<numberOfFiles; i++) T[i].start();
        i++;
    }//GEN-LAST:event_StartProcessingActionPerformed

    private void BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseActionPerformed
        JFileChooser B = new JFileChooser();
       B.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       int response = B.showOpenDialog(WordStatistics.this);
       if(response == JFileChooser.APPROVE_OPTION) {
           f = new File(B.getSelectedFile().toString());
           directoryName.setText(f.getName());       }
    }//GEN-LAST:event_BrowseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Browse;
    private javax.swing.JButton StartProcessing;
    private javax.swing.JLabel background;
    private javax.swing.JTextField directoryName;
    private javax.swing.JLabel directoryNameLable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField longestWord;
    private javax.swing.JLabel longestWordTitle;
    private javax.swing.JLabel selectDirectoryLabel;
    private javax.swing.JCheckBox selectSubDirectories;
    private javax.swing.JTextField shortestWord;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}

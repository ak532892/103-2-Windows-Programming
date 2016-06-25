/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author student
 */


class EvenOddRenderer implements TableCellRenderer {

    public static final DefaultTableCellRenderer DEFAULT_RENDERER =
        new DefaultTableCellRenderer();

    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer =
        DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color foreground=Color.WHITE, background=Color.WHITE;
        
        if (isSelected) {
            foreground = Color.WHITE;
            background = Color.BLUE ;
        } else {
            //foreground = Color.BLACK;
            if (row % 3 == 0) {
                foreground = Color.GREEN;
                background = Color.WHITE;
            } 
            else if (row % 3 == 1){
                background = Color.PINK;
            }
            else if (row % 3 == 2){
                foreground = Color.BLACK;
                background = Color.YELLOW;
            }
        }
        if(value != null){
            switch(column){
                case 0:
                    break;
                case 1:
                    if( !isValidCellPhone(value.toString()) )/*
                        foreground = Color.BLACK;
                    else */
                        foreground = Color.RED;
                    break;
                case 2:
                    if( !isValidDate(value.toString()) ) /*
                        foreground = Color.BLACK;
                    else */
                        foreground = Color.RED;
                    break;
                
                case 3:
                    if( !isValidEmail(value.toString()) ) /*
                        foreground = Color.BLACK;
                    else */
                        foreground = Color.RED;
                    break;
                case 4:
                    if( !isValidWebsite(value.toString()) ) /*
                        foreground = Color.BLACK;
                    else */
                        foreground = Color.RED;
                    break;
                default:
                    foreground = Color.BLACK;
                    break;
            }
        }
        renderer.setForeground(foreground);
        renderer.setBackground(background);
        
        return renderer;
    }
    
    // 判斷是否為日期(yyyy/mm/dd)
    public static boolean isValidDate(String date) {
        if (date == null) {
            return false;
        }
        String datePattern = "[0-9]{4}/[0-9]{2}/[0-9]{2}";
        return date.matches(datePattern);
    }
    
    // 判斷是否為手機號碼(xxxx-xxx-xxx)
    public static boolean isValidCellPhone(String cell) {
        if (cell == null) {
            return false;
        }
        String cellPattern = "[0-9]{4}-[0-9]{3}-[0-9]{3}";
        return cell.matches(cellPattern);
    }
    
    // 判斷是否為E-mail
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String emailPattern = "^([\\w]+)(([-\\.][\\w]+)?)*@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        //String emailPattern = "^[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})$";
        return email.matches(emailPattern);
    }
    
    // 判斷是否為個人網站
    public static boolean isValidWebsite(String web) {
        if (web == null) {
            return false;
        }
        String webPattern = ".*?[.].*?";
        return web.matches(webPattern);
    }
}




public class NewJFrame extends javax.swing.JFrame {
    private Object tableModel;
    private Object form;
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        
        int row = 0;
        initComponents();
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setDefaultRenderer(Object.class,new EvenOddRenderer());
        //Object[] first = {"Example", "1900/01/01","example@ex.tw", "www.example.com"};
        //for(int i=0; i<4; i++) jTable1.setValueAt(first[i], row, i);
        row++;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Phone", "Birthday", "Email", "Url"
            }
        ));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jMenu1.setText("Insert");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Modify");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Delete");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Export");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveFile(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Import");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoadFile(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        JTextField name = new JTextField();
        JTextField phone = new JTextField();
	JTextField birth = new JTextField();
        JTextField email = new JTextField();
        JTextField url = new JTextField();
        Object[] message = {"Name", name,"Phone", phone, "Birthday", birth, "Email", email, "URL", url};
        Object[] options = { "Yes", "No" };
        int n = JOptionPane.showOptionDialog(new JFrame(),
                message, "Insert", JOptionPane.YES_NO_OPTION, 
                JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
       
        if(n == JOptionPane.OK_OPTION){
            String[] str = new String[]{name.getText(),phone.getText(), birth.getText(), email.getText(), url.getText()};
            DefaultTableModel dfm = (DefaultTableModel) jTable1.getModel();
            dfm.addRow(str); 
         }
    }//GEN-LAST:event_jMenu1MouseClicked
private int flag = 0;
    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        
         if(flag == 0){
            jTable1.setEnabled(true);
            jMenu2.setText("Finish");
            flag = 1;
        }
        else{
            jTable1.setEnabled(false);
            jMenu2.setText("Modify");
            flag = 0;
        }
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
        JTextField line = new JTextField();
        DefaultTableModel dfm = (DefaultTableModel) jTable1.getModel();
        Object[] message = {"line", line};
        Object[] options = { "Yes", "No" };
        int n = JOptionPane.showOptionDialog(new JFrame(),
                message, "Delete", JOptionPane.YES_NO_OPTION, 
                JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
       
        if(n == JOptionPane.OK_OPTION){
            dfm.removeRow(Integer.parseInt(line.getText()));
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    private void SaveFile(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveFile
        // TODO add your handling code here:
        
        TableModel tmpModel = jTable1.getModel();
        int row=tmpModel.getRowCount();
        
        String fOutput = JOptionPane.showInputDialog(null, "請輸入檔案名稱","確認", JOptionPane.OK_CANCEL_OPTION);
        try {
            FileWriter file = new FileWriter(fOutput+".txt", false);
            BufferedWriter bw = new BufferedWriter(file);
            bw.write("AAA\r\n");
            for(int i=0; i<row; i++)
            {
                for(int j=0; j<5; j++)
                {
                    bw.write(jTable1.getValueAt(i, j).toString() + "\r\n");
                }
            }
            bw.close();
            file.close();
        }
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, "儲存失敗QQ","離開", JOptionPane.OK_OPTION);
        }
        
    }//GEN-LAST:event_SaveFile

    private void LoadFile(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadFile
        // TODO add your handling code here:
        String Check = new String("AAA");
        //String fInput = JOptionPane.showInputDialog(null, "請輸入檔案名稱","開啟舊檔", JOptionPane.OK_CANCEL_OPTION);
        
        JFileChooser chooser = new JFileChooser();
        int ret = chooser.showOpenDialog(null);
        File fInput = chooser.getSelectedFile();
        
        
        if(ret == JFileChooser.APPROVE_OPTION)
        {
            FileReader file;
            BufferedReader br;
            String Date;
            try {
                //file = new FileReader( fInput+".txt" );
                //br = new BufferedReader(file);
                br=new BufferedReader( new FileReader( fInput ) );
                Date=br.readLine();
                int c=0;     
                int r = 0,JR;
                if (!Check.equals(Date))throw new Exception();
                else
                {
                    while(true)
                    {
                        try
                        {
                        Date=br.readLine();
                        
                        if (Date==null)
                        {
                            if(r < jTable1.getRowCount())
                            {
                                JR=jTable1.getRowCount();
                                DefaultTableModel cutModel = (DefaultTableModel) jTable1.getModel();
                                for(;r < JR;JR--)
                                {
                                    cutModel.removeRow(r);
                                }
                            }
                            break;
                        }
                        if(r >= jTable1.getRowCount()) {
                            DefaultTableModel addModel = (DefaultTableModel) jTable1.getModel();
                            addModel.addRow(new Object[]{"", "", "", "", ""});
                        }

                        jTable1.setValueAt(Date, r, 0);
                        jTable1.setValueAt(br.readLine(), r, 1);
                        jTable1.setValueAt(br.readLine(), r, 2);
                        jTable1.setValueAt(br.readLine(), r, 3);
                        jTable1.setValueAt(br.readLine(), r, 4);
                        r++;
                        }
                        catch(Exception e)
                        {
                            break;
                        }
                    }
                }
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(null, "檔案名稱不對或路徑錯誤!!!", 
                   "開啟失敗", JOptionPane.OK_OPTION);
            }    
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "檔案錯誤!!!\n該檔案非本程式儲存之檔案!!!", 
                   "檔案錯誤", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_LoadFile

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new NewJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

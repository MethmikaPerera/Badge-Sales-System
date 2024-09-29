/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import model.AppIcon;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BadgeItem;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mamet
 */
public class BadgesWindow extends javax.swing.JFrame {

    public static HashMap<Integer, Object> badgeMap = new HashMap<>();

    /**
     * Creates new form BadgesWindow
     */
    public BadgesWindow() {
        initComponents();
        AppIcon.setAppIcon(this);

        loadBadgeData();
        loadSections();
    }

    private void loadBadgeData() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `badges` INNER JOIN `section` ON `badges`.`section_id` = `section`.`id`");

            while (resultSet.next()) {

                BadgeItem badgeItem = new BadgeItem();
                badgeItem.setBadgeId(resultSet.getInt("badges.id"));
                badgeItem.setBadgeNo(resultSet.getString("badge_no"));
                badgeItem.setBadgeName(resultSet.getString("badge_name"));
                badgeItem.setSectionId(resultSet.getInt("section.id"));
                badgeItem.setSectionName(resultSet.getString("section.name"));

                badgeMap.put(resultSet.getInt("badges.id"), badgeItem);

                Vector<String> vector = new Vector();
                vector.add(resultSet.getString("badge_no"));
                vector.add(resultSet.getString("badge_name"));
                vector.add(resultSet.getString("section.name"));

                DefaultTableModel badgeTableModel = (DefaultTableModel) badgeTable.getModel();
                badgeTableModel.addRow(vector);

            }

        } catch (Exception e) {
            SplashWindow.logger.log(Level.SEVERE, "Exception occured while loading badge data", e);
        }

    }

    private void loadSections() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `section` ORDER BY `id` ASC");

            Vector<String> vector = new Vector<>();
            vector.add("Select Section");

            while (resultSet.next()) {
                vector.add(resultSet.getString("section.name"));
            }

            DefaultComboBoxModel sectionModel = new DefaultComboBoxModel(vector);
            addSectionBox.setModel(sectionModel);
            searchSectionBox.setModel(sectionModel);

        } catch (Exception e) {
            SplashWindow.logger.log(Level.SEVERE, "Exception occured while loading section data", e);
        }

    }

    private void loadSearchData() {

        String badgeNo = searchBadgeNumberField.getText();
        String badgeName = searchBadgeNameField.getText();
        int sectionId = searchSectionBox.getSelectedIndex();

        String query = "SELECT * FROM `badges` INNER JOIN `section` ON `badges`.`section_id` = `section`.`id` ";

        if (!badgeNo.isBlank()) {
            if (query.contains("WHERE")) {
                query += "AND `badge_no` LIKE '%" + badgeNo + "%' ";
            } else {
                query += "WHERE `badge_no` LIKE '%" + badgeNo + "%' ";
            }
        } else {
            query += "";
        }

        if (!badgeName.isBlank()) {
            if (query.contains("WHERE")) {
                query += "AND `badge_name` LIKE '%" + badgeName + "%' ";
            } else {
                query += "WHERE `badge_name` LIKE '%" + badgeName + "%' ";
            }
        } else {
            query += "";
        }

        if (sectionId != 0) {
            if (query.contains("WHERE")) {
                query += "AND `section_id`='" + sectionId + "' ";
            } else {
                query += "WHERE `section_id`='" + sectionId + "' ";
            }
        } else {
            query += "";
        }

        try {
            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel badgeTableModel = (DefaultTableModel) badgeTable.getModel();
            badgeTableModel.setRowCount(0);

            while (resultSet.next()) {
                BadgeItem badgeItem = new BadgeItem();
                badgeItem.setBadgeId(resultSet.getInt("badges.id"));
                badgeItem.setBadgeNo(resultSet.getString("badge_no"));
                badgeItem.setBadgeName(resultSet.getString("badge_name"));
                badgeItem.setSectionId(resultSet.getInt("section.id"));
                badgeItem.setSectionName(resultSet.getString("section.name"));

                badgeMap.put(resultSet.getInt("badges.id"), badgeItem);

                Vector<String> vector = new Vector();
                vector.add(resultSet.getString("badge_no"));
                vector.add(resultSet.getString("badge_name"));
                vector.add(resultSet.getString("section.name"));

                badgeTableModel.addRow(vector);
            }

        } catch (Exception e) {
            SplashWindow.logger.log(Level.SEVERE, "Exception occured while loading search data", e);
        }

    }

    private void clearFields() {
        addBadgeNumberField.setText("");
        addBadgeNameField.setText("");
        addSectionBox.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addBadgeButton = new javax.swing.JButton();
        addSectionBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        addBadgeNameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addBadgeNumberField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        badgeTable = new javax.swing.JTable();
        searchBadgeNumberField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        searchBadgeNameField = new javax.swing.JTextField();
        searchSectionBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        printBadgeListButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Badges");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        addBadgeButton.setBackground(new java.awt.Color(0, 102, 0));
        addBadgeButton.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        addBadgeButton.setText("Add Badge");
        addBadgeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBadgeButtonActionPerformed(evt);
            }
        });

        addSectionBox.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        addSectionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setText("Section");

        addBadgeNameField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setText("Badge Name");

        addBadgeNumberField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel7.setText("Badge Number");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setText("Add a Badge");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(addBadgeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addSectionBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBadgeNameField)
                        .addComponent(addBadgeNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel8)
                .addGap(40, 40, 40)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addBadgeNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addBadgeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addSectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(addBadgeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 26)); // NOI18N
        jLabel1.setText("Badges");

        badgeTable.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        badgeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Badge Number", "Name", "Section"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(badgeTable);

        searchBadgeNumberField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        searchBadgeNumberField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBadgeNumberFieldKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setText("Badge Number");

        searchBadgeNameField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        searchBadgeNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBadgeNameFieldKeyReleased(evt);
            }
        });

        searchSectionBox.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        searchSectionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchSectionBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                searchSectionBoxItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setText("Section");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setText("Badge Name");

        printBadgeListButton.setBackground(new java.awt.Color(102, 0, 153));
        printBadgeListButton.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        printBadgeListButton.setText("Print Badge List");
        printBadgeListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBadgeListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchBadgeNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(searchBadgeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchSectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(printBadgeListButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(printBadgeListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchSectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBadgeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBadgeNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addBadgeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBadgeButtonActionPerformed
        String badgeNo = addBadgeNumberField.getText();
        String badgeName = addBadgeNameField.getText();
        int sectionId = addSectionBox.getSelectedIndex();

        if (badgeNo.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter the Badge Number", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (badgeName.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter the Badge Name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (sectionId == 0) {
            JOptionPane.showMessageDialog(this, "Select Section", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                MySQL.executeIUD("INSERT INTO `badges`(`badge_no`,`badge_name`,`section_id`)");
                JOptionPane.showMessageDialog(this, "Successfully Added the " + badgeName + " Badge", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadBadgeData();
                clearFields();
            } catch (Exception e) {
                SplashWindow.logger.log(Level.SEVERE, "Exception occured while Adding Badge", e);
            }
        }
    }//GEN-LAST:event_addBadgeButtonActionPerformed

    private void searchBadgeNumberFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBadgeNumberFieldKeyReleased
        loadSearchData();
    }//GEN-LAST:event_searchBadgeNumberFieldKeyReleased

    private void searchBadgeNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBadgeNameFieldKeyReleased
        loadSearchData();
    }//GEN-LAST:event_searchBadgeNameFieldKeyReleased

    private void searchSectionBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchSectionBoxItemStateChanged
        loadSearchData();
    }//GEN-LAST:event_searchSectionBoxItemStateChanged

    private void printBadgeListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBadgeListButtonActionPerformed
        try {
            String path = "src//reports//badge_report.jasper";

            String fullName = LoginWindow.userdata.getFirstName() + " " + LoginWindow.userdata.getLastName();
            String role = LoginWindow.userdata.getUserRole();
            String email = LoginWindow.userdata.getEmail();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String img = "src/resources/app-icon.png";

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", fullName);
            parameters.put("Parameter2", role);
            parameters.put("Parameter3", email);
            parameters.put("Parameter4", dateTime);
            parameters.put("Parameter5", img);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(badgeTable.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters, dataSource);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            SplashWindow.logger.log(Level.SEVERE, "Exception occured while print badge list report", e);
        }
    }//GEN-LAST:event_printBadgeListButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BadgesWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBadgeButton;
    private javax.swing.JTextField addBadgeNameField;
    private javax.swing.JTextField addBadgeNumberField;
    private javax.swing.JComboBox<String> addSectionBox;
    private javax.swing.JTable badgeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton printBadgeListButton;
    private javax.swing.JTextField searchBadgeNameField;
    private javax.swing.JTextField searchBadgeNumberField;
    private javax.swing.JComboBox<String> searchSectionBox;
    // End of variables declaration//GEN-END:variables
}

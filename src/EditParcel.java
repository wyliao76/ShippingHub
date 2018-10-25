
package ShippingHub;

import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Wei-Yu Liao
 */
public class EditParcel extends javax.swing.JDialog {

    private Parcel newParcel ;
    private boolean error = false;
    private String errorMessage ="";
    Random rand = new Random();
    private int parcelID =rand.nextInt(1000000)+1;
    Date date = new Date() ;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    
    
    /**
     * Creates new form EditParcel
     */
    public EditParcel(){
        initComponents();
        this.getRootPane().setDefaultButton(SaveJButton);
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage("src/ShippingHub/wow-mailboxes.jpg"));
        setLocationRelativeTo(null);
        firstNameJTextField.requestFocus();
        newParcel = null;
        this.setAlwaysOnTop(true);
        this.setModal(true);
    }
    public EditParcel(Person person){
        this();
        firstNameJTextField.setText(person.getFirstName());
        lastNameJTextField.setText(person.getLastName());
        addressJTextField.setText(person.getAddress());
        cityJTextField.setText(person.getCity());
        stateJComboBox.setSelectedItem(person.getState());
        zipJTextField.setText(person.getZip());
        firstNameJTextField.requestFocus();
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleJPanel = new javax.swing.JPanel();
        titleJLabel = new javax.swing.JLabel();
        logoJLabel = new javax.swing.JLabel();
        displayJPanel = new javax.swing.JPanel();
        firstNameJLabel = new javax.swing.JLabel();
        firstNameJTextField = new javax.swing.JTextField();
        lastNameJLabel = new javax.swing.JLabel();
        lastNameJTextField = new javax.swing.JTextField();
        addressJLabel = new javax.swing.JLabel();
        addressJTextField = new javax.swing.JTextField();
        cityJLabel = new javax.swing.JLabel();
        cityJTextField = new javax.swing.JTextField();
        stateJLabel = new javax.swing.JLabel();
        stateJComboBox = new javax.swing.JComboBox<>();
        zipJLabel = new javax.swing.JLabel();
        zipJTextField = new javax.swing.JTextField();
        SaveJPanel = new javax.swing.JPanel();
        SaveJButton = new javax.swing.JButton();
        cancelJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Parcel");
        setResizable(false);

        titleJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 24)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(51, 0, 0));
        titleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleJLabel.setText("Edit Parcel -Customer Info");

        logoJLabel.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        logoJLabel.setForeground(new java.awt.Color(51, 0, 0));
        logoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ShippingHub/wow-mailboxes.jpg"))); // NOI18N
        logoJLabel.setToolTipText("");

        javax.swing.GroupLayout titleJPanelLayout = new javax.swing.GroupLayout(titleJPanel);
        titleJPanel.setLayout(titleJPanelLayout);
        titleJPanelLayout.setHorizontalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        titleJPanelLayout.setVerticalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addComponent(logoJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        displayJPanel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        firstNameJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        firstNameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        firstNameJLabel.setText("First Name : ");

        firstNameJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        firstNameJTextField.setToolTipText("non-digits");

        lastNameJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lastNameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lastNameJLabel.setText("Last Name : ");

        lastNameJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lastNameJTextField.setToolTipText("non-digits");

        addressJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addressJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addressJLabel.setText("Address :");

        addressJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addressJTextField.setToolTipText("address");

        cityJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cityJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cityJLabel.setText("City : ");

        cityJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cityJTextField.setToolTipText("letter only");

        stateJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stateJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stateJLabel.setText("State :");

        stateJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "MA", "MD", "MS", "MI", "MN", "MI", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY" }));
        stateJComboBox.setToolTipText("50 states of America");

        zipJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        zipJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        zipJLabel.setText("Zip :");

        zipJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        zipJTextField.setToolTipText("Ex:12345 or 12345-1234");

        javax.swing.GroupLayout displayJPanelLayout = new javax.swing.GroupLayout(displayJPanel);
        displayJPanel.setLayout(displayJPanelLayout);
        displayJPanelLayout.setHorizontalGroup(
            displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayJPanelLayout.createSequentialGroup()
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cityJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(firstNameJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(addressJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayJPanelLayout.createSequentialGroup()
                        .addComponent(firstNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastNameJTextField))
                    .addComponent(addressJTextField)
                    .addGroup(displayJPanelLayout.createSequentialGroup()
                        .addComponent(cityJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zipJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zipJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))))
        );
        displayJPanelLayout.setVerticalGroup(
            displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayJPanelLayout.createSequentialGroup()
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stateJComboBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cityJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cityJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(zipJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(zipJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        SaveJPanel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SaveJPanel.setLayout(new java.awt.GridLayout(1, 0));

        SaveJButton.setBackground(java.awt.Color.green);
        SaveJButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SaveJButton.setToolTipText("save");
        SaveJButton.setLabel("Save");
        SaveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveJButtonActionPerformed(evt);
            }
        });
        SaveJPanel.add(SaveJButton);

        cancelJButton.setBackground(new java.awt.Color(51, 255, 0));
        cancelJButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cancelJButton.setText("Cancel");
        cancelJButton.setToolTipText("cancel");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });
        SaveJPanel.add(cancelJButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(displayJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(130, Short.MAX_VALUE)
                    .addComponent(SaveJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(114, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(252, Short.MAX_VALUE)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(84, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(400, Short.MAX_VALUE)
                    .addComponent(SaveJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveJButtonActionPerformed

        String firstName = firstNameJTextField.getText();
        String lastName = lastNameJTextField.getText();
        String address = addressJTextField.getText();
        String city =cityJTextField.getText(); 
        String states = String.valueOf(stateJComboBox.getSelectedItem());
        String zip = zipJTextField.getText();
        
        errorMessage="";
        error = false;
        if ( ! Validation.isName(firstName)){
            errorMessage += "Invalid First Name\n";
            firstNameJTextField.requestFocus();
            error = true;
        }
       if ( ! Validation.isName(lastName)){
            errorMessage += "Invalid Last Name\n";
            lastNameJTextField.requestFocus();
            error = true;
        }
        if ( ! Validation.isZip(zip)){
            errorMessage += "Invalid Zip\n";
            zipJTextField.requestFocus();
            error = true;
       }
        if ( !Validation.isLetter(city)){
            errorMessage += "Invalid City\n";
            cityJTextField.requestFocus();
            error = true;
        }
        if ( firstNameJTextField.getText().equals("")){
            errorMessage += "First Name Can't be empty!\n";
            error = true;
        } 
        if ( lastNameJTextField.getText().equals("")){
            errorMessage += "Last Name Can't be empty!\n";
            error = true;
        }  
        if ( addressJTextField.getText().equals("")){
            errorMessage += "Address Can't be empty!\n";
            error = true;
        } 
        if ( cityJTextField.getText().equals("")){
            errorMessage += "City Can't be empty!\n";
            error = true;
        } 
        if ( zipJTextField.getText().equals("")){
            errorMessage += "Zip Can't be empty!\n";
            error = true;
        } 
        if(!errorMessage.isEmpty()){
        JOptionPane.showMessageDialog(null, errorMessage, "Error!", JOptionPane.WARNING_MESSAGE);
        this.setAlwaysOnTop(true); 
        }
        if(error == false){
            newParcel = new Parcel(dateFormat.format(date), firstName,
                    lastName, address, city, states, zip,parcelID) ;
            this.dispose();
        }
    }//GEN-LAST:event_SaveJButtonActionPerformed

    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed
         this.dispose();
    }//GEN-LAST:event_cancelJButtonActionPerformed

    public Parcel getParcel(){
    return newParcel;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SaveJButton;
    private javax.swing.JPanel SaveJPanel;
    private javax.swing.JLabel addressJLabel;
    private javax.swing.JTextField addressJTextField;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JLabel cityJLabel;
    private javax.swing.JTextField cityJTextField;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JLabel firstNameJLabel;
    private javax.swing.JTextField firstNameJTextField;
    private javax.swing.JLabel lastNameJLabel;
    private javax.swing.JTextField lastNameJTextField;
    private javax.swing.JLabel logoJLabel;
    private javax.swing.JComboBox<String> stateJComboBox;
    private javax.swing.JLabel stateJLabel;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JPanel titleJPanel;
    private javax.swing.JLabel zipJLabel;
    private javax.swing.JTextField zipJTextField;
    // End of variables declaration//GEN-END:variables
}
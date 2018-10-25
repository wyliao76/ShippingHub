
package ShippingHub;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Wei-Yu Liao
 */
public class ShippingHubGUI extends javax.swing.JFrame {
    
   private ArrayList <Parcel> parcels = new ArrayList ();
   private ArrayList <Parcel> parcelsByState = new ArrayList();
   private int i = 0;
   Date date = new Date() ;
   SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
   String path = "C:\\Users\\user\\Documents\\NetBeansProjects\\ShippingHub"
                 + "\\src\\ShippingHub\\"+dateFormat.format(date)+".xml";
   
//   private final String fileName = "src/ShippingHub/Shipping.txt";
 //  DecimalFormat number = new DecimalFormat("#,##0");

   /**
     * Method: ShippingHubGUI()
     * pre-condition: 
     * post-condition: 
     */   
    public ShippingHubGUI() {
        initComponents();
        setLocationRelativeTo(null); //centers the form at start.
        disAbleAll();
        this.getRootPane().setDefaultButton(scanNewJButton); //set buttonAdd as default
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage("src/ShippingHub/wow-mailboxes.jpg"));
       File f = new File(path);
       if (f.exists() && !f.isDirectory()) {
                readFromFile(path);
                displayParcel(parcels);
                showParcelData(i,parcels);
            }
       if ( parcels.size() ==1 )
           enableOne();
       if (parcels.size() >1)
           enableTwo();
        // Read form an external file Shipping.txt and create an
        // ArrayList of Parcel type, parcel
    }
    
    /**
     * Method: readFromFile
 Reads City from a text file that is comma delimited and
 creates an instance of the City class with the data read.
     * Then the newly created city is added to the City database.
 Uses an object from the ReadFile class to read record.
     * @param file String
 pre-condition: a valid file name, Citystats.txt is expected
 for input with comma separated text fields (but no spaces) for
 city name, population, median, local, and degree
 post-condition: a new City is created with the read fields
 and added to the ArrayList City
     */
    public void readFromFile(String file)            
    { 
      parcels.clear();
      try{
         FileReader inputFile = new FileReader(file);
         BufferedReader input = new BufferedReader(inputFile);
         String Line = input.readLine();
         while (Line != null){
             Parcel parcel = new Parcel();
             StringTokenizer token = new StringTokenizer(Line, ",");
             while (token.hasMoreElements())
             {
                 parcel.setParcelID(Integer.parseInt(token.nextToken()));
                 parcel.person.setFirstName(token.nextToken());
                 parcel.person.setLastName(token.nextToken());
                 parcel.person.setAddress(token.nextToken());
                 parcel.person.setCity(token.nextToken());
                 parcel.person.setState(token.nextToken());
                 parcel.person.setZip(token.nextToken());
                 parcel.setDate(token.nextToken());
             }
             parcels.add(parcel);
             Line = input.readLine();
         }
      }
      catch (FileNotFoundException exp){
          exp.printStackTrace();
      }
      catch (IOException exp){
          exp.printStackTrace();
      }
    }
    /** showParcelData
     * This method is called from within the constructor to
     * display the data for the selected parcel.
     * @parem index : int
     * @parem parcels : ArrayList
     * @return void
     */
    private void showParcelData(int index, ArrayList < Parcel > parcels)
    {
        parcelJTextField.setText(String.valueOf(parcels.get(index).getParcelID()));
        firstNameJTextField.setText(parcels.get(index).person.getFirstName());
        lastNameJTextField.setText(parcels.get(index).person.getLastName());
        dateJTextField.setText(parcels.get(index).getDate());
        addressJTextField.setText(parcels.get(index).person.getAddress());
        cityJTextField.setText(parcels.get(index).person.getCity());
        stateJTextField.setText(parcels.get(index).person.getState());
        zipJTextField.setText(parcels.get(index).person.getZip());
    }
    /**
     * Method: saveParcels
 Save an arrayList of parcels to a xml file.
     * @parem void
     * @return void
 pre-condition: 
 post-condition: save to a new file or a exist file 
 and added to the ArrayList City
     * @see saveParcels
     */
    private void saveParcels() 
    {       JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("src/ShippingHub/"));
            File f = new File(path);
            if (!f.exists()) {
                try{
               writeToFile(path);
               System.exit(0);
             }
            catch(IOException exp)
                    {
                    exp.printStackTrace();
                    }
            }
            int retrival = fileChooser.showSaveDialog(null);
           if (retrival == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().exists() ) 
         try{
             writeToFile(fileChooser.getSelectedFile().toString());
         }
         catch(IOException exp)
         {
             exp.printStackTrace();
         }  
             if (retrival == JFileChooser.CANCEL_OPTION){
                 JOptionPane.showMessageDialog(null,"File not saved", "Cancel by user", JOptionPane.INFORMATION_MESSAGE);
             } 
             if (retrival == JFileChooser.ERROR_OPTION)
                 JOptionPane.showMessageDialog(null,"File not saved", "Cancel by user", JOptionPane.INFORMATION_MESSAGE);
    }
 
      /**
     * Method: writeToFile
     * Write cities to a text file that is comma delimited.
     * @param file String
     * @see writeToFile
     * @throws IOException throws
     * @see Parcel
     */
    public void writeToFile(String file) throws IOException {
        PrintWriter output = new PrintWriter(file);
        for ( int i =0; i<parcels.size(); i++){
           Parcel temp = parcels.get(i);
           String Line = temp.getParcelID()+ ","+ temp.person.getFirstName()+ ","
                   +temp.person.getLastName()+ ","+temp.person.getAddress()+ ","+
                   temp.person.getCity()+","+temp.person.getState()+","+ temp.person.getZip()
                   +","+temp.getDate();
           output.println(Line);
        }
        output.close();
    }
  /**
     * Method: displayParcel()
     * Displays Parcel in JList.
     * @parem parcels : ArrayList
     * @return void
 pre-condition: showing nothing in parcelJLsit
 post-condition: display parcels by ID
     * @see displayParcel
     */
    private void displayParcel(ArrayList < Parcel > parcels)
    {
      int location = parcelJList.getSelectedIndex();
      String [] ID = new String [parcels.size()];
            for (int i = 0; i < parcels.size(); i++) {
                ID[i] = String.valueOf(parcels.get(i).getParcelID());
            }       
        parcelJList.setListData(ID);
        if (location < 0) //if nothing is selected
            parcelJList.setSelectedIndex(0); //select first parcel
        else
            parcelJList.setSelectedIndex(location);  
    }
    /**
     * Method: findState
     * @param parcels ArrayList 
     * @return ArrayList
     * pre-condition: 
     * post-condition: 
     */
    public ArrayList <Parcel> findState(ArrayList < Parcel > parcels)
    {  
        parcelsByState.clear();
        String all ="ALL";
    if ( all.equalsIgnoreCase(String.valueOf(stateJComboBox.getSelectedItem()))){
        return this.parcels;
    }
      for ( int i = 0 ; i< parcels.size(); i++){
          if ( parcels.get(i).person.getState().equalsIgnoreCase(String.valueOf(stateJComboBox.getSelectedItem())))
              parcelsByState.add(parcels.get(i));
      }
      return parcelsByState;
    }
   
    /**
     * Method: searchParcel
     * @parem input: String 
     * @return void
     * pre-condition: ArrayList members filled-in with members objects, int i >= 0.
     * post-condition: find a Parcel by name
     */
    private void searchParcel(String input){
      try {
        if (input != null && (input.length()>0)){
          displayParcel(parcels);
          String [] ParcelArray = new String [parcels.size()];
          for ( int i = 0; i < ParcelArray.length; i++){
              if (Validation.isLetter(input))
                 ParcelArray[i] = parcels.get(i).person.getFirstName()+" "+parcels.get(i).person.getLastName();
              else 
                 ParcelArray[i] = (String.valueOf(parcels.get(i).getParcelID()));
          }
          int index = linearSearch(ParcelArray, input);
          if ( index<0){
              JOptionPane.showMessageDialog(null, input + " not found ", " Search result", JOptionPane.INFORMATION_MESSAGE);
          }
          else parcelJList.setSelectedIndex(index);
          showParcelData(index,parcels);
        }
      } 
      catch (ArrayIndexOutOfBoundsException e){
          e.printStackTrace();
      }
  }
  
      /**
     * Method: linearSearch
     * @param array String[] Array
     * @param key String
     * @return int
     * pre-condition: 
     * post-condition: find a Parcel by ID
     */
    public int linearSearch(String [] array, String key) {
    for ( int i = 0; i < array.length; i++){
        if (array[i].toLowerCase().contains(key.toLowerCase()))
            return i;
    }
    return -1;
   }
    /**
     * Method: disAbleAll
     * When parcels has 1 or less elements.
     */
    public void disAbleAll(){
         removeJButton.setEnabled(false);
        editJButton.setEnabled(false);
        searchJButton.setEnabled(false);
        nextJButton.setEnabled(false);
        backJButton.setEnabled(false);
        RemoveJMenuItem.setEnabled(false);
        EditJMenuItem.setEnabled(false);
        SearchJMenu.setEnabled(false);
        BackJMenuItem.setEnabled(false);
        NextJMenuItem.setEnabled(false);
        stateJComboBox.setEnabled(false);
    }
    /**
     * Method: enableOne
     */
    public void enableOne(){
        removeJButton.setEnabled(true);
        editJButton.setEnabled(true);
        searchJButton.setEnabled(true);
        RemoveJMenuItem.setEnabled(true);
        EditJMenuItem.setEnabled(true);
        SearchJMenu.setEnabled(true);
        stateJComboBox.setEnabled(true);
        parcelJList.setEnabled(true);
    }
    /**
     * Method: enableTwo
     * Enable all buttons after we gain two parcles
     * pre-condition: back and next are disabled
     * post-condition: enable back and next
     */
    public void enableTwo(){
        BackJMenuItem.setEnabled(true);
        NextJMenuItem.setEnabled(true);
        nextJButton.setEnabled(true);
        backJButton.setEnabled(true);
        removeJButton.setEnabled(true);
        editJButton.setEnabled(true);
        searchJButton.setEnabled(true);
        RemoveJMenuItem.setEnabled(true);
        EditJMenuItem.setEnabled(true);
        SearchJMenu.setEnabled(true);
        stateJComboBox.setEnabled(true);
        parcelJList.setEnabled(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listJPanel = new javax.swing.JPanel();
        listJScrollPane = new javax.swing.JScrollPane();
        parcelJList = new javax.swing.JList<>();
        titleJPanel = new javax.swing.JPanel();
        logoJLabel = new javax.swing.JLabel();
        dateJTextField = new javax.swing.JTextField();
        dateJLabel = new javax.swing.JLabel();
        displayJPanel = new javax.swing.JPanel();
        parcelJLabel = new javax.swing.JLabel();
        parcelJTextField = new javax.swing.JTextField();
        lastNameJLabel = new javax.swing.JLabel();
        lastNameJTextField = new javax.swing.JTextField();
        firstNameJLabel = new javax.swing.JLabel();
        firstNameJTextField = new javax.swing.JTextField();
        addressJLabel = new javax.swing.JLabel();
        addressJTextField = new javax.swing.JTextField();
        cityJLabel = new javax.swing.JLabel();
        cityJTextField = new javax.swing.JTextField();
        stateJLabel = new javax.swing.JLabel();
        stateJTextField = new javax.swing.JTextField();
        zipJLabel = new javax.swing.JLabel();
        zipJTextField = new javax.swing.JTextField();
        controlJPanel = new javax.swing.JPanel();
        scanNewJButton = new javax.swing.JButton();
        removeJButton = new javax.swing.JButton();
        editJButton = new javax.swing.JButton();
        searchJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();
        nextJButton = new javax.swing.JButton();
        stateJComboBox = new javax.swing.JComboBox<>();
        parcelJMenuBar = new javax.swing.JMenuBar();
        FileJMenu = new javax.swing.JMenu();
        OpenJMenuItem = new javax.swing.JMenuItem();
        ClearJMenuItem = new javax.swing.JMenuItem();
        PrintJMenuItem = new javax.swing.JMenuItem();
        JSeparator = new javax.swing.JPopupMenu.Separator();
        ExitJMenuItem = new javax.swing.JMenuItem();
        ActionJMenu = new javax.swing.JMenu();
        ScanNewJMenuItem = new javax.swing.JMenuItem();
        RemoveJMenuItem = new javax.swing.JMenuItem();
        EditJMenuItem = new javax.swing.JMenuItem();
        BackJMenuItem = new javax.swing.JMenuItem();
        NextJMenuItem = new javax.swing.JMenuItem();
        SearchJMenu = new javax.swing.JMenu();
        ByNameJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        ByIdJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        HelpJMenu = new javax.swing.JMenu();
        AboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ShippingHub");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        parcelJList.setToolTipText("display parcels");
        parcelJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                parcelJListValueChanged(evt);
            }
        });
        listJScrollPane.setViewportView(parcelJList);

        javax.swing.GroupLayout listJPanelLayout = new javax.swing.GroupLayout(listJPanel);
        listJPanel.setLayout(listJPanelLayout);
        listJPanelLayout.setHorizontalGroup(
            listJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listJScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );
        listJPanelLayout.setVerticalGroup(
            listJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        logoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shippinghub/wow-mailboxes.jpg"))); // NOI18N

        dateJTextField.setEditable(false);
        dateJTextField.setToolTipText("Arrive time");

        dateJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateJLabel.setText("Arrived at: ");

        javax.swing.GroupLayout titleJPanelLayout = new javax.swing.GroupLayout(titleJPanel);
        titleJPanel.setLayout(titleJPanelLayout);
        titleJPanelLayout.setHorizontalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleJPanelLayout.createSequentialGroup()
                    .addContainerGap(236, Short.MAX_VALUE)
                    .addComponent(logoJLabel)
                    .addContainerGap()))
            .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleJPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(dateJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(602, Short.MAX_VALUE)))
            .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleJPanelLayout.createSequentialGroup()
                    .addContainerGap(85, Short.MAX_VALUE)
                    .addComponent(dateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(679, Short.MAX_VALUE)))
        );
        titleJPanelLayout.setVerticalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
            .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleJPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(logoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleJPanelLayout.createSequentialGroup()
                    .addContainerGap(76, Short.MAX_VALUE)
                    .addComponent(dateJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(titleJPanelLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(dateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(85, Short.MAX_VALUE)))
        );

        parcelJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        parcelJLabel.setText("ParcelID:");

        parcelJTextField.setEditable(false);
        parcelJTextField.setToolTipText("Your Parcel ID");

        lastNameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastNameJLabel.setText("Last Name:");

        lastNameJTextField.setEditable(false);
        lastNameJTextField.setToolTipText("last name");

        firstNameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstNameJLabel.setText("First Name:");

        firstNameJTextField.setEditable(false);
        firstNameJTextField.setToolTipText("first name");

        addressJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addressJLabel.setText("Address:");

        addressJTextField.setEditable(false);
        addressJTextField.setToolTipText("address");

        cityJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cityJLabel.setText("City:");

        cityJTextField.setEditable(false);
        cityJTextField.setToolTipText("city");

        stateJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stateJLabel.setText("State:");

        stateJTextField.setEditable(false);
        stateJTextField.setToolTipText("state");

        zipJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        zipJLabel.setText("Zip:");

        zipJTextField.setEditable(false);
        zipJTextField.setToolTipText("zip code");

        javax.swing.GroupLayout displayJPanelLayout = new javax.swing.GroupLayout(displayJPanel);
        displayJPanel.setLayout(displayJPanelLayout);
        displayJPanelLayout.setHorizontalGroup(
            displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayJPanelLayout.createSequentialGroup()
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(parcelJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(firstNameJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cityJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(displayJPanelLayout.createSequentialGroup()
                        .addComponent(cityJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zipJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zipJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(parcelJTextField)
                    .addComponent(addressJTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, displayJPanelLayout.createSequentialGroup()
                        .addComponent(firstNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayJPanelLayout.createSequentialGroup()
                    .addGap(0, 316, Short.MAX_VALUE)
                    .addComponent(lastNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        displayJPanelLayout.setVerticalGroup(
            displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayJPanelLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(parcelJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parcelJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(firstNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(firstNameJTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cityJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cityJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stateJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(zipJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(zipJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayJPanelLayout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(lastNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(100, Short.MAX_VALUE)))
        );

        controlJPanel.setLayout(new java.awt.GridLayout(1, 0));

        scanNewJButton.setText("Scan New");
        scanNewJButton.setToolTipText("Add a new parcel");
        scanNewJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanNewJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(scanNewJButton);

        removeJButton.setText("Remove");
        removeJButton.setToolTipText("remove a parcel");
        removeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(removeJButton);

        editJButton.setText("Edit");
        editJButton.setToolTipText("edit a parcel");
        editJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(editJButton);

        searchJButton.setText("Search");
        searchJButton.setToolTipText("Search by name or ID");
        searchJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(searchJButton);

        backJButton.setText("< Back");
        backJButton.setToolTipText("Move back to last parcel");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(backJButton);

        nextJButton.setText("Next >");
        nextJButton.setToolTipText("Move to next parcel");
        nextJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(nextJButton);

        stateJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "MA", "MD", "MS", "MI", "MN", "MI", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY" }));
        stateJComboBox.setToolTipText("Display by state");
        stateJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateJComboBoxActionPerformed(evt);
            }
        });

        FileJMenu.setText("File");
        FileJMenu.setToolTipText("file");

        OpenJMenuItem.setText("Open");
        OpenJMenuItem.setToolTipText("Open new file");
        OpenJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenJMenuItemActionPerformed(evt);
            }
        });
        FileJMenu.add(OpenJMenuItem);

        ClearJMenuItem.setText("Clear");
        ClearJMenuItem.setToolTipText("clear all fields");
        ClearJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearJMenuItemActionPerformed(evt);
            }
        });
        FileJMenu.add(ClearJMenuItem);

        PrintJMenuItem.setText("Print");
        PrintJMenuItem.setToolTipText("Print");
        PrintJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintJMenuItemActionPerformed(evt);
            }
        });
        FileJMenu.add(PrintJMenuItem);
        FileJMenu.add(JSeparator);

        ExitJMenuItem.setText("Exit");
        ExitJMenuItem.setToolTipText("Exit program");
        ExitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitJMenuItemActionPerformed(evt);
            }
        });
        FileJMenu.add(ExitJMenuItem);

        parcelJMenuBar.add(FileJMenu);

        ActionJMenu.setText("Action");

        ScanNewJMenuItem.setText("ScanNew");
        ScanNewJMenuItem.setToolTipText("Add new Parcel");
        ScanNewJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScanNewJMenuItemActionPerformed(evt);
            }
        });
        ActionJMenu.add(ScanNewJMenuItem);

        RemoveJMenuItem.setText("Remove");
        RemoveJMenuItem.setToolTipText("Remove a selected parcel");
        RemoveJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveJMenuItemActionPerformed(evt);
            }
        });
        ActionJMenu.add(RemoveJMenuItem);

        EditJMenuItem.setText("Edit");
        EditJMenuItem.setToolTipText("Edit a pracel");
        EditJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditJMenuItemActionPerformed(evt);
            }
        });
        ActionJMenu.add(EditJMenuItem);

        BackJMenuItem.setText("Back");
        BackJMenuItem.setToolTipText("Move back to the previous parcel");
        BackJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackJMenuItemActionPerformed(evt);
            }
        });
        ActionJMenu.add(BackJMenuItem);

        NextJMenuItem.setText("Next");
        NextJMenuItem.setToolTipText("Move to the next parcel");
        NextJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextJMenuItemActionPerformed(evt);
            }
        });
        ActionJMenu.add(NextJMenuItem);

        SearchJMenu.setText("Search");
        SearchJMenu.setToolTipText("Search");

        ByNameJRadioButtonMenuItem.setSelected(true);
        ByNameJRadioButtonMenuItem.setText("By Name");
        ByNameJRadioButtonMenuItem.setToolTipText("by user's name");
        ByNameJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ByNameJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        SearchJMenu.add(ByNameJRadioButtonMenuItem);

        ByIdJRadioButtonMenuItem.setSelected(true);
        ByIdJRadioButtonMenuItem.setText("By ID");
        ByIdJRadioButtonMenuItem.setToolTipText("by parcel's ID");
        ByIdJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ByIdJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        SearchJMenu.add(ByIdJRadioButtonMenuItem);

        ActionJMenu.add(SearchJMenu);

        parcelJMenuBar.add(ActionJMenu);

        HelpJMenu.setText("Help");

        AboutJMenuItem.setText("About");
        AboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutJMenuItemActionPerformed(evt);
            }
        });
        HelpJMenu.add(AboutJMenuItem);

        parcelJMenuBar.add(HelpJMenu);

        setJMenuBar(parcelJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(322, 532, Short.MAX_VALUE)
                        .addComponent(listJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(controlJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(329, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(533, Short.MAX_VALUE)
                    .addComponent(stateJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(listJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(388, Short.MAX_VALUE)
                    .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(177, 177, 177)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(206, Short.MAX_VALUE)
                    .addComponent(stateJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(221, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Method: clearAll
     * @return void
     * pre-condition: TextFields with data
     * post-condition: cleanAll, bye-bye
     */
    private void clearAll()
    {
        //Clear and set JTextFields visible
        parcelJTextField.setText("");
        firstNameJTextField.setText(""); 
        lastNameJTextField.setText("");
        dateJTextField.setText(""); 
        addressJTextField.setText(""); 
        cityJTextField.setText("");
        stateJTextField.setText(""); 
        zipJTextField.setText("");
        parcelJList.setEnabled(false);
        removeJButton.setEnabled(false);
        editJButton.setEnabled(false);
        searchJButton.setEnabled(false);
        nextJButton.setEnabled(false);
        backJButton.setEnabled(false);
        RemoveJMenuItem.setEnabled(false);
        EditJMenuItem.setEnabled(false);
        SearchJMenu.setEnabled(false);
        BackJMenuItem.setEnabled(false);
        NextJMenuItem.setEnabled(false);
        stateJComboBox.setEnabled(false);
        scanNewJButton.requestFocus();  
    }
    private void AboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutJMenuItemActionPerformed
        About shippingHubAbout = new About();
        shippingHubAbout.setVisible(true);
        //Display About form
    }//GEN-LAST:event_AboutJMenuItemActionPerformed

    private void ScanNewJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScanNewJMenuItemActionPerformed
        //call ScanNewActionPerformed
        scanNewJButtonActionPerformed(evt);
    }//GEN-LAST:event_ScanNewJMenuItemActionPerformed

    private void RemoveJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveJMenuItemActionPerformed
        // call removebuttonActionPerformed
          removeJButtonActionPerformed(evt);
    }//GEN-LAST:event_RemoveJMenuItemActionPerformed

    private void BackJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackJMenuItemActionPerformed
          // call backJbuttonActionPerformed;
        backJButtonActionPerformed(evt);
    }//GEN-LAST:event_BackJMenuItemActionPerformed

    private void NextJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextJMenuItemActionPerformed
        // call nextJbuttonActionPerformed;
       nextJButtonActionPerformed(evt);
    }//GEN-LAST:event_NextJMenuItemActionPerformed

    private void OpenJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenJMenuItemActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\ShippingHub\\src\\ShippingHub"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File", "txt"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        readFromFile(selectedFile.getAbsolutePath());
        displayParcel(parcels);
        int index = (parcelJList.getSelectedIndex());
        if (index == -1)
        {
            index = 0;
        }
        showParcelData(index,parcels);
        if (parcels.size()==1){
        enableOne();
        }
        if (parcels.size() >1){
        enableTwo();
        }
}
    }//GEN-LAST:event_OpenJMenuItemActionPerformed

    private void ClearJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearJMenuItemActionPerformed
        clearAll();// Empty all fields and reset form by calling the method clearAll
    }//GEN-LAST:event_ClearJMenuItemActionPerformed

    private void PrintJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintJMenuItemActionPerformed
        PrintUtilities.printComponent(this);// Print entire form
    }//GEN-LAST:event_PrintJMenuItemActionPerformed

    private void ExitJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitJMenuItemActionPerformed
        saveParcels();
        System.exit(0);
    }//GEN-LAST:event_ExitJMenuItemActionPerformed

    private void stateJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateJComboBoxActionPerformed
  try { 
         displayParcel(findState(parcels));
         int index = (parcelJList.getSelectedIndex());
                if (index == -1)
                {
                 index = 0;
                } 
        showParcelData(index,findState(parcels));
  }
  catch (NullPointerException e){
      JOptionPane.showMessageDialog(null, "No Parcel of this state Found.", 
                    "Parcel does not esist.", JOptionPane.WARNING_MESSAGE);
  }
   catch(IndexOutOfBoundsException e){
        JOptionPane.showMessageDialog(null, "No Parcel found",
                     "No parcel belongs to this state", JOptionPane.WARNING_MESSAGE);
        displayParcel(parcels);
        showParcelData(0,parcels);
        parcelsByState.clear();
        stateJComboBox.setSelectedItem("ALL");
  }
    }//GEN-LAST:event_stateJComboBoxActionPerformed

    private void nextJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextJButtonActionPerformed

        try {
   if (i <findState(parcels).size()-1){
    i++;
        showParcelData(i,findState(parcels));
        displayParcel(findState(parcels));
        parcelJList.setSelectedIndex(i);
   }   
   else 
   JOptionPane.showMessageDialog(null, "Last Parcel.", 
                    "Parcel is null or laready exists", JOptionPane.WARNING_MESSAGE);
   }
   catch (IndexOutOfBoundsException e){
   System.err.println("Last Parcel. " + e.getMessage());
   }
   
   
    }//GEN-LAST:event_nextJButtonActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed

        try {
   if (i>0) {
       i--;
       showParcelData(i,findState(parcels));
       displayParcel(findState(parcels));
       parcelJList.setSelectedIndex(i);
   }
   else 
   JOptionPane.showMessageDialog(null, "First Parcel.", 
                    "Parcel is null or laready exists", JOptionPane.WARNING_MESSAGE);
   }
   catch (IndexOutOfBoundsException e){
   System.err.println("First Parcel. " + e.getMessage());
   }
   
    }//GEN-LAST:event_backJButtonActionPerformed

    private void scanNewJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanNewJButtonActionPerformed
        // Add new parcel
         try
        {            
            // Create and display a new ScanNewDialog
            ScanNew scanNew = new ScanNew(this, true);
            scanNew.setVisible(true);
            // The modal dialog takes focus, upon regaining focus:
            Parcel newParcel = scanNew.getParcel();

            if (newParcel != null) 
            {
                // Add the new parcel to the database
                parcels.add(newParcel);              
                displayParcel(parcels);                  //refresh GUI
                int index = (parcelJList.getSelectedIndex());
                if (index == -1)
                {
                 index = 0;
                }
                showParcelData(index,parcels);
      //           saveParcels();
                 if (parcels.size() ==1){
                   enableOne();
                 }
                 if (parcels.size() >1){
                   enableTwo();
                 }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Parcel not Added", 
                    "Parcel is null or laready exists", JOptionPane.WARNING_MESSAGE);                
                parcelJList.setVisible(true);
                parcelJList.setSelectedIndex(0);                
            }

        }
        catch (NullPointerException nullex)
        {
            JOptionPane.showMessageDialog(null, "Parcel not Added", "Input Error",
                  JOptionPane.WARNING_MESSAGE);            
            parcelJList.setVisible(true);
            parcelJList.setSelectedIndex(0);
       }   
    }//GEN-LAST:event_scanNewJButtonActionPerformed

    private void removeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeJButtonActionPerformed
         // Delete selected parcel        
        int result = JOptionPane.showConfirmDialog(null, 
                "Are you sure to delete parcel?", "Delete Parcel",
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.INFORMATION_MESSAGE);
       if ( result == JOptionPane.YES_OPTION){
           int index = parcelJList.getSelectedIndex();
           parcels.remove(index);
   //        saveParcels();
           showParcelData(i,parcels);
           displayParcel(parcels);
           if (parcels.size() == 1){
             disAbleAll();
         }
       }
    }//GEN-LAST:event_removeJButtonActionPerformed

    private void editJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editJButtonActionPerformed
        //Clear and set JTextFields visible--not a good a good implementation
        int index = parcelJList.getSelectedIndex();
        try
        {   
            Person parcelToEdit = new Person(parcels.get(index).person);
            EditParcel editedParcel = new EditParcel(parcelToEdit); 
            editedParcel.setVisible(true);
            
             if(editedParcel.getParcel() != null){
                parcels.remove(index);
                parcels.add(editedParcel.getParcel());
    //            saveParcels();
                displayParcel(parcels);
                showParcelData(i,parcels);
            }
             else
            {
                JOptionPane.showMessageDialog(null, "Parcel not Edited", 
                    "Edit canceled", JOptionPane.WARNING_MESSAGE);                
                parcelJList.setVisible(true);
                parcelJList.setSelectedIndex(0);                
            }
        }
       catch (NullPointerException exp){
                    JOptionPane.showMessageDialog(null, "Parcel was not edited"
                           , " Edit Error", JOptionPane.WARNING_MESSAGE);
                    parcelJList.setSelectedIndex(0);
                    } 
    }//GEN-LAST:event_editJButtonActionPerformed

    private void searchJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJButtonActionPerformed
        String input = JOptionPane.showInputDialog("Please Enter a Name or ID:");
        searchParcel(input);
    }//GEN-LAST:event_searchJButtonActionPerformed

    private void EditJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditJMenuItemActionPerformed
        // call editJbuttonActionPerformed;
        editJButtonActionPerformed(evt);
    }//GEN-LAST:event_EditJMenuItemActionPerformed

    private void ByNameJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ByNameJRadioButtonMenuItemActionPerformed
        String input = JOptionPane.showInputDialog("Please Enter a Name :");
        searchParcel(input);
    }//GEN-LAST:event_ByNameJRadioButtonMenuItemActionPerformed

    private void ByIdJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ByIdJRadioButtonMenuItemActionPerformed
        String input = JOptionPane.showInputDialog("Please Enter an ID :");
        searchParcel(input);
    }//GEN-LAST:event_ByIdJRadioButtonMenuItemActionPerformed

    private void parcelJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_parcelJListValueChanged
        int index = (parcelJList.getSelectedIndex());
        if (index == -1)
        {
            index = 0;
        }
        showParcelData(index,parcels);
    }//GEN-LAST:event_parcelJListValueChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saveParcels();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing
  
    public static void main(String args[]) {
      SplashScreen splashScreen = new SplashScreen();
      splashScreen.setVisible(true);
  
        }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AboutJMenuItem;
    private javax.swing.JMenu ActionJMenu;
    private javax.swing.JMenuItem BackJMenuItem;
    private javax.swing.JRadioButtonMenuItem ByIdJRadioButtonMenuItem;
    private javax.swing.JRadioButtonMenuItem ByNameJRadioButtonMenuItem;
    private javax.swing.JMenuItem ClearJMenuItem;
    private javax.swing.JMenuItem EditJMenuItem;
    private javax.swing.JMenuItem ExitJMenuItem;
    private javax.swing.JMenu FileJMenu;
    private javax.swing.JMenu HelpJMenu;
    private javax.swing.JPopupMenu.Separator JSeparator;
    private javax.swing.JMenuItem NextJMenuItem;
    private javax.swing.JMenuItem OpenJMenuItem;
    private javax.swing.JMenuItem PrintJMenuItem;
    private javax.swing.JMenuItem RemoveJMenuItem;
    private javax.swing.JMenuItem ScanNewJMenuItem;
    private javax.swing.JMenu SearchJMenu;
    private javax.swing.JLabel addressJLabel;
    private javax.swing.JTextField addressJTextField;
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel cityJLabel;
    private javax.swing.JTextField cityJTextField;
    private javax.swing.JPanel controlJPanel;
    private javax.swing.JLabel dateJLabel;
    private javax.swing.JTextField dateJTextField;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JButton editJButton;
    private javax.swing.JLabel firstNameJLabel;
    private javax.swing.JTextField firstNameJTextField;
    private javax.swing.JLabel lastNameJLabel;
    private javax.swing.JTextField lastNameJTextField;
    private javax.swing.JPanel listJPanel;
    private javax.swing.JScrollPane listJScrollPane;
    private javax.swing.JLabel logoJLabel;
    private javax.swing.JButton nextJButton;
    private javax.swing.JLabel parcelJLabel;
    private javax.swing.JList<String> parcelJList;
    private javax.swing.JMenuBar parcelJMenuBar;
    private javax.swing.JTextField parcelJTextField;
    private javax.swing.JButton removeJButton;
    private javax.swing.JButton scanNewJButton;
    private javax.swing.JButton searchJButton;
    private javax.swing.JComboBox<String> stateJComboBox;
    private javax.swing.JLabel stateJLabel;
    private javax.swing.JTextField stateJTextField;
    private javax.swing.JPanel titleJPanel;
    private javax.swing.JLabel zipJLabel;
    private javax.swing.JTextField zipJTextField;
    // End of variables declaration//GEN-END:variables

}

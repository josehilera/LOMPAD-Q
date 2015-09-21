/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.licef.lompad;

//import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.TreeSet;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author usuario
 */
public class EvaluateFrame extends javax.swing.JFrame{

    /**
     * Creates new form EvaluateFrame
     */
    
    JPanelForm jpaf;
    
    private File file = null;
    private String workingFolder = FileSystemView.getFileSystemView().getDefaultDirectory().toString();
        
    private String title1_2;
    private String description1_4;    
    private String structure1_7;
    private String aggregationLevel1_8;
    private String demand1_9;
    private String qualification1_10_1;
    private String validity1_10_3;
    
    private String requirement4_4;
    private String otherPlataforms4_6;
    private String adaptation4_8_1;
    private String help4_8_2;
    private String hardware4_8_3;
    private String[] software4_8_4;
    private String web4_8_5;
    private String usability4_9;
    private String generalUsability4_9_1;
    
    
    private String[] interactivityType5_1;
    private String[] learningResourceType5_2;
    private String[] interactivityLevel5_3;
    private String[] intendedEndUsertRole5_5;
    private String[] context5_6;
    private String[] difficulty5_8;
    private String[] typicalLearningTime5_9;    
    private String[] description5_10;
    private String[] features5_12_1;
    private String[] communication5_12_2;
    private String[] tutoringSystem5_13_1;
    private String[] responseTime5_13_2;
    private String[] learningMethod5_14;
    private String[] trainingType5_15;
    
    private String[] purpose9_1;
    private String[] taxonPath9_2;   
    
    private TreeSet<String> software;   
    
    
    public EvaluateFrame(JPanelForm jpaf) {
        initComponents();
        
        setIconImage(Util.imageEvaluate);
        
        this.jpaf = jpaf;             
        //jPanelMetodology.setVisible(false);
        //jPanelAccessibility.setVisible(false);   
        jTextFieldAccessibility.setVisible(false);
        
        title1_2 = jpaf.lomForm.toEvaluateFrame("1.2", 0, 1);
        description1_4 = jpaf.lomForm.toEvaluateFrame("1.4", 0, 3);  //.substring(0, description1_4.length() - 1) + ":.-.:";
        structure1_7 = jpaf.lomForm.toEvaluateFrame("1.7", 0, 6);
        aggregationLevel1_8 = jpaf.lomForm.toEvaluateFrame("1.8", 0, 7);
        demand1_9 = jpaf.lomForm.toEvaluateFrame("1.9", 0, 8);
        qualification1_10_1 = jpaf.lomForm.toEvaluateFrame("1.10.1", 0, 9);
        validity1_10_3 = jpaf.lomForm.toEvaluateFrame("1.10.3", 0, 9);
        
        requirement4_4 = jpaf.lomForm.toEvaluateFrame("4.4", 3, 3);
        otherPlataforms4_6 = jpaf.lomForm.toEvaluateFrame("4.6", 3, 5);
        adaptation4_8_1 = jpaf.lomForm.toEvaluateFrame("4.8.1", 3, 7);
        help4_8_2 = jpaf.lomForm.toEvaluateFrame("4.8.2", 3, 7);
        hardware4_8_3 = jpaf.lomForm.toEvaluateFrame("4.8.3", 3, 7).replace("\n", "");
        software4_8_4 = jpaf.lomForm.toEvaluateFrame("4.8.4", 3, 7).split("\n");
        web4_8_5 = jpaf.lomForm.toEvaluateFrame("4.8.5", 3, 7).replace("\n", "");
        //usability4_9 = jpaf.lomForm.toEvaluateFrame("4.9", 3, 8);
        generalUsability4_9_1 = jpaf.lomForm.toEvaluateFrame("4.9.1", 3, 8);
        
        interactivityType5_1 = jpaf.lomForm.toEvaluateFrame("5.1", 4, 0).split(":.-.:");        
        learningResourceType5_2 = jpaf.lomForm.toEvaluateFrame("5.2", 4, 1).split(":.-.:");
        interactivityLevel5_3 = jpaf.lomForm.toEvaluateFrame("5.3", 4, 2).split(":.-.:");
        intendedEndUsertRole5_5 = jpaf.lomForm.toEvaluateFrame("5.5", 4, 4).split(":.-.:");
        context5_6 = jpaf.lomForm.toEvaluateFrame("5.6", 4, 5).split(":.-.:");
        difficulty5_8 = jpaf.lomForm.toEvaluateFrame("5.8", 4, 7).split(":.-.:");
        typicalLearningTime5_9 = jpaf.lomForm.toEvaluateFrame("5.9", 4, 8).split(":.-.:");
        description5_10 = jpaf.lomForm.toEvaluateFrame("5.10", 4, 9).split(":.-.:");
        features5_12_1 = jpaf.lomForm.toEvaluateFrame("5.12.1", 4, 11).split(":.-.:");
        communication5_12_2 = jpaf.lomForm.toEvaluateFrame("5.12.2", 4, 11).split(":.-.:");
        tutoringSystem5_13_1 = jpaf.lomForm.toEvaluateFrame("5.13.1", 4, 12).split(":.-.:");
        responseTime5_13_2 = jpaf.lomForm.toEvaluateFrame("5.13.2", 4, 12).split(":.-.:");
        learningMethod5_14 = jpaf.lomForm.toEvaluateFrame("5.14", 4, 13).split(":.-.:");
        trainingType5_15 = jpaf.lomForm.toEvaluateFrame("5.15", 4, 14).split(":.-.:");
        
        purpose9_1 = jpaf.lomForm.toEvaluateFrame("9.1", 8, 0).split(":.-.:");
        taxonPath9_2 = jpaf.lomForm.toEvaluateFrame("9.2", 8, 1).split(":.-.:");  
        
        software = new TreeSet<>(Arrays.asList(software4_8_4)); //Para accessibility Software (como conjunto)
        
        //Relleno los textField y los textArea
        
        this.jTextFieldName.setText(title1_2.replace("\n", ""));        
        this.jTextAreaObjectives.setText(description1_4 + fillPurposeAndTaxonPath());
            this.jTextAreaObjectives.setCaretPosition(0); //Pone el cursor al principio del textArea
        fillFields(trainingType5_15, jTextAreaTrainingType);
            this.jTextAreaTrainingType.setCaretPosition(0);
        this.jTextFieldDemand.setText(demand1_9);        
        fillPreviousTraining();        
        fillFields(typicalLearningTime5_9, jTextFieldDedication);        
        this.jTextAreaHardwareRequirements.setText(otherPlataforms4_6);
            this.jTextAreaHardwareRequirements.setCaretPosition(0);
        this.jTextAreaSoftwareRequirements.setText(requirement4_4);
            this.jTextAreaSoftwareRequirements.setCaretPosition(0);
        this.jTextFieldRecognitionTrainingEmployability.setText(Integer.toString(recognitionTrainingEmployability()));
        this.jTextFieldMetodology.setText(Integer.toString(metodology()));
        this.jTextFieldAccessibility.setText(Integer.toString(accessibility(software)));                
       
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
        jPanel1 = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelObjectives = new javax.swing.JLabel();
        jLabelTrainingType = new javax.swing.JLabel();
        jLabelDemand = new javax.swing.JLabel();
        jTextFieldDemand = new javax.swing.JTextField();
        jLabelPreviousTraining = new javax.swing.JLabel();
        jLabelDedication = new javax.swing.JLabel();
        jTextFieldDedication = new javax.swing.JTextField();
        jLabelHardwareRequirements = new javax.swing.JLabel();
        jLabelSoftwareRequirements = new javax.swing.JLabel();
        jScrollPaneTrainingType = new javax.swing.JScrollPane();
        jTextAreaTrainingType = new javax.swing.JTextArea();
        jLabelTitleWindow = new javax.swing.JLabel();
        jPanelLevels = new javax.swing.JPanel();
        jLabelSubtittle = new javax.swing.JLabel();
        jLabelRecognitionTrainingEmployability = new javax.swing.JLabel();
        jTextFieldRecognitionTrainingEmployability = new javax.swing.JTextField();
        jLabelMetodology = new javax.swing.JLabel();
        jTextFieldMetodology = new javax.swing.JTextField();
        jPanelMetodology = new javax.swing.JPanel();
        jLabelMetodology_Design = new javax.swing.JLabel();
        jLabelMetodology_Resources = new javax.swing.JLabel();
        jLabelMetodology_Tutoring = new javax.swing.JLabel();
        jTextFieldMetodology_Design = new javax.swing.JTextField();
        jTextFieldMetodology_Resources = new javax.swing.JTextField();
        jTextFieldMetodology_Tutoring = new javax.swing.JTextField();
        jLabelMetodology_Environment = new javax.swing.JLabel();
        jTextFieldMetodology_Environment = new javax.swing.JTextField();
        jLabelAccessibility = new javax.swing.JLabel();
        jTextFieldAccessibility = new javax.swing.JTextField();
        jPanelAccessibility = new javax.swing.JPanel();
        jLabelHardwareAccessibility = new javax.swing.JLabel();
        jLabelSoftwareAccessibility = new javax.swing.JLabel();
        jLabelWebAccessibility = new javax.swing.JLabel();
        jTextFieldHardwareAccessibility = new javax.swing.JTextField();
        jTextFieldSoftwareAccessibility = new javax.swing.JTextField();
        jTextFieldWebAccessibility = new javax.swing.JTextField();
        jLabelWarning = new javax.swing.JLabel();
        jLabelIconWarning = new javax.swing.JLabel();
        jScrollPaneObjectives = new javax.swing.JScrollPane();
        jTextAreaObjectives = new javax.swing.JTextArea();
        jScrollPaneHardwareRequirements = new javax.swing.JScrollPane();
        jTextAreaHardwareRequirements = new javax.swing.JTextArea();
        jScrollPanePreviousTraining = new javax.swing.JScrollPane();
        jTextAreaPreviousTraining = new javax.swing.JTextArea();
        jScrollPaneSoftwareRequirements = new javax.swing.JScrollPane();
        jTextAreaSoftwareRequirements = new javax.swing.JTextArea();
        jMenuEvaluate = new javax.swing.JMenuBar();
        jMenuEvaluateFile = new javax.swing.JMenu();
        jMenuEvaluateFileSave = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuEvaluateFileViewXML = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuEvaluateFileQuit = new javax.swing.JMenuItem();
        jMenuEvaluateHelp = new javax.swing.JMenu();
        jMenuEvaluateHelpUNE = new javax.swing.JMenuItem();
        jMenuEvaluateHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Evaluating");
        setPreferredSize(new java.awt.Dimension(1024, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(10, 10));

        jLabelName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelName.setText("Name:");

        jTextFieldName.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTextFieldName.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jLabelObjectives.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelObjectives.setText("Objectives:");

        jLabelTrainingType.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelTrainingType.setText("Training Type: ");

        jLabelDemand.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelDemand.setText("Demand: ");

        jTextFieldDemand.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTextFieldDemand.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jLabelPreviousTraining.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelPreviousTraining.setText("Previous Training: ");

        jLabelDedication.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelDedication.setText("Dedication: ");

        jTextFieldDedication.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTextFieldDedication.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jLabelHardwareRequirements.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelHardwareRequirements.setText("Hardware Requirements: ");

        jLabelSoftwareRequirements.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelSoftwareRequirements.setText("Software Requirements: ");

        jTextAreaTrainingType.setColumns(20);
        jTextAreaTrainingType.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTextAreaTrainingType.setRows(5);
        jTextAreaTrainingType.setCaretColor(new java.awt.Color(185, 209, 234));
        jTextAreaTrainingType.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jScrollPaneTrainingType.setViewportView(jTextAreaTrainingType);

        jLabelTitleWindow.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTitleWindow.setText("UNE 66181:2012 EVALUATION");

        jPanelLevels.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));

        jLabelSubtittle.setFont(new java.awt.Font("Tahoma", 3, 19)); // NOI18N
        jLabelSubtittle.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSubtittle.setText("Factors of Satisfaction and Quality Levels");

        jLabelRecognitionTrainingEmployability.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelRecognitionTrainingEmployability.setText("Recognition of Training for Employability: ");

        jTextFieldRecognitionTrainingEmployability.setEditable(false);

        jLabelMetodology.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelMetodology.setText("Learning Metodology:");
        jLabelMetodology.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMetodologyMouseClicked(evt);
            }
        });

        jTextFieldMetodology.setEditable(false);

        jPanelMetodology.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelMetodology_Design.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabelMetodology_Design.setText("Didactic Instructional Design: ");

        jLabelMetodology_Resources.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabelMetodology_Resources.setText("Training Resources and Learning Activities: ");

        jLabelMetodology_Tutoring.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabelMetodology_Tutoring.setText("Tutoring: ");

        jTextFieldMetodology_Design.setEditable(false);

        jTextFieldMetodology_Resources.setEditable(false);

        jTextFieldMetodology_Tutoring.setEditable(false);

        jLabelMetodology_Environment.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabelMetodology_Environment.setText("Technological and Digital Environment: ");

        jTextFieldMetodology_Environment.setEditable(false);

        javax.swing.GroupLayout jPanelMetodologyLayout = new javax.swing.GroupLayout(jPanelMetodology);
        jPanelMetodology.setLayout(jPanelMetodologyLayout);
        jPanelMetodologyLayout.setHorizontalGroup(
            jPanelMetodologyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMetodologyLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanelMetodologyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMetodology_Tutoring)
                    .addComponent(jLabelMetodology_Environment)
                    .addComponent(jLabelMetodology_Resources)
                    .addComponent(jLabelMetodology_Design))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMetodologyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldMetodology_Tutoring, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jTextFieldMetodology_Resources, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldMetodology_Design, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldMetodology_Environment))
                .addGap(42, 42, 42))
        );
        jPanelMetodologyLayout.setVerticalGroup(
            jPanelMetodologyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMetodologyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMetodologyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMetodology_Design)
                    .addComponent(jTextFieldMetodology_Design, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMetodologyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMetodology_Resources)
                    .addComponent(jTextFieldMetodology_Resources, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMetodologyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMetodology_Tutoring)
                    .addComponent(jTextFieldMetodology_Tutoring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMetodologyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMetodology_Environment)
                    .addComponent(jTextFieldMetodology_Environment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jLabelAccessibility.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabelAccessibility.setText("Accessibility:");
        jLabelAccessibility.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAccessibilityMouseClicked(evt);
            }
        });

        jTextFieldAccessibility.setEditable(false);

        jPanelAccessibility.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelHardwareAccessibility.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabelHardwareAccessibility.setText("Hardware Accessibility:");

        jLabelSoftwareAccessibility.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabelSoftwareAccessibility.setText("Software Accessibility:");

        jLabelWebAccessibility.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabelWebAccessibility.setText("Web Accessibility:");

        jTextFieldHardwareAccessibility.setEditable(false);

        jTextFieldSoftwareAccessibility.setEditable(false);

        jTextFieldWebAccessibility.setEditable(false);

        javax.swing.GroupLayout jPanelAccessibilityLayout = new javax.swing.GroupLayout(jPanelAccessibility);
        jPanelAccessibility.setLayout(jPanelAccessibilityLayout);
        jPanelAccessibilityLayout.setHorizontalGroup(
            jPanelAccessibilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccessibilityLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanelAccessibilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAccessibilityLayout.createSequentialGroup()
                        .addComponent(jLabelWebAccessibility)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldWebAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAccessibilityLayout.createSequentialGroup()
                        .addComponent(jLabelHardwareAccessibility)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addComponent(jTextFieldHardwareAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAccessibilityLayout.createSequentialGroup()
                        .addComponent(jLabelSoftwareAccessibility)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldSoftwareAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelAccessibilityLayout.setVerticalGroup(
            jPanelAccessibilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccessibilityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAccessibilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHardwareAccessibility)
                    .addComponent(jTextFieldHardwareAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelAccessibilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSoftwareAccessibility)
                    .addComponent(jTextFieldSoftwareAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAccessibilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelWebAccessibility)
                    .addComponent(jTextFieldWebAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout jPanelLevelsLayout = new javax.swing.GroupLayout(jPanelLevels);
        jPanelLevels.setLayout(jPanelLevelsLayout);
        jPanelLevelsLayout.setHorizontalGroup(
            jPanelLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLevelsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLevelsLayout.createSequentialGroup()
                        .addComponent(jPanelAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLevelsLayout.createSequentialGroup()
                        .addGroup(jPanelLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelMetodology, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))))
            .addGroup(jPanelLevelsLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanelLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLevelsLayout.createSequentialGroup()
                        .addGroup(jPanelLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAccessibility)
                            .addComponent(jLabelMetodology))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldMetodology, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLevelsLayout.createSequentialGroup()
                        .addComponent(jLabelRecognitionTrainingEmployability)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldRecognitionTrainingEmployability, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
            .addGroup(jPanelLevelsLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabelSubtittle, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanelLevelsLayout.setVerticalGroup(
            jPanelLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLevelsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSubtittle)
                .addGap(28, 28, 28)
                .addGroup(jPanelLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRecognitionTrainingEmployability)
                    .addComponent(jTextFieldRecognitionTrainingEmployability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMetodology)
                    .addComponent(jTextFieldMetodology, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanelMetodology, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanelLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAccessibility)
                    .addComponent(jTextFieldAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanelAccessibility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabelWarning.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabelWarning.setText("<html>This evaluation was performed by an automated process.<blockquote><blockquote><blockquote><blockquote>Please check the contents.</blockquote></blockquote></blockquote></blockquote></html>");
        jLabelWarning.setMaximumSize(new java.awt.Dimension(365, 84));

        jLabelIconWarning.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caution.png"))); // NOI18N

        jTextAreaObjectives.setColumns(20);
        jTextAreaObjectives.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTextAreaObjectives.setRows(5);
        jTextAreaObjectives.setCaretColor(new java.awt.Color(185, 209, 234));
        jTextAreaObjectives.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextAreaObjectives.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jScrollPaneObjectives.setViewportView(jTextAreaObjectives);

        jTextAreaHardwareRequirements.setColumns(20);
        jTextAreaHardwareRequirements.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTextAreaHardwareRequirements.setRows(5);
        jTextAreaHardwareRequirements.setCaretColor(new java.awt.Color(185, 209, 234));
        jTextAreaHardwareRequirements.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jScrollPaneHardwareRequirements.setViewportView(jTextAreaHardwareRequirements);

        jTextAreaPreviousTraining.setColumns(20);
        jTextAreaPreviousTraining.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTextAreaPreviousTraining.setRows(5);
        jTextAreaPreviousTraining.setCaretColor(new java.awt.Color(185, 209, 234));
        jTextAreaPreviousTraining.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jScrollPanePreviousTraining.setViewportView(jTextAreaPreviousTraining);

        jTextAreaSoftwareRequirements.setColumns(20);
        jTextAreaSoftwareRequirements.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTextAreaSoftwareRequirements.setRows(5);
        jTextAreaSoftwareRequirements.setCaretColor(new java.awt.Color(185, 209, 234));
        jTextAreaSoftwareRequirements.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jScrollPaneSoftwareRequirements.setViewportView(jTextAreaSoftwareRequirements);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelTitleWindow)
                                .addGap(198, 198, 198))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanelLevels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGap(25, 25, 25)
                                            .addComponent(jLabelObjectives))
                                        .addComponent(jLabelTrainingType)
                                        .addComponent(jLabelDemand))
                                    .addComponent(jLabelName, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelPreviousTraining, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDedication, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelHardwareRequirements, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelSoftwareRequirements, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPaneHardwareRequirements, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPaneTrainingType, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPaneObjectives, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldDemand, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPanePreviousTraining, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldDedication, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPaneSoftwareRequirements, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabelIconWarning)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelWarning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabelTitleWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPaneObjectives, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPaneTrainingType, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelName)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabelObjectives)
                        .addGap(50, 50, 50)
                        .addComponent(jLabelTrainingType)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDemand)
                            .addComponent(jTextFieldDemand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelPreviousTraining)
                        .addGap(92, 92, 92)
                        .addComponent(jLabelHardwareRequirements)
                        .addGap(49, 49, 49)
                        .addComponent(jLabelSoftwareRequirements))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPanePreviousTraining, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDedication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDedication))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPaneHardwareRequirements, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPaneSoftwareRequirements, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jPanelLevels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelIconWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jMenuEvaluate.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        jMenuEvaluateFile.setText("File");

        jMenuEvaluateFileSave.setText("Save");
        jMenuEvaluateFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEvaluateFileSaveActionPerformed(evt);
            }
        });
        jMenuEvaluateFile.add(jMenuEvaluateFileSave);
        jMenuEvaluateFile.add(jSeparator1);

        jMenuEvaluateFileViewXML.setText("View XML");
        jMenuEvaluateFileViewXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEvaluateFileViewXMLActionPerformed(evt);
            }
        });
        jMenuEvaluateFile.add(jMenuEvaluateFileViewXML);
        jMenuEvaluateFile.add(jSeparator2);

        jMenuEvaluateFileQuit.setText("Quit");
        jMenuEvaluateFileQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEvaluateFileQuitActionPerformed(evt);
            }
        });
        jMenuEvaluateFile.add(jMenuEvaluateFileQuit);

        jMenuEvaluate.add(jMenuEvaluateFile);

        jMenuEvaluateHelp.setText("Help");

        jMenuEvaluateHelpUNE.setText("UNE_66181=2012 document");
        jMenuEvaluateHelpUNE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEvaluateHelpUNEActionPerformed(evt);
            }
        });
        jMenuEvaluateHelp.add(jMenuEvaluateHelpUNE);

        jMenuEvaluateHelpAbout.setText("About...");
        jMenuEvaluateHelp.add(jMenuEvaluateHelpAbout);

        jMenuEvaluate.add(jMenuEvaluateHelp);

        setJMenuBar(jMenuEvaluate);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuEvaluateFileViewXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEvaluateFileViewXMLActionPerformed
        // TODO add your handling code here:
        String lom = this.toXMLEval();
        String tempFile = "evalXML.xml";
        
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String file = null;
        try {
            String tempDir = System.getProperty("java.io.tmpdir").replace('\\', '/');
            if (!tempDir.endsWith("/")) tempDir += "/";
            file = tempDir + tempFile;

            reader = new BufferedReader( new StringReader( lom ) );
            writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( file ), "UTF-8" ) );
            Util.copy( reader, writer );
        } 
        catch( FileNotFoundException e ) {
            e.printStackTrace();
        }
        catch( UnsupportedEncodingException e2 ) {
            e2.printStackTrace();
        }
        catch( IOException e3 ) {
            e3.printStackTrace();
        }
        finally {
            try {
                reader.close();
            }
            catch( IOException e ) {
                e.printStackTrace();
            }
            try {
                writer.close();
            }
            catch( IOException e ) {
                e.printStackTrace();
            }
        }
        Util.launchFile(file);
        
    }//GEN-LAST:event_jMenuEvaluateFileViewXMLActionPerformed

    private void jMenuEvaluateFileQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEvaluateFileQuitActionPerformed
        // TODO add your handling code here:
        this.exitEvaluateXML();
    }//GEN-LAST:event_jMenuEvaluateFileQuitActionPerformed

    private void jMenuEvaluateFileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEvaluateFileSaveActionPerformed
        // TODO add your handling code here:ç
        if (file == null){
            file =  selectFile("Evaluate XML - Saving...");   
            if (file != null)
                this.setTitle(this.getTitle() + " - " + file.getName());
        }
        if (file != null)
             try {
                String lqm = this.toXMLEval();
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
                writer.write(lqm);
                writer.flush();
                writer.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        
        
       
    }//GEN-LAST:event_jMenuEvaluateFileSaveActionPerformed

    private void jMenuEvaluateHelpUNEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEvaluateHelpUNEActionPerformed
        // TODO add your handling code here:
        Util.launchFile("http://ltsc.ieee.org/wg12/files/LOM_1484_12_1_v1_Final_Draft.pdf");
    }//GEN-LAST:event_jMenuEvaluateHelpUNEActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.exitEvaluateXML();
    }//GEN-LAST:event_formWindowClosing

    private void jLabelAccessibilityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAccessibilityMouseClicked
        // TODO add your handling code here:
        jPanelAccessibility.setVisible(!jPanelAccessibility.isVisible());
    }//GEN-LAST:event_jLabelAccessibilityMouseClicked

    private void jLabelMetodologyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMetodologyMouseClicked
        // TODO add your handling code here:
        jPanelMetodology.setVisible(!jPanelMetodology.isVisible());
    }//GEN-LAST:event_jLabelMetodologyMouseClicked
 
    
    private void exitEvaluateXML(){
        if (file == null){
            JDialogQuestion dialog = new JDialogQuestion(Util.getTopJFrame(this), "title", "text2");
            dialog.setVisible( true );
            int res = dialog.res;
            dialog.dispose();
         
            if (res == JDialogQuestion.NO)
                this.setVisible(false);
            else if (res == JDialogQuestion.YES) {            
                file = selectFile("Evaluate XML - Saving...");                              
            }
        }
        else
            this.setVisible(false);    
    }
    
    File selectFile(String label) {
        File f = null;        
        JFileChooser fc = new JFileChooser();        
        fc.setCurrentDirectory(new File(workingFolder));
        if (label != null)
            fc.setDialogTitle(" " + label);
        //fc.addChoosableFileFilter(new XMLFilter());
        fc.setFileFilter(new XMLFilter()); //Mejor que se elija directamente los *.xml
        int rVal;
        String s = "evaluate.xml";                            
        if (file != null)
            s = file.getName();
        fc.setSelectedFile(new File(s));
        rVal = fc.showSaveDialog(this);
        
        if (rVal == JFileChooser.APPROVE_OPTION) {
            if (fc.getSelectedFile().getName().endsWith(".xml")) 
                f = new File(fc.getCurrentDirectory() + File.separator +
                        fc.getSelectedFile().getName());
            else
                f = new File(fc.getCurrentDirectory() + File.separator +
                        fc.getSelectedFile().getName() + ".xml");
        }

        if (f != null)
            workingFolder = fc.getSelectedFile().isDirectory() ? f.toString() : f.getParentFile().toString();
        return f;
    }
    
    
    private int recognitionTrainingEmployability(){        
        int levelRecognitionTrainingEmployability;        
        
        if (qualification1_10_1.contains("completion") && isWithin(tutoringSystem5_13_1, "post-course") && validity1_10_3.contains("international"))
            levelRecognitionTrainingEmployability = 5;            
        else if (qualification1_10_1.contains("completion") && isWithin(tutoringSystem5_13_1, "post-course") && validity1_10_3.contains("recognized"))
            levelRecognitionTrainingEmployability = 4;            
        else if (qualification1_10_1.contains("completion"))
            levelRecognitionTrainingEmployability = 3;
        else if (qualification1_10_1.contains("attendance"))
            levelRecognitionTrainingEmployability = 2;
        else 
            levelRecognitionTrainingEmployability = 1;
        
        return levelRecognitionTrainingEmployability;
    }       
    
    private int metodology(){
        int levelDidacticInstructionalDesign;
        int levelTrainingResourcesLearningActivities;
        int levelTutoring;
        int levelTechnologicalDigitalEnvironment;      
        
        int nCincoEstrellas = 0;
        int nCuatroEstrellas = 0;
        int nTresEstrellas = 0;
        int nDosEstrellas = 0;
        int nUnaEstrella = 0;
        int nCeroEstrellas = 0;
        
        this.jTextFieldMetodology_Tutoring.setEnabled(true);
        
        //Didactic Instructional Design
        if (!description1_4.isEmpty() && isPurposeAndTaxonPath("educational objective") && isFilled(learningMethod5_14) && isWithin(features5_12_1, "final evaluation")
            && isWithin(learningResourceType5_2, "experiment") && (aggregationLevel1_8.contains("2") || aggregationLevel1_8.contains("3") 
            || aggregationLevel1_8.contains("4")) && (structure1_7.contains("collection") || structure1_7.contains("networked") 
            || structure1_7.contains("hierarchical")) && isWithin(features5_12_1, "initial evaluation") && isPurposeAndTaxonPath("competency")
            && (isWithin(learningMethod5_14, "project-based learning") || isWithin(learningMethod5_14, "case-based learning") || isWithin(learningMethod5_14, "problem-based learning"))
            && isWithin(tutoringSystem5_13_1, "post-course")){
                levelDidacticInstructionalDesign = 5;
                nCincoEstrellas++;
        }
        else if (!description1_4.isEmpty() && isPurposeAndTaxonPath("educational objective") && isFilled(learningMethod5_14) && isWithin(features5_12_1, "final evaluation")
                 && isWithin(learningResourceType5_2, "experiment") && (aggregationLevel1_8.contains("2") || aggregationLevel1_8.contains("3") 
                 || aggregationLevel1_8.contains("4")) && (structure1_7.contains("collection") || structure1_7.contains("networked") 
                 || structure1_7.contains("hierarchical")) && isWithin(features5_12_1, "initial evaluation")){
                    levelDidacticInstructionalDesign = 4;
                    nCuatroEstrellas++;                    
        }
        else if (!description1_4.isEmpty() && isPurposeAndTaxonPath("educational objective") && isFilled(learningMethod5_14) && isWithin(features5_12_1, "final evaluation")){
                    levelDidacticInstructionalDesign = 3;
                    nTresEstrellas++;
        }            
        else if (!description1_4.isEmpty() && isPurposeAndTaxonPath("educational objective")){
                    levelDidacticInstructionalDesign = 2;
                    nDosEstrellas++;
        }
        else if (!description1_4.isEmpty()){
                    levelDidacticInstructionalDesign = 1;
                    nUnaEstrella++;
        }
        else{ 
                    levelDidacticInstructionalDesign = 0;
                    nCeroEstrellas++;
        }
        
        //Training Resources And Learning Activities 
        if ((isWithin(interactivityType5_1, "expositive") || isWithin(interactivityType5_1, "active") || isWithin(interactivityType5_1, "mixed")) && (isWithin(interactivityLevel5_3,"medium") || isWithin(interactivityLevel5_3,"high") || isWithin(interactivityLevel5_3,"very high"))
            && isWithin(features5_12_1, "self-assessment") && isFilled(description5_10) && (isWithin(learningMethod5_14, "project-based learning") || isWithin(learningMethod5_14, "case-based learning") || isWithin(learningMethod5_14, "problem-based learning"))
            && isWithin(features5_12_1, "teaching guide") && isFilledOneOrMore(learningResourceType5_2) && (isWithin(difficulty5_8,"difficult") || isWithin(difficulty5_8,"very difficult"))
            && isWithin(tutoringSystem5_13_1,"synchronous sessions") && isWithin(communication5_12_2,"repository")){
                levelTrainingResourcesLearningActivities = 5;
                nCincoEstrellas++;
        }
        else if ((isWithin(interactivityType5_1, "expositive") || isWithin(interactivityType5_1, "active") || isWithin(interactivityType5_1, "mixed")) && (isWithin(interactivityLevel5_3,"medium") || isWithin(interactivityLevel5_3,"high") || isWithin(interactivityLevel5_3,"very high"))
                 && isWithin(features5_12_1, "self-assessment") && isFilled(description5_10) && (isWithin(learningMethod5_14, "project-based learning") || isWithin(learningMethod5_14, "case-based learning") || isWithin(learningMethod5_14, "problem-based learning"))
                 && isWithin(features5_12_1, "teaching guide") && isFilledOneOrMore(learningResourceType5_2) && (isWithin(difficulty5_8,"difficult") || isWithin(difficulty5_8,"very difficult"))){
                    levelTrainingResourcesLearningActivities = 4;
                    nCuatroEstrellas++;
        }
        else if ((isWithin(interactivityType5_1, "expositive") || isWithin(interactivityType5_1, "active") || isWithin(interactivityType5_1, "mixed")) && (isWithin(interactivityLevel5_3,"medium") || isWithin(interactivityLevel5_3,"high") || isWithin(interactivityLevel5_3,"very high"))
                 && isWithin(features5_12_1, "self-assessment") && isFilled(description5_10) && (isWithin(learningMethod5_14, "project-based learning") || isWithin(learningMethod5_14, "case-based learning") || isWithin(learningMethod5_14, "problem-based learning"))
                 && isWithin(features5_12_1, "teaching guide")){
                    levelTrainingResourcesLearningActivities = 3;
                    nTresEstrellas++;
        }
        else if ((isWithin(interactivityType5_1, "expositive") || isWithin(interactivityType5_1, "active") || isWithin(interactivityType5_1, "mixed")) && (isWithin(interactivityLevel5_3,"medium") || isWithin(interactivityLevel5_3,"high") || isWithin(interactivityLevel5_3,"very high"))
                 && isWithin(features5_12_1, "self-assessment") && isFilled(description5_10)){
                    levelTrainingResourcesLearningActivities = 2;
                    nDosEstrellas++;
        }
        else{
                    levelTrainingResourcesLearningActivities = 1;
                    nUnaEstrella++;
        }
        
        
        //Tutoring
        if (!isOnly(trainingType5_15, "self-training")){
            if (isWithin(tutoringSystem5_13_1, "consultation") && isFilled(responseTime5_13_2) && isWithin(tutoringSystem5_13_1, "progress monitoring") 
                && isWithin(tutoringSystem5_13_1, "learning monitoring") && isWithin(tutoringSystem5_13_1, "personalized") && isWithin(tutoringSystem5_13_1, "synchronous sessions")){
                    levelTutoring = 5;
                    nCincoEstrellas++;
            }
            else if (isWithin(tutoringSystem5_13_1, "consultation") && isFilled(responseTime5_13_2) && isWithin(tutoringSystem5_13_1, "progress monitoring") 
                     && isWithin(tutoringSystem5_13_1, "learning monitoring")){
                        levelTutoring = 4;
                        nCuatroEstrellas++;
            }
            else if (isWithin(tutoringSystem5_13_1, "consultation") && isFilled(responseTime5_13_2) && isWithin(tutoringSystem5_13_1, "progress monitoring")){
                        levelTutoring = 3;
                        nTresEstrellas++;
            }
            else if (isWithin(tutoringSystem5_13_1, "consultation")){
                        levelTutoring = 2;
                        nDosEstrellas++;
            }
            else{
                        levelTutoring = 1;
                        nUnaEstrella++;
            }
        }
        else{
                levelTutoring = -1;
                this.jTextFieldMetodology_Tutoring.setText("NR");
                this.jTextFieldMetodology_Tutoring.setEnabled(false);                
        }
        
        
        //Technological and Digital Environment
        if ((!requirement4_4.isEmpty() || !otherPlataforms4_6.isEmpty()) && isFilled(communication5_12_2) && isWithin(features5_12_1, "integrated")
            && (isWithin(features5_12_1, "FAQ") || isWithin(features5_12_1, "help")) && (isWithin(features5_12_1, "search engine") || (structure1_7.contains("networked") 
            || structure1_7.contains("hierarchical") || structure1_7.contains("linear")) || generalUsability4_9_1.contains("4.7.4") || generalUsability4_9_1.contains("4.4.5"))
            && isWithin(features5_12_1, "reports") && isWithin(features5_12_1, "persistency") && isWithin(communication5_12_2, "forum") && isWithin(features5_12_1, "progress")
            && isWithin(features5_12_1, "reuse") && isWithin(features5_12_1, "customizable") && isWithin(communication5_12_2, "social")){
                levelTechnologicalDigitalEnvironment = 5;
                nCincoEstrellas++;
        }
        else if ((!requirement4_4.isEmpty() || !otherPlataforms4_6.isEmpty()) && isFilled(communication5_12_2) && isWithin(features5_12_1, "integrated")
                 && (isWithin(features5_12_1, "FAQ") || isWithin(features5_12_1, "help")) && (isWithin(features5_12_1, "search engine") || (structure1_7.contains("networked") 
                 || structure1_7.contains("hierarchical") || structure1_7.contains("linear")) || generalUsability4_9_1.contains("4.7.4") || generalUsability4_9_1.contains("4.4.5"))
                 && isWithin(features5_12_1, "reports") && isWithin(features5_12_1, "persistency") && isWithin(communication5_12_2, "forum")){
                    levelTechnologicalDigitalEnvironment = 4;
                    nCuatroEstrellas++;
        }
        else if ((!requirement4_4.isEmpty() || !otherPlataforms4_6.isEmpty()) && isFilled(communication5_12_2) && isWithin(features5_12_1, "integrated")
                 && (isWithin(features5_12_1, "FAQ") || isWithin(features5_12_1, "help")) && (isWithin(features5_12_1, "search engine") || (structure1_7.contains("networked") 
            || structure1_7.contains("hierarchical") || structure1_7.contains("linear")) || generalUsability4_9_1.contains("4.7.4") || generalUsability4_9_1.contains("4.4.5"))){
                    levelTechnologicalDigitalEnvironment = 3;
                    nTresEstrellas++;
        }
        else if ((!requirement4_4.isEmpty() || !otherPlataforms4_6.isEmpty()) && isFilled(communication5_12_2)){
                    levelTechnologicalDigitalEnvironment = 2;
                    nDosEstrellas++;
        }
        else if (!requirement4_4.isEmpty() || !otherPlataforms4_6.isEmpty()){
                    levelTechnologicalDigitalEnvironment = 1;
                    nUnaEstrella++;
        }
        else{ 
                levelTechnologicalDigitalEnvironment = 0;       
                nCeroEstrellas++;
        }
        
        this.jTextFieldMetodology_Design.setText(Integer.toString(levelDidacticInstructionalDesign));
        this.jTextFieldMetodology_Resources.setText(Integer.toString(levelTrainingResourcesLearningActivities));        
        this.jTextFieldMetodology_Environment.setText(Integer.toString(levelTechnologicalDigitalEnvironment));
        
        if (levelTutoring != -1)
            this.jTextFieldMetodology_Tutoring.setText(Integer.toString(levelTutoring));
        
        
        return levelCalculate(nCincoEstrellas, nCuatroEstrellas, nTresEstrellas, nDosEstrellas, nUnaEstrella, nCeroEstrellas);
    }
    
    private int accessibility(TreeSet<String> software){
        int levelHardwareAccessibility; 
        int levelSoftwareAccessibility;
        int levelWebAccessibility;
        
        int nCincoEstrellas = 0;
        int nCuatroEstrellas = 0;
        int nTresEstrellas = 0;
        int nDosEstrellas = 0;
        int nUnaEstrella = 0;
        int nCeroEstrellas = 0;
        
        String[] groupA = {"8.1.1", "8.1.4", "8.2.4", "8.2.7", "8.3.1", "8.3.3", "8.4.4", "8.4.5", "8.4.9", "8.5.2", "8.5.3", "8.5.4", "8.5.5", "8.5.6", "8.5.7", "8.5.9", "8.5.10", "8.5.11", 
                           "8.5.12", "8.6.1", "8.6.2", "8.6.3", "8.6.4", "9.1.2", "9.2.1", "9.2.2", "9.3.2", "9.3.3", "9.3.4", "9.3.5", "9.3.8", "9.3.12", "9.3.14", "9.4.2", "9.4.4", "9.4.6", 
                           "9.4.9", "9.4.10", "9.4.11", "9.4.13", "9.4.14", "10.1.1", "10.1.2", "10.1.3", "10.2.4", "10.4.1", "10.5.3", "10.5.4", "10.5.5", "10.5.7", "10.5.10", "10.6.2", "10.6.7", 
                           "10.6.8", "10.6.9", "10.7.1", "10.7.3", "10.8.1", "10.8.4", "11.1.2", "11.1.3", "11.1.5", "11.2.1"};
        
        String[] groupB = {"8.1.2", "8.1.5", "8.1.6", "8.2.1", "8.2.2", "8.3.2", "8.3.4", "8.3.5", "8.3.6", "8.4.3", "8.4.6", "8.4.8", "8.4.10", "8.4.11", "8.4.12", "8.5.8", "9.1.1", "9.1.3", "9.1.4",
                           "9.2.3", "9.3.6", "9.3.7", "9.3.9", "9.3.11", "9.3.15", "9.3.16", "9.4.3", "9.4.5", "9.4.7", "9.4.8", "10.3.1", "10.3.3", "10.4.3", "10.4.4", "10.4.5", "10.5.1", "10.5.2", 
                           "10.5.8", "10.5.9", "10.6.1", "10.7.2", "10.7.4", "10.8.2", "10.8.3", "10.9.1", "10.9.2", "10.9.3", "11.1.1", "11.1.4", "11.2.2"};
        
        TreeSet<String> a = new TreeSet<>(Arrays.asList(groupA));
        TreeSet<String> b = new TreeSet<>(Arrays.asList(groupB));
        TreeSet<String> aUb = union(a,b);
                
        //Hardware Accessibility
        if (!adaptation4_8_1.isEmpty() && help4_8_2.contains("hardware") && hardware4_8_3.equals("2")){
            levelHardwareAccessibility = 5;
            nCincoEstrellas++;
        }
        else if (!adaptation4_8_1.isEmpty() && help4_8_2.contains("hardware") && hardware4_8_3.equals("1")){
                    levelHardwareAccessibility = 4;
                    nCuatroEstrellas++;
        }
        else if (!adaptation4_8_1.isEmpty() && help4_8_2.contains("hardware")){
                    levelHardwareAccessibility = 3;
                    nTresEstrellas++;
        }
        else if (!adaptation4_8_1.isEmpty()){
                    levelHardwareAccessibility = 2;
                    nDosEstrellas++;
        }
        else{
                    levelHardwareAccessibility = 1;
                    nUnaEstrella++;
        }
        
        //Software Accessibility
        if (!adaptation4_8_1.isEmpty() && help4_8_2.contains("environment") && isIntersection(software, aUb)){
            levelSoftwareAccessibility = 5;
            nCincoEstrellas++;
        }
        else if (!adaptation4_8_1.isEmpty() && help4_8_2.contains("environment") && isIntersection(software, a)){
                    levelSoftwareAccessibility = 4;
                    nCuatroEstrellas++;
        }
        else if (!adaptation4_8_1.isEmpty() && help4_8_2.contains("environment")){
                    levelSoftwareAccessibility = 3;
                    nTresEstrellas++;
        }
        else if (!adaptation4_8_1.isEmpty()){
                    levelSoftwareAccessibility = 2;
                    nDosEstrellas++;
        }
        else{
                    levelSoftwareAccessibility = 1;
                    nUnaEstrella++;
        }
        
        //Web Accessibility
        if (!adaptation4_8_1.isEmpty() && help4_8_2.contains("environment") && web4_8_5.equals("AA")){
            levelWebAccessibility = 5;
            nCincoEstrellas++;
        }
        else if (!adaptation4_8_1.isEmpty() && help4_8_2.contains("environment") && web4_8_5.equals("A")){
                    levelWebAccessibility = 4;
                    nCuatroEstrellas++;
        }
        else if (!adaptation4_8_1.isEmpty() && help4_8_2.contains("environment")){
                    levelWebAccessibility = 3;
                    nTresEstrellas++;
        }
        else if (!adaptation4_8_1.isEmpty()){
                    levelWebAccessibility = 2;
                    nDosEstrellas++;
        }
        else{                    
                    levelWebAccessibility = 1;
                    nUnaEstrella++;
        }
        
        
        this.jTextFieldHardwareAccessibility.setText(Integer.toString(levelHardwareAccessibility));
        this.jTextFieldSoftwareAccessibility.setText(Integer.toString(levelSoftwareAccessibility));
        this.jTextFieldWebAccessibility.setText(Integer.toString(levelWebAccessibility));
                
        return levelCalculate(nCincoEstrellas, nCuatroEstrellas, nTresEstrellas, nDosEstrellas, nUnaEstrella, nCeroEstrellas);        
    }
    
    private int levelCalculate (int nCincoEstrellas, int nCuatroEstrellas, int nTresEstrellas, int nDosEstrellas, int nUnaEstrella, int nCeroEstrellas){
        
        if (nCincoEstrellas >= 2 && nDosEstrellas == 0 && nUnaEstrella == 0 && nCeroEstrellas == 0)
            return 5;
        else if ((nCincoEstrellas + nCuatroEstrellas) >= 2 && nDosEstrellas == 0 && nUnaEstrella == 0 && nCeroEstrellas == 0)
            return 4;
        else if ((nCincoEstrellas + nCuatroEstrellas + nTresEstrellas) >= 3 && nDosEstrellas == 0 && nUnaEstrella == 0 && nCeroEstrellas == 0)
            return 3;
        else if (nDosEstrellas > 0 && nUnaEstrella == 0 && nCeroEstrellas == 0)
            return 2;
        else if (nUnaEstrella > 0 && nCeroEstrellas == 0)
            return 1;
        else
            return 0;
        
    }
    
    private TreeSet intersection(TreeSet a, TreeSet b) {
        TreeSet res = new TreeSet(a);
        res.retainAll(b);
        return res;
    }
    
    private TreeSet union(TreeSet a, TreeSet b) {
        TreeSet res = new TreeSet(a);
        res.addAll(b);        
        return res;
    }
    
    private boolean isIntersection(TreeSet a, TreeSet b) {
        TreeSet res = new TreeSet(b);
        return !res.retainAll(a);        
    }
    
    private boolean isPurposeAndTaxonPath(String text){        
        for (int i = 0; i<purpose9_1.length && i<taxonPath9_2.length; i++)
            if((purpose9_1[i].contains(text)) && (!taxonPath9_2[i].isEmpty()))
                return true;
        
        return false;        
    }
    
    
    private String fillPurposeAndTaxonPath(){
        String key = "educational objective";
        String text = "";
        
        for (int i = 0; i<purpose9_1.length && i<taxonPath9_2.length; i++)
            if((purpose9_1[i].contains(key)) && (!taxonPath9_2[i].isEmpty()))
                text += taxonPath9_2[i] + "\n";
        
        return text;             
    }
    
    
    private void fillFields (String[] field, javax.swing.JTextField textField){         
        for (int i=0; i<field.length ;i++)
            textField.setText(textField.getText() + field[i]);            
    }
    
    private void fillFields (String[] field, javax.swing.JTextArea textArea){        
        for (int i=0; i<field.length ;i++)
            textArea.setText(textArea.getText() + field[i]);                  
    }
    
    private void fillPreviousTraining (){
        String text = "";
        
        for (int i=0; i < intendedEndUsertRole5_5.length || i < context5_6.length || i < description5_10.length; i++){
            if (intendedEndUsertRole5_5.length > i && !intendedEndUsertRole5_5[i].isEmpty())
                text = text + "Intended End User Role: " + intendedEndUsertRole5_5[i].replace("\n", ",").substring(0, intendedEndUsertRole5_5[i].length() - 1) + "; ";
            if (context5_6.length > i && !context5_6[i].isEmpty())
                text = text + "Context: " + context5_6[i].replace("\n", ",").substring(0, context5_6[i].length() - 1) + "; ";
            if (description5_10.length > i && !description5_10[i].isEmpty())
                text = text + "Description: " + description5_10[i].replace("\n", ",").substring(0, description5_10[i].length() - 1);
            
            text = text + "\n";             
        }        
        jTextAreaPreviousTraining.setText(text);
        jTextAreaPreviousTraining.setCaretPosition(0);
    }
    
    
    
    private boolean isOnly(String[] field, String text){
        boolean res = true;
        
        for (String element: field) //bucle for - each
            if(element.contains(text) || element.isEmpty())
                res = true;
            else{        
                res = false;
                break;
            }
        
       return res;
    }
    
    private boolean isWithin(String[] field, String text){        
        for (String element: field) //bucle for - each
            if(element.contains(text))
                return true;
        
        return false;      
    }
    
    private boolean isFilled(String[] field){         
        for(String element: field)
            if ((element != null) && (!element.trim().equals("")))
                return true;
            
        return false;             
    }
    
    private boolean isFilledOneOrMore(String[] field){ 
        String[] aux = null;
        int count = 0;
        
        for(String element: field)
            if ((element != null) && (!element.trim().equals(""))){
                aux = element.split("\n");
                for(String subElement : aux)
                    if ((subElement != null) && (!subElement.trim().equals("")))
                        count ++;
                
                if (count > 1) return true;                
            }       
            
        return false;             
    }
    
    //XML
    
    private String toXMLEval(){
               
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
        xml += "<lom xmlns=\"http://www.uah.es/LQM\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xsi:schemaLocation=\"http://www.uah.es/LQM LQM_XSD/lqm.xsd\">\n";
        
        xml += "<une66181_2012>\n";
        xml += "<resource>\n";
        
        xml += fillXML("name", jTextFieldName.getText());
        xml += fillXMLObjectives("objective", jTextAreaObjectives.getText());        
        xml += fillXML("training_type", jTextAreaTrainingType.getText());
        xml += fillXML("demand", jTextFieldDemand.getText());
        xml += fillXML("previous_training", jTextAreaPreviousTraining.getText());        
        xml += fillXML("dedication", jTextFieldDedication.getText());
        xml += fillXML("hardware_requirements", jTextAreaHardwareRequirements.getText());
        xml += fillXML("software_requirements", jTextAreaSoftwareRequirements.getText());
        xml += "<employability>" + jTextFieldRecognitionTrainingEmployability.getText().trim() + "</employability>\n";
        xml += "<methodology>" + jTextFieldMetodology.getText().trim() + "</methodology>\n";
        xml += "<design>" + jTextFieldMetodology_Design.getText().trim() + "</design>\n";
        xml += "<resources>" + jTextFieldMetodology_Resources.getText().trim() + "</resources>\n";
        xml += "<tutoring>" + jTextFieldMetodology_Tutoring.getText().trim() + "</tutoring>\n";
        xml += "<environment>" + jTextFieldMetodology_Environment.getText().trim() + "</environment>\n";       
        xml += "<hardware_accessibility>" + jTextFieldHardwareAccessibility.getText().trim() + "</hardware_accessibility>\n";
        xml += "<software_accessibility>" + jTextFieldSoftwareAccessibility.getText().trim() + "</software_accessibility>\n";
        xml += "<web_accessibility>" + jTextFieldWebAccessibility.getText().trim() + "</web_accessibility>\n";
        
        xml += "</resource>\n";
        xml += "</une66181_2012>\n";        
        
        xml += "</lom>\n";
        return xml;
    }

    private String fillXML(String label, String field){
        String xml = "";
        String[] aux = field.split("\n");              
        
        for (String element: aux)
            if ((element != null) && (!element.trim().equals("")))
                xml += "<" + label + ">" + element + "</" + label + ">\n";
        
        return xml;        
    }
    
    /*NO SE UTILIZA
     * 
     * private String fillXML(String label, String[] field){
        String xml = "";
        String[] aux=null;        
        
        for (String instance: field){            
            aux = instance.split("\n");
            for (String element: aux)
                if ((element != null) && (!element.trim().equals("")))
                    xml += "<" + label + ">" + element + "</" + label + ">\n";
        }
        return xml;        
    }*/
    
    private String fillXMLObjectives(String label, String field){
        String xml = "<objectives>" + "\n";        
        String[] aux = null;        
        aux = field.split("\n");   
        
        for (String element : aux)
            if ((element != null) && (!element.trim().equals("")))
                xml += "<" + label + ">" + element + "</" + label + ">\n";
        
        if (xml.length() == 13)
            return xml = "";
        
        return xml + "</objectives>" + "\n";        
    }
    
    
    
    
   
   
    
    /**
     * @param args the command line arguments
     */
  
  /*Ya tengo una clase main  
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       /* try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EvaluateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EvaluateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EvaluateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EvaluateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EvaluateFrame().setVisible(true);
            }
        });
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAccessibility;
    private javax.swing.JLabel jLabelDedication;
    private javax.swing.JLabel jLabelDemand;
    private javax.swing.JLabel jLabelHardwareAccessibility;
    private javax.swing.JLabel jLabelHardwareRequirements;
    private javax.swing.JLabel jLabelIconWarning;
    private javax.swing.JLabel jLabelMetodology;
    private javax.swing.JLabel jLabelMetodology_Design;
    private javax.swing.JLabel jLabelMetodology_Environment;
    private javax.swing.JLabel jLabelMetodology_Resources;
    private javax.swing.JLabel jLabelMetodology_Tutoring;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelObjectives;
    private javax.swing.JLabel jLabelPreviousTraining;
    private javax.swing.JLabel jLabelRecognitionTrainingEmployability;
    private javax.swing.JLabel jLabelSoftwareAccessibility;
    private javax.swing.JLabel jLabelSoftwareRequirements;
    private javax.swing.JLabel jLabelSubtittle;
    private javax.swing.JLabel jLabelTitleWindow;
    private javax.swing.JLabel jLabelTrainingType;
    private javax.swing.JLabel jLabelWarning;
    private javax.swing.JLabel jLabelWebAccessibility;
    private javax.swing.JMenuBar jMenuEvaluate;
    private javax.swing.JMenu jMenuEvaluateFile;
    private javax.swing.JMenuItem jMenuEvaluateFileQuit;
    private javax.swing.JMenuItem jMenuEvaluateFileSave;
    private javax.swing.JMenuItem jMenuEvaluateFileViewXML;
    private javax.swing.JMenu jMenuEvaluateHelp;
    private javax.swing.JMenuItem jMenuEvaluateHelpAbout;
    private javax.swing.JMenuItem jMenuEvaluateHelpUNE;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAccessibility;
    private javax.swing.JPanel jPanelLevels;
    private javax.swing.JPanel jPanelMetodology;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneHardwareRequirements;
    private javax.swing.JScrollPane jScrollPaneObjectives;
    private javax.swing.JScrollPane jScrollPanePreviousTraining;
    private javax.swing.JScrollPane jScrollPaneSoftwareRequirements;
    private javax.swing.JScrollPane jScrollPaneTrainingType;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextArea jTextAreaHardwareRequirements;
    private javax.swing.JTextArea jTextAreaObjectives;
    private javax.swing.JTextArea jTextAreaPreviousTraining;
    private javax.swing.JTextArea jTextAreaSoftwareRequirements;
    private javax.swing.JTextArea jTextAreaTrainingType;
    private javax.swing.JTextField jTextFieldAccessibility;
    private javax.swing.JTextField jTextFieldDedication;
    private javax.swing.JTextField jTextFieldDemand;
    private javax.swing.JTextField jTextFieldHardwareAccessibility;
    private javax.swing.JTextField jTextFieldMetodology;
    private javax.swing.JTextField jTextFieldMetodology_Design;
    private javax.swing.JTextField jTextFieldMetodology_Environment;
    private javax.swing.JTextField jTextFieldMetodology_Resources;
    private javax.swing.JTextField jTextFieldMetodology_Tutoring;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldRecognitionTrainingEmployability;
    private javax.swing.JTextField jTextFieldSoftwareAccessibility;
    private javax.swing.JTextField jTextFieldWebAccessibility;
    // End of variables declaration//GEN-END:variables
}

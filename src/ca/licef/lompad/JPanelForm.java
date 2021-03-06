/*
 * Copyright (C) 2005  Alexis Miara (alexis.miara@licef.ca)
 *
 * This file is part of LomPad.
 *
 * LomPad is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * LomPad is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LomPad; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package ca.licef.lompad;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.*;

class JPanelForm extends JPanel {

    static JPanelForm instance;

    JPanel jPanelProfile;
    JPanel jPanelNormeticProfileIcons;
    JPanel jPanelNormeticLegend;
    JLabel jLabelRequired;
    JLabel jLabelRecommended;
    JLabel jLabelOptional;
    JLabel jLabelProfileName;
    JLabel jLabelProfileIcon;
    JLabel jLabelProfile;
    JLabel jLabelLegend;

    JSplitPane splitPane;
    FileBrowser browser;
    JPanel jPanelWrapper;

    boolean bInit;
    LomForm lomForm;

    String currentSelectedProfile = "LQM";
    boolean isNormeticProfile = false;
    boolean isLQMprofile = false;
    
    Icon normeticIcon;
    Hashtable normeticLabels;

    File file = null;
    String workingFolder = FileSystemView.getFileSystemView().getDefaultDirectory().toString();

    public JPanelForm() {

        instance = this;

        setOpaque(false);
        setLayout(new BorderLayout(0, 0));
        lomForm = new LomForm();

        jPanelProfile = new JPanel();
        jPanelProfile.setLayout(new BoxLayout(jPanelProfile, BoxLayout.X_AXIS));
        add(BorderLayout.NORTH, jPanelProfile);
        jPanelProfile.add(Box.createHorizontalStrut(5));
        jLabelProfile = new JLabel("profile");
        jLabelProfile.setFont(new Font("Dialog", Font.PLAIN, 12));
        jPanelProfile.add(jLabelProfile);
        JLabel jLabelColon = new JLabel(" : ");
        jLabelColon.setFont(jLabelProfile.getFont());
        jPanelProfile.add(jLabelColon);
        jLabelProfileName = new JLabel();
        jLabelProfileName.setPreferredSize(new Dimension(300, 28));
        jLabelProfileName.setFont(jLabelProfile.getFont());
        jPanelProfile.add(jLabelProfileName);
        jPanelProfile.add(Box.createHorizontalGlue());
        jLabelProfileIcon = new JLabel();
        jLabelProfileIcon.setHorizontalAlignment(JLabel.RIGHT);
        jLabelProfileIcon.setPreferredSize(new Dimension(40,28));
        jLabelProfileIcon.setVisible(false);
        jPanelProfile.add(jLabelProfileIcon);
        jPanelProfile.add(Box.createHorizontalStrut(5));

        add(BorderLayout.CENTER, lomForm);

        jPanelNormeticLegend = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        add(BorderLayout.SOUTH, jPanelNormeticLegend);
        jLabelLegend = new JLabel("legend");
        jLabelLegend.setFont(jPanelProfile.getFont());
        jPanelNormeticLegend.add(jLabelLegend);
        jLabelRequired = new JLabel("required", Util.redIcon, SwingConstants.LEADING);
        jLabelRequired.setFont(jLabelProfile.getFont());
        jPanelNormeticLegend.add(jLabelRequired);
        jLabelRecommended = new JLabel("recommended", Util.yellowIcon, SwingConstants.LEADING);
        jLabelRecommended.setFont(jLabelProfile.getFont());
        jPanelNormeticLegend.add(jLabelRecommended);
        jLabelOptional = new JLabel("optional", Util.greenIcon, SwingConstants.LEADING);
        jLabelOptional.setFont(jLabelProfile.getFont());
        jPanelNormeticLegend.add(jLabelOptional);

        updateProfile("LQM");
        normeticIcon = Util.normeticDisabledIcon;
        normeticLabels = new Hashtable();
        ResourceBundle rb = ResourceBundle.getBundle("properties.JFrameFormRes", new Locale(""));
        normeticLabels.put("Normetic (" + rb.getString("normetic1") + ")", "normetic1");
        normeticLabels.put("Normetic (" + rb.getString("normetic2") + ")", "normetic2");
        normeticLabels.put("Normetic (" + rb.getString("normetic3") + ")", "normetic3");
        rb = ResourceBundle.getBundle("properties.JFrameFormRes", Locale.FRENCH);
        normeticLabels.put("Normetic (" + rb.getString("normetic1") + ")", "normetic1");
        normeticLabels.put("Normetic (" + rb.getString("normetic2") + ")", "normetic2");
        normeticLabels.put("Normetic (" + rb.getString("normetic3") + ")", "normetic3");

        browser = new FileBrowser( workingFolder );
        browser.setFont( new Font( "Dialog", Font.PLAIN, 12 ) );  
        browser.setVisible( false );
        browser.addFileBrowserListener( new FileBrowserListener() {

            public void fileSelected( FileBrowserEvent e ) {
                openFileFromBrowser( e.getFile() + "" );
            }

            public void directorySelected( FileBrowserEvent e ) {
                workingFolder = e.getFile() + "";
            } 

            public void browserClosed() {
                hideBrowser();
            }

        } );

        bInit = true;
        lomForm.init();
        bInit = false;

        updateLocalization();
    }

    public void showBrowser() {
        if( splitPane == null ) {
            remove( jPanelProfile );
            remove( lomForm ); 
            remove( jPanelNormeticLegend );
            jPanelWrapper = new JPanel( new BorderLayout( 0, 0 ) );
            jPanelWrapper.add( BorderLayout.NORTH, jPanelProfile );
            jPanelWrapper.add( BorderLayout.CENTER, lomForm );
            jPanelWrapper.add( BorderLayout.SOUTH, jPanelNormeticLegend );
            splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, browser, jPanelWrapper ); 
            add( BorderLayout.CENTER, splitPane );
            validate();
        }
        browser.setDirectory( workingFolder );
        browser.setVisible( true );
    }

    private void hideBrowser() {
        if( splitPane != null ) {
            remove( splitPane );
            browser.setVisible( false );
            splitPane.remove( browser );
            splitPane.remove( jPanelWrapper );
            splitPane = null;
            add( BorderLayout.NORTH, jPanelProfile );
            add( BorderLayout.SOUTH, jPanelNormeticLegend );
            add( BorderLayout.CENTER, lomForm );
            validate();
        }
    }

    public void updateLocalization() {
        ResourceBundle resBundle = ResourceBundle.getBundle("properties.JFrameFormRes", Util.locale);
        jLabelProfile.setText(resBundle.getString("profile"));
        jLabelLegend.setText(resBundle.getString("legend") + " :");
        jLabelRequired.setText(resBundle.getString("required"));
        jLabelRecommended.setText(resBundle.getString("recommended"));
        jLabelOptional.setText(resBundle.getString("optional"));

        if (jLabelProfileName.getText().startsWith("Normetic")) {
            String key = (String)normeticLabels.get(jLabelProfileName.getText());
            jLabelProfileName.setText("Normetic (" + resBundle.getString(key) + ")");
        }
    }

    void doFilter() {
        if (bInit) return;
        if (!currentSelectedProfile.equals("LQM"))
            changeStandard(currentSelectedProfile, false);
    }
    
    //To change the profile to LQM
    void changeToLQM() {
        lomForm.preUpdateVocabularies();

        undo();
        currentSelectedProfile = "LQM";
        
        Util.setLQMVocabulary(getCurrentSelectedProfile());
        
        bInit = true;
        lomForm.updateVocabularies();
        bInit = false;
    }

    void changeToIEEE() {
        lomForm.preUpdateVocabularies();

        undo();
        currentSelectedProfile = "IEEE";
        
        changeStandard(currentSelectedProfile, false);

        bInit = true;
        lomForm.updateVocabularies();
        bInit = false;
    }

    void undo() {
        if (!currentSelectedProfile.equals("LQM"))
            changeStandard(currentSelectedProfile, true);
    }

    void changeStandardActionPerformed(String profile, boolean isVisible) {
        lomForm.preUpdateVocabularies();
        changeStandard(profile, isVisible);
        Util.setExternalVocabulary(getCurrentSelectedProfile());
        bInit = true;
        lomForm.updateVocabularies();
        bInit = false;
    }

    void changeStandard(String profile, boolean isVisible) {          
        if (!isVisible) {
            undo();
            currentSelectedProfile = profile;
        }

        ResourceBundle resBundle = null;
        try {
            resBundle = ResourceBundle.getBundle("properties." + profile);
        } catch (Exception e) {
        }
        StringTokenizer st =
                new StringTokenizer(resBundle.getString("hideComponent"), ",");
        while (st.hasMoreTokens())
            lomForm.setFormVisible(st.nextToken(), isVisible);
    }

    public String getCurrentSelectedProfile() {
        String res = currentSelectedProfile;
        if (res.startsWith("NORMETIC"))
            res = "NORMETIC";
        if (res.startsWith("LQM"))
            res = "LQM";
        return res;
    }

    public boolean isValidNormetic() {
        ResourceBundle resBundle = null;
        try {
            resBundle = ResourceBundle.getBundle("properties.NORMETIC1");
        } catch (Exception e) {
        }
        StringTokenizer st =
                new StringTokenizer(resBundle.getString("mandatoryComponent"), ",");
        while (st.hasMoreTokens()) {
            boolean res = lomForm.isComplete(st.nextToken());
            if (!res) return false;
        }
        return true;
    }

    boolean manageCurrentLom() {
        if (lomForm.hasChanged()) {
            JDialogQuestion dialog = new JDialogQuestion(Util.getTopJFrame(this), "title", "text1");
            dialog.setVisible( true );
            int res = dialog.res;
            dialog.dispose();
            if (res == JDialogQuestion.CANCEL)
                return false;
            if (res == JDialogQuestion.NO)
                return true;
            else if (res == JDialogQuestion.YES) {
                ResourceBundle resBundle = ResourceBundle.getBundle("properties.JFrameFormRes", Util.locale);
                return saveFile(resBundle.getString("save"));
            }
        }

        return true;
    }

    void updateFrameTitle() {
        String s = "";
        JFrame top = Util.getTopJFrame(this);
        String title = top.getTitle();
        int index = title.indexOf(" -");
        if (index != -1)
            title = title.substring(0, index);
        if (file != null)
            s = " - " + file.getName();

        top.setTitle(title + s);
    }

    void newForm(boolean callFromMenu) {
        if (callFromMenu && !manageCurrentLom())
            return;

        bInit = true;
        lomForm.init();
        bInit = false;
        doFilter();
        lomForm.update();
        lomForm.initiateHasChanged();

        file = null;

        if (callFromMenu) {
            updateFrameTitle();
            updateNormeticIcon();
        }
    }

    public void openFileFromBrowser( String file ) {
        if (!manageCurrentLom())
            return;

        newForm(false);

        try {
            lomForm.fromXML(new FileInputStream(file));
        } catch (Exception e) {
            JDialogAlert dialog = new JDialogAlert(Util.getTopJFrame(this), "titleErr", "text1");
            dialog.setVisible( true );
        }

        this.file = new File( file );

        lomForm.initiateHasChanged();
        updateFrameTitle();
        updateNormeticIcon();
    }

    public void openFile(String label) {
        if (!manageCurrentLom())
            return;

        File fileTmp = selectFile(true, label);
        if (fileTmp == null)
            return;

        newForm(false);

        if( fileTmp.isDirectory() ) {
            showBrowser();
            return;
        }

        file = fileTmp;

        try {
            lomForm.fromXML(new FileInputStream(file));
        } catch (Exception e) {
            JDialogAlert dialog = new JDialogAlert(Util.getTopJFrame(this), "titleErr", "text1");
            dialog.setVisible( true );
        }
        lomForm.initiateHasChanged();
        updateFrameTitle();
        updateNormeticIcon();
    }

    File selectFile(boolean openMode, String label) {
        File f = null;
        JFileChooser fc = new JFileChooser();
        if( openMode )
            fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
        fc.setCurrentDirectory(new File(workingFolder));
        if (label != null)
            fc.setDialogTitle(" " + label);
        //fc.addChoosableFileFilter(new XMLFilter());
        fc.setFileFilter(new XMLFilter()); //Mejor que se elija directamente los *.xml
        int rVal;
        if (openMode)
            rVal = fc.showOpenDialog(this);
        else {
            String s = "lom.xml";
            if (getCurrentSelectedProfile().equals("LQM"))
                s = "lqm.xml";                
            if (file != null)
                s = file.getName();
            fc.setSelectedFile(new File(s));
            rVal = fc.showSaveDialog(this);
        }
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

    public boolean saveFile(String label) {
        if (file == null)
            file = selectFile(false, label);
        if (file == null)
            return false;
        else {
            writeFile(file);
            lomForm.initiateHasChanged();
            updateFrameTitle();
            updateNormeticIcon();
            return true;
        }
    }

    public void saveAsFile(String label) {
        file = selectFile(false, label);
        if (file != null)
            writeFile(file);
        updateFrameTitle();
        updateNormeticIcon();
    }

    void writeFile(File f) {
        try {
            String lom = lomForm.toXML();
            FileOutputStream fos = new FileOutputStream(f);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            writer.write(lom);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    void viewHTML() {
        popHTML(lomForm.toHTML());
    }

    void viewXML() {
        popXML(lomForm.toXML());
    }
    
    void copyToEvaluateFrom(){        
        
    }

    void popXML(String xml) {
        try {
            popLomFromTempFile( xml, "lom.xml" );
        }
        catch( Exception ignore ) {
            ignore.printStackTrace();
        }
    }

    void popHTML(String html) {
        try {
            popLomFromTempFile( html, "lom.html" );
        }
        catch( Exception ignore ) {
            ignore.printStackTrace();
        }
    }

    void changeLanguage(String lang) {
        try {
            if (lang.equals("fr")) {
                Util.setBundleLocale(Locale.FRENCH);
                lomForm.graphicalUpdate();
            }
            if (lang.equals("en")) {
                Util.setBundleLocale(Locale.ENGLISH);
                lomForm.graphicalUpdate();
            }
            if (lang.equals("sp")) {
                Util.setBundleLocale(new Locale("sp"));
                lomForm.graphicalUpdate();
            }

            lomForm.update();
        } catch (Exception e) {
        }
    }

    void updateProfile(String profile) {
        isNormeticProfile = profile.startsWith("Normetic");
        jPanelNormeticLegend.setVisible(isNormeticProfile);
        
        //LQM assigned like principal profile
        isLQMprofile = profile.startsWith("LQM");
        if (isLQMprofile) Util.setLQMVocabulary(getCurrentSelectedProfile());

        //profile name
        jLabelProfileName.setText(profile);

        //Icon
        if (isNormeticProfile) {
            jLabelProfileIcon.setIcon(normeticIcon);
            jLabelProfileIcon.setVisible(true);
        }
        else if ("CanCore".equals(profile)) {
            jLabelProfileIcon.setIcon(Util.cancoreIcon);
            jLabelProfileIcon.setVisible(true);
        }        
        else {
            jLabelProfileIcon.setIcon(null);
            jLabelProfileIcon.setVisible(false);
        }
        jLabelProfileIcon.updateUI();   //Update the User Interface

    }

    private void updateNormeticIcon() {
        normeticIcon = (isValidNormetic())?Util.normeticIcon:Util.normeticDisabledIcon;
        if (isNormeticProfile)
            jLabelProfileIcon.setIcon(normeticIcon);
    }

    private void popLomFromTempFile( String lom, String tempFile ) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String file = null;
        try {
            String tempDir = System.getProperty("java.io.tmpdir").replace('\\', '/'); //Gets the path of the temporary directory
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
    }
    
    
    //This method returns lomForm, was created for the Evaluation process
    public LomForm getLomForm(){
        return this.lomForm;
    }

}

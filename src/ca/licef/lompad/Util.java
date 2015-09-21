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
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Util {

    public static final String lomNSURI = "http://www.uah.es/LQM";

    //For UI Localization
    public static Locale locale = Locale.ENGLISH;    

    //For Metatagger Localization
    static ResourceBundle resBundleLabel;
    static ResourceBundle resBundleTag;
    static ResourceBundle resBundlePosTag;
    static ResourceBundle resBundleVocabulary;
    static ResourceBundle resBundleXMLVocabulary;
    static ResourceBundle resBundlePosVocabulary;
    static ResourceBundle resBundleExternalVocabularySource;

    static String externalProfile;
    static ResourceBundle resBundleProfileVocabulary;
    static ResourceBundle resBundleProfileXMLVocabulary;
    static ResourceBundle resBundleProfilePosVocabulary;
    static ResourceBundle resBundleProfileVocabularySource;
    
    //For LQM
    static String LQMProfile;    
    static ResourceBundle resBundleLQMTag;
    static ResourceBundle resBundleLQMPosTag;
    static ResourceBundle resBundleLQMVocabulary;    
    static ResourceBundle resBundleLQMXMLVocabulary;
    static ResourceBundle resBundleLQMPosVocabulary;    
    static ResourceBundle resBundleLQMVocabularySource;

    static {
        try {
            resBundleLabel = ResourceBundle.getBundle("properties.LomLabel", locale);
            resBundleTag = ResourceBundle.getBundle("properties.LomTag");
            resBundlePosTag = ResourceBundle.getBundle("properties.LomPosTag");
            resBundleVocabulary = ResourceBundle.getBundle("properties.LomVocabulary", locale);            
            resBundleXMLVocabulary = ResourceBundle.getBundle("properties.LomVocabulary", new Locale(""));
            resBundlePosVocabulary = ResourceBundle.getBundle("properties.LomPosVocabulary");
            resBundleExternalVocabularySource = ResourceBundle.getBundle("properties.ExternalVocabularySource");
        } catch (Exception e) {
        }
    }

    
    //Create a new method for the LQM profile
    static void setLQMVocabulary(String profile) {
        try {            
            LQMProfile = profile;
            resBundleLQMTag = ResourceBundle.getBundle("properties.LQMTag");
            resBundleLQMPosTag = ResourceBundle.getBundle("properties.LQMPosTag");
            resBundleLQMVocabulary = ResourceBundle.getBundle("properties." + profile + "Vocabulary", locale);            
            resBundleLQMXMLVocabulary = ResourceBundle.getBundle("properties." + profile + "Vocabulary", new Locale(""));            
            resBundleLQMPosVocabulary = ResourceBundle.getBundle("properties." + profile + "PosVocabulary");            
            resBundleLQMVocabularySource = ResourceBundle.getBundle("properties." + profile + "VocabularySource");
        } catch (Exception e) { //no externeal vocabularies for this profile
            LQMProfile = null;
            resBundleLQMTag = null;
            resBundleLQMPosTag = null;
            resBundleLQMVocabulary = null;            
            resBundleLQMXMLVocabulary = null;            
            resBundleLQMPosVocabulary = null;            
            resBundleLQMVocabularySource = null;
        }
    }    
    
    
    static void setExternalVocabulary(String profile) {
        try {
            externalProfile = profile;
            resBundleProfileVocabulary = ResourceBundle.getBundle("properties." + profile + "Vocabulary", locale);
            resBundleProfileXMLVocabulary = ResourceBundle.getBundle("properties." + profile + "Vocabulary", new Locale(""));
            resBundleProfilePosVocabulary = ResourceBundle.getBundle("properties." + profile + "PosVocabulary");
            resBundleProfileVocabularySource = ResourceBundle.getBundle("properties." + profile + "VocabularySource");
        } catch (Exception e) { //no externeal vocabularies for this profile
            externalProfile = null;
            resBundleProfileVocabulary = null;
            resBundleProfileXMLVocabulary = null;
            resBundleProfilePosVocabulary = null;
            resBundleProfileVocabularySource = null;
        }
    }

    static String getBundleValue(ResourceBundle resBundle, String key) {
        String res = null;
        try {
            res = resBundle.getString(key);
        } catch (Exception e) {
        }
        return res;
    }

    static int getPosValue(ResourceBundle resBundle, String key) throws IllegalTagException {
        int pos = -1;        
        String s = getBundleValue(resBundle, key);
        if (s != null)
            pos = Integer.parseInt(s);
        else
            throw new IllegalTagException();
        return pos;
    }

    static void setBundleLocale(Locale locale) {
        resBundleLabel = ResourceBundle.getBundle("properties.LomLabel", locale);
        resBundleVocabulary = ResourceBundle.getBundle("properties.LomVocabulary", locale);    
        resBundleLQMVocabulary = ResourceBundle.getBundle("properties.LQMVocabulary", locale);
        if (externalProfile != null)
            resBundleProfileVocabulary = ResourceBundle.getBundle("properties." + externalProfile + "Vocabulary", locale);
    }

    static String getLabel(String key) {
        return getBundleValue(resBundleLabel, key);
    }

    static String getTag(String key) {                
        if((key.startsWith("1.9")) || key.startsWith("1.10")|| key.startsWith("4.8") || key.startsWith("4.9") 
                || key.startsWith("5.12") || key.startsWith("5.13") || key.startsWith("5.14") || key.startsWith("5.15"))                   
                    return getBundleValue(resBundleLQMTag, key);
        else
            return getBundleValue(resBundleTag, key);
    }

    static int getPosTag(String key) throws IllegalTagException {
        StringBuffer altKey = new StringBuffer();
        Pattern pattern = Pattern.compile( "(.+?):(.+?)(/|$)" );
        Matcher matcher = pattern.matcher( key );
        while( matcher.find() ) {
            matcher.appendReplacement( altKey, matcher.group( 2 ) + matcher.group( 3 ) );
        }
        matcher.appendTail( altKey );  
        
        if ((key.contains("demand")) || key.contains("certification") || (key.contains("accessibility")) || (key.contains("usability"))
             || key.contains("environment") || key.contains("tutoring") || key.contains("learningmethod") || key.contains("trainingtype"))                
                return getPosValue(resBundleLQMPosTag, altKey.toString());        
        else   
            return getPosValue(resBundlePosTag, altKey.toString());
    }

    static String getVocabulary(String key) {        
        if (key.startsWith("x"))
            return externalProfile + ": " + getBundleValue(resBundleProfileVocabulary, key);
        //This may be util for labels with the format  <LQM:label>
        /*if (key.startsWith("Q"))
            return externalProfile + ": " + getBundleValue(resBundleProfileVocabulary, key);*/
        
        else if (key.startsWith("Q"))
                return getBundleValue(resBundleLQMVocabulary, key);
        else
            return getBundleValue(resBundleVocabulary, key);
    }

    static String getXMLVocabulary(String key) {
        String res;
        String num = "";
       
        int i=0;
        if (key.startsWith("x"))
            return getBundleValue(resBundleProfileXMLVocabulary, key);
        
        else if (key.startsWith("Q")){
            res = getBundleValue(resBundleLQMXMLVocabulary, key);            
            if ((key.startsWith("Q4.8.4")) || (key.startsWith("Q4.9.1")) || key.startsWith("Q4.9.2")){
                while(res.charAt(i)!=' '){                    
                    num += res.charAt(i);
                    i++;
                }
                return num;                
            }
            return res;
        }
        else
            return getBundleValue(resBundleXMLVocabulary, key);
    }
    
    static int getPosVocabulary(String key, boolean isProfileKey) throws IllegalTagException {
        key = key.replace(' ', '_');
        key = key.replace(':', '_');
        if (isProfileKey)
            return getPosValue(resBundleProfilePosVocabulary, key);
        else
            return getPosValue(resBundlePosVocabulary, key);
    }
    
    static int getLQMPosVocabulary(String key, String path) throws IllegalTagException {
        key = key.replace(' ', '_');
        key = key.replace(':', '_');
        
        if (path.equals("technical/usability/webusability"))
            return getPosValue(resBundleLQMPosVocabulary, "wu" + key); //Because repeated one or more keys in software and web usability 
        else
            return getPosValue(resBundleLQMPosVocabulary, key);
    }

    static String getProfileVocabularySource(String key) {
        return getBundleValue(resBundleProfileVocabularySource, key);
    }
    
    static String getLQMVocabularySource(String key) {
        return getBundleValue(resBundleLQMVocabularySource, key);
    }
    

    static String getExternalProfileFromVocabularySource(String key) {
        key = key.replace(' ', '_');
        key = key.replace(':', '_');
        return getBundleValue(resBundleExternalVocabularySource, key);
    }

    public static String convertSpecialCharactersForXML(String str) {
        if (str == null)
            return ("");
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '&')
                res += "&amp;";
            else if (c == '<')
                res += "&lt;";
            else if (c == '>')
                res += "&gt;";
            else if (c == '"')
                res += "&quot;";
            else
                res += c;
        }
        return res;
    }

    static Image imageApp;
    static Image imageEvaluate;
    static Image imageAbout;
    static ImageIcon imageIconCollapse;
    static ImageIcon imageIconExpand;
    static ImageIcon plusIcon;
    static ImageIcon minusIcon;
    static ImageIcon wizardIcon;
    static ImageIcon cancoreIcon;
    static ImageIcon normeticIcon;
    static ImageIcon normeticDisabledIcon;
    static ImageIcon redIcon;
    static ImageIcon yellowIcon;
    static ImageIcon greenIcon;
    static ImageIcon folderIcon;
    static ImageIcon fileIcon;

    static void initImageIcon(Class cl) {
        imageApp = getImage(cl, "app.gif");
        imageEvaluate = getImage(cl, "evaluateIcon.gif");
        imageAbout = getImage(cl, "about.gif");
        imageIconExpand = new ImageIcon(getImage(cl, "handleExpand.gif"));
        imageIconCollapse = new ImageIcon(getImage(cl, "handleCollapse.gif"));
        plusIcon = new ImageIcon(getImage(cl, "plus.gif"));
        minusIcon = new ImageIcon(getImage(cl, "minus.gif"));
        wizardIcon = new ImageIcon(getImage(cl, "wizard.gif"));
        cancoreIcon = new ImageIcon(getImage(cl, "cancore.gif"));
        normeticIcon = new ImageIcon(getImage(cl, "normetic.gif"));
        normeticDisabledIcon = new ImageIcon(getImage(cl, "normeticDisabled.gif"));
        redIcon = new ImageIcon(getImage(cl, "red.gif"));
        yellowIcon = new ImageIcon(getImage(cl, "yellow.gif"));
        greenIcon = new ImageIcon(getImage(cl, "green.gif"));
        folderIcon = new ImageIcon(getImage(cl, "folder.gif"));
        fileIcon = new ImageIcon(getImage(cl, "file.gif"));
    }

    public static Image getImage(Class cl, String name) {
        Image image = null;
        try {
            BufferedInputStream in = new BufferedInputStream(cl.getResourceAsStream("/images/" + name));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            copy(in, out);
            image = Toolkit.getDefaultToolkit().createImage(out.toByteArray());
        } catch (Exception e) {
            return null;
        }
        return image;
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        synchronized (in) {
            synchronized (out) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int bytesRead = in.read(buffer);
                    if (bytesRead == -1) break;
                    out.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    public static void copy(Reader in, Writer out) throws IOException {
        synchronized (in) {
            synchronized (out) {
                char[] buffer = new char[1024];
                while (true) {
                    int charRead = in.read(buffer);
                    if (charRead == -1) break;
                    out.write(buffer, 0, charRead);
                }
            }
        }
    }

    /**
     * Lire un fichier texte # partir
     * Retourne un liste de chaque ligne
     */
    public static Object[] readFile(Class cl, String filename) {
        Object[] res = null;
        ArrayList list = new ArrayList();
        String nextLine = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(cl.getResourceAsStream("/res/" + filename)));
            while ((nextLine = in.readLine()) != null)
                list.add(nextLine);
            in.close();
            res = list.toArray();
            return res;
        } catch (IOException e) {
            return null;
        }
    }

    /*
     * Retourne le JFrame conteneur le plus haut dans la hierarchie.
     * Dans le cas d'une applet, on a ainsi un frame pour ouvrir
     * des dialogs de type modal, qui restent par dessus le browser
     */
    public static JFrame getTopJFrame(Container c) {
        if (c instanceof JFrame) return (JFrame) c;
        Container theFrame = c;
        do {
            theFrame = theFrame.getParent();
        } while ((theFrame != null) && !(theFrame instanceof JFrame));
        if (theFrame == null) theFrame = new JFrame();
        return (JFrame) theFrame;
    }

    /*static void launchRessourceName(String name, String param) {
        String OSName = (System.getProperty("os.name")).toLowerCase();
        String commandLine =
                (OSName.startsWith("windows 9") || OSName.startsWith("windows me")) ?
                "start " + name :
                "cmd /c start \"nom\" " + name;
        try {
            Runtime.getRuntime().exec(commandLine + (param == null ? "" : " " + param));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    static void launchFile(String filePath) {
        String osName = (System.getProperty("os.name")).toLowerCase();//Gets the operative system
        String commandLine = null;
        if (osName.startsWith("windows")) {
            commandLine = (osName.startsWith("windows 9") || osName.startsWith("windows me")) ?
                    "start " + filePath :
                    "cmd /c start \"nom\" " + "\"" + filePath + "\"";
        }
        else if (osName.startsWith("mac"))
            commandLine = "/usr/bin/open " + filePath;

        try {
            Runtime.getRuntime().exec(commandLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne une chaine de caracteres commencant par une lettre majuscule.
     */
    public static String capitalize(String texte) {
        return texte.substring(0, 1).toUpperCase() + texte.substring(1);
    }

    /**
     * Retourne true si deux dates correspondent a la meme journee.
     */
    public static boolean memeJournee(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return ((cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) &&
                (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) &&
                (cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)));
    }

    /**
     * Retourne la m#me string pr#c#d#e par des 0 si besoin est pour
     * que la nouvelle string ait la taille pass#e en parametre
     */
    public static String completeDigit(String s, int length) {
        if (s.length() == length)
            return s;
        else {
            String prefix = "";
            for (int i = 0; i < length - s.length(); i++)
                prefix += "0";
            return prefix + s;
        }
    }
}

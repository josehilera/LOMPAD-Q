/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.licef.lompad;

import org.w3c.dom.Element;

import javax.swing.*;
import java.util.Enumeration;

/**
 *
 * @author usuario
 */
class AccessibilityForm extends FormContainer {
    
    String rootNumber;    
    ImageIcon icon;

    public AccessibilityForm(String title, boolean isLine, ImageIcon icon, boolean isMultiple) {
        super(title,isLine,isMultiple);
        this.rootNumber = title;
        this.icon = icon;        
    }
    
    void addFormContent(){
        FormWrapper wrapper = new FormWrapper("", true); 
        wrapper.addToggle(); //Add to the title a Toggle
        
        //Adaptation
        LangstringForm adaptation = new LangstringForm(rootNumber + ".1", true, false, false);
        adaptation.setIcon(icon);
        adaptation.addToggle();
        adaptation.addFormContent();        
        wrapper.addComponent(adaptation);        
        
        //Help
        VocabularyForm help = new VocabularyForm(rootNumber + ".2", true, true, new Object[]{null}, true);
        help.setIcon(icon);
        help.setAlignRight();
        help.addFormContent();
        wrapper.addComponent(help);
        
        //Hardware
        VocabularyForm hardware = new VocabularyForm(rootNumber + ".3", true, false, null, true);
        hardware.setIcon(icon);
        hardware.setAlignRight();
        hardware.addFormContent();
        wrapper.addComponent(hardware);
        
        //Software
        VocabularyForm software = new VocabularyForm(rootNumber + ".4", true, true, new Object[]{null}, true);
        software.setIcon(icon);
        software.setAlignRight();
        software.addFormContent();
        wrapper.addComponent(software);
        
        //Web
        VocabularyForm web = new VocabularyForm(rootNumber + ".5", true, false, null, true);
        web.setIcon(icon);
        web.setAlignRight();
        web.addFormContent();
        wrapper.addComponent(web);        
        
        addComponent(wrapper);
    }
    
    
    String toEvaluateFrame(String key) {
       String res = "";
        Object c = vComponents.elementAt(0);        
        res = ((FormWrapper) c).toEvaluateFrame(key);        
                
        return res;    
    }
    
    
    //XML
    String toXML(String key) {
        String xml = "";

        for (Enumeration e = vComponents.elements(); e.hasMoreElements();) {
            FormComponent c = (FormComponent) e.nextElement();
            String res = ((FormWrapper) c).toXML(key, true);
            if (res != null){
                
                
                xml += "<" + Util.getTag(key) + ">\n" + res + "</" + Util.getTag(key) + ">\n";
            
            }
        }

        if (xml.equals(""))
            xml = null;

        return xml;
    }

    void fromXML(String path, Element e) {
        FormWrapper c = null;
        if (isFilled()) {
            if (!isMultipleContainer) return; //pas d'import multiple possible
            addFormContent();
            c = (FormWrapper) vComponents.lastElement();
        } else
            c = (FormWrapper) vComponents.firstElement();

        c.fromXML(path, e, true);
    }

    //HTML
    String toHTML(String key) {
        String html = "";
        for (Enumeration e = vComponents.elements(); e.hasMoreElements();) {
            FormComponent c = (FormComponent) e.nextElement();
            String res = ((FormWrapper) c).toHTML(key, true);
            if (res != null) html += (html.equals("")?"":"<br>") + res;
        }

        if (!html.equals(""))
            html = "<TR><TD WIDTH=\"160\" VALIGN=\"TOP\"><B>" + Util.getLabel(key)+ "</B></TD>" +
                    "<TD VALIGN=\"TOP\">" + html + "</TD></TR>";
        else
            html = null;

        return html;
    }
    
}

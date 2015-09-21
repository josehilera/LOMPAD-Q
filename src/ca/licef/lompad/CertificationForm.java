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
class CertificationForm extends FormContainer {
    String rootNumber;    
    ImageIcon icon;
    
    public CertificationForm(String title, boolean isLine, ImageIcon icon, boolean isMultiple){
        super(title, isLine, isMultiple);
        this.rootNumber = title;
        this.icon = icon;
    }
    
    void addFormContent(){
        FormWrapper wrapper = new FormWrapper("", true); 
        wrapper.addToggle(); //Add to the title a Toggle
        
        //Qualification
        VocabularyForm qualification = new VocabularyForm(rootNumber + ".1", true, true, new Object[]{null},  true);
        qualification.setIcon(icon);
        qualification.setAlignRight();
        qualification.addFormContent();
        wrapper.addComponent(qualification);
        
        //Entity
        EntityForm entity = new EntityForm(rootNumber + ".2", true, false);
        entity.setIcon(icon);
        entity.addFormContent();
        wrapper.addComponent(entity);
        
        //Validity
        VocabularyForm validity = new VocabularyForm(rootNumber + ".3", true, false, null, true);
        validity.setIcon(icon);
        validity.setAlignRight();
        validity.addFormContent();
        wrapper.addComponent(validity);
        
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
            if (res != null)
                xml += "<" + Util.getTag(key) + ">\n" + res + "</" + Util.getTag(key) + ">\n";
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
    


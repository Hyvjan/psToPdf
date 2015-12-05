/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pstopdf;

/**
 *
 * @author Janne
 */

import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.io.IOUtils;
import org.ghost4j.converter.PDFConverter;
import org.ghost4j.document.PSDocument;


public class PsToPdf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileOutputStream fos = null;
        System.setProperty("jna.library.path", "C:\\Program Files (x86)\\gs\\gs9.05\\bin");
        try{
 
            //load PostScript document
            PSDocument document = new PSDocument();
            document.load(new File("C:\\Users\\Janne\\Desktop\\testi\\bell_206.ps"));
 
            //create OutputStream
            fos = new FileOutputStream(new File("C:\\Users\\Janne\\Desktop\\testi\\bell_206.pdf"));
 
            //create converter
            PDFConverter converter = new PDFConverter();
 
            //set options
            converter.setPDFSettings(PDFConverter.OPTION_PDFSETTINGS_PREPRESS);
 
            //convert
            converter.convert(document, fos);
 
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally{
            IOUtils.closeQuietly(fos);
        }
    }
    
}

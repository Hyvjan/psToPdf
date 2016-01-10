/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pstopdf;

/**
 *
 * @author Janne Hyvönen 05.12.15
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
        
        //Initializing fos
        FileOutputStream fos = null;
        
        //Hardcoded path to find .dll needed for testing
       // System.setProperty("jna.library.path", "C:\\Program Files (x86)\\gs\\gs9.05\\bin");
        
        //Get the absolut path to the folder where the file was executed
        
        String path="";
        if (args.length>0){
            path=args[0];
        }
        else {
            path=System.getProperty("user.dir");
        }
        
        //Pointing that .dll needed is in the working directory
        System.setProperty("jna.library.path", System.getProperty("user.dir")+"\\lib");
        
        //Make list of .ps files in the working directory
        File file=new File(path);
        File[] psList = new File[99];//Over 99 .ps files in the folder causes problem
        File[] listOfFiles = file.listFiles();
        int i=0;
        for (File tiedosto : listOfFiles) {    
            if(tiedosto.getName().endsWith(".ps")) {
                psList[i]=(tiedosto);
                i+=1;
            }   
        }
        
        //Iterate over the listed .ps files and create .pdf file from each 
        i=0;
        for (File tiedosto:psList){
        try{
 
            //load PostScript document
            PSDocument document = new PSDocument();
            document.load(psList[i]);
            //System.out.println("Listassa:"+psList[i]); for testing
            
            i++;//add 1 to the list pointer
            
            //System.out.println("i on: "+i); for testing
            
            //Get absolut path to the file to be transformed
            String name=psList[i-1].getAbsolutePath();
            //System.out.println("name on:"+name); for testing
            
            //Take away the file extension
            String[] extension=name.split("[.]");
            //System.out.println("extensio on:"+extension[0]); for testing
            
            //Add new file extension
            name=extension[0]+".pdf";
            //System.out.println("Nimi:"+name); for testing
            
            //create OutputStream
            fos = new FileOutputStream(new File(name));
            System.out.println(name);
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
}

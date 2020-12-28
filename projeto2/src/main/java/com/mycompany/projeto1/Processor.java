/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1;

import bookpackage.Catalog;
import bookpackage.AuthorCatalog;
import bookpackage.Author;
import bookpackage.Person;
import bookpackage.Book;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Antonio
 */
public class Processor {
        public static void main(String[] args) {

        File XMLfile = new File("XMLafterSelector.xml");

        Catalog catalogo = jaxbXmlFileToObject(XMLfile);

        AuthorCatalog novo = new AuthorCatalog();
        ArrayList<String> listaAutores = getListaAutores(catalogo);

        int flag = 0;

        while(flag != 1){
            System.out.println("----------Processor----------");
            System.out.println("Choose Number of Authors with Biggest Seling books");
            System.out.println("Number must be between 0 and "+listaAutores.size()+" :");
            int option = 0; //scanner para ver a opçao
            option = verifyInput(0,listaAutores.size()+1);
            novo = createPersonClass(listaAutores,catalogo);
            novo = createAuthorRank(novo, option);
            jaxbObjectToXML(novo);
            flag = 1;
        }
    }

    private static Catalog jaxbXmlFileToObject(File XMLfile) {
	/**
	* reads the XMLfile to Java object
	* returns rg
	*/

        JAXBContext jc;
	Catalog rg = new Catalog();
	try {
            jc = JAXBContext.newInstance(Catalog.class);
            // Create unnarshaller
            Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
            SchemaFactory schmFact = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaFile = new File("XMLafterSelector.xsd");
            Schema schemaXSD;
            try {
                schemaXSD = schmFact.newSchema(schemaFile);
                jaxbUnmarshaller.setSchema(schemaXSD);
            } catch (SAXException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            rg = (Catalog) jaxbUnmarshaller.unmarshal(XMLfile);
	}
        catch (JAXBException e) {
      		e.printStackTrace();
            }
        return (rg);
	}

    private static void jaxbObjectToXML(AuthorCatalog rg)
    {
		/**
		 * converts Java Oject (rg) to XML file
		 */
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(AuthorCatalog.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

           //Store XML to File
            File file = new File("XMLafterProcessor.xml");

            //Writes XML file to file-system
            jaxbMarshaller.marshal(rg, file);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getListaAutores(Catalog catalogo){
        ArrayList<String> Autores = new ArrayList<String>();
        for(int i = 0 ; i < catalogo.getBook().size(); i++){
            if(!Autores.contains(catalogo.getBook().get(i).getAuthor())){
                Autores.add(catalogo.getBook().get(i).getAuthor());
                 //System.out.println("yo:"+ catalogo.getBook().get(i).getAuthor());
            }
        }
        return Autores;
    }

    public static AuthorCatalog createPersonClass(ArrayList<String> listaAutores, Catalog catalogoLivros){
        AuthorCatalog rg = new AuthorCatalog();
        for(int i = 0 ; i  < listaAutores.size(); i++){
            Person novo = new Person();
            novo.setPersonname(listaAutores.get(i));
            for(int j = 0; j < catalogoLivros.getBook().size();j++){
                if(catalogoLivros.getBook().get(j).getAuthor().equals(listaAutores.get(i))){
                    novo.getBook().add(catalogoLivros.getBook().get(j));
                }
            }
            Collections.sort(novo.getBook(), new Comparator<Book>() {
            public int compare(Book book1, Book book2) {
                return book1.getBestSellerRank().compareTo(book2.getBestSellerRank());
            }
        });
            rg.getPerson().add(novo);
        }
        BigInteger bigInteger = BigInteger.valueOf(listaAutores.size());
        rg.setNumberofauthorsprocessed(bigInteger);
        return rg;
    }

    public static AuthorCatalog createAuthorRank(AuthorCatalog catalogo, int N){
        ArrayList<String> added = new ArrayList();
        for(int i = 0 ; i < N; i++){
            int x = -1;
            BigInteger maior = BigInteger.valueOf(999999999);
            for(int j = 0; j < catalogo.getPerson().size();j++){
                //da skip dos que foram added
                if(!added.contains(catalogo.getPerson().get(j).getPersonname())){
                    if(catalogo.getPerson().get(j).getBook().get(0).getBestSellerRank().compareTo(maior) == -1){ //escolhe o maior
                        maior = catalogo.getPerson().get(j).getBook().get(0).getBestSellerRank();
                        x = j;

                    }
                }
            }
            //guarda o indice do rank mais baixo

            if(x != -1){
                added.add(catalogo.getPerson().get(x).getPersonname());
                Author nova = new Author();
                nova.setAuthorname(catalogo.getPerson().get(x).getPersonname());
                nova.setRank(maior);
                catalogo.getAuthor().add(nova);
                //System.out.println(x);
            }
        }
        return catalogo;
    }



    public int compareRanks(Book b1, Book b2){
        return b1.getBestSellerRank().compareTo(b2.getBestSellerRank());
    }

    public static int verifyInput(int min, int max) {
        Scanner scan = new Scanner(System.in);
        int option;
        while(true){
            if(scan.hasNextInt()){
                option = scan.nextInt();
                if(option >= min && option < max){
                        break;
                    }else{
                        System.out.println("Invalid Option.");
                        scan.next();
                    }
                }
            }
        return option;
    }
}

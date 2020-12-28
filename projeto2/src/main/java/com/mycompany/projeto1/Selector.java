/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import bookpackage.Catalog;

/**
 * @author Antonio
 */
public class Selector {
    public static void main(String[] args) {

        File XMLfile = new File("../projeto2/src/main/java/com/mycompany/projeto1/beforeSelector.xml");

        Catalog catalogo = jaxbXmlFileToObject(XMLfile);

        Catalog novo = new Catalog();
        ArrayList<String> listaAutores = getListaAutores(catalogo);

        System.out.println("----------Selector----------");
        System.out.println("Select By:");
        System.out.println("1 - Author");
        System.out.println("2 - Year");
        System.out.println("3 - Title");
        System.out.println("4 - Rating");
        System.out.println("5 - Total ratings");
        System.out.println("6 - Best Seller rank");
        System.out.println("7 - Author + Rating + Year");
        System.out.println("8 - Author + Year");
        System.out.println("9 - Author + Rating");
        System.out.println("10 - Rating + Year");
        System.out.println("Select option:");
        int option = verifyInput(1, 10);

        if (option == 1) {
            for (int i = 0; i < listaAutores.size(); i++) {
                System.out.println(i + " - " + listaAutores.get(i));
            }
            int number = verifyInput(1, listaAutores.size());
            novo = getBooksWithAuthorName(catalogo, listaAutores.get(number));
            jaxbObjectToXML(novo);

        } else if (option == 2) {
            int min = catalogo.getBook().get(0).getYear().intValue();
            int max = min;
            for (int i = 1; i < catalogo.getBook().size(); i++) {
                int number = catalogo.getBook().get(i).getYear().intValue();
                if (number < min) {
                    min = number;
                } else if (max < number) {
                    max = number;
                }
            }
            System.out.println("Select Year Between " + min + " - " + max);
            int number = verifyInput(min, max);
            novo = getBooksWithYear(catalogo, number);
            jaxbObjectToXML(novo);

        } else if (option == 3) {
            for (int i = 0; i < catalogo.getBook().size(); i++) {
                System.out.println(i + " - " + catalogo.getBook().get(i).getTitle());
            }
            int number = verifyInput(1, catalogo.getBook().size());
            //System.out.println("A: "+catalogo.getBook().get(number).getTitle());
            novo = getBooksWithTitle(catalogo, catalogo.getBook().get(number).getTitle());
            jaxbObjectToXML(novo);

        } else if (option == 4) {
            System.out.println("Select Number Between 1(Lowest) to 5(Highest)");
            int number = verifyInput(1, 5);
            novo = getBooksWithRatings(catalogo, number);
            jaxbObjectToXML(novo);

        } else if (option == 5) {
            System.out.println("Select Minimum Number of Reviews de Book needs to have:");
            int number = verifyInput(1, 999999999);
            novo = getBooksWithRatings(catalogo, number);
            jaxbObjectToXML(novo);

        } else if (option == 6) {
            System.out.println("Select Minimun Number in the rank the Book needs to have:");
            int number = verifyInput(1, 99999999);
            novo = getBooksWithRatings(catalogo, number);
            jaxbObjectToXML(novo);

        } else if (option == 7) {
            for (int i = 0; i < listaAutores.size(); i++) {
                System.out.println(i + " - " + listaAutores.get(i));
            }
            int number = verifyInput(1, listaAutores.size());
            novo = getBooksWithAuthorName(catalogo, listaAutores.get(number));
            System.out.println("Select Number Between 1(Lowest) to 5(Highest)");
            number = verifyInput(1, 5);
            novo = getBooksWithRatings(novo, number);
            int min = novo.getBook().get(0).getYear().intValue();
            int max = min;
            for (int i = 1; i < novo.getBook().size(); i++) {
                int numberAux = novo.getBook().get(i).getYear().intValue();
                if (numberAux < min) {
                    min = numberAux;
                } else if (max < numberAux) {
                    max = numberAux;
                }
            }
            System.out.println("Select Year Between " + min + " - " + max);
            number = verifyInput(min, max);
            novo = getBooksWithYear(novo, number);
            jaxbObjectToXML(novo);

        } else if (option == 8) {
            for (int i = 0; i < listaAutores.size(); i++) {
                System.out.println(i + " - " + listaAutores.get(i));
            }
            int number = verifyInput(1, listaAutores.size());
            novo = getBooksWithAuthorName(catalogo, listaAutores.get(number));
            int min = novo.getBook().get(0).getYear().intValue();
            int max = min;
            for (int i = 1; i < novo.getBook().size(); i++) {
                int numberAux = novo.getBook().get(i).getYear().intValue();
                if (numberAux < min) {
                    min = numberAux;
                } else if (max < numberAux) {
                    max = numberAux;
                }
            }
            System.out.println("Select Year Between " + min + " - " + max);
            number = verifyInput(min, max);
            novo = getBooksWithYear(novo, number);
            jaxbObjectToXML(novo);

        } else if (option == 9) {
            for (int i = 0; i < listaAutores.size(); i++) {
                System.out.println(i + " - " + listaAutores.get(i));
            }
            int number = verifyInput(1, listaAutores.size());
            novo = getBooksWithAuthorName(catalogo, listaAutores.get(number));
            System.out.println("Select Number Between 1(Lowest) to 5(Highest)");
            number = verifyInput(1, 5);
            novo = getBooksWithRatings(novo, number);
            jaxbObjectToXML(novo);

        } else if (option == 10) {
            System.out.println("Select Number Between 1(Lowest) to 5(Highest)");
            int number = verifyInput(1, 5);
            novo = getBooksWithRatings(catalogo, number);
            int min = novo.getBook().get(0).getYear().intValue();
            int max = min;
            for (int i = 1; i < novo.getBook().size(); i++) {
                int numberAux = novo.getBook().get(i).getYear().intValue();
                if (numberAux < min) {
                    min = numberAux;
                } else if (max < numberAux) {
                    max = numberAux;
                }
            }
            System.out.println("Select Year Between " + min + " - " + max);
            number = verifyInput(min, max);
            novo = getBooksWithYear(novo, number);
            jaxbObjectToXML(novo);
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
            File schemaFile = new File("../projeto2/src/main/java/com/mycompany/projeto1/beforeSelector.xsd");
            Schema schemaXSD;
            try {
                schemaXSD = schmFact.newSchema(schemaFile);
                jaxbUnmarshaller.setSchema(schemaXSD);
            } catch (SAXException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            rg = (Catalog) jaxbUnmarshaller.unmarshal(XMLfile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return (rg);
    }

    private static void jaxbObjectToXML(Catalog rg) {
        /**
         * converts Java Oject (rg) to XML file
         */
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Store XML to File
            File file = new File("XMLafterSelector.xml");

            //Writes XML file to file-system
            jaxbMarshaller.marshal(rg, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getListaAutores(Catalog catalogo) {
        ArrayList<String> Autores = new ArrayList<String>();
        for (int i = 0; i < catalogo.getBook().size(); i++) {
            if (!Autores.contains(catalogo.getBook().get(i).getAuthor())) {
                Autores.add(catalogo.getBook().get(i).getAuthor());
                //System.out.println("yo:"+ catalogo.getBook().get(i).getAuthor());
            }
        }
        return Autores;
    }

    public static Catalog getBooksWithAuthorName(Catalog catalogo, String name) {
        Catalog rg = new Catalog();
        for (int i = 0; i < catalogo.getBook().size(); i++) {
            if (name.equals(catalogo.getBook().get(i).getAuthor()) == true) {
                rg.getBook().add(catalogo.getBook().get(i));
            }
        }
        return rg;
    }

    public static Catalog getBooksWithYear(Catalog catalogo, int year) {
        Catalog rg = new Catalog();
        BigInteger bigInteger = BigInteger.valueOf(year);
        for (int i = 0; i < catalogo.getBook().size(); i++) {
            //System.out.println(catalogo.getBook().get(i).getYear());
            // System.out.println(catalogo.getBook().get(i).getYear().compareTo(bigInteger));
            if (catalogo.getBook().get(i).getYear().compareTo(bigInteger) != -1) {
                rg.getBook().add(catalogo.getBook().get(i));
            }
        }
        return rg;
    }

    public static Catalog getBooksWithTitle(Catalog catalogo, String title) {
        Catalog rg = new Catalog();
        for (int i = 0; i < catalogo.getBook().size(); i++) {
            //System.out.println(title);
            //System.out.println(title.equals(catalogo.getBook().get(i).getTitle()));
            if (title.equals(catalogo.getBook().get(i).getTitle()) == true) {
                rg.getBook().add(catalogo.getBook().get(i));
            }
        }
        return rg;
    }

    public static Catalog getBooksWithRatings(Catalog catalogo, int rating) {
        Catalog rg = new Catalog();
        BigDecimal bigDeci = BigDecimal.valueOf(rating);
        for (int i = 0; i < catalogo.getBook().size(); i++) {
            //System.out.println(catalogo.getBook().get(i).getRating());
            //System.out.println(catalogo.getBook().get(i).getRating().compareTo(bigDeci));
            if (catalogo.getBook().get(i).getRating().compareTo(bigDeci) != -1) {
                rg.getBook().add(catalogo.getBook().get(i));
            }
        }
        return rg;
    }

    public static Catalog getBooksWithReviews(Catalog catalogo, int reviews) {
        Catalog rg = new Catalog();
        BigInteger bigInt = BigInteger.valueOf(reviews);
        for (int i = 0; i < catalogo.getBook().size(); i++) {
            if (catalogo.getBook().get(i).getTotalRatings().compareTo(bigInt) != -1) {
                rg.getBook().add(catalogo.getBook().get(i));
            }
        }
        return rg;
    }

    public static Catalog getBooksWithRanking(Catalog catalogo, int ranking) {
        Catalog rg = new Catalog();
        BigInteger bigInt = BigInteger.valueOf(ranking);
        for (int i = 0; i < catalogo.getBook().size(); i++) {
            if (catalogo.getBook().get(i).getBestSellerRank().compareTo(bigInt) != 1) {
                rg.getBook().add(catalogo.getBook().get(i));
            }
        }
        return rg;
    }

    public static int verifyInput(int min, int max) {
        Scanner scan = new Scanner(System.in);
        int option;
        while (true) {
            if (scan.hasNextInt()) {
                option = scan.nextInt();
                if (option >= min && option <= max) {
                    break;
                } else {
                    System.out.println("Invalid Option.");
                    scan.next();
                }
            }
        }
        return option;
    }
}
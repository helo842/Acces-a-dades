import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Pt4 {
        public static void main(String[] args)
                throws ParserConfigurationException, javax.xml.transform.TransformerException,
                SAXException, IOException {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.newDocument();
                        
                        Element Zoo = doc.createElement("Zoo");
                        doc.appendChild(Zoo);
                        
                        Element animal = doc.createElement("animal");
                        animal.setAttribute("id", "01");
                        Zoo.appendChild(animal);

                        Element nom = doc.createElement("nom");
                        nom.setTextContent("Pinguí");
                        animal.appendChild(nom);

                        Element especie = doc.createElement("especie");
                        especie.setTextContent("Apetrodytes forsteri");
                        animal.appendChild(especie);

                        Element aliment = doc.createElement("aliment");
                        aliment.setTextContent("Pescado");
                        animal.appendChild(aliment);


                        Element edat = doc.createElement("edat");
                        edat.setTextContent("20");
                        animal.appendChild(edat);

                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();

                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(new File("zoo.xml"));

                        transformer.transform(source, result);

                        DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder2 = factory2.newDocumentBuilder();
                        Document doc2 = builder2.parse(new File("zoo.xml"));
                        Element zooLeido = doc2.getDocumentElement();
                        NodeList animales = zooLeido.getElementsByTagName("animal");


                        for (int i = 0; i < animales.getLength(); i++) {
                                Element animalLeido = (Element) animales.item(i);
                                String idLeido = animalLeido.getAttribute("id");
                                System.out.println(idLeido);
                                String nombreLeido = animalLeido.getElementsByTagName("nom").item(0).getTextContent();
                                String especieLeida = animalLeido.getElementsByTagName("especie").item(0).getTextContent();
                                String alimentLeido = animalLeido.getElementsByTagName("aliment").item(0).getTextContent();
                                String edatLeida = animalLeido.getElementsByTagName("edat").item(0).getTextContent();

                                System.out.println(idLeido);
                                System.out.println(nombreLeido);
                                System.out.println(especieLeida);
                                System.out.println(alimentLeido);
                                System.out.println(edatLeida);
                }
        }
}
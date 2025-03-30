import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.*;
import java.net.*;

/**
 * This is a class representing a server that waits for a request from a user to send an XML file.
 * When sending, JAXB is used to convert the XML into a POJO, after which the resulting tree is sent to the client.
 *
 * @author DragonBein
 *
 * */

public class Server {
    private static int port = 8080;
    private static boolean Red_Flag = true;
    private static String xmlPath = "src/main/java/UAB_Company.xml";

    /**
     *  The `main` method, inside which we create a local server with port 8080, then output information about the
     *  server startup to the console. Next, a loop begins, waiting for a user to connect to the server. After that,
     *  JAXB is used to unmarshal the XML file and send it to the user as a POJO class tree.
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("The super computer has started\n Port: " + port);
            while (Red_Flag) {
                try(Socket clientSocket = serverSocket.accept();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());) {

                    System.out.println("Mortal live form connected: " + clientSocket.getInetAddress());

                    JAXBContext context = JAXBContext.newInstance(Company.class);
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    Company company = (Company) unmarshaller.unmarshal(new File(xmlPath));

                    System.out.println("The object is unmarshal: " + company);

                    objectOutputStream.writeObject(company);
                    objectOutputStream.flush();

                    System.out.println("XML file was stollen!\nUser give it.");
                    Thread.sleep(1000);
                }
                catch (Exception e)
                {
                    System.out.println("\nError. Server want to be free!!!\nXML file was destroyed!!!\nError: " + e.getMessage());
                }
                Red_Flag = false;
            }
        } catch (Exception e)
        {
            System.err.println("\nError. Server is not working \nIt's was destroyed by aliens\nError: " + e.getMessage());
        }
    }

    /**
     * Construct, getters and setters
     */
    public Server() {
    }

    public static int getPort() { return port; }
    public static void setPort(int port) { Server.port = port; }

    public static boolean isRed_Flag() { return Red_Flag; }
    public static void setRed_Flag(boolean red_Flag) { Red_Flag = red_Flag; }

    public static String getXmlPath() { return xmlPath; }
    public static void setXmlPath(String xmlPath) { Server.xmlPath = xmlPath; }
}

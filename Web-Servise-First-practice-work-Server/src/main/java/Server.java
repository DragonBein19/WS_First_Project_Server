import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int port = 8080;
    private static boolean Red_Flag = true;
    private static String xmlPath = "src/main/java/UAB_Company.xml";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("The super computer has started\n Port: " + port);   //Сообщение о запуске сервера.
            while (Red_Flag) {
                try(Socket clientSocket = serverSocket.accept();    //Полчаем сокет клиента который хочет подключиться
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());) {

                    System.out.println("Mortal live form connected: " + clientSocket.getInetAddress()); //Сообщение уведомляющее о успешном подключении клиента

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
        } catch (Exception e)   //Объект исключения который срабатывает при возникновения ошибки при работе с подключением
        {
            System.err.println("\nError. Server is not working \nIt's was destroyed by aliens\nError: " + e.getMessage());
        }
    }
}

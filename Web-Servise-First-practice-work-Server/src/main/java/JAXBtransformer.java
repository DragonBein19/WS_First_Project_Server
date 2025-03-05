import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JAXBtransformer {

    //Method for converting an object to XML and saving it to a file (Marshalling)
    public static void Marshaling (Company company, String filePath)
    {
        try {
            JAXBContext context = JAXBContext.newInstance(Company.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(company, new File(filePath));
            System.out.println("XML file successes created: " + filePath);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    //Method for loading an object from an XML file (Unmarshalling)
    public static Company unMarshaling (String filePath)
    {
        try{
            JAXBContext context = JAXBContext.newInstance(Company.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Company) unmarshaller.unmarshal(new File(filePath));
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}

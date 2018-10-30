import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class PartManagerMockTest {

    static final String VALID_TEST_XML_partnumber = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";


    static final String Partnumber_out_Stock = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1111</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String No_longer_manfactured = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>2222</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    private Item deserializeXMLToItemObject(String xml)
    {
        try
        {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Item item = (Item) jaxbUnmarshaller.unmarshal(reader);
            return item;

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    @Test
    public void valid_test_XML_Partmanager(){

        Item item = deserializeXMLToItemObject(VALID_TEST_XML_partnumber);
        PartManagerMock partManagerMock= new PartManagerMock();
        assertEquals(IPARTMANAGER.PartResponse.SUCCESS, partManagerMock.SubmitPartForManufactureAndDelivery(item.getPartnumber(), item.getQuantity(),null));
    }

    @Test
    public void Partnumber_out_Stock(){

        Item item = deserializeXMLToItemObject(Partnumber_out_Stock);
        PartManagerMock partManagerMock= new PartManagerMock();
        assertEquals(IPARTMANAGER.PartResponse.OUT_OF_STOCK, partManagerMock.SubmitPartForManufactureAndDelivery(item.getPartnumber(), item.getQuantity(),null));
    }

    @Test
    public void No_longer_manfactured(){

        Item item = deserializeXMLToItemObject(No_longer_manfactured);
        PartManagerMock partManagerMock= new PartManagerMock();
        assertEquals(IPARTMANAGER.PartResponse.NO_LONGER_MANUFACTURED, partManagerMock.SubmitPartForManufactureAndDelivery(item.getPartnumber(), item.getQuantity(),null));
    }
}


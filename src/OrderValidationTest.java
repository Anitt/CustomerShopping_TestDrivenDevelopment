import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OrderValidationTest {

    static final String VALID_TEST_XML_partnumber = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String Invalid_partnumber_zero = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>0</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String Invalid_quantity_zero = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>0</quantity>\n" +
            "</item>";

    final String Valid_TEST_XML_Delivery = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryTo>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street>35 Streetname</street>" +
            "	<city>Halifax</city>" +
            "	<province>NS</province>" +
            "	<postalCode>B2T1A4</postalCode>" +
            "</deliveryTo>";

    final String Invalid_TEST_XML_Delivery_StreetMissing = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryTo>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street></street>" +
            "	<city>Halifax</city>" +
            "	<province>NS</province>" +
            "	<postalCode>B2T1A4</postalCode>" +
            "</deliveryTo>";

    final String Invalid_TEST_XML_Delivery_cityMissing = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryTo>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street>35 Streetname</street>" +
            "	<city></city>" +
            "	<province>NS</province>" +
            "	<postalCode>B2T1A4</postalCode>" +
            "</deliveryTo>";

    final String Invalid_TEST_XML_Delivery_ProvinceMissing = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryTo>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street>35 Streetname</street>" +
            "	<city>Halifax</city>" +
            "	<province></province>" +
            "	<postalCode>B2T1A4</postalCode>" +
            "</deliveryTo>";

    final String Invalid_TEST_XML_Delivery_PostalMissing = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryTo>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street>35 Streetname</street>" +
            "	<city>Halifax</city>" +
            "	<province>NS</province>" +
            "	<postalCode></postalCode>" +
            "</deliveryTo>";


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
    public void Validpartnumber(){

        Item item = deserializeXMLToItemObject(VALID_TEST_XML_partnumber);
         //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        DatabaseMock databaseMock = new DatabaseMock();
        assertEquals(IDatabase.Partresponsse.Valid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));
        assertNotNull(item);
    }
    @Test
    public void test_Invalid_quantity_zero(){

        Item item = deserializeXMLToItemObject(Invalid_quantity_zero);
        DatabaseMock databaseMock = new DatabaseMock();
        assertEquals(IDatabase.Partresponsse.Invalid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));
       // assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        assertNotNull(item);

    }

    @Test
    public void test_Invalid_partnumber_zero(){

        Item item = deserializeXMLToItemObject(Invalid_partnumber_zero);
        DatabaseMock databaseMock = new DatabaseMock();
        assertEquals(IDatabase.Partresponsse.Invalid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        assertNotNull(item);

    }




}

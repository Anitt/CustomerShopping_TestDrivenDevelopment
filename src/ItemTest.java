import com.sun.org.apache.xpath.internal.operations.Equals;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ItemTest {

    static final String VALID_TEST_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";


    static final String Invalid_partnumber = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>A</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String Invalid_quantity = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>A</quantity>\n" +
            "</item>";

    static final String Invalid_quantity_negative = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>-1</quantity>\n" +
            "</item>";

    static final String Invalid_quantity_zero = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>0</quantity>\n" +
            "</item>";

    static final String Invalid_partnumber_zero = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>0</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String MISSING_partnumber = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";
    static final String MISSING_QUANTITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "</item>";


    public Item deserializeXMLToItemObject(String xml)
    {
        try
        {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);
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
    public void testdeserialization(){

        Item item = deserializeXMLToItemObject(VALID_TEST_XML);
        assertEquals(1234,item.getPartnumber());
        assertEquals(2,item.getQuantity());
        assertNotNull(item);

    }

    // Have to test this

    @Test
    public void test_invalidpartnumber(){

        Item item = deserializeXMLToItemObject(Invalid_partnumber);
        DatabaseMock databaseMock = new DatabaseMock();
        assertNotNull(item);
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        assertEquals(IDatabase.Partresponsse.Invalid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));
    }
    @Test
    public void test_invalidquantity(){

        Item item = deserializeXMLToItemObject(Invalid_quantity);
        assertNotNull(item);
        DatabaseMock databaseMock = new DatabaseMock();
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        assertEquals(IDatabase.Partresponsse.Invalid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));

    }
    @Test
    public void test_invalid_negative_quality(){

        Item item = deserializeXMLToItemObject(Invalid_quantity_negative);
        assertNotNull(item);
        DatabaseMock databaseMock = new DatabaseMock();
      //  assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        assertEquals(IDatabase.Partresponsse.Invalid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));


    }
    @Test
    public void test_Invalid_quantity_zero(){

        Item item = deserializeXMLToItemObject(Invalid_quantity_zero);
        DatabaseMock databaseMock = new DatabaseMock();
       // assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        assertEquals(IDatabase.Partresponsse.Invalid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));
        assertNotNull(item);

    }

    @Test
    public void test_Invalid_partnumber_zero(){

        Item item = deserializeXMLToItemObject(Invalid_partnumber_zero);
        DatabaseMock databaseMock = new DatabaseMock();
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        assertEquals(IDatabase.Partresponsse.Invalid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));
        assertNotNull(item);

    }


    @Test
    public void test_missing_partnumber(){

        Item item = deserializeXMLToItemObject(MISSING_partnumber);
        DatabaseMock databaseMock = new DatabaseMock();
        assertEquals(IDatabase.Partresponsse.Invalid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        assertNotNull(item);

    }

    @Test
    public void test_missing_quantity(){

        Item item = deserializeXMLToItemObject(MISSING_QUANTITY);
        DatabaseMock databaseMock = new DatabaseMock();

        assertEquals(IDatabase.Partresponsse.Invalid, databaseMock.IsPartNumberValid(item.getPartnumber(), item.getQuantity()));
        assertNotNull(item);

    }







}

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeliveryaddressTest {

    final String Valid_TEST_XML_Delivery = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street>35 Streetname</street>" +
            "	<city>Halifax</city>" +
            "	<province>NS</province>" +
            "	<postalCode>B2T1A4</postalCode>" +
            "</deliveryaddress>";

    final String Invalid_TEST_XML_Delivery_StreetMissing = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street></street>" +
            "	<city>Halifax</city>" +
            "	<province>NS</province>" +
            "	<postalCode>B2T1A4</postalCode>" +
            "</deliveryaddress>";

    final String Invalid_TEST_XML_Delivery_cityMissing = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street>35 Streetname</street>" +
            "	<city></city>" +
            "	<province>NS</province>" +
            "	<postalCode>B2T1A4</postalCode>" +
            "</deliveryaddress>";

    final String Invalid_TEST_XML_Delivery_ProvinceMissing = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street>35 Streetname</street>" +
            "	<city>Halifax</city>" +
            "	<province></province>" +
            "	<postalCode>B2T1A4</postalCode>" +
            "</deliveryaddress>";

    final String Invalid_TEST_XML_Delivery_PostalMissing = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>" +
            "	<name>Mrs. Jane Smith</name>" +
            "	<street>35 Streetname</street>" +
            "	<city>Halifax</city>" +
            "	<province>NS</province>" +
            "	<postalCode></postalCode>" +
            "</deliveryaddress>";



    private Deliveryaddress deserializeXMLToItemObject(String xml)
    {
        try
        {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Deliveryaddress.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Deliveryaddress deliveryaddress = (Deliveryaddress) jaxbUnmarshaller.unmarshal(reader);
            return deliveryaddress;

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }


// test class when the delivery address is valid
    @Test
    public void Valid_TEST_XML_Delivery(){

        Deliveryaddress deliveryaddress = deserializeXMLToItemObject(Valid_TEST_XML_Delivery);
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        OrderValidation  orderValidation = new OrderValidation();
        assertEquals(IOrderValidation.Ordervalidation.SUCCESS, orderValidation.validDeliveryaddress(deliveryaddress.getName(),deliveryaddress.getStreet(),deliveryaddress.getCity(),deliveryaddress.getProvince(),"B2T1A4"));
        //assertNotNull(item);
    }

    @Test
    public void Invalid_TEST_XML_Delivery_StreetMissing(){

        Deliveryaddress deliveryaddress = deserializeXMLToItemObject(Invalid_TEST_XML_Delivery_StreetMissing);
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        OrderValidation  orderValidation = new OrderValidation();
        assertEquals(IOrderValidation.Ordervalidation.Invalid_delivery_address, orderValidation.validDeliveryaddress(deliveryaddress.getName(),deliveryaddress.getStreet(),deliveryaddress.getCity(),deliveryaddress.getProvince(),deliveryaddress.getPostalcode()));
        //assertNotNull(item);
    }

    @Test
    public void Invalid_TEST_XML_Delivery_cityMissing(){

        Deliveryaddress deliveryaddress = deserializeXMLToItemObject(Invalid_TEST_XML_Delivery_cityMissing);
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        OrderValidation  orderValidation = new OrderValidation();
        assertEquals(IOrderValidation.Ordervalidation.Invalid_delivery_address, orderValidation.validDeliveryaddress(deliveryaddress.getName(),deliveryaddress.getStreet(),deliveryaddress.getCity(),deliveryaddress.getProvince(),deliveryaddress.getPostalcode()));
        //assertNotNull(item);
    }


    @Test
    public void Invalid_TEST_XML_Delivery_ProvinceMissing(){

        Deliveryaddress deliveryaddress = deserializeXMLToItemObject(Invalid_TEST_XML_Delivery_ProvinceMissing);
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        OrderValidation  orderValidation = new OrderValidation();
        assertEquals(IOrderValidation.Ordervalidation.Invalid_delivery_address, orderValidation.validDeliveryaddress(deliveryaddress.getName(),deliveryaddress.getStreet(),deliveryaddress.getCity(),deliveryaddress.getProvince(),deliveryaddress.getPostalcode()));
        //assertNotNull(item);
    }

    @Test
    public void Invalid_TEST_XML_Delivery_PostalMissing(){

        Deliveryaddress deliveryaddress = deserializeXMLToItemObject(Invalid_TEST_XML_Delivery_PostalMissing);
        //assertTrue(!item.IsPartNumberValid(item.getPartnumber(),item.getQuantity()));
        OrderValidation  orderValidation = new OrderValidation();
        assertEquals(IOrderValidation.Ordervalidation.Failure, orderValidation.validDeliveryaddress(deliveryaddress.getName(),deliveryaddress.getStreet(),deliveryaddress.getCity(),deliveryaddress.getProvince(),deliveryaddress.getPostalcode()));
        //assertNotNull(item);
    }


}

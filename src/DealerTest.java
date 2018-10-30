import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DealerTest {

//  static   ISecurity securityinterface ;
//  static   HashMap<String,String> fakedealersandkeys = new HashMap<String, String>();
//  public static void initAll(){
//
//
//        fakedealersandkeys.put("FAKEDEALER","FAKEACCESSKEY");
//        securityinterface = new SecurityMock(fakedealersandkeys);
//
//    }

    static final String FakeDealer =  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>FAKEDEALER</dealerid>\n" +
            "	<dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>\n" +
            "</dealer>";

    static final String FakeDealerkeys =  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>XXX-1234-ABCD-1234</dealerid>\n" +
            "	<dealeraccesskey>FAKEACCESSKEY</dealeraccesskey>\n" +
            "</dealer>";

    static final String VALID_DEALER_XML =  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>XXX-1234-ABCD-1234</dealerid>\n" +
            "	<dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>\n" +
            "</dealer>";

    static final String INVALID_DEALERID_XML_null =  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>0</dealerid>\n" +
            "	<dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>\n" +
            "</dealer>";

    static final String INVALID_DEALERACCESSKEY_XML_null =  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>XXX-1234-ABCD-1234</dealerid>\n" +
            "	<dealeraccesskey>0</dealeraccesskey>\n" +
            "</dealer>";

    static final String MISSING_Dealerid = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>\n" +
            "</dealer>";

    static final String MISSING_DEALERACCESSKEY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>XXX-1234-ABCD-1234</dealerid>\n" +
            "</dealer>";

    // deserializing the XML

    private Dealer deserializeXMLToItemObject(String xml)
    {
        try
        {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Dealer.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Dealer dealer = (Dealer) jaxbUnmarshaller.unmarshal(reader);
            return dealer;
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }



// function to check fake dealers , it fails if fake Dealer exists

    @Test
    public void fake_dealers(){
        Dealer dealer = deserializeXMLToItemObject(FakeDealer);
        SecurityMock securityMock = new SecurityMock();
        assertTrue(!securityMock.IsDealerAuthorized(dealer.getDealerid(),dealer.getDealeraccesskey()));
        assertNotNull(dealer);

    }
// testing when dealer id is null
    @Test
    public void Test_invalid_Dealerid_XML_null(){
        Dealer dealer = deserializeXMLToItemObject(INVALID_DEALERID_XML_null);
        SecurityMock securityMock = new SecurityMock();
        assertNotNull(dealer);
    }
// testing when the dealer and dealer access key are valid
    @Test
    public void Test_valid_Dealer_XML(){
        Dealer dealer = deserializeXMLToItemObject(VALID_DEALER_XML);
        assertEquals("XXX-1234-ABCD-1234",dealer.getDealerid());
        assertEquals("kkklas8882kk23nllfjj88290",dealer.getDealeraccesskey());

    }
// testing when dealer access key is null
    @Test
    public void Test_invalid_Dealeraccesskey_XML_Null(){
        Dealer dealer = deserializeXMLToItemObject(INVALID_DEALERACCESSKEY_XML_null);

         assertNotNull(dealer);
    }
// testing when missing dealer id is null
    @Test
    public void Test_MissingDealerID(){
        Dealer dealer = deserializeXMLToItemObject(MISSING_Dealerid);
        SecurityMock securityMock = new SecurityMock();
        assertTrue(!securityMock.IsDealerAuthorized(dealer.getDealerid(),dealer.getDealeraccesskey()));
        assertNotNull(dealer);

    }
    //testing when dealeraccesskey is missing
    @Test
    public void Test_MissingDealerAccessKey(){
        Dealer dealer = deserializeXMLToItemObject(MISSING_DEALERACCESSKEY);
        SecurityMock securityMock = new SecurityMock();
        assertTrue(!securityMock.IsDealerAuthorized(dealer.getDealerid(),dealer.getDealeraccesskey()));
        assertNotNull(dealer);

    }
}

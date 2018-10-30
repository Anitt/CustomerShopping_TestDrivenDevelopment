import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import org.junit.Test;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class LowercaseXMLElementStreamReaderTest
{
	@Test
	//@DisplayName("LowercaseXMLElementStreamReaderTest")

	public void lowercaseXMLElementStreamReaderTest()
	{
		try
		{
			//System.out.println("Working Directory = " + System.getProperty("user.dir"));
			LowercaseXMLElementStreamReader reader = new LowercaseXMLElementStreamReader("src//IncomingOrder.xml", Order.class);
			Order order = (Order) reader.DeserializeXMLIntoObject();
			//Item item = (Item)reader.DeserializeXMLIntoObject();
			assertEquals("Mrs. Jane Smith", order.getDeliveryaddress().getName());
			assertEquals("Halifax", order.getDeliveryaddress().getCity());
           // System.out.println(Object.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("Failed to parse IncomingOrder.xml " + e);
		}
	}
}










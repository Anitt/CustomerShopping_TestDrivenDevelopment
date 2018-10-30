import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderResponseXML {

    private Orderitemsresponse orderitemsresponse;

    public Orderitemsresponse getOrderitemsresponse ()
    {
        return orderitemsresponse;
    }

    public void setOrderitems (Orderitemsresponse orderitemsresponse)
    {
        this.orderitemsresponse = orderitemsresponse;
    }
}

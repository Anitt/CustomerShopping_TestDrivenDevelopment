import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Orderitemsresponse {


    private Itemresponse[] itemresponse;

    public Itemresponse[] getItemresponse ()
    {
        return itemresponse;
    }

    public void setItemresponse (Itemresponse[] itemresponse)
    {
        this.itemresponse = itemresponse;
    }


}

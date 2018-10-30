import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
// model class to store and reterive the part number
@XmlRootElement
public class Item  {

    private int partnumber;

    private int quantity;

    public int getPartnumber ()
    {
        return partnumber;
    }

    public void setPartnumber (int partnumber)
    {
        this.partnumber = partnumber;
    }

    public int getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (int quantity)
    {
        this.quantity = quantity;
    }


    public static enum Partresponsse{

        Valid,
        Invalid

    }


}

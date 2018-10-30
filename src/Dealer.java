import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.security.Security;
import java.util.HashMap;

// model class to store dealer id and dealer access key
@XmlRootElement
public class Dealer {


    private String dealerid;

    private String dealeraccesskey;

    public String getDealerid ()
    {
        return dealerid;
    }

    public void setDealerid (String dealerid)
    {
        this.dealerid = dealerid;
    }

    public String getDealeraccesskey ()
    {
        return dealeraccesskey;
    }

    public void setDealeraccesskey (String dealeraccesskey)
    {
        this.dealeraccesskey = dealeraccesskey;
    }



}

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
// model class to store the Item response
@XmlRootElement
public class    Itemresponse {

    private String resultresponse;

    private int partnumberresponse;

    private String errormessageresponse;

    private int quantityresponse;

    public String getResultresponse ()
    {
        return resultresponse;
    }

    public void setResultresponse (String resultresponse)
    {
        this.resultresponse = resultresponse;
    }

    public int getPartnumberresponse ()
    {
        return partnumberresponse;
    }

    public void setPartnumberresponse (int partnumberresponse)
    {
        this.partnumberresponse = partnumberresponse;
    }

    public String getErrormessageresponse ()
    {
        return errormessageresponse;
    }

    public void setErrormessageresponse (String errormessageresponse)
    {
        this.errormessageresponse = errormessageresponse;
    }

    public int getQuantityresponse ()
    {
        return quantityresponse;
    }

    public void setQuantityresponse (int quantityresponse)
    {
        this.quantityresponse = quantityresponse;
    }


}

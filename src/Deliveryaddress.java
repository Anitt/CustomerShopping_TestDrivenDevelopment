import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
// model class to store and reterive the delivery address
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Deliveryaddress
{
    private String postalcode;

    private String name;

    private String street;

    private String province;

    private String city;

    public String getPostalcode ()
    {
        return postalcode;
    }

    public void setPostalcode (String postalcode)
    {
        this.postalcode = postalcode;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getStreet ()
    {
        return street;
    }

    public void setStreet (String street)
    {
        this.street = street;
    }

    public String getProvince ()
    {
        return province;
    }

    public void setProvince (String province)
    {
        this.province = province;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }


}

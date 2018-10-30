import javax.xml.bind.annotation.*;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order
{
    private Dealer dealer;

    private Deliveryaddress deliveryaddress;

    private Orderitems orderitems;

    private int partnumber;

    private int quantity;


    public int getPartnumber() {
        return partnumber;
    }

    public void setPartnumber(int partnumber) {
        this.partnumber = partnumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Dealer getDealer ()
    {
        return dealer;
    }

    public void setDealer (Dealer dealer)
    {
        this.dealer = dealer;
    }

    public Deliveryaddress getDeliveryaddress ()
    {
        return deliveryaddress;
    }

    public void setDeliveryaddress (Deliveryaddress deliveryaddress)
    {
        this.deliveryaddress = deliveryaddress;
    }

    public Orderitems getOrderitems ()
    {
        return orderitems;
    }

    public void setOrderitems (Orderitems orderitems)
    {
        this.orderitems = orderitems;
    }



}

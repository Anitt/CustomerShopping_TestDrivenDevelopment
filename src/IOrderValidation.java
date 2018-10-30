public interface IOrderValidation {

    // interface for XML validation
    public enum Ordervalidation
    {
        SUCCESS,
        Failure,
        Invalid_order,
        Invalid_delivery_address,
        Invalid_order_item_list,
        Invalid_order_item_entry
    }

    public Ordervalidation IsXmlValid(String dealerid, String dealeraccesskey,int partNumber , int quantity , String name ,String street ,String city,String province,String postalCode);



}

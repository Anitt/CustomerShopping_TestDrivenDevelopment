public class OrderValidation implements IOrderValidation {
    @Override
    public Ordervalidation IsXmlValid(String dealerid, String dealeraccesskey,int partNumber, int quantity, String name, String street, String city, String province, String postalCode) {

        if (dealervalid(dealerid , dealeraccesskey).equals(Ordervalidation.SUCCESS) && partvalid(partNumber, quantity).equals(Ordervalidation.SUCCESS) && validDeliveryaddress(name,street,city,province,postalCode).equals(Ordervalidation.SUCCESS)){

            return Ordervalidation.SUCCESS ;
        }else {

            return Ordervalidation.Failure ;
        }



//        try {
//            if (!dealerid.isEmpty() && !dealeraccesskey.isEmpty()) {
//                if (partNumber > 0 && quantity > 0) {
//
//                    if (!street.isEmpty() && street != null && !city.isEmpty() && city != null && !province.isEmpty() && province != null && !postalCode.isEmpty() && postalCode != null) {
//
//                        return Ordervalidation.SUCCESS;
//
//                    } else {
//
//                        return Ordervalidation.Invalid_delivery_address;
//                    }
//
//                } else {
//
//                    return Ordervalidation.Invalid_order_item_entry;
//
//                }
//            }else{
//
//                return Ordervalidation.Invalid_order ;
//            }
//        }catch (NullPointerException e){
//
//            return Ordervalidation.Invalid_order_item_entry ;
//        }

    }

    public Ordervalidation dealervalid(String dealerid ,String dealeraccesskey){
        try {
            if (!dealerid.isEmpty() && !dealeraccesskey.isEmpty()) {

                return Ordervalidation.SUCCESS;
            } else {

                return Ordervalidation.Failure;

            }
        }catch (NullPointerException ex){

            return Ordervalidation.Failure ;
        }
    }

    public  Ordervalidation partvalid(int partNumber , int quantity){

        try {
            if (partNumber > 0 && quantity > 0) {

                return Ordervalidation.SUCCESS;
            } else {

                return Ordervalidation.Invalid_order;
            }

        }catch (NullPointerException e)
        {

            return Ordervalidation.Failure  ;
        }
    }

    public  Ordervalidation validDeliveryaddress(String name, String street, String city, String province, String postalCode){
        try {
            if (!street.isEmpty() && street != null && !city.isEmpty() && city != null && !province.isEmpty() && province != null && !postalCode.isEmpty() && postalCode != null) {

                return Ordervalidation.SUCCESS;
            } else {

                return Ordervalidation.Invalid_delivery_address;

            }
        }catch (Exception ex){

            return Ordervalidation.Failure ;

        }
    }




}

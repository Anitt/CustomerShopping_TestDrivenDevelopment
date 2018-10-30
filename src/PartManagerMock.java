public class PartManagerMock implements IPARTMANAGER {
    @Override
    public PartResponse SubmitPartForManufactureAndDelivery(int partNumber, int quantity, Order deliveryAddress) {

        if (partNumber == 1111){

            return PartResponse.OUT_OF_STOCK;

        }else if (partNumber == 2222){

            return PartResponse.NO_LONGER_MANUFACTURED ;
        }else {

            return PartResponse.SUCCESS ;
        }

    }
}

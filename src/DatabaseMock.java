public class DatabaseMock implements IDatabase {

    // Databasemock for validating whether the part number is valid or not

    @Override
    public Partresponsse IsPartNumberValid(int partnumber, int quantity) {

        if (partnumber > 0 && quantity > 0){

            return Partresponsse.Valid ;
        }else
        {

            return Partresponsse.Invalid ;
        }
    }
}

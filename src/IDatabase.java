public interface IDatabase {

    public enum Partresponsse{

        Valid,
        Invalid

    }

    // return whether part numnber is valid or not
    public Partresponsse IsPartNumberValid(int partnumber , int quantity);
}

import java.util.HashMap;

public class SecurityMock implements ISecurity {

    @Override
    public boolean IsDealerAuthorized(String dealerid, String dealeraccesskey) {

        try {
            if (dealerid.equals("FAKEDEALER") || dealeraccesskey.equals("FAKEACCESSKEY")) {

                return false;
            } else if ((!dealerid.isEmpty() || dealerid != null) && (!dealeraccesskey.isEmpty() || dealeraccesskey != null)) {

                return true;

            } else {

                return false;
            }
        }catch (NullPointerException e){
            return false ;

        }catch (NumberFormatException e){

            return false ;
        }
      //  return false ;
    }

}


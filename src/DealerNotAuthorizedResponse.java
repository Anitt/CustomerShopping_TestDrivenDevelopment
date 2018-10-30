import javax.xml.bind.annotation.XmlRootElement;

//model class to store dealernot authorized response
@XmlRootElement
public class DealerNotAuthorizedResponse {

    private String result;

    private String error;
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}

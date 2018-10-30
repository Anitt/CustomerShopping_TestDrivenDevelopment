import com.sun.org.apache.xpath.internal.operations.Or;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

import static org.junit.Assert.fail;

public class MainXMLInputOutput {

    static String Resultvalidation ;
    static String errormessageValidation ;
    static String errorValidation ;
    static int partnumberresponse;
    static int quantitynumberresponse;
    static String parterrormessage;
    static String partresponse;
   static boolean IsAuthenticated = false ;


    public static void main (String args[]){



        try {
            //validation the XML
            XMLvalidation();

            // if validation successfull , dealer authentication function will be called
            if (Resultvalidation.equals("SUCCESS")&& Resultvalidation.equals("SUCCESS")&& Resultvalidation.equals("SUCCESS")){
                //System.out.println(XMLvalidation());
                writetoafile(XMLvalidation());
                //dealer authentication function will be called
                writetoafile(dealerAuthentication());

               // System.out.println(dealerAuthentication());
                if (IsAuthenticated){
                   // dont do anything , this is being takencare inside of the class
                }
            }else{
                //System.out.println(XMLvalidation());
                writetoafile(XMLvalidation());

            }


        } catch (Exception e) {
            System.out.println("Failed to Parse input XML");
            e.printStackTrace();

        }


    }



    public static String XMLvalidation(){
        try {

               // Reading the XML file , do change the file name here .
            LowercaseXMLElementStreamReader lowercaseXMLElementStreamReader = new LowercaseXMLElementStreamReader("src//IncomingOrder.xml", Order.class);
            Order order = (Order) lowercaseXMLElementStreamReader.DeserializeXMLIntoObject();

            OrderValidation orderValidation = new OrderValidation();
            // instanting Author dealer response to set the XML response
            AuthorDealerResponse authorDealerResponse = new AuthorDealerResponse();
            Resultvalidation = String.valueOf(orderValidation.IsXmlValid(order.getDealer().getDealerid(), order.getDealer().getDealeraccesskey(), order.getOrderitems().getItem()[0].getPartnumber(), order.getOrderitems().getItem()[0].getQuantity(), order.getDeliveryaddress().getName(), order.getDeliveryaddress().getStreet(), order.getDeliveryaddress().getCity(), order.getDeliveryaddress().getProvince(), order.getDeliveryaddress().getPostalcode()));
            errorValidation = String.valueOf(orderValidation.partvalid(order.getOrderitems().getItem()[0].getPartnumber(), order.getOrderitems().getItem()[0].getQuantity()));
            errormessageValidation = String.valueOf(orderValidation.validDeliveryaddress(order.getDeliveryaddress().getName(), order.getDeliveryaddress().getStreet(), order.getDeliveryaddress().getCity(), order.getDeliveryaddress().getProvince(), order.getDeliveryaddress().getPostalcode()));
            authorDealerResponse.setResult(Resultvalidation);
            authorDealerResponse.setError(errorValidation);
            authorDealerResponse.setErrormessage(errormessageValidation);

            try {
                java.io.StringWriter sw = new StringWriter();
                JAXBContext jaxbContext = JAXBContext.newInstance(AuthorDealerResponse.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                // output pretty printed
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                jaxbMarshaller.marshal(authorDealerResponse, sw);
                return sw.toString().toLowerCase();


            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
return  "invalid XML , cannot parse" ;
    }
// Authenticating dealer
    public static String dealerAuthentication(){
        try {
            LowercaseXMLElementStreamReader lowercaseXMLElementStreamReader = new LowercaseXMLElementStreamReader("src//IncomingOrder.xml", Order.class);
            Order order = (Order) lowercaseXMLElementStreamReader.DeserializeXMLIntoObject();
            SecurityMock securityMock = new SecurityMock();
           // AuthorDealerResponse authorDealerResponse1 = new AuthorDealerResponse();
            DealerNotAuthorizedResponse dealerNotAuthorizedResponse = new DealerNotAuthorizedResponse();
            if (securityMock.IsDealerAuthorized(order.getDealer().getDealerid(),order.getDealer().getDealeraccesskey())){
                dealerNotAuthorizedResponse.setResult("success");
                dealerNotAuthorizedResponse.setError("Authorized dealer");
                IsAuthenticated = true ;
                for (int i = 0 ; i < order.getOrderitems().getItem().length ; i++) {

                    //System.out.println(DatabaseValidpartmanager(i));
                     writetoafile(DatabaseValidpartmanager(i));

                }

            }else {

                dealerNotAuthorizedResponse.setResult("Failure");
                dealerNotAuthorizedResponse.setError("Unauthorized dealer");
            }

            try {
                java.io.StringWriter sw = new StringWriter();
                JAXBContext jaxbContext = JAXBContext.newInstance(DealerNotAuthorizedResponse.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                // output pretty printed
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                jaxbMarshaller.marshal(dealerNotAuthorizedResponse, sw);
                return sw.toString().toLowerCase();


            } catch (JAXBException e) {
                e.printStackTrace();
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "Failure" ;
    }


    // Validating and submitting to partmanager
    public static String DatabaseValidpartmanager(int i){
        try {

            LowercaseXMLElementStreamReader lowercaseXMLElementStreamReader = new LowercaseXMLElementStreamReader("src//IncomingOrder.xml", Order.class);
            Order order = (Order) lowercaseXMLElementStreamReader.DeserializeXMLIntoObject();
            DatabaseMock databaseMock = new DatabaseMock();
            Itemresponse itemresponse = new Itemresponse();

                partresponse = String.valueOf(databaseMock.IsPartNumberValid(order.getOrderitems().getItem()[i].getPartnumber(), order.getOrderitems().getItem()[i].getQuantity()));
                PartManagerMock partManagerMock = new PartManagerMock();
                parterrormessage = String.valueOf(partManagerMock.SubmitPartForManufactureAndDelivery(order.getOrderitems().getItem()[i].getPartnumber(), order.getOrderitems().getItem()[i].getQuantity(), null));
                if (partresponse.equals("valid")) {

                    itemresponse.setPartnumberresponse(order.getOrderitems().getItem()[i].getPartnumber());
                    itemresponse.setQuantityresponse(order.getOrderitems().getItem()[i].getQuantity());
                    itemresponse.setErrormessageresponse(parterrormessage);


                } else {

                    //   parterrormessage = String.valueOf(partManagerMock.SubmitPartForManufactureAndDelivery(order.getPartnumber(),order.getQuantity(),null));
                    itemresponse.setPartnumberresponse(order.getOrderitems().getItem()[i].getPartnumber());
                    itemresponse.setQuantityresponse(order.getOrderitems().getItem()[i].getQuantity());
                    itemresponse.setResultresponse(partresponse);
                    itemresponse.setErrormessageresponse(parterrormessage);

                }


                try {
                    java.io.StringWriter sw = new StringWriter();
                    JAXBContext jaxbContext = JAXBContext.newInstance(Itemresponse.class);
                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                    // output pretty printed
                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                    jaxbMarshaller.marshal(itemresponse, sw);
                    return sw.toString().toLowerCase();


                } catch (JAXBException e) {
                    e.printStackTrace();
                }


        }catch (Exception ex){

            ex.printStackTrace();
        }
        return "Not valid";

    }
// writing a file
    public static void writetoafile(String xml) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("outresponse.txt",true));
        writer.append("\n");
        writer.write(xml);
        writer.close();


    }
}

package ojan.org.Controller;
import ojan.org.Model.Costumer;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.Timestamp;
import java.util.Date;
@Path("costumer")
public class CostumerService {
    @Path("register")
    @POST
    @Transactional
    public String costumerRegister(JsonObject body){
        String statusRegistration = null;
        Costumer costumer = new Costumer();
        costumer.name = body.getString("name");
        costumer.address = body.getString("address");
        costumer.age = body.getInt("age");
        costumer.status = "in service";
        costumer.persist();
        if (costumer.isPersistent()){
            statusRegistration = "Successful Registration";
        }else {statusRegistration = "Registration Failed";}
        return statusRegistration;
    }
    @Path("checkin")
    @POST
    @Transactional
    public String costumerCheckIn(JsonObject body){
        String name = body.getString("name");
        Date date = new Date();
        Timestamp timeNow = new Timestamp(date.getTime());//2022-11-11 00:00:00
        Integer result = Costumer.update("status = 'in service(checkIn)', checkin = '"+timeNow.toString()+"' where name = '"+name+"'");
        String status = (result >= 1)? "successfully checked in":"failed check-in";
        return status;
    }
    @Path("checkout")
    @POST
    @Transactional
    public String costumerCheckout(JsonObject body){
        String name = body.getString("name");
        Date date = new Date();
        Timestamp timeNow = new Timestamp(date.getTime());
        Integer result = Costumer.update("status = 'served', checkout = '"+timeNow.toString()+"' where name = '"+name+"'");
        String status = (result >= 1)? "successfully checked out":"failed check-out";
        return status;
    }
    @DELETE()
    @Transactional
    public String delete(){
        Integer results = (int) Costumer.deleteAll();
        return results.toString();
    }
}

package ojan.org.Controller;
import ojan.org.Model.Costumer;
import ojan.org.Model.Facilities;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
@Path("facilities")
public class FacilitiesService {
    @GET
    public List<Costumer> getAllData(){
        return Costumer.listAll();
    }
    @POST
    @Transactional
    @Path("{id}")
    public String addFacilities(@PathParam("id")Long id, Facilities facilities){
        String status = null;
        Costumer costumer = Costumer.findById(id);
        costumer.facilities.add(facilities);
        facilities.persist();
        if (facilities.isPersistent()){
            status = "done";
        }else {status="failed add request";}
        return status;
    }
}

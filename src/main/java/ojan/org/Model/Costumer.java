package ojan.org.Model;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "costumers")
public class Costumer extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String name;
    public String address;
    public Integer age;
    public String status;
    public Date checkin;
    public Date checkout;
    @OneToMany
    @JoinColumn(name = "id_costumer")
    public List<Facilities> facilities;
}

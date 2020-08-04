import com.bida.httpconnection.Requests;
import com.bida.httpconnection.domain.Category;
import com.bida.httpconnection.domain.Status;
import com.bida.httpconnection.domain.Tag;

public class Main {

    public static void main(String[] args) throws Exception {

        String[] photos = new String[1];
        photos[0] = "/bida.com";
        Tag[] tags = new Tag[1];
        tags[0] = new Tag("dogs", 0);
        Requests.createRequests().doPostNewPet(1, "Ben", new Category("Labrador", 0), photos, tags, Status.AVAILABLE);
        Requests.createRequests().doPostNewImage(1, "test2x2.bmp");
        Requests.createRequests().doGetPetsByStatus(Status.PENDING);
        Requests.createRequests().doGetByPetID(1);
        Requests.createRequests().doDeletePetByID(1);
        Requests.createRequests().doPutUpdateExistingPet(1, "Ben", new Category("Labrador", 0), photos, tags, Status.PENDING);
        Requests.createRequests().doGetByPetID(1);
        Requests.createRequests().doPostByIdNameStatus(1, "Brabus", Status.AVAILABLE);
        Requests.createRequests().doGetByPetID(1);
    }
}

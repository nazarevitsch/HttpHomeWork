import com.bida.httpconnection.Requests;
import com.bida.httpconnection.domain.Category;
import com.bida.httpconnection.domain.Tag;

public class Main {

    public static void main(String[] args) throws Exception {

        String[] photos = new String[1];
        photos[0] = "/bida.com";
        Tag[] tags = new Tag[1];
        tags[0] = new Tag("dogs", 0);
        new Requests().doPost(1, "Ben", new Category("Labrador", 0), photos, tags);

    }

}

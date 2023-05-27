package simpson.services.dossier.services;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import simpson.services.dossier.user.UserId;

import java.io.Serializable;
import java.util.UUID;

@Dependent
public class UserContext implements Serializable {

    static final UserId USER_ID = new UserId(UUID.fromString("06f4a094-6f4d-49fa-aee5-b7444f359029"));
    private static final long serialVersionUID = -3180897163953722425L;

    @Produces
    public UserId produceUserId() {
        return USER_ID;
    }
}

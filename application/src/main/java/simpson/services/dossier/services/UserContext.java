package simpson.services.dossier.services;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import simpson.services.dossier.user.UserId;

@Dependent
public class UserContext {

    private static final UserId USER_ID = new UserId();

    @Produces
    public UserId produceUserId() {
        return USER_ID;
    }
}

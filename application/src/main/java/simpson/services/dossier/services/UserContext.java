package simpson.services.dossier.services;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import simpson.services.dossier.user.UserId;

import java.util.UUID;

@Dependent
public class UserContext {

    //This is hard coded until userIds are pulled from the securityContext
    private static final UserId USER_ID = new UserId(UUID.fromString("bcc01b76-f8e0-4a41-9e7e-c33b7301c9a1"));

    @Produces
    public UserId produceUserId() {
        return USER_ID;
    }
}

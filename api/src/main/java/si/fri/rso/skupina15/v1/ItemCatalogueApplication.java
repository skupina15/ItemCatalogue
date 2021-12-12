package si.fri.rso.skupina15.v1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "API for items", version = "v1", contact = @Contact(email = "rso@fri.uni-lj.si"), license = @
        License(name = "dev"), description = "API for items."), servers = @Server(url ="http://localhost:8080/v1"))
@ApplicationPath("v1")
public class ItemCatalogueApplication extends Application {
}

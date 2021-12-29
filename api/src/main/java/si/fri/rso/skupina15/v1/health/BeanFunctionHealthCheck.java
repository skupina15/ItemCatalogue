package si.fri.rso.skupina15.v1.health;


import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import si.fri.rso.skupina15.beans.CDI.ItemBean;
import si.fri.rso.skupina15.beans.config.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class BeanFunctionHealthCheck implements HealthCheck {

    @Inject
    private ItemBean itemBean;

    @Override
    public HealthCheckResponse call() {
        QueryParameters query = QueryParameters.query("").build();
        if(itemBean.findAllItems(query) != null){
            return HealthCheckResponse.up(BeanFunctionHealthCheck.class.getSimpleName());
        }
        else{
            return HealthCheckResponse.down(BeanFunctionHealthCheck.class.getSimpleName());
        }
    }
}

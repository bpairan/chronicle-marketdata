package marketdata.example.binding;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Created by bharathi pairan on 22/01/2016.
 */
public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        Names.bindProperties(binder(), System.getProperties());
    }
}

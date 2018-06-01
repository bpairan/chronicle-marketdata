package marketdata.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import marketdata.example.binding.ConfigModule;
import marketdata.example.binding.MainModule;

import java.io.IOException;

/**
 * Main Application entry point
 * <p>
 * Created by bharathi pairan on 22/01/2016.
 */
public class Application {

    public static void main(String[] args) throws IOException {
        Injector injector = Guice.createInjector(new ConfigModule(), new MainModule());
        MarketDataPublisher publisher = injector.getInstance(MarketDataPublisher.class);
        MarketDataConsumer consumer = injector.getInstance(MarketDataConsumer.class);
        new Thread(publisher).start();
        new Thread(consumer).start();
        publisher.close();
        consumer.close();
    }
}

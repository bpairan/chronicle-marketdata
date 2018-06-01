package marketdata.example.binding;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import marketdata.example.BloombergEvent;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.ExcerptTailer;
import net.openhft.lang.model.DataValueClasses;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by bharathi pairan on 22/01/2016.
 */
public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BloombergEvent.class).toProvider(() -> DataValueClasses.newDirectReference(BloombergEvent.class));
        bind(CountDownLatch.class).toProvider(() -> new CountDownLatch(1)).in(Singleton.class);
    }

    @Provides
    @Singleton
    Chronicle chronicleProvider(@Named("chronicle.queue.path") String chroniclePath) throws IOException {
        final Chronicle chronicle = ChronicleQueueBuilder.vanilla(chroniclePath).build();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    chronicle.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return chronicle;

    }

    @Provides
    @Singleton
    ExcerptAppender queueAppenderProvider(Chronicle marketDataQueue) throws IOException {
        return marketDataQueue.createAppender();
    }

    @Provides
    @Singleton
    ExcerptTailer queueTailerProvider(Chronicle marketDataQueue) throws IOException {
        return marketDataQueue.createTailer();
    }
}

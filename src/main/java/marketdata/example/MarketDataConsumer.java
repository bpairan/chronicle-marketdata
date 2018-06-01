package marketdata.example;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.openhft.chronicle.ExcerptTailer;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by bharathi pairan on 22/01/2016.
 */
public class MarketDataConsumer implements Runnable, Closeable {
    private final ExcerptTailer queueTailer;
    private final BloombergEvent bloombergEvent;
    private final int noOfEvents;

    @Inject
    public MarketDataConsumer(ExcerptTailer queueTailer,
                              BloombergEvent bloombergEvent,
                              @Named("no-of-events") int noOfEvents) {
        this.queueTailer = queueTailer;
        this.bloombergEvent = bloombergEvent;
        this.noOfEvents = noOfEvents;
    }


    @Override
    public void close() throws IOException {
        queueTailer.close();
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        int consumedEvents = 0;
        while (consumedEvents != noOfEvents) {
            if (!queueTailer.nextIndex()) {
                continue;
            }
            bloombergEvent.bytes(queueTailer, queueTailer.position());
            queueTailer.skipBytes(bloombergEvent.maxSize());
            queueTailer.finish();
            consumedEvents++;
        }
        System.out.printf("Consumed %d events in %.2f ms %n", consumedEvents, (System.nanoTime() - startTime) / 1e6);
    }
}

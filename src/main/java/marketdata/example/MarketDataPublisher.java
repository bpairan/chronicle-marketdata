package marketdata.example;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ExcerptAppender;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by bharathi pairan on 22/01/2016.
 */
public class MarketDataPublisher implements Runnable, Closeable {
    private final Chronicle marketDataQueue;
    private final ExcerptAppender marketDataAppender;
    private final BloombergEvent bloombergEvent;
    private final int noOfEvents;

    @Inject
    public MarketDataPublisher(Chronicle marketDataQueue,
                               ExcerptAppender marketDataAppender,
                               BloombergEvent bloombergEvent,
                               @Named("no-of-events") int noOfEvents) {
        this.marketDataQueue = marketDataQueue;
        this.marketDataAppender = marketDataAppender;
        this.bloombergEvent = bloombergEvent;
        this.noOfEvents = noOfEvents;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        for (int i = 0; i < noOfEvents; i++) {
            marketDataAppender.startExcerpt();
            bloombergEvent.bytes(marketDataAppender, marketDataAppender.position());
            bloombergEvent.setBid(23.9);
            bloombergEvent.setAsk(21.4);
            bloombergEvent.setBidSize(200);
            bloombergEvent.setAskSize(250);
            bloombergEvent.setSymbol("IBM");
            marketDataAppender.skipBytes(bloombergEvent.maxSize());
            marketDataAppender.finish();
        }
        System.out.printf("Published %d events in %.2f ms%n", noOfEvents, (System.nanoTime() - startTime) / 1e6);

    }


    @Override
    public void close() throws IOException {
        marketDataAppender.close();
    }

}

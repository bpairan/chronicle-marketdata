package marketdata.example;

import junit.framework.TestCase;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.ExcerptTailer;
import net.openhft.chronicle.tools.ChronicleTools;
import net.openhft.lang.model.DataValueClasses;
import net.openhft.lang.model.DataValueGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * Created by bharathi pairan on 06/02/2016.
 */
public class TradeEventTest {
    Logger logger = LoggerFactory.getLogger(TradeEventTest.class);
    static DataValueGenerator dataValueGenerator = new DataValueGenerator();

    static {
        dataValueGenerator.setDumpCode(true);
    }

    Consumer<Class<?>> printClazz = aClass -> {
        logger.info(dataValueGenerator.generateNativeObject(aClass));
    };
    @Test
    public void testCreate() throws Exception {
        TradeEvent writeTradeEvent = DataValueClasses.newDirectReference(TradeEvent0.class);
        TradeEvent readTradeEvent = DataValueClasses.newDirectReference(TradeEvent0.class);
        String path = "build/tmp/TradeEventTest_testCreate";
        ChronicleTools.deleteDirOnExit(path);

        Chronicle chronicle = ChronicleQueueBuilder.vanilla(path).build();
        ExcerptAppender appender = chronicle.createAppender();
        appender.startExcerpt();
        writeTradeEvent.bytes(appender, appender.position());
        writeTradeEvent.setPrice(123.34);
        writeTradeEvent.setSymbol("IBM");
        writeTradeEvent.setSide(Side.BUY);
        appender.skipBytes(writeTradeEvent.maxSize());
        appender.finish();

        ExcerptTailer tailer = chronicle.createTailer();
        while (tailer.nextIndex()) ;
        readTradeEvent.bytes(tailer, tailer.position());
        Assert.assertEquals(Side.BUY, readTradeEvent.getSide());
        Assert.assertEquals(123.34, readTradeEvent.getPrice(), 0.001);
        Assert.assertEquals("IBM", readTradeEvent.getSymbol());

    }

    @Test
    public void printGrouping() {
        //TradeEvent tradeEvent = DataValueClasses.newDirectInstance(TradeEvent.class);
        printClazz.accept(TradeEvent.class);

    }

}
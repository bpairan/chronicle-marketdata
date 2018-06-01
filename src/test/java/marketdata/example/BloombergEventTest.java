package marketdata.example;

import junit.framework.TestCase;
import net.openhft.lang.model.DataValueClasses;
import org.junit.Assert;

/**
 * Created by bharathi pairan on 22/01/2016.
 */
public class BloombergEventTest extends TestCase {

    public void testNewInstanceCreation() throws Exception {
        BloombergEvent bEvent = DataValueClasses.newDirectInstance(BloombergEvent.class);
        bEvent.setBid(23.9);
        bEvent.setAsk(21.4);
        bEvent.setBidSize(200);
        bEvent.setAskSize(250);
        bEvent.setSymbol("IBM");
        Assert.assertEquals(23.9, bEvent.getBid(), 0.01);
        Assert.assertEquals(21.4, bEvent.getAsk(), 0.01);
        Assert.assertEquals("IBM", bEvent.getSymbol());
    }
}
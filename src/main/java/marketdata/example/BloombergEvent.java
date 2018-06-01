package marketdata.example;

import net.openhft.lang.model.Byteable;
import net.openhft.lang.model.constraints.MaxSize;

/**
 * Bloomberg market data representation
 * <p>
 * Created by bharathi pairan on 22/01/2016.
 */
public interface BloombergEvent extends Byteable {

    CharSequence getSymbol();

    void setSymbol(@MaxSize(10) CharSequence symbol);

    double getBid();

    void setBid(double bid);

    double getAsk();

    void setAsk(double ask);

    long getBidSize();

    void setBidSize(long bidSize);

    long getAskSize();

    void setAskSize(long askSize);



}

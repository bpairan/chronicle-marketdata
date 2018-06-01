package marketdata.example;

import net.openhft.lang.model.Byteable;
import net.openhft.lang.model.constraints.Group;
import net.openhft.lang.model.constraints.MaxSize;

/**
 * Created by bharathi pairan on 06/02/2016.
 */
public interface TradeEvent extends Byteable {

    @Group(1)
    CharSequence getSymbol();

    void setSymbol(@MaxSize(10) CharSequence symbol);

    @Group(2)
    double getPrice();

    void setPrice(double price);

    Side getSide();

    void setSide(Side side);

}

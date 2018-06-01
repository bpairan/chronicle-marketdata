package marketdata.example;

import net.openhft.lang.io.Bytes;
import net.openhft.lang.io.serialization.BytesMarshallable;
import net.openhft.lang.model.Byteable;
import net.openhft.lang.model.Copyable;

import static net.openhft.lang.Compare.calcLongHashCode;
import static net.openhft.lang.Compare.isEqual;

public class TradeEvent0 implements TradeEvent, BytesMarshallable, Byteable, Copyable<marketdata.example.TradeEvent> {
    private static final int PRICE = 0;
    private static final int SIDE = 8;
    private static final int SYMBOL = 10;


    private Bytes _bytes;
    private long _offset;


    public void setPrice(double $) {
        _bytes.writeDouble(_offset + PRICE, $);
    }

    public double getPrice() {
        return _bytes.readDouble(_offset + PRICE);
    }


    public void setSide(marketdata.example.Side $) {
        _bytes.writeShort(_offset + SIDE, $.value);
    }

    public marketdata.example.Side getSide() {
        return marketdata.example.Side.valueOf(_bytes.readShort(_offset + SIDE));
    }


    public void setSymbol(java.lang.CharSequence $) {
        _bytes.writeUTFΔ(_offset + SYMBOL, 10, $);
    }

    public java.lang.CharSequence getSymbol() {
        return _bytes.readUTFΔ(_offset + SYMBOL);
    }

    @Override
    public void copyFrom(marketdata.example.TradeEvent from) {
        setPrice(from.getPrice());
        setSide(from.getSide());
        setSymbol(from.getSymbol());
    }

    @Override
    public void writeMarshallable(Bytes out) {
        {
            out.writeDouble(getPrice());
        }
        {
            out.writeShort(getSide().value);
        }
        {
            long pos = out.position();
            out.writeUTFΔ(getSymbol());
            long newPos = pos + 10;
            out.zeroOut(out.position(), newPos);
            out.position(newPos);
        }
    }

    @Override
    public void readMarshallable(Bytes in) {
        setPrice(in.readDouble());
        setSide(Side.valueOf(in.readShort()));
        setSymbol(in.readUTFΔ());
    }

    @Override
    public void bytes(Bytes bytes, long offset) {
        this._bytes = bytes;
        this._offset = offset;
    }

    @Override
    public Bytes bytes() {
        return _bytes;
    }

    @Override
    public long offset() {
        return _offset;
    }

    @Override
    public int maxSize() {
        return 22;
    }

    public int hashCode() {
        long lhc = longHashCode();
        return (int) ((lhc >>> 32) ^ lhc);
    }

    public long longHashCode() {
        return ((calcLongHashCode(getPrice())) * 10191 +
                calcLongHashCode(getSide())) * 10191 +
                calcLongHashCode(getSymbol());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradeEvent0)) return false;
        TradeEvent0 that = (TradeEvent0) o;

        if (!isEqual(getPrice(), that.getPrice())) return false;
        if (!isEqual(getSide(), that.getSide())) return false;
        if (!isEqual(getSymbol(), that.getSymbol())) return false;
        return true;
    }

    public String toString() {
        if (_bytes == null) return "bytes is null";
        StringBuilder sb = new StringBuilder();
        sb.append("TradeEvent0{ ");
        sb.append("price= ").append(getPrice());
        sb.append(", ")
        ;
        sb.append("side= ").append(getSide());
        sb.append(", ")
        ;
        sb.append("symbol= ").append(getSymbol());
        sb.append(" }");
        return sb.toString();
    }
}
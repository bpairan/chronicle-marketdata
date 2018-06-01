package marketdata.example;

/**
 * Created by bharathi pairan on 06/02/2016.
 */
public enum Side {
    BUY((short) 1),
    SELL((short) 2);

    final short value;

    Side(short value) {
        this.value = value;
    }

    public static Side valueOf(short value) {
        for (Side side: Side.values()) {
            if (side.value == value) {
                return side;
            }
        }
        return null;
    }
}

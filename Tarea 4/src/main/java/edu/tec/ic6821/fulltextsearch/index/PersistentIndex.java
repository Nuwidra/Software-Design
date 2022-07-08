package edu.tec.ic6821.fulltextsearch.index;

public interface PersistentIndex  extends Index {
    void persist();
    boolean restore();
}

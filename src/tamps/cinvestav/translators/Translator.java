package tamps.cinvestav.translators;

import tamps.cinvestav.records.DataRecord;

public interface Translator<T> {
    public DataRecord translate(T elemento);
}

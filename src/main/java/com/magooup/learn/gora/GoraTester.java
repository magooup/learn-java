package com.magooup.learn.gora;

import org.apache.avro.Schema;
import org.apache.gora.hbase.store.HBaseStore;
import org.apache.gora.persistency.Persistent;
import org.apache.gora.persistency.Tombstone;
import org.apache.gora.persistency.impl.PersistentBase;
import org.apache.gora.store.DataStore;

/**
 * Created by zhiyong.ma on 2016/2/25.
 */
public class GoraTester {

    public static void main(String[] args) {

        DataStore<String, Demo> store = new HBaseStore<String, Demo>();

    }

    class Demo extends PersistentBase {

        @Override
        public int getFieldsCount() {
            return 0;
        }

        @Override
        public Tombstone getTombstone() {
            return null;
        }

        @Override
        public Persistent newInstance() {
            return null;
        }

        @Override
        public Schema getSchema() {
            return null;
        }

        @Override
        public Object get(int i) {
            return null;
        }

        @Override
        public void put(int i, Object o) {

        }
    }
}

package com.magooup.learn;


import org.apache.hadoop.io.ArrayFile;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Learner implements Cloneable {

    public static void main(String[] args) {
        ArrayFile.Writer arrayWriter;
    }

    class Demo implements Writable {

        @Override
        public void write(DataOutput dataOutput) throws IOException {

        }

        @Override
        public void readFields(DataInput dataInput) throws IOException {

        }
    }

}



package com.victor.guava.utils;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.util.Calendar;

public class ObjectsExample {

    public static void main(String[] args) {
        Guava guava = new Guava("V01", Calendar.getInstance());
        System.out.println(guava);
        System.out.println(guava.hashCode());

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR, 2);
        Guava guava2 = new Guava("V02", instance);
        System.out.println(guava.compareTo(guava2));

    }

    static class Guava implements Comparable<Guava> {

        private String version;

        private Calendar releaseDate;

        public Guava(String version, Calendar releaseDate) {
            this.version = version;
            this.releaseDate = releaseDate;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;

            Guava guava = (Guava) obj;
            return Objects.equal(this.version, guava.version)
                    && Objects.equal(this.releaseDate, guava.releaseDate);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("version", this.version)
                    .add("releaseDate", this.releaseDate)
                    .toString();
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(version, releaseDate);
        }

        @Override
        public int compareTo(Guava o) {
            return ComparisonChain.start().compare(this.version, o.version)
                    .compare(this.releaseDate, o.releaseDate).result();
        }
    }
}

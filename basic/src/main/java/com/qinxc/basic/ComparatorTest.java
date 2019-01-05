package com.qinxc.basic;

import java.util.*;

/**
 * @author qxc
 * @date 2018/10/11.
 */
public class ComparatorTest {

    public static void main(String[] args) {
//        SortedSet<BucketSortBeanFloat> floatRankSet = new TreeSet<>(Comparator.comparingDouble(
//                o -> o.value
//        ));
        SortedSet<BucketSortBeanFloat> floatRankSet = new TreeSet<>();

        float[] scores = {0.1f, 0.2f, 0.3f, 0.2f, 1.8f, -0.5f, 0.0f, 0.1f};
        for (int i = 0; i < scores.length; i++) {
            floatRankSet.add(new BucketSortBeanFloat(scores[i], 1));
        }
        System.out.println(floatRankSet);

        Map<Float, Integer> map = new HashMap<>();
        map.put(1.0f, 1);
        Integer cnt = map.get(1.0f);

        map.put(1.0f,++cnt);
        System.out.println(map.get(1.0f));

    }
}

class BucketSortBeanFloat implements Comparable<BucketSortBeanFloat> {
    public float value;
    public int cnt;

    public BucketSortBeanFloat(float value, int cnt) {
        this.value = value;
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "BucketSortBena{" +
                "value=" + value +
                ", cnt=" + cnt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BucketSortBeanFloat that = (BucketSortBeanFloat) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(BucketSortBeanFloat o) {
        if (this.value > o.value)
            return 1;
        else
            return -1;
    }
}

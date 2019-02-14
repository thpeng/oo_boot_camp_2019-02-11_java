package rectangle;

import compare.MyComparable;

import java.util.List;

public interface ShapeSorter {

    static <T extends MyComparable<T>> T max(List<T> comparables) {
        T max = null;
        for (int i = 0; i < comparables.size(); i++) {
            if(max == null){
                max = comparables.get(i);
            }
            if (i+1 >= comparables.size()) {
                return max;
            }
            int result = max.compare(comparables.get(i));
            System.out.println(result);
            if (max == null || result < 0) {
                max = comparables.get(i);
            }
        }
        return null;
    }
}

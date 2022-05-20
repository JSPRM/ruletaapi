package com.ibm.academia.ruletaapi.utils;

import java.util.List;
import java.util.Optional;

public class ListUtils {
    public static <T> Optional<T> last(List<T> list) {
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(list.size() - 1));
    }
}
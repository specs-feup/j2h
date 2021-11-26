/**
 * Copyright 2016 SPeCS.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. under the License.
 */

package pt.up.fe.specs.j2h.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import pt.up.fe.specs.j2h.list.affine.HListAffineDouble;
import pt.up.fe.specs.j2h.list.affine.HListAffineInt;
import pt.up.fe.specs.j2h.list.affine.HListAffineLong;
import pt.up.fe.specs.j2h.list.iterator.HListIteratorSimple;
import pt.up.fe.specs.j2h.list.numbers.HListInt;
import pt.up.fe.specs.j2h.list.numbers.HListInt32;
import pt.up.fe.specs.j2h.list.string.HString;
import pt.up.fe.specs.j2h.list.util.HListJava;
import pt.up.fe.specs.j2h.utils.TriFunction;
import pt.up.fe.specs.j2h.utils.iterators.BiFunctionIterator;
import pt.up.fe.specs.j2h.utils.iterators.FunctionIterator;
import pt.up.fe.specs.j2h.utils.iterators.TriFunctionIterator;

public interface ListFactory {

    static <T> HList<T> empty() {
	return new HListJava<>(Collections.emptyList());
    }

    /**
     * Creates a finite list, backed by a Java ArrayList.
     * 
     * @param elements
     * @return
     */
    @SafeVarargs
    static <T> HList<T> create(T... elements) {
	return new HListJava<>(Arrays.asList(elements));
    }

    /**
     * Creates a finite list from the given list.
     * 
     * @param elements
     * @return
     */
    static <T> HList<T> create(List<T> elements) {
	return new HListJava<>(elements);
    }

    static HListInt32 createInt(int base, int step, int size) {
	return HListInt32.createNative(new HListAffineInt(base, step, size));
    }

    static HListInt createLong(long base, long step, int size) {
	return HListInt.createNative(new HListAffineLong(base, step, size));
    }

    static HList<Double> createDouble(double base, double step, int size) {
	return new HListAffineDouble(base, step, size);
    }

    static HListInt32 createInfInt(int base, int step) {
	return HListInt32.createNative(new HListAffineInt(base, step));
    }

    static HListInt createInfLong(long base, long step) {
	return HListInt.createNative(new HListAffineLong(base, step));
    }

    static HString createString(List<Character> chars) {
	return HString.create(chars);
    }

    static HString createString(String string) {
	return createString(string.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
    }

    static HString createString(Supplier<Iterator<Character>> chars) {
	return HString.create(chars, false);
    }

    static HString createStringInf(Supplier<Iterator<Character>> chars) {
	return HString.create(chars, true);
    }

    static <T, R> HList<R> comp(Function<T, R> function, HList<T> list,
	    List<Function<T, Boolean>> predicates) {

	Supplier<Iterator<R>> supplier = () -> new FunctionIterator<>(function, list, predicates);

	return new HListIteratorSimple<>(supplier, list.isInf());
    }

    static <T, U, R> HList<R> comp(BiFunction<T, U, R> function, HList<T> list1, HList<U> list2,
	    List<BiFunction<T, U, Boolean>> predicates) {

	Supplier<Iterator<R>> supplier = () -> new BiFunctionIterator<>(function, list1, list2, predicates);

	boolean isInf = list1.isInf() || list2.isInf();
	return new HListIteratorSimple<>(supplier, isInf);
    }

    static <T1, T2, T3, R> HList<R> comp(TriFunction<T1, T2, T3, R> function, HList<T1> list1, HList<T2> list2,
	    HList<T3> list3, List<TriFunction<T1, T2, T3, Boolean>> predicates) {

	Supplier<Iterator<R>> supplier = () -> new TriFunctionIterator<>(function, list1, list2, list3, predicates);

	boolean isInf = list1.isInf() || list2.isInf() || list3.isInf();
	return new HListIteratorSimple<>(supplier, isInf);
    }

}

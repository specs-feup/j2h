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

package pt.up.fe.specs.j2h.list.affine;

import java.util.function.Function;

import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.prelude.classes.HEnum;

public class HListAffineEnum<T extends HEnum<T>> extends HListAffine<T> {

    private final int step;
    private final int absStep;
    private final Function<T, T> nextFunc;

    public HListAffineEnum(T first, T second) {
	this(first, second, -1);
    }

    public HListAffineEnum(T first, T second, int size) {
	this(first, buildStep(first, second), size);
    }

    private HListAffineEnum(T first, int step, int size) {
	super(first, null, size);

	this.step = step;

	// Get step signal
	boolean positive = step > 0;

	absStep = Math.abs(step);
	nextFunc = getFunction(positive);
    }

    private static <T extends HEnum<T>> int buildStep(T first, T second) {
	// Convert both to Int and subtract
	return second.fromEnum().getNumber().intValue() -
		first.fromEnum().getNumber().intValue();
    }

    public static <T extends HEnum<T>> HList<T> inf(T base) {
	return new HListAffineEnum<>(base, base.succ());
    }

    public static <T extends HEnum<T>> HList<T> inf(T first, T second) {
	return new HListAffineEnum<>(first, second);
    }

    public static <T extends HEnum<T>> HList<T> range(T first, T last) {
	return range(first, first.succ(), last);
    }

    public static <T extends HEnum<T>> HList<T> range(T first, T second, T last) {
	int start = first.fromEnum().getNumber().intValue();
	int end = last.fromEnum().getNumber().intValue();

	// If negative, return empty range
	// if (end - start < 0) {
	// return ListFactory.empty();
	// }

	int step = buildStep(first, second);

	// Calculate size
	int total = end - start + step;
	int size = Math.abs(total / step);

	return new HListAffineEnum<>(first, second, size);
    }

    @Override
    protected T nextIndex(int index) {
	// Start at base
	T current = getBase();

	// Advance step * index
	for (int i = 0; i < absStep * index; i++) {
	    current = nextFunc.apply(current);
	}

	return current;
	// IntStream.range(0, step*index).forEach( i -> );
	// return base + step * index;
    }

    private Function<T, T> getFunction(boolean positive) {
	if (positive) {
	    return value -> value.succ();
	}

	return value -> value.pred();
    }

    @Override
    public HList<T> subList(int fromIndex, int toIndex) {
	// T newBase = getBase() + getStep() * fromIndex;
	T newBase = nextIndex(fromIndex);
	int newSize = toIndex - fromIndex;
	return new HListAffineEnum<>(newBase, step, newSize);
    }

}

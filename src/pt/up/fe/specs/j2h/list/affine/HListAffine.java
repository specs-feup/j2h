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

import java.math.BigInteger;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.ListUtils;
import pt.up.fe.specs.j2h.prelude.classes.Integral;

public abstract class HListAffine<N> extends HList<N> {

    private final N base;
    private final N step;
    private final int size;

    public HListAffine(N base, N step) {
	this(base, step, -1);
    }

    public HListAffine(N base, N step, int size) {
	super(SizeType.fastOrInfinite(size == -1));
	this.base = base;
	this.step = step;
	this.size = size;
    }

    /*
     * Static Constructors
     */

    public static HList<BigInteger> New(BigInteger base, BigInteger step, int size) {
	return new HListAffineBigInteger(base, step, size);
    }

    public static <N extends Number, T extends Integral<N, T>> HList<T> New(T base, T step, int size) {
	return new HListAffineIntegral<>(base, step, size);
    }

    /*
     * Internal methods
     */

    protected N getBase() {
	return base;
    }

    public N getStep() {
	return step;
    }

    // protected abstract HListAffine<N> newInstance(N base, N step, int size);

    // protected HListAffine(AffineGenerator<T> generator) {
    // this(generator, size, false);
    // }

    // protected HListAffine(Function<Integer, T> generator) {
    // this(generator, -1, true);
    // }

    // private HListAffine(AffineGenerator<T> generator) {
    // super(SizeType.fastOrInfinite(generator.isInf()));
    // this.generator = generator;
    // // size = size;
    // }

    /**
     * Implements the function "base + step*index"
     * 
     * @param base
     * @param step
     * @param size
     * @return
     */
    protected abstract N nextIndex(int index);

    @Override
    public N get(int index) {
	if (!isInf() && index >= size) {
	    throw new JavaHaskellException("Index '" + index + "' cannot access list of size '" + size + "'");
	}
	return nextIndex(index);
	// base + step*index
	// return base.plus(step.times(index));
	// return new HLong(base.getNumber() + step.getNumber() * index);
	//
	// return generator.nextIndex(base, step, index);
    }

    @Override
    public int size() {
	if (isInf()) {
	    return ListUtils.infListSize();
	}

	return size;
    }

    /**
     * Implements a new list with: <br>
     * - newBase = base + step*fromIndex <br>
     * - newStep = step <br>
     * - newSize = toIndex - fromIndex
     * 
     * <p>
     * toIndex is exclusive
     */
    @Override
    public abstract HList<N> subList(int fromIndex, int toIndex);

}

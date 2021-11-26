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

package pt.up.fe.specs.j2h.prelude.data;

import java.math.RoundingMode;
import java.util.Arrays;

import com.google.common.math.IntMath;

import pt.up.fe.specs.j2h.list.affine.HListAffineInt;
import pt.up.fe.specs.j2h.list.numbers.HListInt32;
import pt.up.fe.specs.j2h.prelude.AJavaNum;
import pt.up.fe.specs.j2h.prelude.classes.Integral;
import pt.up.fe.specs.util.exceptions.NotImplementedException;

public class HInt32 extends AJavaNum<Integer> implements Integral<Integer, HInt32>, HInt32Static {

    public static final HInt32 ZERO = new HInt32(0);
    public static final HInt32 ONE = new HInt32(1);

    public static final HInt32Static STATIC = HInt32.ZERO;

    public HInt32(Integer integer) {
	super(integer);
    }

    /*
     * Java support
     */

    public static HInt32 create(Number number) {
	return HInt32.ZERO.fromNumber(number);
    }

    @Override
    public HInt32 zero() {
	return HInt32.ZERO;
    }

    @Override
    public HInt32 one() {
	return HInt32.ONE;
    }

    @Override
    public HInt32 getThis() {
	return this;
    }

    /*
     * Eq
     */
    @Override
    public HListInt32 list() {
	return HListInt32.create(Arrays.asList(this));
    }

    /*
     * Haskell methods
     */

    @Override
    public HInt32 fromNumber(Number number) {
	if (number instanceof Integer) {
	    return new HInt32((Integer) number);
	}

	return new HInt32(number.intValue());
    }

    @Override
    public Rational toRational() {
	// TODO: When rational is implemented
	throw new NotImplementedException(getClass());
    }

    @Override
    public HInt32 plus(Integer a) {
	return new HInt32(getNumber() + a);
    }

    @Override
    public HInt32 minus(Number a) {
	return new HInt32(getNumber() - a.intValue());
    }

    @Override
    public HInt32 times(Integer a) {
	return new HInt32(getNumber() * a);
    }

    @Override
    public HInt32 negate() {
	return new HInt32(getNumber().intValue() * -1);
    }

    @Override
    public HInt32 abs() {
	return new HInt32(Math.abs(getNumber().intValue()));
    }

    @Override
    public HInt32 signum() {
	int value = getNumber().intValue();
	if (value > 0) {
	    return new HInt32(1);
	}

	if (value < 0) {
	    return new HInt32(-1);
	}

	return new HInt32(0);
    }

    @Override
    public int compareTo(Integer a) {
	return getNumber().compareTo(a);
    }

    @Override
    public HInt32 maxBound() {
	return new HInt32(Integer.MAX_VALUE);
    }

    @Override
    public HInt32 minBound() {
	return new HInt32(Integer.MIN_VALUE);
    }

    @Override
    public HInt32 quot(Number a) {
	return new HInt32(getNumber() / a.intValue());
    }

    @Override
    public HInt32 rem(Number a) {
	return new HInt32(getNumber() % a.intValue());
    }

    @Override
    public HInt32 div(Number a) {
	return new HInt32(IntMath.divide(getNumber(), a.intValue(), RoundingMode.FLOOR));
    }

    @Override
    public HInt32 mod(Number a) {
	return new HInt32(IntMath.mod(getNumber(), a.intValue()));
    }

    /*
     * Enum
     */
    @Override
    public HListInt32 enumFrom() {
	return HListInt32.createNative(new HListAffineInt(getNumber(), 1));
	// return HListInt.create(Integral.super.enumFrom());
    }
}

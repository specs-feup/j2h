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

import com.google.common.math.LongMath;

import pt.up.fe.specs.j2h.list.affine.HListAffineLong;
import pt.up.fe.specs.j2h.list.numbers.HListInt;
import pt.up.fe.specs.j2h.prelude.AJavaNum;
import pt.up.fe.specs.j2h.prelude.classes.Integral;
import pt.up.fe.specs.util.exceptions.NotImplementedException;

public class HInt extends AJavaNum<Long> implements Integral<Long, HInt>, HIntStatic {

    public static final HInt ZERO = new HInt(0l);
    public static final HInt ONE = new HInt(1l);

    public static final HIntStatic STATIC = HInt.ZERO;

    public HInt(Number integer) {
	super(integer instanceof Long ? (Long) integer : integer.longValue());
    }

    /*
     * Java support
     */

    public static HInt create(Number number) {
	return HInt.ZERO.fromNumber(number);
    }

    @Override
    public HInt zero() {
	return HInt.ZERO;
    }

    @Override
    public HInt one() {
	return HInt.ONE;
    }

    @Override
    public HInt getThis() {
	return this;
    }

    /*
     * Eq
     */
    @Override
    public HListInt list() {
	return HListInt.create(this);
    }

    /*
     * Haskell methods
     */

    @Override
    public HInt fromNumber(Number number) {
	if (number instanceof Long) {
	    return new HInt((Long) number);
	}

	return new HInt(number.longValue());
    }

    @Override
    public Rational toRational() {
	// TODO: When rational is implemented
	throw new NotImplementedException(getClass());
    }

    @Override
    public HInt plus(Long a) {
	return new HInt(getNumber() + a);
    }

    @Override
    public HInt minus(Number a) {
	return new HInt(getNumber() - a.longValue());
    }

    @Override
    public HInt times(Long a) {
	return new HInt(getNumber() * a);
    }

    @Override
    public HInt negate() {
	return new HInt(getNumber().longValue() * -1);
    }

    @Override
    public HInt abs() {
	return new HInt(Math.abs(getNumber().longValue()));
    }

    @Override
    public HInt signum() {
	long value = getNumber().longValue();
	if (value > 0) {
	    return new HInt(1l);
	}

	if (value < 0) {
	    return new HInt(-1l);
	}

	return new HInt(0l);
    }

    @Override
    public int compareTo(Long a) {
	return getNumber().compareTo(a);
    }

    @Override
    public HInt maxBound() {
	return new HInt(Long.MAX_VALUE);
    }

    @Override
    public HInt minBound() {
	return new HInt(Long.MIN_VALUE);
    }

    @Override
    public HInt quot(Number a) {
	return new HInt(getNumber() / a.longValue());
    }

    @Override
    public HInt rem(Number a) {
	return new HInt(getNumber() % a.longValue());
    }

    @Override
    public HInt div(Number a) {
	return new HInt(LongMath.divide(getNumber(), a.longValue(), RoundingMode.FLOOR));
    }

    @Override
    public HInt mod(Number a) {
	return new HInt(LongMath.mod(getNumber(), a.longValue()));
    }

    /*
     * Enum
     */
    @Override
    public HListInt enumFrom() {
	return HListInt.createNative(new HListAffineLong(getNumber(), 1l));
	// return HListInt.create(Integral.super.enumFrom());
    }
}

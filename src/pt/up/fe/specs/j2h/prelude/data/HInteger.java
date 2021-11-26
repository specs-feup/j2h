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

import java.math.BigInteger;

import pt.up.fe.specs.j2h.prelude.AJavaNum;
import pt.up.fe.specs.j2h.prelude.classes.Bounded;
import pt.up.fe.specs.j2h.prelude.classes.Integral;
import pt.up.fe.specs.util.exceptions.NotImplementedException;

public class HInteger extends AJavaNum<BigInteger> implements Integral<BigInteger, HInteger>, Bounded<HInteger> {

    public static final HInteger ZERO = new HInteger(BigInteger.ZERO);
    public static final HInteger ONE = new HInteger(BigInteger.ONE);

    public HInteger(BigInteger integer) {
	super(integer);
    }

    public HInteger(long val) {
	this(BigInteger.valueOf(val));
    }

    /*
     * Java support
     */

    public static HInteger create(Number number) {
	return HInteger.ZERO.fromNumber(number);
    }

    @Override
    public HInteger zero() {
	return HInteger.ZERO;
    }

    @Override
    public HInteger one() {
	return HInteger.ONE;
    }

    @Override
    public HInteger getThis() {
	return this;
    }

    /*
     * Haskell methods
     */

    @Override
    public Rational toRational() {
	// TODO: When rational is implemented
	throw new NotImplementedException(getClass());
    }

    @Override
    public HInteger plus(BigInteger n) {
	return new HInteger(getNumber().add(n));
    }

    @Override
    public HInteger minus(Number n) {
	return new HInteger(getNumber().subtract(BigInteger.valueOf(n.longValue())));
    }

    @Override
    public HInteger times(BigInteger n) {
	return new HInteger(getNumber().multiply(n));
    }

    @Override
    public HInteger fromNumber(Number a) {
	if (a instanceof BigInteger) {
	    return new HInteger((BigInteger) a);
	}

	return new HInteger(a.longValue());

    }

    @Override
    public int compareTo(BigInteger a) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public HInteger negate() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HInteger abs() {
	return new HInteger(getNumber().abs());
    }

    @Override
    public HInteger signum() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HInteger minBound() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HInteger maxBound() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HInteger quot(Number a) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HInteger rem(Number a) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HInteger div(Number a) {
	BigInteger value = fromNumber(a).getNumber();
	return new HInteger(getNumber().divide(value));
    }

    @Override
    public HInteger mod(Number a) {
	if (a instanceof BigInteger) {
	    return new HInteger(getNumber().mod((BigInteger) a));
	}

	return new HInteger(getNumber().mod(BigInteger.valueOf(a.longValue())));
    }

}

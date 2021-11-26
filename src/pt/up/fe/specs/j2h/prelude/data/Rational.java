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

import pt.up.fe.specs.j2h.tuple.Tuple2;

public class Rational extends Ratio<BigInteger, HInteger, Rational> {

    public Rational(HInteger numerator, HInteger denominator) {
	super(numerator, denominator);
    }

    @Override
    public Rational ratio(HInteger numerator, HInteger denominator) {
	return new Rational(numerator, denominator);
    }

    @Override
    public Tuple2<HInteger, Ratio<BigInteger, HInteger, Rational>> properFraction() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Rational toRational() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> plus(BigInteger n) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> minus(Number n) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> times(BigInteger n) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> fromNumber(Number a) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int compareTo(BigInteger a) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> fromInteger(HInteger a) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> fromInteger(BigInteger a) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> zero() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> one() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public BigInteger getNumber() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> plus(Ratio<BigInteger, HInteger, Rational> a) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> minus(Ratio<BigInteger, HInteger, Rational> a) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> times(Ratio<BigInteger, HInteger, Rational> a) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> negate() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> abs() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> signum() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> getThis() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int compareTo(Ratio<BigInteger, HInteger, Rational> o) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> fdiv(Ratio<BigInteger, HInteger, Rational> a) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Ratio<BigInteger, HInteger, Rational> fromRational(Rational a) {
	// TODO Auto-generated method stub
	return null;
    }

    // public static final Rational STATIC = new Rational(HInteger.ZERO, HInteger.ONE);

}

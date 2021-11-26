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

package pt.up.fe.specs.j2h.list.numbers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.util.HListJava;
import pt.up.fe.specs.j2h.list.util.HListWrapper;
import pt.up.fe.specs.j2h.prelude.data.HInteger;

/**
 * A list of ints, with a list based on BigInteger, to avoid the cost of translating all Integers to HLong.
 * 
 * @author João Bispo
 *
 */
class HListIntegerNative extends HListInteger {
    private final HList<BigInteger> list;
    private final boolean isWrapperList;

    public HListIntegerNative(HList<BigInteger> ints) {
	super(ints.getSizeType());
	list = ints;

	isWrapperList = NumberListUtils.isValidWrapper(ints, HInteger.class);
    }

    @Override
    protected Class<HInteger> getElementClass() {
	return HInteger.class;
    }

    public HListIntegerNative(List<BigInteger> ints) {
	this(new HListJava<>(ints));
    }

    public HListIntegerNative(BigInteger... ints) {
	this(Arrays.asList(ints));
    }

    public HListIntegerNative(HInteger... elements) {
	this(Arrays.stream(elements)
		.map(hint -> hint.getNumber())
		.collect(Collectors.toList()));
    }

    static HListIntegerNative create(HList<HInteger> list) {
	// If already an HListIntNative, just return list
	// if (list instanceof HListIntNative) {
	// return (HListIntNative) list;
	// }

	// Create new Wrapper around HInteger
	return new HListIntegerNative(new HListWrapper<>(HInteger.class, list, hint -> hint.getNumber()));
    }

    /*
     * Constructors
     */
    @Override
    public HListInteger nativeList(List<? extends Number> list) {
	return new HListIntegerNative(list.stream()
		.map(number -> number instanceof BigInteger ? (BigInteger) number
			: BigInteger.valueOf(number.longValue()))
		.collect(Collectors.toList()));
    }

    /*
     * Abstract class contract
     */
    @Override
    public HListInteger getThis() {
	return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public HInteger get(int index) {
	// If a wrapper class, bypass the conversion
	if (isWrapperList) {
	    return ((HListWrapper<HInteger, BigInteger>) list).getOriginal(index);
	}

	return new HInteger(list.get(index));
    }

    @Override
    public int size() {
	return list.size();
    }

    @Override
    public List<BigInteger> toJavaList() {
	return list;
    }

    /*
     * Performance overrides
     */

    @Override
    public HListInteger take(int amount) {
	// Limit amount to size, if list is not infinite
	if (!isInf() && amount > size()) {
	    amount = size();
	}

	return new HListIntegerNative(list.subList(0, amount));
    }

    @Override
    public HInteger sum() {
	return new HInteger(list.stream().reduce(BigInteger.ZERO, (acc, next) -> acc.add(next)));
    }

}

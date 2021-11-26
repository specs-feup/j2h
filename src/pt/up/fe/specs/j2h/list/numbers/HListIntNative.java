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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.util.HListJava;
import pt.up.fe.specs.j2h.list.util.HListWrapper;
import pt.up.fe.specs.j2h.prelude.data.HInt;

/**
 * A list of ints, with a list based on Long, to avoid the cost of translating all Integers to HLong.
 * 
 * @author João Bispo
 *
 */
class HListIntNative extends HListInt {
    private final HList<Long> list;
    private final boolean isWrapperList;

    public HListIntNative(HList<Long> ints) {
	super(ints.getSizeType());
	list = ints;

	isWrapperList = NumberListUtils.isValidWrapper(ints, HInt.class);
    }

    @Override
    protected Class<HInt> getElementClass() {
	return HInt.class;
    }

    public HListIntNative(List<Long> ints) {
	this(new HListJava<>(ints));
    }

    public HListIntNative(Long... ints) {
	this(Arrays.asList(ints));
    }

    public HListIntNative(HInt... elements) {
	this(Arrays.stream(elements)
		.map(hint -> hint.getNumber().longValue())
		.collect(Collectors.toList()));
    }

    static HListIntNative create(HList<HInt> list) {
	// If already an HListIntNative, just return list
	// if (list instanceof HListIntNative) {
	// return (HListIntNative) list;
	// }

	// Create new Wrapper around HInt
	return new HListIntNative(new HListWrapper<>(HInt.class, list, hint -> hint.getNumber().longValue()));
    }

    /*
     * Constructors
     */
    @Override
    public HListInt nativeList(List<? extends Number> list) {
	return new HListIntNative(list.stream()
		.map(number -> number.longValue())
		.collect(Collectors.toList()));
    }

    /*
     * Abstract class contract
     */
    @Override
    public HListInt getThis() {
	return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public HInt get(int index) {
	// If a wrapper class, bypass the conversion
	if (isWrapperList) {
	    return ((HListWrapper<HInt, Long>) list).getOriginal(index);
	}

	return new HInt(list.get(index));
    }

    @Override
    public int size() {
	return list.size();
    }

    @Override
    public List<Long> toJavaList() {
	return list;
    }

    /*
     * Performance overrides
     */

    @Override
    public HListInt take(int amount) {
	// Limit amount to size, if list is not infinite
	if (!isInf() && amount > size()) {
	    amount = size();
	}

	return new HListIntNative(list.subList(0, amount));
    }

    @Override
    public HInt sum() {
	return new HInt(list.stream().reduce(0l, (acc, next) -> acc + next));
    }

}

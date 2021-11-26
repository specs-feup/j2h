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
import pt.up.fe.specs.j2h.prelude.data.HInt32;

public class HListInt32Native extends HListInt32 {

    private final HList<Integer> list;
    private final boolean isWrapperList;

    public HListInt32Native(HList<Integer> ints) {
	super(ints.getSizeType());
	list = ints;

	isWrapperList = NumberListUtils.isValidWrapper(ints, HInt32.class);
    }

    public HListInt32Native(List<Integer> ints) {
	this(new HListJava<>(ints));
    }

    public HListInt32Native(Integer... ints) {
	this(Arrays.asList(ints));
    }

    /**
     * Constructor for a list of HInt32.
     * 
     * <p>
     * For some reason, Eclipse does not like when HInt32 is used as varargs.
     * 
     * @param elements
     * @return
     */
    public static HListInt32Native create(List<HInt32> elements) {
	return new HListInt32Native(elements.stream()
		.map(hint -> hint.getNumber().intValue())
		.collect(Collectors.toList()));
    }

    static HListInt32Native create(HList<HInt32> list) {
	// If already an HListIntNative, just return list
	// if (list instanceof HListIntNative) {
	// return (HListIntNative) list;
	// }

	// Create new Wrapper around HInt32
	return new HListInt32Native(new HListWrapper<>(HInt32.class, list, hint -> hint.getNumber().intValue()));
    }

    /*
     * Constructors
     */
    @Override
    public HListInt32 nativeList(List<? extends Number> list) {
	return new HListInt32Native(list.stream()
		.map(number -> number.intValue())
		.collect(Collectors.toList()));
    }

    /*
     * Abstract class contract
     */

    @Override
    public HListInt32 getThis() {
	return this;
    }

    @Override
    protected Class<HInt32> getElementClass() {
	return HInt32.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public HInt32 get(int index) {
	// If a wrapper class, bypass the conversion
	if (isWrapperList) {
	    return ((HListWrapper<HInt32, Integer>) list).getOriginal(index);
	}

	return new HInt32(list.get(index));
    }

    @Override
    public int size() {
	return list.size();
    }

    @Override
    public List<Integer> toJavaList() {
	return list;
    }

    /*
     * Performance overrides
     */

    @Override
    public HListInt32 take(int amount) {
	// Limit amount to size, if list is not infinite
	if (!isInf() && amount > size()) {
	    amount = size();
	}

	return new HListInt32Native(list.subList(0, amount));
    }

    @Override
    public HInt32 sum() {
	return new HInt32(list.stream()
		.mapToInt(Integer::intValue)
		.sum());
	// return new HInt32(list.stream().reduce(0, (acc, next) -> acc + next));
    }
}

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

import java.util.Collections;
import java.util.List;

import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.prelude.data.HInt32;

/**
 * A list of ints.
 * 
 * @author João Bispo
 *
 */
public abstract class HListInt32 extends HListNumber<Integer, HInt32, HListInt32> {

    public static final HListNumberStatic<Integer, HInt32, HListInt32> STATIC = new HListInt32Native(
	    Collections.emptyList());

    protected HListInt32(SizeType sizeType) {
	super(sizeType);
    }

    /*
     * Constructors
     */
    @Override
    public HInt32 createElement(Number number) {
	return HInt32.create(number);
    }

    // @Override
    // protected HListLong createList(Number... elements) {
    // return create(elements);
    // }

    @Override
    public HListInt32 wrapperList(HList<HInt32> list) {
	// Check if already an HListInt
	if (list instanceof HListInt32) {
	    return (HListInt32) list;
	}

	return HListInt32Native.create(list);
    }

    /**
     * Optimized version that directly uses a native implementation.
     */
    // @Override
    // public HListInt fromArray(Number... elements) {
    // return new HListIntNative(
    // Arrays.stream(elements)
    // .map(number -> number.longValue())
    // .collect(Collectors.toList()));
    //
    // }

    // public static HListLong create(Number... elements) {
    // return HListLong.STATIC.createList(elements);
    // // return new HListLongNative(Arrays.stream(elements)
    // // .map(number -> number.longValue())
    // // .collect(Collectors.toList()));
    // }

    // public static HListLong create(List<HLong> elements) {
    // return HListLong.STATIC.createList(elements);
    // // return new HListLongSimple(elements);
    // }

    public static HListInt32 createNative(HList<Integer> elements) {
	return new HListInt32Native(elements);
    }

    public static HListInt32 create(List<HInt32> elements) {
	return HListInt32Native.create(elements);
    }

    /*
     * Real re-implementations
     */
    @Override
    public HInt32 product() {
	return stream().reduce(new HInt32(1), (acc, element) -> acc.times(element));
    }

}

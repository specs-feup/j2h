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

import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.prelude.data.HInt;

/**
 * A list of ints.
 * 
 * @author João Bispo
 *
 */
public abstract class HListInt extends HListNumber<Long, HInt, HListInt> {

    public static final HListNumberStatic<Long, HInt, HListInt> STATIC = new HListIntNative(Collections.emptyList());

    protected HListInt(SizeType sizeType) {
	super(sizeType);
    }

    /*
     * Constructors
     */
    @Override
    public HInt createElement(Number number) {
	return HInt.create(number);
    }

    // @Override
    // protected HListLong createList(Number... elements) {
    // return create(elements);
    // }

    @Override
    public HListInt wrapperList(HList<HInt> list) {
	// Check if already an HListInt
	if (list instanceof HListInt) {
	    return (HListInt) list;
	}
	// LoggingUtils.msgWarn("IS NOT:" + list.getClass());
	// return new HListLongNative(list.stream()
	// .map(n -> n.getNumber())
	// .collect(Collectors.toList()));

	return HListIntNative.create(list);
	// return new HListIntSimple(list);

	// return create(list);

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

    public static HListInt createNative(HList<Long> elements) {
	return new HListIntNative(elements);
    }

    public static HListInt create(HInt... elements) {
	return new HListIntNative(elements);
    }

    /*
     * Real re-implementations
     */
    // @Override
    // public HInt sum() {
    // // long result = 0l;
    // // stream().forEach(hint -> {
    // // result = result + hint.getNumber();
    // // });
    //
    // long result = stream()
    // // .parallel()
    // .mapToLong(element -> element.getNumber().longValue())
    // .sum();
    //
    // return new HInt(result);
    // }

    @Override
    public HInt product() {
	return stream().reduce(new HInt(1l), (acc, element) -> acc.times(element));
    }

}

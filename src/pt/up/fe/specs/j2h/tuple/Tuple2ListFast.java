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

package pt.up.fe.specs.j2h.tuple;

import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.ListUtils;

/**
 * Tuple2 list that efficiently implements random access gets.
 * 
 * @author JoaoBispo
 *
 * @param <T1>
 * @param <T2>
 */
class Tuple2ListFast<T1, T2> extends Tuple2List<T1, T2> {
    private final HList<T1> list1;
    private final HList<T2> list2;

    private final int tupleListSize;

    Tuple2ListFast(HList<T1> list1, HList<T2> list2) {
	super(SizeType.fastOrInfinite(Tuple.isInfinite(list1, list2)));
	this.list1 = list1;
	this.list2 = list2;

	tupleListSize = Tuple.calculateSize(list1, list2).orElse(-1);

	if (tupleListSize == -1) {
	    assert isInf(); // If no size, all lists must be infinite
	}
    }

    @Override
    public Tuple2<T1, T2> get(int index) {

	if (index >= tupleListSize) {
	    throw new IndexOutOfBoundsException(
		    "Index '" + index + "' is out of range, list size is '" + tupleListSize + "'");
	}

	T1 value1 = list1.get(index);
	T2 value2 = list2.get(index);

	return new Tuple2<>(value1, value2);
    }

    @Override
    public int size() {
	if (isInf()) {
	    return ListUtils.infListSize();
	}

	return tupleListSize;
    }

}

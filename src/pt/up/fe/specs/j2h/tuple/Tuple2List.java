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

public abstract class Tuple2List<T1, T2> extends HList<Tuple2<T1, T2>> {

    public Tuple2List(SizeType sizeType) {
	super(sizeType);
    }

    public static <T1, T2> Tuple2List<T1, T2> create(HList<T1> list1, HList<T2> list2) {
	// If any of the list has a size of type iterator, use Iterator list
	if (SizeType.has(SizeType.ITERATOR, list1, list2)) {
	    return new Tuple2ListIterator<>(list1, list2);
	}

	return new Tuple2ListFast<>(list1, list2);
    }
}

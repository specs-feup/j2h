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

import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.prelude.classes.Real;

public interface HListNumberStatic<N extends Number, T extends Real<N, T>, L extends HListNumber<N, T, L>> {

    /**
     * Creates a Type from a Number.
     * 
     * @param number
     * @return
     */
    T createElement(Number number);

    /**
     * Creates a specialized list from an arbitrary number of Number elements.
     * 
     * @param elements
     * @return
     */
    default L nativeList(Number... elements) {
	return nativeList(Arrays.asList(elements));
    }

    /**
     * Creates a specialized HList from a generic HList.
     * 
     * @param list
     * @return
     */
    L wrapperList(HList<T> list);

    /**
     * Creates a specialized HList from a generic List of numbers.
     * 
     * @param list
     * @return
     */
    L nativeList(List<? extends Number> list);

    /**
     * Converts a list of T to the equivalent Java Numbers.
     * 
     * @param list
     * @return
     */
    // HList<N> toJavaHList(HList<T> list);
}

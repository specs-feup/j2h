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

package pt.up.fe.specs.j2h.prelude.classes;

import pt.up.fe.specs.j2h.interfaces.ThisProvider;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.affine.HListAffineEnum;
import pt.up.fe.specs.j2h.prelude.data.HInt;

/**
 * Class Enum defines operations on sequentially ordered types.
 * 
 * @author JoaoBispo
 *
 */
public interface HEnum<T extends HEnum<T>> extends ThisProvider<T> {

    /**
     * 
     * @return the successor of a value. For numeric types, succ adds 1
     */
    default T succ() {
	return toEnum(fromEnum().plus(1));
    }

    /**
     * 
     * @return the predecessor of a value. For numeric types, pred subtracts 1
     */
    default T pred() {
	return toEnum(fromEnum().minus(1));
    }

    /**
     * Convert from an Int.
     * 
     * @param a
     * @return
     */
    default T toEnum(HInt a) {
	return toEnum(a.getNumber());
    }

    /**
     * Convert from a Number.
     * 
     * @param a
     * @return
     */
    T toEnum(Number a);

    /**
     * Convert to an int. If the value is too large to fit in an int, throws an exception.
     * 
     * @param a
     * @return
     */
    HInt fromEnum();

    /**
     * Equivalent to [n..]
     * 
     * @return
     */
    default HList<T> enumFrom() {
	return HListAffineEnum.inf(getThis());
    }

    /**
     * Equivalent to [n,n'..]
     * 
     * @param second
     * @return
     */
    default HList<T> enumFromThen(T second) {
	return HListAffineEnum.inf(getThis(), second);
    }

    /**
     * Equivalent to [n..m]
     * 
     * @param second
     * @return
     */
    default HList<T> enumFromTo(T last) {
	return HListAffineEnum.range(getThis(), last);
    }

    /**
     * Equivalent to [n,n'..m]
     * 
     * @param second
     * @return
     */
    default HList<T> enumFromThenTo(T second, T last) {
	return HListAffineEnum.range(getThis(), second, last);
    }

}

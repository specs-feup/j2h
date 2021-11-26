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

import java.util.Arrays;

import pt.up.fe.specs.j2h.interfaces.ThisProvider;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.util.HListJava;

/**
 * The Eq class defines inequality (/=). Equality is already defined by Object.
 * 
 * @author João Bispo
 *
 */
public interface Eq<T extends Eq<T>> extends ThisProvider<T> {

    default boolean notEqual(T a) {
	return !equals(a);
    }

    /**
     * 
     * @return a list of size 1 with the current element
     */
    default HList<T> list() {
	return new HListJava<>(Arrays.asList(getThis()));
    }
}

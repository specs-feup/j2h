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

import java.util.Arrays;
import java.util.List;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.prelude.classes.Ord;

public class Tuple2<T1, T2> extends Tuple implements Ord<Tuple2<T1, T2>> {

    private final T1 value1;
    private final T2 value2;

    public Tuple2(T1 value1, T2 value2) {
	this.value1 = value1;
	this.value2 = value2;
    }

    @SuppressWarnings("unchecked")
    private Tuple2(List<Object> elements) {
	this((T1) elements.get(0), (T2) elements.get(1));
    }

    public static <T1, T2> Tuple2<T1, T2> create(T1 value1, T2 value2) {
	return new Tuple2<>(value1, value2);
    }

    /*
     * Java support
     */
    @Override
    public Tuple2<T1, T2> getThis() {
	return this;
    }

    /*
     * Haskell methods
     * 
     */
    public T1 fst() {
	return value1;
    }

    public T2 snd() {
	return value2;
    }

    @Override
    public int compareTo(Tuple2<T1, T2> o) {
	// TODO Auto-generated method stub
	throw new JavaHaskellException("Not implemented yet");
    }

    @Override
    public List<Object> getElements() {
	return Arrays.asList(value1, value2);
    }

    /*
     * Bounded implementation
     */

    @Override
    public Tuple2<T1, T2> minBound() {
	List<Object> minBounds = TupleUtils.minBound(this);

	return new Tuple2<>(minBounds);
    }

    @Override
    public Tuple2<T1, T2> maxBound() {
	// TupleUtils.checkBounded(this);
	List<Object> maxBounds = TupleUtils.maxBound(this);

	return new Tuple2<>(maxBounds);
    }

}

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

package pt.up.fe.specs.j2h.list.jtypes;

import java.util.Arrays;
import java.util.List;

import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.util.HListJava;

/**
 * A list of ints.
 * 
 * @author João Bispo
 *
 */
public class ListInt extends HList<Integer> {

    private final HList<Integer> list;

    public ListInt(HList<Integer> ints) {
	super(ints.getSizeType());
	list = ints;
    }

    public ListInt(Integer... ints) {
	this(Arrays.asList(ints));
    }

    public ListInt(List<Integer> ints) {
	this(new HListJava<>(ints));
    }

    @Override
    public Integer get(int index) {
	return list.get(index);
    }

    @Override
    public int size() {
	return list.size();
    }

    /*
     * Constructors
     */
    public static ListInt create(HList<Integer> list) {
	return new ListInt(list);
    }

    public static ListInt create(List<Integer> elements) {
	return new ListInt(elements);
    }

    public static ListInt create(Integer... elements) {
	return new ListInt(elements);
    }

    /*
     * Overriden methods
     */

    @Override
    public ListInt conc(HList<Integer> list) {
	return create(super.conc(list));
    }

    /**
     * Helper method which accepts an Integer.
     */
    public ListInt conc(Integer integer) {
	return conc(create(integer));
    }

    /**
     * Helper method which accepts a char.
     * 
     * @param c
     * @return
     */
    @Override
    public ListInt cons(Integer c) {
	return create(super.cons(c));
    }

    @Override
    public ListInt subList(int fromIndex, int toIndex) {
	return create(super.subList(fromIndex, toIndex));
    }

    @Override
    public ListInt tail() {
	return create(super.tail());
    }

    @Override
    public ListInt init() {
	return create(super.init());
    }

    @Override
    public ListInt reverse() {
	return create(super.reverse());
    }

    @Override
    public ListInt take(int amount) {
	return create(super.take(amount));
    }

    @Override
    public ListInt drop(int amount) {
	return create(super.drop(amount));
    }

    @Override
    public ListInt cycle() {
	return create(super.cycle());
    }

    @Override
    public boolean elem(Integer elem) {
	return contains(elem);
    }

    /*
     * Number methods.
     */
    public Integer sum() {
	int result = stream()
		.mapToInt(element -> element)
		.sum();

	return new Integer(result);
    }

    public Integer product() {
	return stream().reduce(1, (acc, element) -> acc * element);
    }

}

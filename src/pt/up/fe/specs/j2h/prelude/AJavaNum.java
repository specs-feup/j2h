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

package pt.up.fe.specs.j2h.prelude;

public abstract class AJavaNum<T extends Number> implements JavaNum<T> {

    private final T number;

    protected AJavaNum(T number) {
	this.number = number;
    }

    @Override
    public T getNumber() {
	return number;
    }

    @Override
    public String toString() {
	return number.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof JavaNum) {
	    return number.equals(((JavaNum<?>) obj).getNumber());
	}

	return number.equals(obj);
    }

    @Override
    public int hashCode() {
	return number.hashCode();
    }

}

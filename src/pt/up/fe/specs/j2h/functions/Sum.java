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

package pt.up.fe.specs.j2h.functions;

import java.util.List;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.prelude.classes.Real;

public interface Sum {

    static Integer jint(List<Integer> list) {
	return list.stream()
		.mapToInt(element -> element.intValue())
		.sum();
    }

    static Long jlong(List<Long> list) {
	return list.stream()
		.mapToLong(element -> element.longValue())
		.sum();
    }

    static Double jdouble(List<Double> list) {
	return list.stream()
		.mapToDouble(element -> element.doubleValue())
		.sum();
    }

    static <N extends Number, T extends Real<N, T>> T real(List<T> list) {
	if (list.isEmpty()) {
	    throw new JavaHaskellException("Method not supported for empty lists");
	}

	// Get 'bootstrap'
	T firstElement = list.get(0);

	return real(firstElement.zero(), list);
    }

    static <N extends Number, T extends Real<N, T>> T real(T identity, List<T> list) {
	return list.stream().reduce(identity, (acc, real) -> acc.plus(real));
    }
}

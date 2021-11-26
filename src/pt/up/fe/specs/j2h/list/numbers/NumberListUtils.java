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

import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.util.HListWrapper;
import pt.up.fe.specs.util.SpecsLogs;

public class NumberListUtils {

    static boolean isValidWrapper(HList<?> list, Class<?> targetClass) {
	if (!(list instanceof HListWrapper)) {
	    return false;
	}
	Class<?> originalClass = ((HListWrapper<?, ?>) list).getOriginalClass();
	boolean isValidWrapper = targetClass.equals(originalClass);

	if (isValidWrapper == false) {
	    SpecsLogs.warn("WRAPPER LIST IS FALSE. Original:" + originalClass.getSimpleName() + ". Target:"
		    + targetClass.getSimpleName());
	}

	return isValidWrapper;
    }
}

/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.channels;

import java.util.Comparator;

import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

/**
 * @author Marius Bogoevici
 */
@Component
public class CustomerPriorityComparator implements Comparator<Message<Booking>> {

	@Override
	public int compare(Message<Booking> left, Message<Booking> right) {
		
		Booking leftPayload = left.getPayload();
		Booking rightPayload = right.getPayload();
	
		int result = leftPayload.getCustomerAge().compareTo(rightPayload.getCustomerAge());
		
		Booking willHandle = (result < 0)?rightPayload:leftPayload;
		
		System.out.println("leftPayload->"+leftPayload + "||"+"rightPayload->"+rightPayload+"=> choosing "+willHandle.getCustomerAge());
		System.out.println("============================================================");
		
		return result;
	}
}

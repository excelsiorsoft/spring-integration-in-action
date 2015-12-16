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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Marius Bogoevici
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:channels-priority.xml")
public class ChannelsPriorityTest {

    @Autowired
    @Qualifier("bookingConfirmationRequests")
    MessageChannel bookingsChannel;

    @Autowired
    StubEmailConfirmationService emailConfirmationService;

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @Test
    public void testChannels() throws Exception {
       
    	Random rand = new Random();
    	
    	int size = 6;
    	int start = 1;
    	
    	List<Integer> availableSeats = availableSeats(start, size);
    	System.out.println("has " +availableSeats.size() +" available seats.");
    	
    	for (int num = start; num <= size; num++){
    	String customerEmail = "user"+num+"@example.com";
    	
    	int randomNum = rand.nextInt((size - start) + 1) + start;
    	
    	
    	Booking booking = new Booking();
        booking.setCustomerEmail(customerEmail);
        booking.setFlightId("AC100");
        
        System.out.println("n="+num);
        System.out.println("availableSeats.get(["+num+"])="+availableSeats.get(num-1));
        
        booking.setCustomerAge(availableSeats.get(num-1));
        //booking.setCustomerAge(randomNum);
        Message<Booking> bookingMessage = MessageBuilder.withPayload(booking).build();
        bookingsChannel.send(bookingMessage);
    	}
        List<Email> emails = emailConfirmationService.getEmails(); //this is a blocking call
        
        Assert.assertEquals(size, emails.size());
        //Assert.assertEquals(customerEmail, emails.get(0).getRecipient());
        
        System.out.println("Received: " +emails);
    }
    
    
    public List<Integer> availableSeats(int from, int to) {
        
    	List<Integer> list = new ArrayList<Integer>();
        
    	for (int i=from; i<=to; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        
            System.out.println("available seats: " + list);
            
            return list;
        
    }
    
}

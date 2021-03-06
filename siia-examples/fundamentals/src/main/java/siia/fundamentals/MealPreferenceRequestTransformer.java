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

package siia.fundamentals;

import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

/**
 * @author Marius Bogoevici
 */
public class MealPreferenceRequestTransformer {

  /*public Source buildMealPreferenceUpdateRequest(MealPreference mealPreference) {
    return new StringSource(
        "<updateMealPreference>" +
            "<flightRef>" +
               mealPreference.getFlightReference() +
            "</flightRef>" +
            "<mealPreference>" +
               mealPreference + "" +
            "</mealPreference>" +
        "</updateMealPreference>");
  }*/
  
  public String buildMealPreferenceUpdateRequest(MealPreference mealPreference) {
	    return new String(
	        "<updateMealPreference>" +
	            "<flightRef>" +
	               mealPreference.getFlightReference() +
	            "</flightRef>" +
	            "<mealPreference>" +
	               mealPreference + "" +
	            "</mealPreference>" +
	        "</updateMealPreference>");
	  }
}

package com.mediscreen.diabetes_assessment.constants;

import java.util.Arrays;
import java.util.List;

public class Triggers {
   /* *
    * @return A list of triggers.
    */
	   public static List<String> getTriggers() {
	       return Arrays.asList(
	               "Hemoglobin A1C", "Microalbumin", "Body Height", "Body Weight", "Smoker",
	               "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies", "LDL high", "Stress",
	               "shortness of breath"
	       );
	   }
	   
	  
}

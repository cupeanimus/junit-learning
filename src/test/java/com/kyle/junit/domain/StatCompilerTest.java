/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package com.kyle.junit.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class StatCompilerTest {

   @Test
   public void test() {
      StatCompiler stats = new StatCompiler();
      
      List<BooleanAnswer> answers = new ArrayList<>();
      answers.add(new BooleanAnswer(1, true));
      answers.add(new BooleanAnswer(1, true));
      answers.add(new BooleanAnswer(1, true));
      answers.add(new BooleanAnswer(1, false));
      answers.add(new BooleanAnswer(2, true));
      answers.add(new BooleanAnswer(2, true));
      
      Map<String, Map<Boolean,AtomicInteger>> responses = stats.responsesByQuestion(answers);
      
      assertThat(responses.get("Tuition reimbursement?").get(Boolean.TRUE).get(), equalTo(3));
      assertThat(responses.get("Tuition reimbursement?").get(Boolean.FALSE).get(), equalTo(1));
      assertThat(responses.get("Relocation package?").get(Boolean.TRUE).get(), equalTo(2));
      assertThat(responses.get("Relocation package?").get(Boolean.FALSE).get(), equalTo(0));
   }
}

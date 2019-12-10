///*
// * Licensed to the Apache Software Foundation (ASF) under one
// * or more contributor license agreements.  See the NOTICE file
// * distributed with this work for additional information
// * regarding copyright ownership.  The ASF licenses this file
// * to you under the Apache License, Version 2.0 (the
// * "License"); you may not use this file except in compliance
// * with the License.  You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
package org.apache.flink.ml.classification
//
//import org.apache.flink.api.scala._
////import org.apache.flink.test.util.FlinkTestBase
//import org.apache.flink.test.util.TestEnvironment
////import org.scalatest.{FlatSpec, Matchers}
//import org.scalatest._
//
//class DecisionTreeSuite extends FlatSpec with Matchers{
//
//  behavior of "The Decision Tree implementation"
//
//  it should "train a decision tree" in {
//    val env = ExecutionEnvironment.getExecutionEnvironment
//
//    val learner = DecisionTree().setMaxBins(3).setDepth(10).setDimension(6).setClasses(2)
//      .setSplitStrategy("Entropy").setCategory(Array(1, 2, 3, 4, 5))
//
//    val trainingDS = env.fromCollection(Classification.DiagnosisTrainData).setParallelism(4)
//
//    learner.fit(trainingDS)
//    println("Accuracy:" +
//      learner.testAccuracy(
//        env.fromCollection(Classification.DiagnosisTestData).setParallelism(4),
//        learner.treeOption.get))
////    learner.predict()
//  }
//}
import org.scalatest.{FlatSpec, Reporter, Stopper, Suite}
import org.scalatest.Assertions.assertThrows

import scala.runtime.Nothing$

class DecisionTreeSuite extends FlatSpec {

  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }

  override def getTestNameForReport(s: String): String = ???

  override def runTests(option: Option[_], reporter: Reporter, stopper: Stopper, set: Set[_], set1: Set[_], map: Map[_, _]): Unit = ???

  override def org$scalatest$Suite$$ReporterInParens(): String = ???

  override def org$scalatest$Suite$$TestMethodPrefix(): String = ???

  override def runNestedSuites(reporter: Reporter, stopper: Stopper, set: Set[_], set1: Set[_], map: Map[_, _], option: Option[_]): Unit = ???

  override def org$scalatest$Suite$$ReporterInParens_$eq(s: String): Unit = ???

  override def nestedSuites(): Nothing = ???

  override def groups(): Map[_, _] = ???

  override def expectedTestCount(set: Set[_], set1: Set[_]): Int = ???

  override def execute(): Unit = ???

  override def execute(s: String): Unit = ???

  override def execute(option: Option[_], reporter: Reporter, stopper: Stopper, set: Set[_], set1: Set[_], map: Map[_, _], option1: Option[_]): Unit = ???

  override def org$scalatest$Suite$$TestMethodPrefix_$eq(s: String): Unit = ???

  override def org$scalatest$Suite$$IgnoreAnnotation_$eq(s: String): Unit = ???

  override def runTest(s: String, reporter: Reporter, stopper: Stopper, map: Map[_, _]): Unit = ???

  override def fail(): Nothing$ = ???

  override def fail(s: String, throwable: Throwable): Nothing$ = ???

  override def fail(s: String): Nothing$ = ???

  override def suiteName(): String = ???

  override def org$scalatest$Suite$$IgnoreAnnotation(): String = ???

  override def expect(o: Any, function0: Function0[_]): Unit = ???

  override def expect(o: Any, o1: Any, function0: Function0[_]): Unit = ???

  override def intercept(aClass: Class[_], function0: Function0[_]): Throwable = ???

  override def intercept(aClass: Class[_], o: Any, function0: Function0[_]): Throwable = ???

  override def convertToEqualizer(o: Any): Suite#Equalizer = ???

  override def assert(option: Option[_]): Unit = ???

  override def assert(option: Option[_], o: Any): Unit = ???

  override def assert(b: Boolean, o: Any): Unit = ???

  override def assert(b: Boolean): Unit = ???

  override def fail(throwable: Throwable): Nothing$ = ???
}
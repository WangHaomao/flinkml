/*
 * Copyright (C) 2015 Holmes Team at HUAWEI Noah's Ark Lab.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.flink.streaming.api.scala.ml.classifiers

import org.apache.flink.streaming.api.scala.ml.core.{Example, Learner}
//import org.apache.spark.streaming.dstream.DStream
import org.apache.flink.streaming.api.datastream.DataStream

/**
 * A Classifier trait defines the needed operations on any implemented
 * classifier. It is a subtrait of Learner and it adds a method for predicting
 * the class of and input stream of Examples.
 */
trait Classifier extends Learner with Serializable {

  /* Predict the label of the Example stream, given the current Model
   *
   * @param instance the input Example stream 
   * @return a stream of tuples containing the original instance and the
   * predicted value
   */
//  def predict(input: DStream[Example]): DStream[(Example, Double)]
  def predict(input: DataStream[Example]): DataStream[(Example, Double)]
}

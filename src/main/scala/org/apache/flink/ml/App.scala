package org.apache.flink.ml

import org.apache.flink.api.scala._
import org.apache.flink.ml.classifier.DecisionTree
import org.apache.flink.ml.math.Vector
import org.apache.flink.ml.DemoData
import org.apache.flink.ml.common.LabeledVector
import org.apache.flink.streaming.api.scala.DataStream

object App {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val learner = DecisionTree().setMaxBins(3).setDepth(10).setDimension(6).setClasses(2)
      .setSplitStrategy("Gini").setCategory(Array(1, 2, 3, 4, 5))

//    print(learner)
    val trainingDS = env.fromCollection(DemoData.DiagnosisTrainData).setParallelism(2)
    val testingDS = env.fromCollection(DemoData.DiagnosisTestData).setParallelism(2)
    val testDS = env.fromCollection(DemoData.pp_data).setParallelism(2)

    learner.fit(trainingDS)
    val res = learner.predict(testDS)
    val tmp = res.map{
      e=>(e._2)
    }.collect().toArray
    print(tmp.mkString(","))
//      res.print()
//    println("Accuracy:" + learner.testAccuracy(testingDS,learner.treeOption.get))
  }
}

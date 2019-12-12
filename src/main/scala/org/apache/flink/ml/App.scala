package org.apache.flink.ml

import org.apache.flink.api.scala._
import org.apache.flink.ml.classification.DecisionTree
import org.apache.flink.ml.math.Vector
import org.apache.flink.ml.DemoData
import org.apache.flink.ml.common.LabeledVector


object App {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val learner = DecisionTree().setMaxBins(3).setDepth(10).setDimension(6).setClasses(2)
      .setSplitStrategy("Entropy").setCategory(Array(1, 2, 3, 4, 5))

//    print(learner)

    val trainingDS = env.fromCollection(DemoData.DiagnosisTrainData).setParallelism(4)
    val testingDS = env.fromCollection(DemoData.DiagnosisTestData).setParallelism(4)

    learner.fit(trainingDS)
//    val tmp = res.map{
//      e=>(e._2,1)
//    }.reduce(
//      (a, b) => (a._1 + b._1, a._2 + b._2)
//    ).collect().toArray
//    print(tmp)


    println("Accuracy:" + learner.testAccuracy(testingDS,learner.treeOption.get))
  }
}

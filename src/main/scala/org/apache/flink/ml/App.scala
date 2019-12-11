package org.apache.flink.ml

import org.apache.flink.api.scala._
import org.apache.flink.ml.classification.DecisionTree
import org.apache.flink.ml.DemoData


object App {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val learner = DecisionTree().setMaxBins(3).setDepth(10).setDimension(6).setClasses(2)
      .setSplitStrategy("Entropy").setCategory(Array(1, 2, 3, 4, 5))

//    print(learner)

    val trainingDS = env.fromCollection(DemoData.DiagnosisTrainData).setParallelism(4)
    val testDS = env.fromCollection(DemoData.DiagnosisTestData).setParallelism(4)
    learner.fit(trainingDS)
    println("Accuracy:" + learner.testAccuracy(testDS,learner.treeOption.get))
  }
}

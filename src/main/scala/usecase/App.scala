package usecase

import org.apache.flink.api.scala._
import org.apache.flink.ml.classifier.DecisionTree

object App {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val learner = DecisionTree().
      setMaxBins(3).
      setDepth(4).
      setDimension(4).
      setClasses(2)
      .setSplitStrategy("Gini")
//      .setCategory(Array(6))

    val trainD = DataHelper.loadTrainData()
    val testD = DataHelper.loadValidData()
    val trainingDS = env.fromCollection(trainD).setParallelism(4)
    val testingDS = env.fromCollection(testD).setParallelism(4)

    val t1 = System.nanoTime
    learner.fit(trainingDS)
    println("Accuracy:" + learner.testAccuracy(testingDS,learner.treeOption.get))
    val duration = (System.nanoTime - t1) / 1e9d
    println(duration)
  }
}

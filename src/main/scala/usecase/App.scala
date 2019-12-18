package usecase

import org.apache.flink.api.scala._
import org.apache.flink.ml.classifier.DecisionTree

object App {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val learner = DecisionTree().
      setMaxBins(3).
      setDepth(4).
      setDimension(6).
      setClasses(2)
      .setSplitStrategy("Gini")
//      .setCategory(Array(6))

    val trainD = DataHelper.loadTrainData()
    val testD = DataHelper.loadValidData()
    val trainingDS = env.fromCollection(trainD).setParallelism(4)
    val testingDS = env.fromCollection(testD).setParallelism(4)
    learner.fit(trainingDS)
//    val res = learner.predict(testDS)
//    val tmp = res.map{
//      e=>(e._2)
//    }.collect().toArray
//    print(tmp.mkString(","))
//      res.print()
    println("Accuracy:" + learner.testAccuracy(testingDS,learner.treeOption.get))
  }
}

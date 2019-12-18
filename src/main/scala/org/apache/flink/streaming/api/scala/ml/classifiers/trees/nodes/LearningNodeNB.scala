package org.apache.flink.streaming.api.scala.ml.classifiers.trees.nodes

import org.apache.flink.streaming.api.scala.ml.classifiers.bayes.NaiveBayes
import org.apache.flink.streaming.api.scala.ml.classifiers.trees.{HoeffdingTreeModel, NullFeatureClassObserver, Utils}
import org.apache.flink.streaming.api.scala.ml.core.Example
import org.apache.flink.streaming.api.scala.ml.core.specification.InstanceSpecification

/**
  * Naive Bayes based learning node.
  */
class LearningNodeNB(classDistribution: Array[Double], instanceSpecification: InstanceSpecification,
                     numSplitFeatures: Int)
  extends ActiveLearningNode(classDistribution, instanceSpecification, numSplitFeatures) with Serializable {

  def this(that: LearningNodeNB) {
    this(Utils.addArrays(that.classDistribution, that.blockClassDistribution),
      that.instanceSpecification, that.numSplitFeatures)
    //init()
  }

  /**
    * Returns the predicted class distribution
    *
    * @param ht a Hoeffding tree model
    * @param example  the Example to be evaluated
    * @return the predicted class distribution
    */
  override def classVotes(ht: HoeffdingTreeModel, example: Example): Array[Double] = {
    if (weight() > ht.nbThreshold)
      NaiveBayes.predict(example, classDistribution, featureObservers)
    else super.classVotes(ht, example)
  }

  /**
    * Disable a feature having an index
    *
    * @param fIndex the index of the feature
    */
  override def disableFeature(fIndex: Int): Unit = {
    featureObservers(fIndex) = new NullFeatureClassObserver()
  }
}

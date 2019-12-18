package org.apache.flink.streaming.api.scala.ml.classifiers.trees.nodes

import org.apache.flink.streaming.api.scala.ml.classifiers.trees.{HoeffdingTreeModel, Utils}
import org.apache.flink.streaming.api.scala.ml.core.Example
/**
  * Inactive learning node for Hoeffding trees
  */
class InactiveLearningNode(classDistribution: Array[Double])
  extends LearningNode(classDistribution) with Serializable {

  def this(that: InactiveLearningNode) {
    this(Utils.addArrays(that.classDistribution, that.blockClassDistribution))
  }

  /**
    * Learn and update the node. No action is taken for InactiveLearningNode
    *
    * @param ht HoeffdingTreeModel
    * @param example an Example will be processed
    */
  override def learn(ht: HoeffdingTreeModel, example: Example): Unit = {}

  /**
    * Return whether a learning node is active
    */
  override def isActive(): Boolean = false

  /**
    * Merge two nodes
    *
    * @param that the node which will be merged
    * @param trySplit flag indicating whether the node will be split
    * @return new node
    */
  override def merge(that: Node, trySplit: Boolean): Node = this

  override def toString: String = "level[" + dep + "] InactiveLearningNode"
}

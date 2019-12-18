package org.apache.flink.streaming.api.scala.ml.classifiers.trees.nodes

import org.apache.flink.streaming.api.scala.ml.classifiers.trees.HoeffdingTreeModel
import org.apache.flink.streaming.api.scala.ml.core.Example

/**
  * Learning node class type for Hoeffding trees.
  */
abstract class LearningNode(classDistribution: Array[Double]) extends Node(classDistribution)
  with Serializable {

  /**
    * Learn and update the node
    *
    * @param ht a Hoeffding tree model
    * @param example the input Example
    */
  def learn(ht: HoeffdingTreeModel, example: Example): Unit

  /**
    * Return whether a learning node is active
    */
  def isActive(): Boolean

  /**
    * Filter the data to the related leaf node
    *
    * @param example the input example
    * @param parent the parent of current node
    * @param index the index of current node in the parent children
    * @return FoundNode containing the leaf node
    */
  override def filterToLeaf(example: Example, parent: SplitNode, index: Int): FoundNode =
    new FoundNode(this, parent, index)

}

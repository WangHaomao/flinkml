package org.apache.flink.streaming.api.scala.ml.classifiers.trees.nodes

import java.util.Random

import org.apache.flink.streaming.api.scala.ml.classifiers.trees.{HoeffdingTreeModel, Utils}
import org.apache.flink.streaming.api.scala.ml.core.Example

/**
  * Abstract class containing the node information for the Hoeffding trees.
  */
abstract class Node(val classDistribution: Array[Double]) extends Serializable{

  var dep: Int = 0
  // stores class distribution of a block of RDD
  val blockClassDistribution: Array[Double] = new Array[Double](classDistribution.length)

  val classifierRandom: Random = new Random()

  /**
    * Filter the data to the related leaf node
    *
    * @param example the input Example
    * @param parent the parent of current node
    * @param index the index of current node in the parent children
    * @return a FoundNode containing the leaf node
    */
  def filterToLeaf(example: Example, parent: SplitNode, index: Int): FoundNode

  /**
    * Return the class distribution
    * @return an Array containing the class distribution
    */
  def classVotes(ht: HoeffdingTreeModel, example: Example): Array[Double] =
    classDistribution.clone()

  /**
    * Checks whether a node is a leaf
    * @return <i>true</i> if a node is a leaf, <i>false</i> otherwise
    */
  def isLeaf(): Boolean = true

  /**
    * Returns height of the tree
    *
    * @return the height
    */
  def height(): Int = 0

  /**
    * Returns depth of current node in the tree
    *
    * @return the depth
    */
  def depth(): Int = dep

  /**
    * Set the depth of current node
    *
    * @param depth the new depth
    */
  def setDepth(depth: Int): Unit = {
    dep = depth
    if (this.isInstanceOf[SplitNode]) {
      val splitNode = this.asInstanceOf[SplitNode]
      splitNode.children.foreach { _.setDepth(depth + 1) }
    }
  }

  /**
    * Merge two nodes
    *
    * @param that the node which will be merged
    * @param trySplit flag indicating whether the node will be split
    * @return new node
    */
  def merge(that: Node, trySplit: Boolean): Node

  /**
    * Returns number of children
    *
    * @return number of children
    */
  def numChildren(): Int = 0

  /**
    * Returns the node description
    * @return String containing the description
    */
  def description(): String = {
    "  " * dep + "depth: "+ dep + " | numChildren: "  + numChildren() + " | observedClassDistribution: " +
      Utils.arraytoString(this.classDistribution) + " | hashCode: " + this.hashCode() + "\n"
  }

}

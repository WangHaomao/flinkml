package org.apache.flink.streaming.api.scala.ml.classifiers.trees.nodes

/**
  * The container of a node.
  */
class FoundNode(val node: Node, val parent: SplitNode, val index: Int) extends Serializable {

}

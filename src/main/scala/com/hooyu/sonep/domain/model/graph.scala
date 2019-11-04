package com.hooyu.sonep.domain.model

import scala.collection.immutable.{HashMap, HashSet}

/** Choosing HashSet as collection type because it gives fastest non integer element lookup,
  * see: https://docs.scala-lang.org/overviews/collections/performance-characteristics.html.
  * For this to work, the [[Person]] class needs to be uniquely identifiable, see its comments.
  * */
sealed trait Graph {

  /** Get all vertices (direct 1 to 1 connections) for the given person.
    * @param person person for which to return direct connections
    * @return direct connections for the given person
    * */
  def verticesFor(person: Person): Option[HashSet[Person]]

  /** Get all persons in the graph
    * @return all persons in the graph.
    * */
  def allPersons: HashSet[Person]
}

object Graph {

  /** Factory method for default graph implementation.
    *  Default chosen implementation is [[AdjacencyList]]
    * */
  def apply(connections: Connections): Graph = AdjacencyList(connections)
}

/** Representing graph as adjacency list see https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs
  * Choosing HashMap and HashSet as collection type because it gives fastest non integer element lookup,
  *    see: https://docs.scala-lang.org/overviews/collections/performance-characteristics.html
  *    @note This class and it's companion is model package private as it should not be used outside, use default [[Graph]] trait and object outside.
  * */
private[model] case class AdjacencyList(private val vertices: HashMap[Person, HashSet[Person]]) extends Graph {
  override def verticesFor(person: Person): Option[HashSet[Person]] = vertices.get(person)

  override def allPersons: HashSet[Person] = HashSet.from(vertices.keySet)
}

private[model] object AdjacencyList {

  /** Factory method to create an [[AdjacencyList]] from [[Connections]].
    * @note This is graph implementation specific, thus needs to be close to the implementation as a factory method.
    * */
  def apply(connections: Connections): AdjacencyList = {
    def findRelationships(p: Person): HashSet[Person] = {
      val personConnections: Seq[Person] = connections.relationships.flatMap(_.findRelatedTo(p))
      HashSet.from(personConnections)
    }
    val personToConnections: Seq[(Person, HashSet[Person])] = connections.people.map(p => p -> findRelationships(p))
    new AdjacencyList(HashMap.from(personToConnections))
  }
}

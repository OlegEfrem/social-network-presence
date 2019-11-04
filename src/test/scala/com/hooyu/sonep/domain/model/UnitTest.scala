package com.hooyu.sonep.domain.model

import org.scalatest.{FreeSpec, Matchers, OptionValues}

import scala.collection.immutable.{HashMap, HashSet}

trait UnitTest extends FreeSpec with Matchers with TestData with OptionValues

trait TestData {
  val john                                     = Person("John")
  val harry                                    = Person("Harry")
  val peter                                    = Person("Peter")
  private val george                           = Person("George")
  private val anna                             = Person("Anna")
  val john_peter: Relationship                 = relationship(john, peter)
  private val john_george                      = relationship(john, george)
  private val peter_george                     = relationship(peter, george)
  private val peter_anna                       = relationship(peter, anna)
  val persons: Seq[Person]                     = Seq(john, harry, peter, george, anna)
  private val relationships: Seq[Relationship] = Seq(john_peter, john_george, peter_george, peter_anna)
  val connections                              = Connections("facebook", persons, relationships)
  val relationsHashMap: HashMap[Person, HashSet[Person]] = HashMap(
    john   -> HashSet(peter, george),
    peter  -> HashSet(john, george, anna),
    george -> HashSet(john, peter),
    harry  -> HashSet.empty,
    anna   -> HashSet(peter)
  )

  def relationship(start: Person, end: Person): Relationship = {
    Relationship(RelationshipType.HasConnection, start, end)
  }

}

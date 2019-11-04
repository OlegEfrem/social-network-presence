package com.hooyu.sonep.domain.model

import enumeratum.values._

sealed abstract class RelationshipType(val value: Int, val name: String) extends IntEnumEntry

object RelationshipType extends IntEnum[RelationshipType] {
  val values: IndexedSeq[RelationshipType] = findValues

  case object HasConnection extends RelationshipType(1, "hasConnection")
}

case class Relationship(`type`: RelationshipType, startNode: Person, endNode: Person) {
  def findRelatedTo(person: Person): Option[Person] =
    (`type`, startNode, endNode) match {
      case (RelationshipType.HasConnection, start, _) if (start == person) => Some(endNode)
      case (RelationshipType.HasConnection, _, end) if (end == person)     => Some(startNode)
      case _                                                               => None
    }
}

/** @note A person should be uniquely identifiable, so this class would be extended with parameters conferring uniqueness.
  * */
case class Person(name: String)

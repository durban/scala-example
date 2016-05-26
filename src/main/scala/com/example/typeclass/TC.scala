package com.example.typeclass

import scala.language.implicitConversions

class X
class Y
class Z
class P
class Q

trait TC[A] {
  type B
}

object TC {

  type Aux[A, B0] = TC[A] {
    type B = B0
  }

  implicit val inst1: Aux[X, Y] = new TC[X] {
    final override type B = Y
  }

  implicit val inst2: Aux[P, Q] = new TC[P] {
    final override type B = Q
  }

  implicit def YfromZ(z: Z): Y = new Y
}

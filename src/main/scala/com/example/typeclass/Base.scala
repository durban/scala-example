package com.example.typeclass

abstract class Base[A, B](val tc: TC.Aux[A, B]) {
  //val f = 78
  def foo: tc.B
}

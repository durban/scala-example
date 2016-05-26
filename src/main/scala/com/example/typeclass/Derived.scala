package com.example.typeclass

object Derived extends Base(TC.inst1) {

  import TC.YfromZ

  def foo = new Z
}

package com.example.tagging

class Q
trait Tag

object XYZ {

  type Tagged[A] = A with Tag

  def tag[A](a: A): Tagged[A] =
    a.asInstanceOf[Tagged[A]]

  type Alias[A] = Tagged[A]

  def q: Q = new Q

  // this works:
  def foo1: Alias[Q] = {
    val res = tag(q)
    res
  }

  // this also works:
  def foo2: Tagged[Q] = {
    tag(q)
  }

  // this doesn't:
  def foo3: Alias[Q] = {
    ???  // tag(q)
  }
}

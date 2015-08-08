package com.cocroworld.domain

class CockroachModel {
  val cockroachs: Set[Cockroach] = Set()

  def aggregate: () => Gene =
    () => {new Gene()}

  def train(cockroach: Cockroach => Int) {
    //perform genetic training
    0
  }
}

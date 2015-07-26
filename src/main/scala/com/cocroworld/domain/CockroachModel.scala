package com.cocroworld.domain

/**
 * Created by Anna on 7/27/2015.
 */
class CockroachModel {
  val cockroachs: Set[Cockroach] = Set()

  def aggregate: () => Gene =
    () => {new Gene()}

  def train(cockroach: Cockroach => Int) {
    //perform genetic training
    0
  }
}

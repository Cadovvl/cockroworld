object TestPlayerCounter {
  private var count = 0
  def getNext = {
    count += 1
    count
  }
}

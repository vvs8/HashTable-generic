class HashTable2 extends HashTab1e {
  
  private int hashFun(String str) {
    int hash = 0;
    for (int i = 0; i < str.length(); i++) {
      hash = hash*33 + str.charAt(i) - 'a' + 1;
    }
    return Math.abs(hash);
  }

  //Override to use another hash function
  private int getBucketIndex(String key) {
		//int hashCode = polynomialHash(key);
    int hashCode = hashFun(key);
		int index = hashCode % capacity;
		return index;
	}

}
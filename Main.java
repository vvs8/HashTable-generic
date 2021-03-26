class Main {
  public static void main(String[] args) {
    HashTab1e HT = new HashTab1e();

    StudentEntry SE1 = new StudentEntry("John", "Smith", "Computer Science", 1988, new ContactInfo(5555555, "jsmith@email.com")); 
    StudentEntry SE2 = new StudentEntry("Howard", "Calico", "Chemical Engineering", 1991, new ContactInfo(8675309, "hcalico@email.com"));  
    StudentEntry SE3 = new StudentEntry("Yarah", "Bahri", "Psychology", 1976, new ContactInfo(6647665, "ybahri3@email.com")); 
    StudentEntry SE4 = new StudentEntry("Aveline", "Macon", "Arts", 1991, new ContactInfo(7181234, "its_aveline@avelineworld.com"));
    StudentEntry SE5 = new StudentEntry("Pratik", "Gera", "Psychology", 1996, new ContactInfo(3232368, "pgera@email.com"));
    StudentEntry SE6 = new StudentEntry("Usui", "Aimi", "Psychology", 1996, new ContactInfo(4623457, "x_shadow_hunter_x03@gaming.com"));      
    StudentEntry SE7 = new StudentEntry("Jane", "Smith", "Computer Science", 1990, new ContactInfo(5555555, "j2smith@email.com")); 
    StudentEntry SE8 = new StudentEntry("Vreni", "Spitz", "Law", 1992, new ContactInfo(5554444, "vspitz@email.com"));   
    StudentEntry SE9 = new StudentEntry("Pratik", "Comar", "Business", 1991, new ContactInfo(6645622, "pcomar@email.com"));  
    StudentEntry SE10 = new StudentEntry("Padma", "Gera", "Arts", 1994, new ContactInfo (5554544, "pgera@realemail.org"));


    StudentEntry[] arraySE = {SE1, SE2, SE3, SE4, SE5, SE6, SE7, SE8, SE9, SE10};


    //Testing with email key
    System.out.println("\n *** Key = email, polynomialHash() ***");
    for (StudentEntry e: arraySE) {
      HT.add(e.personalInfo.email, e);
    }

    System.out.println(HT.isEmpty());
    HT.printHashTable();
    HT.printCollisions();
    System.out.println("Highest entries/bucket: " + HT.mostPopulousBucket());

    System.out.println(HT.remove("jsmith@email.com"));
    System.out.println(HT.get("hcalico@email.com"));
    
    
    //Testing with long all data key
    HashTab1e HT1 = new HashTab1e();
    System.out.println("\n *** Key = all data, polynomialHash() ***");
    for (StudentEntry entry: arraySE) {
      HT1.add((entry.personalInfo.email + entry.birthYear + entry.major + entry.firstName + entry.lastName + entry.personalInfo.phoneNumber).toLowerCase(), entry);
    }

    System.out.println(HT1.isEmpty());
    HT1.printHashTable();
    HT1.printCollisions();
    System.out.println("Highest entries/bucket: " + HT1.mostPopulousBucket());


    //Testing with long key = birthYear + personalInfo.phoneNumber + major
    HashTab1e HT2 = new HashTab1e();
    System.out.println("\n *** Key = birthYear + phoneNumber + major, polynomialHash() ***");
    for (StudentEntry entry: arraySE) {
      HT2.add((entry.birthYear + entry.personalInfo.phoneNumber + entry.major), entry);
    }

    System.out.println(HT2.isEmpty());
    HT2.printHashTable();
    HT2.printCollisions();
    System.out.println("Highest entries/bucket: " + HT2.mostPopulousBucket());


    //Testing with email key, hashFun()
    HashTable2 HT3 = new HashTable2();
    System.out.println("\n *** Key = email, hashFun() ***");
    for (StudentEntry entry: arraySE) {
      HT3.add(entry.personalInfo.email, entry);
    }

    System.out.println(HT2.isEmpty());
    HT3.printHashTable();
    HT3.printCollisions();
    System.out.println("Highest entries/bucket: " + HT3.mostPopulousBucket());


    //Testing with long all data key, hashFun()
    HashTable2 HT4 = new HashTable2();
    System.out.println("\n *** Key = all data, hashFun() ***");
    for (StudentEntry entry: arraySE) {
      HT4.add((entry.personalInfo.email + entry.birthYear + entry.major + entry.firstName + entry.lastName + entry.personalInfo.phoneNumber).toLowerCase(), entry);
    }

    System.out.println(HT4.isEmpty());
    HT4.printHashTable();
    HT4.printCollisions();
    System.out.println("Highest entries/bucket: " + HT4.mostPopulousBucket());


    //Testing with long birthYear + phoneNumber + major, hashFun()
    HashTable2 HT5 = new HashTable2();
    System.out.println("\n *** Key = birthYear + phoneNumber + major, hashFun() ***");
    for (StudentEntry entry: arraySE) {
      HT5.add((entry.birthYear + entry.personalInfo.phoneNumber + entry.major), entry);
    }

    System.out.println(HT5.isEmpty());
    HT5.printHashTable();
    HT5.printCollisions();
    System.out.println("Highest entries/bucket: " + HT5.mostPopulousBucket());


    //5 entries that would all end up in the same bucket
    HashTab1e HTx = new HashTab1e();
    StudentEntry SE11 = new StudentEntry("Jim", "Jay", "Biology", 1997, new ContactInfo (6047381, "jayfamily@gmail.com"));
    StudentEntry SE12 = new StudentEntry("Mark", "Johnson", "Arts", 1993, new ContactInfo (604837, "johnsonjohnson@gmail.com"));
    StudentEntry SE13 = new StudentEntry("Mike", "Jay", "Biology", 1994, new ContactInfo (604738, "jayfamily@gmail.com"));
    StudentEntry SE14 = new StudentEntry("Masha", "Jay", "Biology", 1994, new ContactInfo (604774, "jayfamily@gmail.com"));
    StudentEntry SE15 = new StudentEntry("Olya", "Markus", "Arts", 1997, new ContactInfo (9049258, "aaalll@gmail.com"));
    
    StudentEntry[] XarraySE = {SE11, SE12, SE13, SE14, SE15};

    System.out.println("\n *** 5 Entries Test ***");
    for (StudentEntry entry: XarraySE) {
      HTx.add((entry.personalInfo.email + entry.birthYear + entry.major + entry.firstName + entry.lastName + entry.personalInfo.phoneNumber).toLowerCase(), entry);
    }
    
    HTx.printHashTable();
    HTx.printCollisions();
    System.out.println("Highest entries/bucket: " + HTx.mostPopulousBucket());

  }

}
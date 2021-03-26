class StudentEntry {
  String firstName;
  String lastName;
  String major;
  int birthYear;
  ContactInfo personalInfo;
  
  public StudentEntry(String firstNameIn, String lastNameIn, String majorIn, int birthYearIn, ContactInfo contactInfoIn) {
    firstName = firstNameIn; 
    lastName = lastNameIn; 
    major = majorIn;
    birthYear = birthYearIn; 
    personalInfo = contactInfoIn;
  } 
  
  public String toString() {
    return "("+ firstName + ", " + lastName + ")";
  }
}

class ContactInfo{
  int phoneNumber;
  String email;
  public ContactInfo(int phoneNumberIn, String emailIn) {
    phoneNumber = phoneNumberIn;
    email = emailIn; 
  }

}
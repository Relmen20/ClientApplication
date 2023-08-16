//package service;
//
//import supports.Gender;
//import supports.User;
//import controller.UserScanner;
//import repository.UserRepository;
//
//
//public class Registration {
//
//    private User user;
//
//    public void getNewUser(UserScanner scanner) {
//        System.out.print("Please enter your name: ");
//        String name = scanner.nextLine();
//
//
//        Gender gender;
//        do {
//            System.out.print("Please enter your gender m/f: ");
//            String inputGender = scanner.nextLine();
//            gender = checkGender(inputGender);
//        }while(gender == null);
//
//        System.out.print("Please enter your password: ");
//        String password = scanner.nextLine();
//
//        boolean ageFlag;
//        byte age;
//        System.out.print("Please enter your age: ");
//        do {
//            age = scanner.nextByte();
//            ageFlag = checkAge(age);
//        }while(ageFlag);
//
//        User user = new User(name, age, password, gender);
//        userRepository.objectToFile(user);
//    }
//    private boolean checkAge(byte age){
//        if(age > 0 && age <= 100) return false;
//        else{
//            System.out.println("Please set your age between 1 and 100");
//            return true;
//        }
//    }
//    private Gender checkGender(String inputGender){
//        Gender gender;
//        if (inputGender.equals("male")){
//            gender = Gender.MALE;
//            return gender;
//        } else if (inputGender.equals("female")) {
//            gender = Gender.FEMALE;
//            return gender;
//        }else{
//            System.out.println("Please enter your gender by choose <<male>> if u r male\n" +
//                               "Or by choose <<female>> if u r female");
//            return null;
//        }
//    }
//}

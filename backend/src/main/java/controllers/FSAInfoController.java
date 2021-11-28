//package controllers;
//
//import compiler.RegexPattern;
//
//import java.util.List;
//
//public class FSAInfoController {
//    private final RegexPattern regexPattern;
//
//    public FSAInfoController(RegexPattern regexPattern){
//        this.regexPattern = regexPattern;
//    }
//
//    public List<Object> graph() {
//        if (this.regexPattern.getFSAType().equals("NFA")) {
//            //TODO
//        }
//        if(this.regexPattern.getFSAType().equals("DFA")){
//            //TODO
//        }
//        else{
//            throw new IllegalArgumentException("Unknown FSA type");
//        }
//
//        return;
//    }
//}

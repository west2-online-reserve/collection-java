
class ChineseDog extends Animal{
    private boolean isVaccineInjected;
    public ChineseDog(){
        super();
    }
    public ChineseDog(String name, int age,String gender,boolean isVaccineInjected){
        super(name,age,gender,100.0);
        this.isVaccineInjected = isVaccineInjected;
    }

   @Override
    public String toString(){
        return super.toString()+"\nisVaccineInjected="+isVaccineInjected;
   }

}

package dashboard.android.hdw.com.krystaldashboard.view;

public class PRModelClass {

    String Name,nickName,employeeCode,runDrink,posiTion,onFloor;
    Long Start,All;

   public PRModelClass(String name,String nickname,String employeecode,String rundrink
           ,String position,String onfloor,Long start,Long all){
        this.Name = name;
        this.nickName = nickname;
        this.employeeCode = employeecode;
        this.runDrink = rundrink;
        this.posiTion = position;
        this.onFloor = onfloor;
        this.Start = start;
        this.All = all;
   }

    public String getName() {
        return Name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public String getRunDrink() {
        return runDrink;
    }

    public String getPosiTion() {
        return posiTion;
    }

    public String getOnFloor() {
        return onFloor;
    }

    public Long getStart() {
        return Start;
    }

    public Long getAll() {
        return All;
    }
}

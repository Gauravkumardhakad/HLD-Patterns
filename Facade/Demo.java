
class VersionControlSystem{

    public void pullLatestChanges(){
        System.out.println("Pulling the latest changes from github...");
    }
}

class BuildSystem{

    public boolean compileProject(){
        System.out.println("compiling the project...");
        System.out.println("build successful..");
        return true;
    }

    public String getArtificialPath(){
        String str="/target/my-app/v1";
        System.out.println("Artificial path generated...");

        return str;
    } 
}

class TestingFramework{

    public boolean unitTesting(){
        System.out.println("perfroming unit testing..");
        System.out.println("Unit testing successful..");

        return true;
    } 

    public boolean systemTesting(){
        System.out.println("perfroming system testing..");
        System.out.println("System testing successful..");

        return true;
    }
}

class DeploymentTarget{

    public void deploy(){
        System.out.println("deployig the newer version of the software...");
    }
}

class DeploymentFacade{
    private VersionControlSystem vcs = new VersionControlSystem();
    private BuildSystem build = new BuildSystem();
    private TestingFramework tester =  new TestingFramework();
    private DeploymentTarget deployer = new DeploymentTarget();

    public boolean DeployApplication(){
        System.out.println("Deploying the software....");
        System.out.println("Successs....");

        return true;
    }
}


public class Demo{
    public static void main(String a[]){
        DeploymentFacade facade = new DeploymentFacade();
        facade.DeployApplication();
    }
}
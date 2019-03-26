package demo.juster.spboot.pojo.user;


public class Role {

    private long id;

    private String rolename;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "role:"+this.rolename;
    }
}

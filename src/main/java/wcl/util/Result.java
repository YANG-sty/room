package wcl.util;

public class Result {
	
	private int status;
	private String message;
    private Object data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    public Result() {
	}

	public static Result success(){
		Result result = new Result();
		result.setStatus(1);
		return result;
	}

	public static Result success(Object data){
		Result result = new Result();
		result.setStatus(1);
		result.setData(data);
		return result;
	}

	public static Result fail(String msg){
		Result result = new Result();
		result.setStatus(0);
		result.setMessage(msg);
		return result;
	}

}

package operations;

import org.json.JSONArray;
import org.json.JSONObject;
import bank.EmployeeType;
import bank.OperationType;
import bank.ServiceFactory;
import database.AdminServiceInterface;
import pojo.Branch;
import pojo.Employees;
import utility.BankException;
import utility.InputDefectException;
import utility.UtilityHelper;
import utility.Validation;

public class Admin extends Employee {

	AdminServiceInterface admin = ServiceFactory.getAdminService();

	public long createBranch(JSONObject branchJson) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(branchJson);
		Branch branch=jsonToBranch(branchJson);
		validateCreateBranch(branch);
		checkBranchNameAbsence(branch.getBranchName());
		setTime();
		setCreationDetails(branch);
		long id= admin.createBranch(branch);
		LogAgent.log("-",OperationType.createBranch);
		return id;
	}

	public void addAdmin(JSONObject admin) throws BankException, InputDefectException {
		addEmployee(admin);
	}

	public void addEmployee(JSONObject emp) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(emp);
		Employees employee = jsonToEmployee(emp);
		validateCreateEmployee(employee);
		checkIdUserPresence(employee.getId());
		checkForWorkersAbsence(employee.getId());
		checkBranchIdPresence(employee.getBranchId());
		setTime();
		setCreationDetails(employee);
		admin.addEmployee(employee);
		LogAgent.log("-",OperationType.addEmployee);
	}

	public void removeEmployee(JSONObject employee) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(employee);
		long id = UtilityHelper.getLong(employee, "Id");
		Validation.validateUserId(id);
		checkForWorkersPresence(id);
		setTime();
		admin.removeEmployee(id);
		LogAgent.log("-",OperationType.removeEmployee);
	}
	
	public void activateEmployee(long id) throws BankException, InputDefectException {
		Validation.validateUserId(id);
		checkForWorkersPresence(id);
		checkEmpActive(id);
		setTime();
		admin.activateCustomer(id);
		LogAgent.log("-",OperationType.activateEmployee);
	}
	
	public void inactivateEmployee(long id) throws BankException, InputDefectException {
		Validation.validateUserId(id);
		checkForWorkersPresence(id);
		checkEmpInactive(id);
		setTime();
		admin.deactivateCustomer(id);
		LogAgent.log("-",OperationType.deactivateEmployee);
	}

	public JSONArray getAllBranchId() throws BankException {
		return admin.getAllBranchId();
	}
	
	public void validateBranchId(long branchId) throws BankException {
		JSONArray jArray= admin.getAllBranchId();
		int length=jArray.length();
		boolean flag=false;
        for(int temp=0;length>temp;temp++){
			if(jArray.getJSONObject(temp).getLong("BranchId")==branchId) {
				flag=true;
			}
        }
        if(!flag) {
        	throw new BankException("Invalid Branch Id");
        }
	}

	// checkers methods
	protected void checkBranchIdAbsence(long branchId) throws BankException, InputDefectException {
		admin.checkBranchAbsence(branchId, "BranchId");
	}

	protected void checkBranchIdPresence(long branchId) throws BankException, InputDefectException {
		admin.checkBranchPrecence(branchId, "BranchId");
	}

	protected void checkForWorkersAbsence(long employeeId) throws BankException, InputDefectException {
		admin.checkEmployeeAbsence(employeeId, "Id");
	}

	protected void checkForWorkersPresence(long employeeId) throws BankException, InputDefectException {
		admin.checkEmployeePrecence(employeeId, "Id");
	}
	
	protected void checkBranchNameAbsence(String BranchName) throws BankException, InputDefectException {
		admin.checkBranchNameAbsence(BranchName,"BranchName");
	}
	
	
	private Employees jsonToEmployee(JSONObject employeeJson) throws BankException, InputDefectException {
		Employees employees = new Employees();
		employees.setBranchId(UtilityHelper.getInt(employeeJson, "BranchId"));
		employees.setId(UtilityHelper.getLong(employeeJson, "Id"));
		employees.setType(EmployeeType.valueOf(UtilityHelper.getString(employeeJson, "Type")));
		return employees;
	}

	protected Branch jsonToBranch(JSONObject branchJson) throws BankException, InputDefectException {
		Branch branch = new Branch();
		branch.setAddress(UtilityHelper.getString(branchJson, "Address"));
		//branch.setBranchId(UtilityHelper.getInt(branchJson, "BranchId"));
		branch.setBranchName(UtilityHelper.getString(branchJson, "BranchName"));
		branch.setIfscCode(UtilityHelper.getString(branchJson, "IfscCode"));
		return branch;
	}
	
	private void validateCreateEmployee(Employees employee) throws BankException {
		Validation.validateUserId(employee.getId());
		Validation.validateUserId(employee.getBranchId());
	}
	
	
	private void validateCreateBranch(Branch branch) throws BankException {
		Validation.address(branch.getAddress());
		Validation.branchName(branch.getBranchName());
		Validation.validateIfsc(branch.getIfscCode());
	}
	
	private void checkEmpActive(long id) throws BankException, InputDefectException  {
		String statu= UserStatus(id);
		if(statu.equals("active")) {
			throw new BankException("Employee Active");
		}
	}
	
	private void checkEmpInactive(long id) throws BankException, InputDefectException  {
		String statu= UserStatus(id);
		if(statu.equals("inactive")) {
			throw new BankException("Employee Inactive");
		}
	}
}

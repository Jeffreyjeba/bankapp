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

public class Admin extends Employee {

	AdminServiceInterface admin = ServiceFactory.getAdminService();

	public void createBranch(JSONObject branchJson) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(branchJson);
		Branch branch=jsonToBranch(branchJson);
		long branchId = branch.getBranchId();
		checkBranchIdAbsence(branchId);
		setTime();
		setCreationDetails(branch);
		admin.createBranch(branch);
		log.log("-",OperationType.createBranch);
	}

	public void addAdmin(JSONObject admin) throws BankException, InputDefectException {
		addEmployee(admin);
	}

	public void addEmployee(JSONObject emp) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(emp);
		Employees employee = jsonToEmployee(emp);
		checkIdUserPresence(employee.getId());
		checkForWorkersAbsence(employee.getId());
		checkBranchIdPresence(employee.getBranchId());
		setTime();
		setCreationDetails(employee);
		admin.addEmployee(employee);
		log.log("-",OperationType.addEmployee);
	}

	public void removeEmployee(JSONObject employee) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(employee);
		long id = UtilityHelper.getLong(employee, "Id");
		checkForWorkersPresence(id);
		setTime();
		admin.removeEmployee(id);
		log.log("-",OperationType.removeEmployee);
	}
	
	public void activateEmployee(long id) throws BankException, InputDefectException {
		checkForWorkersPresence(id);
		setTime();
		admin.activateCustomer(id);
		log.log("-",OperationType.activateEmployee);
	}
	
	public void inactivateEmployee(long id) throws BankException, InputDefectException {
		checkForWorkersPresence(id);
		setTime();
		admin.deactivateCustomer(id);
		log.log("-",OperationType.deactivateEmployee);
	}

	public JSONArray getAllBranchId() throws BankException, InputDefectException {
		return admin.getAllBranchId();
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
		branch.setBranchId(UtilityHelper.getInt(branchJson, "BranchId"));
		branch.setBranchName(UtilityHelper.getString(branchJson, "BranchName"));
		branch.setIfscCode(UtilityHelper.getString(branchJson, "IfscCode"));
		return branch;
	}
}

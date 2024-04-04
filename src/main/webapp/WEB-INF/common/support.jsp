 
  <%HttpSession customerSession=request.getSession();
	long currentAccount= (long)customerSession.getAttribute("currentAccount");
	String path=(String) request.getAttribute("path");
	 %>

        
        
        <div class="support">
            <div class="switchAccount">
               
            <table>
                <th>
                    Current Account
                </th>
                <tr>
                    <td>
                        Account Number :
                    </td>
                    <td >
                        <%=currentAccount %>
                    </td>

                </tr>
            </table>
            </div>

            <div class="switchAccount">
           
                <table>
                    <th>
                        Switch Account
                    </th>
                    <tr>
                        <td>
                            Account Number :
                        </td>
                        <td>
                         <form  id="01" action=<%= path%> method="post">
                            <select name="account" >
							<%long[] account=(long[])customerSession.getAttribute("accounts");
				              for(long acc:account){
				        	%>
							<option><%=acc%></option>
							<%}%>
							</select>
						 </form>
                        </td>
                    </tr>
                    <tr>
                    <td><button form="01" type="submit">switch</button></td>
                    </tr>
                </table>
            </div>
        </div>
        
        
        <%--  <div class="support">
            <div class="currentAccount">
                Current Account
                 <%=currentAccount %>
            </div>

            <div class="switchAccount">
			      <form action="switchCredit" method="post">
						<label>choose your account number :</label>
						 <select name="account">
							<%long[] account=(long[])customerSession.getAttribute("accounts");
				              for(long acc:account){
				        	%>
							<option><%=acc%></option>
							<%}%>
						</select><br>
						<button type="submit">switch</button>
				</form>
            </div>
        </div> --%>